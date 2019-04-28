package com.nhl.dflib.series;

import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleArraySeriesTest {

    @Test
    public void testGetDouble() {
        DoubleArraySeries s = new DoubleArraySeries(1., 2.1);
        assertEquals(1, s.getDouble(0), 0.001);
        assertEquals(2.1, s.getDouble(1), 0.001);
    }

    @Test
    public void testGetDouble_Offset() {
        DoubleArraySeries s = new DoubleArraySeries(new double[]{1., 2.1, 3.2, 4}, 1, 2);
        assertEquals(2.1, s.getDouble(0), 0.001);
        assertEquals(3.2, s.getDouble(1), 0.001);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetDouble_Offset_OutOfBounds() {
        DoubleArraySeries s = new DoubleArraySeries(new double[]{1., 2.1, 3.2, 4}, 1, 2);
        s.getDouble(2);
    }
}
