package com.example.app;

import com.example.api.Calculator;
import com.example.api.Calculators;

public class Main {

    public static void main(String[] args) {
        Calculator calc = Calculators.create();
        System.out.println(calc.add(2, 3));
    }
}
