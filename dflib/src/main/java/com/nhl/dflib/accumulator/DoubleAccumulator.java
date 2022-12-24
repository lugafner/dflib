package com.nhl.dflib.accumulator;

import com.nhl.dflib.DoubleSeries;
import com.nhl.dflib.series.DoubleArraySeries;

import java.util.Arrays;

/**
 * An expandable list of primitive int values that has minimal overhead and can be converted to compact and efficient
 * immutable {@link com.nhl.dflib.DoubleSeries}.
 *
 * @since 0.6
 */
public class DoubleAccumulator implements ValueAccum<Double> {

    private double[] data;
    private int size;

    public DoubleAccumulator() {
        this(10);
    }

    public DoubleAccumulator(int capacity) {
        this.size = 0;
        this.data = new double[capacity];
    }

    public void fill(int from, int to, double value) {

        if (to - from < 1) {
            return;
        }

        if (data.length <= to) {
            expand(to);
        }

        Arrays.fill(data, from, to, value);
        size += to - from;
    }

    /**
     * @since 0.8
     */
    @Override
    public void push(Double v) {
        pushDouble(v != null ? v : 0.);
    }

    /**
     * @since 0.8
     */
    @Override
    public void pushDouble(double value) {

        if (size == data.length) {
            expand(data.length * 2);
        }

        data[size++] = value;
    }

    @Override
    public void replace(int pos, Double v) {
        replaceDouble(pos, v != null ? v : 0.);
    }

    @Override
    public void replaceDouble(int pos, double value) {

        if (pos >= size) {
            size = pos + 1;
        }

        if (size >= data.length) {
            expand(Math.max(data.length * 2, size));
        }

        data[pos] = value;
    }

    @Override
    public DoubleSeries toSeries() {
        double[] data = compactData();

        // making sure no one can change the series via the Mutable List anymore
        this.data = null;

        return new DoubleArraySeries(data, 0, size);
    }

    public int size() {
        return size;
    }

    private double[] compactData() {
        if (data.length == size) {
            return data;
        }

        double[] newData = new double[size];
        System.arraycopy(data, 0, newData, 0, size);
        return newData;
    }

    private void expand(int newCapacity) {

        double[] newData = new double[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);

        this.data = newData;
    }
}
