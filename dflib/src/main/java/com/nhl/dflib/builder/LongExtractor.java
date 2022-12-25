package com.nhl.dflib.builder;

import com.nhl.dflib.LongValueMapper;
import com.nhl.dflib.Extractor;

/**
 * @since 0.8
 */
public class LongExtractor<F> implements Extractor<F, Long> {

    private final LongValueMapper<F> mapper;

    public LongExtractor(LongValueMapper<F> mapper) {
        this.mapper = mapper;
    }

    @Override
    public void extractAndStore(F from, ValueStore<Long> to) {
        to.pushLong(mapper.map(from));
    }

    @Override
    public void extractAndStore(F from, ValueStore<Long> to, int toPos) {
        to.replaceLong(toPos, mapper.map(from));
    }

    @Override
    public ValueAccum<Long> createAccum(int capacity) {
        return new LongAccum(capacity);
    }

    @Override
    public ValueHolder<Long> createHolder() {
        return new LongHolder();
    }
}
