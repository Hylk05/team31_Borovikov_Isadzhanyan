package com.example;

import com.example.Stringtools;

public class Main {
    public static void main(String[] args) {
        String text = "Hello, world!\n";
        String repeated = Stringtools.repeat(text, 3);
        System.out.println("Result:\n" + repeated);
    }
}