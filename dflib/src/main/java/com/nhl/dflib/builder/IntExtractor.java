package com.nhl.dflib.builder;

import com.nhl.dflib.IntValueMapper;
import com.nhl.dflib.Extractor;

/**
 * @since 0.8
 */
public class IntExtractor<F> implements Extractor<F, Integer> {

    private final IntValueMapper<F> mapper;

    public IntExtractor(IntValueMapper<F> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void extractAndStore(F from, ValueStore<Integer> to) {
        to.pushInt(mapper.map(from));
    }

    @Override
    public void extractAndStore(F from, ValueStore<Integer> to, int toPos) {
        to.replaceInt(toPos, mapper.map(from));
    }

    @Override
    public ValueAccum<Integer> createAccum(int capacity) {
        return new IntAccum(capacity);
    }

    @Override
    public ValueHolder<Integer> createHolder() {
        return new IntHolder();
    }
}
