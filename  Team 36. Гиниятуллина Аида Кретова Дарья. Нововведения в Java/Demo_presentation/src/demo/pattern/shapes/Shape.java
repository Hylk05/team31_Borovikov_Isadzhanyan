package demo.pattern.shapes;

// Слайд 10: Sealed класс - контролируем наследование
public sealed abstract class Shape
        permits Circle, Rectangle, Triangle { // Разрешены только эти 3 класса

    public abstract double area();
    public abstract String getName();
}