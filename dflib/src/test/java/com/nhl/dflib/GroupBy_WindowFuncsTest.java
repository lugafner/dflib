package com.nhl.dflib;

import com.nhl.dflib.unit.IntSeriesAsserts;
import org.junit.jupiter.api.Test;

public class GroupBy_WindowFuncsTest {

    @Test
    public void testGroupBy_RowNumbers_Emtpy() {
        GroupBy gb = DataFrame.newFrame("a", "b", "c").empty().group("a");
        IntSeries rn = gb.rowNumber();
        new IntSeriesAsserts(rn).expectData();
    }

    @Test
    public void testGroupBy_RowNumbers0() {
        GroupBy gb = DataFrame.newFrame("a", "b", "c").foldByRow(
                1, "x", "m",
                2, "y", "n",
                1, "z", "k",
                0, "a", "f",
                1, "x", "s").group("a");

        IntSeries rn = gb.rowNumber();
        new IntSeriesAsserts(rn).expectData(1, 1, 2, 1, 3);
    }

    @Test
    public void testGroupBy_RowNumbers1() {
        GroupBy gb = DataFrame.newFrame("a", "b", "c").foldByRow(
                3, "x", "m",
                2, "y", "n",
                1, "z", "k",
                0, "a", "f",
                -1, "x", "s").group("a");

        IntSeries rn = gb.rowNumber();
        new IntSeriesAsserts(rn).expectData(1, 1, 1, 1, 1);
    }

    @Test
    public void testGroupBy_RowNumbers2() {
        GroupBy gb =  DataFrame.newFrame("a", "b", "c").foldByRow(
                3, "x", "m",
                0, "y", "n",
                3, "z", "k",
                3, "a", "f",
                1, "x", "s").group("a");

        IntSeries rn = gb.rowNumber();
        new IntSeriesAsserts(rn).expectData(1, 1, 2, 3, 1);
    }

    @Test
    public void testGroupBy_RowNumbers_Sort() {
        GroupBy gb = DataFrame.newFrame("a", "b", "c").foldByRow(
                3, "x", "m",
                0, "y", "n",
                3, "z", "k",
                3, "a", "f",
                1, "x", "s").group("a");

        IntSeries rn = gb.sort("b", true).rowNumber();
        new IntSeriesAsserts(rn).expectData(2, 1, 3, 1, 1);
    }
}
