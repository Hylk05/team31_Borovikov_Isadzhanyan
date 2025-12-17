package com.example.internal;

import com.example.api.Calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
