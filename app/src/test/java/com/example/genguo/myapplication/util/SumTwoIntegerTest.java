package com.example.genguo.myapplication.util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SumTwoIntegerTest{
    @Test
    public void testAdd(){
        assertEquals(SumTwoInteger.add(2,2), 2 + 2);
    }
}
