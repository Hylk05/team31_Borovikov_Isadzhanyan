package com.example.api;

import com.example.api.Calculator;
import com.example.internal.CalculatorImpl;

public class Calculators {

    public static Calculator create() {
        return new CalculatorImpl();
    }
}
