package demo.pattern;

import demo.pattern.shapes.*;

public class PatternMatchingDemo {

    // Слайд 9: Pattern Matching для instanceof (старый vs новый)
    private static void demoInstanceOf() {
        System.out.println("1. PATTERN MATCHING ДЛЯ INSTANCEOF:");

        Object shape = new Circle(5.0);

        // Старый способ (до Java 16)
        if (shape instanceof Circle) {
            Circle circle = (Circle) shape; // Явное приведение
            System.out.println("  Старый способ: радиус = " + circle.radius());
        }

        // Новый способ (Java 16+)
        if (shape instanceof Circle c) { // Переменная объявляется в условии
            System.out.println("  Новый способ: радиус = " + c.radius());
        }
    }

    // Слайд 11: Pattern Matching для switch
    private static void demoSwitch() {
        System.out.println("\n2. PATTERN MATCHING ДЛЯ SWITCH:");

        Shape[] shapes = {
                new Circle(5.0),
                new Rectangle(4.0, 6.0),
                new Triangle(3.0, 4.0)
        };

        for (Shape shape : shapes) {
            // Switch-выражение с паттерн-матчингом
            String description = switch (shape) {
                case Circle c -> String.format("Круг радиусом %.1f", c.radius());
                case Rectangle r -> String.format("Прямоугольник %.1fx%.1f",
                        r.width(), r.height());
                case Triangle t -> String.format("Треугольник с основанием %.1f",
                        t.base());
                // default не нужен - компилятор знает все варианты
            };

            System.out.println("  " + description + ", площадь = " + shape.area());
        }
    }

    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ PATTERN MATCHING И SEALED CLASSES:");
        System.out.println("-".repeat(40));

        demoInstanceOf();
        demoSwitch();

        System.out.println("\nПримечание: если добавить новый класс в 'permits',");
        System.out.println("компилятор потребует добавить case в switch!");
    }
}