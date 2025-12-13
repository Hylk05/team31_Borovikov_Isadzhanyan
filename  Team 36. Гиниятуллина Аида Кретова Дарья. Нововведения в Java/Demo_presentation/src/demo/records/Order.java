package demo.records;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

// Слайд 7-8: Record вместо болтливого класса
public record Order(
        String orderId,
        Customer customer,
        List<OrderItem> items,
        LocalDateTime createdAt
) {
    // Компактный конструктор - валидация при создании
    public Order {
        Objects.requireNonNull(orderId, "Order ID cannot be null");
        Objects.requireNonNull(customer, "Customer cannot be null");
        Objects.requireNonNull(items, "Items cannot be null");

        // Проверка бизнес-правила
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Order must have at least one item");
        }

        // Защитная копия для иммутабельности
        items = List.copyOf(items);
    }

    // Вычисляемое свойство
    public double totalAmount() {
        return items.stream()
                .mapToDouble(OrderItem::totalPrice)
                .sum();
    }

    // Ещё один вычисляемый метод
    public boolean isLargeOrder() {
        return totalAmount() > 1000;
    }
}