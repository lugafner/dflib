package com.nhl.dflib.exp.num;

import com.nhl.dflib.BooleanSeries;
import com.nhl.dflib.Exp;
import com.nhl.dflib.IntSeries;
import com.nhl.dflib.Series;
import com.nhl.dflib.exp.map.MapCondition3;
import com.nhl.dflib.f.Function3;
import com.nhl.dflib.f.Predicate3;

/**
 * @since 1.0.0-M19
 */
public class IntCondition3 extends MapCondition3<Integer, Integer, Integer> {

    public static IntCondition3 mapVal(
            String opName1,
            String opName2,
            Exp<Integer> one,
            Exp<Integer> two,
            Exp<Integer> three,
            Predicate3<Integer, Integer, Integer> op,
            Function3<IntSeries, IntSeries, IntSeries, BooleanSeries> primitiveOp) {
        return new IntCondition3(opName1, opName2, one, two, three, valToSeries(op), primitiveOp);
    }

    private final Function3<IntSeries, IntSeries, IntSeries, BooleanSeries> primitiveOp;

    public IntCondition3(
            String opName1,
            String opName2,
            Exp<Integer> one,
            Exp<Integer> two,
            Exp<Integer> three,
            Function3<Series<Integer>, Series<Integer>, Series<Integer>, BooleanSeries> op,
            Function3<IntSeries, IntSeries, IntSeries, BooleanSeries> primitiveOp) {

        super(opName1, opName2, one, two, three, op);
        this.primitiveOp = primitiveOp;
    }

    @Override
    protected BooleanSeries doEval(Series<Integer> one, Series<Integer> two, Series<Integer> three) {
        return one instanceof IntSeries && two instanceof IntSeries && three instanceof IntSeries
                ? primitiveOp.apply((IntSeries) one, (IntSeries) two, (IntSeries) three)
                : super.doEval(one, two, three);
    }
}
