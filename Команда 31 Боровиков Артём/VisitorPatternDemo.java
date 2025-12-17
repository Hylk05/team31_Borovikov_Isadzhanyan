import java.util.ArrayList;
import java.util.List;

// Интерфейс элемента (фигуры)
interface Shape {
    void accept(Visitor visitor);
}

// Конкретные элементы
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

// Интерфейс посетителя
interface Visitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

// Конкретный посетитель для расчета площади
class AreaVisitor implements Visitor {
    private double totalArea = 0;

    @Override
    public void visit(Circle circle) {
        double area = Math.PI * circle.getRadius() * circle.getRadius();
        System.out.println("Площадь круга радиусом " + circle.getRadius() + " = " + area);
        totalArea += area;
    }

    @Override
    public void visit(Rectangle rectangle) {
        double area = rectangle.getWidth() * rectangle.getHeight();
        System.out.println("Площадь прямоугольника " + rectangle.getWidth() +
                "x" + rectangle.getHeight() + " = " + area);
        totalArea += area;
    }

    public double getTotalArea() {
        return totalArea;
    }
}

// Конкретный посетитель для экспорта в XML
class XmlExportVisitor implements Visitor {
    private StringBuilder xml = new StringBuilder();

    @Override
    public void visit(Circle circle) {
        xml.append("<circle>\n")
                .append("  <radius>").append(circle.getRadius()).append("</radius>\n")
                .append("</circle>\n");
    }

    @Override
    public void visit(Rectangle rectangle) {
        xml.append("<rectangle>\n")
                .append("  <width>").append(rectangle.getWidth()).append("</width>\n")
                .append("  <height>").append(rectangle.getHeight()).append("</height>\n")
                .append("</rectangle>\n");
    }

    public String getXml() {
        return xml.toString();
    }
}

// Демонстрация паттерна Visitor
public class VisitorPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Демонстрация паттерна Visitor ===\n");

        // Создаем коллекцию фигур
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(5));
        shapes.add(new Rectangle(4, 6));
        shapes.add(new Circle(3));
        shapes.add(new Rectangle(2, 7));

        // Демонстрируем расчет площади
        System.out.println("1. Расчет площади фигур:");
        AreaVisitor areaCalculator = new AreaVisitor();

        for (Shape shape : shapes) {
            shape.accept(areaCalculator);
        }

        System.out.println("\nОбщая площадь всех фигур: " + areaCalculator.getTotalArea());

    }
}







