package org.dflib;

import org.dflib.unit.DataFrameAsserts;
import org.junit.jupiter.api.Test;

public class RowSet_Map_ExpTest {

    @Test
    public void all() {
        DataFrame df = DataFrame.foldByRow("a", "b", "c")
                .of(
                        1, "x", "a",
                        2, "y", "b",
                        -1, "m", "n")
                .rows()
                .map(
                        Exp.$int(0).mul(3),
                        Exp.concat(Exp.$str(1), Exp.$str(2)),
                        Exp.$str(2)
                );

        new DataFrameAsserts(df, "a", "b", "c")
                .expectHeight(3)
                .expectRow(0, 3, "xa", "a")
                .expectRow(1, 6, "yb", "b")
                .expectRow(2, -3, "mn", "n");
    }

    @Test
    public void byIndex() {
        DataFrame df = DataFrame.foldByRow("a", "b", "c")
                .of(
                        1, "x", "a",
                        2, "y", "b",
                        -1, "m", "n")
                .rows(Series.ofInt(0, 2))
                .map(
                        Exp.$int(0).mul(3),
                        Exp.concat(Exp.$str(1), Exp.$str(2)),
                        Exp.$str(2)
                );

        new DataFrameAsserts(df, "a", "b", "c")
                .expectHeight(3)
                .expectRow(0, 3, "xa", "a")
                .expectRow(1, 2, "y", "b")
                .expectRow(2, -3, "mn", "n");
    }

    @Test
    public void rowsExcept_byIndex() {
        DataFrame df = DataFrame.foldByRow("a", "b", "c")
                .of(
                        1, "x", "a",
                        2, "y", "b",
                        -1, "m", "n")
                .rowsExcept(Series.ofInt(0, 2))
                .map(
                        Exp.$int(0).mul(3),
                        Exp.concat(Exp.$str(1), Exp.$str(2)),
                        Exp.$str(2)
                );

        new DataFrameAsserts(df, "a", "b", "c")
                .expectHeight(3)
                .expectRow(0, 1, "x", "a")
                .expectRow(1, 6, "yb", "b")
                .expectRow(2, -1, "m", "n");
    }

    @Test
    public void byIndex_Duplicate() {
        DataFrame df = DataFrame.foldByRow("a", "b", "c")
                .of(
                        1, "x", "a",
                        2, "y", "b",
                        -1, "m", "n")
                .cols(0).compactInt(0)
                .rows(Series.ofInt(0, 2, 2, 0))
                .map(
                        Exp.$int(0).mul(3),
                        Exp.concat(Exp.$str(1), Exp.$str(2)),
                        Exp.$str(2)
                );

        new DataFrameAsserts(df, "a", "b", "c")
                .expectHeight(5)
                .expectRow(0, 3, "xa", "a")
                .expectRow(1, 2, "y", "b")
                .expectRow(2, -3, "mn", "n")
                .expectRow(3, -3, "mn", "n")
                .expectRow(4, 3, "xa", "a");
    }

    @Test
    public void byRange() {
        DataFrame df = DataFrame.foldByRow("a", "b", "c")
                .of(
                        1, "x", "a",
                        2, "y", "b",
                        -1, "m", "n")
                .rowsRange(1, 3)
                .map(
                        Exp.$int(0).mul(3),
                        Exp.concat(Exp.$str(1), Exp.$str(2)),
                        Exp.$str(2)
                );

        new DataFrameAsserts(df, "a", "b", "c")
                .expectHeight(3)
                .expectRow(0, 1, "x", "a")
                .expectRow(1, 6, "yb", "b")
                .expectRow(2, -3, "mn", "n");
    }

    @Test
    public void byCondition() {
        DataFrame df = DataFrame.foldByRow("a", "b", "c")
                .of(
                        1, "x", "a",
                        2, "y", "b",
                        -1, "m", "n")
                .rows(Series.ofBool(true, false, true))
                .map(
                        Exp.$int(0).mul(3),
                        Exp.concat(Exp.$str(1), Exp.$str(2)),
                        Exp.$str(2)
                );

        new DataFrameAsserts(df, "a", "b", "c")
                .expectHeight(3)
                .expectRow(0, 3, "xa", "a")
                .expectRow(1, 2, "y", "b")
                .expectRow(2, -3, "mn", "n");
    }
}
