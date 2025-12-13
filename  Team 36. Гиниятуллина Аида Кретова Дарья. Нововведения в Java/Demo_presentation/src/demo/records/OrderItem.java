package demo.records;

public record OrderItem(
        String productId,
        String name,
        double price,
        int quantity
) {
    public OrderItem {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
    }

    public double totalPrice() {
        return price * quantity;
    }
}