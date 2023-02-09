package com.example.android.SimpleCalcChallenge;

import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;

@RunWith(JUnit4.class)
@SmallTest
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() {
        mCalculator = new Calculator();
    }

    @Test
    public void addTwoNumbers() {
        double resultAdd = mCalculator.add(1d, 1d);
        assertThat(resultAdd, is(equalTo(2d)));
    }

    @Test
    public void addTwoNumbersNegative() {
        double resultAdd = mCalculator.add(-1d, 2d);
        assertThat(resultAdd, is(equalTo(1d)));
    }

    @Test
    public void addTwoNumbersFloat() {
        double resultAdd = mCalculator.add(1.111f, 1.111d);
        assertThat(resultAdd, is(closeTo(2.222, 0.01)));
    }

    @Test
    public void subTwoNumbers() {
        double resultSub = mCalculator.sub(1d, 1d);
        assertThat(resultSub, is(equalTo(0d)));
    }

    @Test
    public void subWorksWithNegativeResult() {
        double resultSub = mCalculator.sub(1d, 17d);
        assertThat(resultSub, is(equalTo(-16d)));
    }

    @Test
    public void mulTwoNumbers() {
        double resultMul = mCalculator.mul(32d, 2d);
        assertThat(resultMul, is(equalTo(64d)));
    }

    @Test
    public void divTwoNumbers() {
        double resultDiv = mCalculator.div(32d,2d);
        assertThat(resultDiv, is(equalTo(16d)));
    }

    @Test
    public void divByZeroThrows() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            mCalculator.div(32d,0f);
        });

        assertThat("Can't divide with zero", is(equalTo(exception.getMessage())));
    }


    @Test
    public void powDuaBilanganPositif() {
        double resultPow = mCalculator.pow(2,3);
        assertThat(resultPow, is(equalTo(8d)));
    }

    @Test
    public void powNegatifOperandPertama() {
        double resultPow = mCalculator.pow(-2,3);
        assertThat(resultPow, is(equalTo(-8d)));
    }

    @Test
    public void powNegatifOperandKedua() {
        double resultRow = mCalculator.pow(2, -3);
        assertThat(resultRow, is(equalTo(0.125d)));
    }
    @Test
    public void powNolPertama() {
        double resultRow = mCalculator.pow(0, 999);
        assertThat(resultRow, is(equalTo(0d)));
    }

    @Test
    public void powNolKedua() {
        double resultRow = mCalculator.pow(2, 0);
        assertThat(resultRow, is(equalTo(1d)));
    }

    @Test
    public void powNolDanNegatifDua() {
        double resultRow = mCalculator.pow(0, -1);
        assertThat(resultRow, is(equalTo(Double.POSITIVE_INFINITY)));
    }

    @Test
    public void powNegatifNolDanNegatifDua() {
        double resultRow = mCalculator.pow(-0, -2);
        assertThat(resultRow, is(equalTo(Double.POSITIVE_INFINITY)));
    }

}