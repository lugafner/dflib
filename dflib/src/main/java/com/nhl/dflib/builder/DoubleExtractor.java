package com.nhl.dflib.builder;

import com.nhl.dflib.DoubleValueMapper;
import com.nhl.dflib.Extractor;

/**
 * @since 0.16
 */
public class DoubleExtractor<F> implements Extractor<F, Double> {

    private final DoubleValueMapper<F> mapper;

    public DoubleExtractor(DoubleValueMapper<F> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void extractAndStore(F from, ValueStore<Double> to) {
        to.pushDouble(mapper.map(from));
    }

    @Override
    public void extractAndStore(F from, ValueStore<Double> to, int toPos) {
        to.replaceDouble(toPos, mapper.map(from));
    }

    @Override
    public ValueAccum<Double> createAccum(int capacity) {
        return new DoubleAccum(capacity);
    }

    @Override
    public ValueHolder<Double> createHolder() {
        return new DoubleHolder();
    }
}