package com.example.app;

import com.example.api.Calculator;
import com.example.internal.CalculatorImpl;

public class Main {

    public static void main(String[] args) {
        Calculator calc = new CalculatorImpl();
        System.out.println(calc.add(2, 3));
    }
}

