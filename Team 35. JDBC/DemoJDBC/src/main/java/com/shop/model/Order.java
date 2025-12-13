package com.shop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    public enum OrderStatus {
        PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
    }

    private Long id;
    private Long customerId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private String deliveryAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order() {
    }

    public Order(Long customerId, BigDecimal totalAmount, String deliveryAddress) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.deliveryAddress = deliveryAddress;
        this.status = OrderStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Order(Long id, Long customerId, OrderStatus status, BigDecimal totalAmount,
                 String deliveryAddress, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.totalAmount = totalAmount;
        this.deliveryAddress = deliveryAddress;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", status=" + status +
                ", totalAmount=" + totalAmount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public static class OrderStats {
        public long totalOrders;
        public BigDecimal totalRevenue;
        public BigDecimal averageOrder;
        public long deliveredCount;
        public long pendingCount;

        public OrderStats(long totalOrders, BigDecimal totalRevenue, BigDecimal averageOrder,
                          long deliveredCount, long pendingCount) {
            this.totalOrders = totalOrders;
            this.totalRevenue = totalRevenue;
            this.averageOrder = averageOrder;
            this.deliveredCount = deliveredCount;
            this.pendingCount = pendingCount;
        }

        @Override
        public String toString() {
            return "OrderStats{" +
                    "totalOrders=" + totalOrders +
                    ", totalRevenue=" + totalRevenue +
                    ", averageOrder=" + averageOrder +
                    ", deliveredCount=" + deliveredCount +
                    ", pendingCount=" + pendingCount +
                    '}';
        }
    }
}
