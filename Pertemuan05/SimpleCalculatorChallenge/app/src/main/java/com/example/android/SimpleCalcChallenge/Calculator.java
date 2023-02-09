package com.example.android.SimpleCalcChallenge;

public class Calculator {

    
    public enum Operator {ADD, SUB, DIV, MUL, POW}

    public double add(double firstOperand, double secondOperand) {
        return firstOperand + secondOperand;
    }

    public double sub(double firstOperand, double secondOperand) {
        return firstOperand - secondOperand;
    }

    public double div(double firstOperand, double secondOperand) {
        if (secondOperand == 0) {
            throw new IllegalArgumentException("Can't divide with zero");
        }

        return firstOperand / secondOperand;
    }

    public double mul(double firstOperand, double secondOperand) {
        return firstOperand * secondOperand;
    }

    public double pow(double firstOperand, double secondOperand) {
        return Math.pow(firstOperand, secondOperand);
    }
}