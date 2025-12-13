package demo.records;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDemo {
    public static void main(String[] args) {
        System.out.println("ДЕМОНСТРАЦИЯ RECORDS:");
        System.out.println("-".repeat(30));

        // Создаём данные
        Address address = new Address(
                "ул. Ленина, д. 10",
                "Москва",
                "123456",
                "Россия"
        );

        Customer customer = new Customer(
                "CUST-001",
                "Иван Иванов",
                "ivan@example.com",
                address
        );

        List<OrderItem> items = List.of(
                new OrderItem("PROD-001", "Ноутбук", 75000, 1),
                new OrderItem("PROD-002", "Мышь", 2500, 2),
                new OrderItem("PROD-003", "Клавиатура", 3500, 1)
        );

        // Создаём заказ через record
        Order order = new Order(
                "ORD-001",
                customer,
                items,
                LocalDateTime.now()
        );

        // Используем автоматически сгенерированные методы
        System.out.println("Заказ ID: " + order.orderId());
        System.out.println("Клиент: " + order.customer().name());
        System.out.println("Количество позиций: " + order.items().size());

        // Используем наши вычисляемые методы
        System.out.println("\nИтого: " + order.totalAmount() + " руб.");
        System.out.println("Крупный заказ: " + order.isLargeOrder());

        // toString() сгенерирован автоматически
        System.out.println("\nАвтоматический toString():");
        System.out.println(order);
    }
}