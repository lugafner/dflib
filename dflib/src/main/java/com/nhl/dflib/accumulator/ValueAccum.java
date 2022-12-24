package com.nhl.dflib.accumulator;

import com.nhl.dflib.Series;

/**
 * A mutable Series builder with API to create primitive and Object Series.
 *
 * @since 0.16
 */
public interface ValueAccum<T> extends ValueStore<T> {

    Series<T> toSeries();
}
