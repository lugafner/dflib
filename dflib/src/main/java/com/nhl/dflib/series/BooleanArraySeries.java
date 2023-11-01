package com.nhl.dflib.series;

import com.nhl.dflib.BooleanSeries;
import com.nhl.dflib.agg.PrimitiveSeriesCount;

/**
 * @since 0.6
 */
public class BooleanArraySeries extends BooleanBaseSeries {

    private final boolean[] data;

    public BooleanArraySeries(boolean... data) {
        this.data = data;
    }

    @Override
    public int size() {
        return data.length;
    }

    @Override
    public boolean getBool(int index) {
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return data[index];
    }

    @Override
    public void copyToBool(boolean[] to, int fromOffset, int toOffset, int len) {
        if (fromOffset + len > size()) {
            throw new ArrayIndexOutOfBoundsException(fromOffset + len);
        }

        System.arraycopy(data, fromOffset, to, toOffset, len);
    }

    @Override
    public BooleanSeries head(int len) {
        if (Math.abs(len) >= size()) {
            return this;
        }

        return len < 0 ? tail(size() + len) : new BooleanArrayRangeSeries(data, 0, len);
    }

    @Override
    public BooleanSeries tail(int len) {

        if (Math.abs(len) >= size()) {
            return this;
        }

        return len < 0 ? head(size() + len) : new BooleanArrayRangeSeries(data, size() - len, len);
    }

    @Override
    public BooleanSeries rangeOpenClosedBool(int fromInclusive, int toExclusive) {
        return fromInclusive == 0 && toExclusive == size()
                ? this
                : new BooleanArrayRangeSeries(data, fromInclusive, toExclusive - fromInclusive);
    }

    @Override
    public BooleanSeries materialize() {
        return this;
    }

    @Override
    public int firstTrue() {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (data[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int countTrue() {
        return PrimitiveSeriesCount.countTrueInArray(data, 0, size());
    }

    @Override
    public int countFalse() {
        return PrimitiveSeriesCount.countFalseInArray(data, 0, size());
    }
}
