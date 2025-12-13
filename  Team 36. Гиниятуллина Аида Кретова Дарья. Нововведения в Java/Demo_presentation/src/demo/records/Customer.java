package demo.records;

public record Customer(
        String id,
        String name,
        String email,
        Address address
) {
    public Customer {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Customer name is required");
        }
    }
}