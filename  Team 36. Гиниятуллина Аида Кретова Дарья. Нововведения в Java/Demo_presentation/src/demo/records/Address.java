package demo.records;

public record Address(
        String street,
        String city,
        String zipCode,
        String country
) {}