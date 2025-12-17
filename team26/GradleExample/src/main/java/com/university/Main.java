package com.university;

import com.google.common.base.Joiner;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Иванов", 20),
                new Student("Крапивина", 21),
                new Student("Петров", 22)
        );

        // Используем Guava для примера
        String joined = Joiner.on(", ").join(students);
        System.out.println("Список студентов: " + joined);
        System.out.println("Приложение запущено!");
    }
}
