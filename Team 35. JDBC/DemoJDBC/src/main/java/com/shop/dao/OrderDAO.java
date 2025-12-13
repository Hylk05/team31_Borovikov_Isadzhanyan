package com.shop.dao;

import com.shop.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAO {
    private static final Logger logger = LoggerFactory.getLogger(OrderDAO.class);
    private final DataSource dataSource;

    public OrderDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public long save(Order order) {
        String sql = "INSERT INTO orders (customer_id, status, total_amount, " +
                "delivery_address, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, NOW(), NOW()) RETURNING id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, order.getCustomerId());
            pstmt.setString(2, order.getStatus().toString());
            pstmt.setBigDecimal(3, order.getTotalAmount());
            pstmt.setString(4, order.getDeliveryAddress());

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    long orderId = rs.getLong("id");
                    logger.info("Order saved: ID={}, customer_id={}", orderId, order.getCustomerId());
                    return orderId;
                }
            }
        } catch (SQLException e) {
            logger.error("Error saving order", e);
            throw new RuntimeException("Cannot save order", e);
        }
        return -1;
    }

    public Optional<Order> findById(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Order order = mapResultSetToOrder(rs);
                    logger.info("Order found: {}", id);
                    return Optional.of(order);
                }
            }
        } catch (SQLException e) {
            logger.error("Error finding order by id: {}", id, e);
            throw new RuntimeException("Cannot find order", e);
        }

        logger.warn("Order not found: {}", id);
        return Optional.empty();
    }

    public List<Order> findByCustomerId(long customerId) {
        String sql = "SELECT * FROM orders WHERE customer_id = ? ORDER BY created_at DESC";
        List<Order> orders = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, customerId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Order order = mapResultSetToOrder(rs);
                    orders.add(order);
                }
            }
            logger.info("Found {} orders for customer: {}", orders.size(), customerId);
        } catch (SQLException e) {
            logger.error("Error finding orders for customer: {}", customerId, e);
            throw new RuntimeException("Cannot fetch orders", e);
        }

        return orders;
    }

    public void updateStatus(Long orderId, Order.OrderStatus newStatus) {
        String sql = "UPDATE orders SET status = ?, updated_at = NOW() WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newStatus.toString());
            pstmt.setLong(2, orderId);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("Order status updated: ID={}, status={}", orderId, newStatus);
            } else {
                logger.warn("Order not found for update: {}", orderId);
            }
        } catch (SQLException e) {
            logger.error("Error updating order status: {}", orderId, e);
            throw new RuntimeException("Cannot update order status", e);
        }
    }

    public void delete(Long orderId) {
        String sql = "DELETE FROM orders WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, orderId);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Order deleted: ID={}", orderId);
            } else {
                logger.warn("Order not found for deletion: {}", orderId);
            }
        } catch (SQLException e) {
            logger.error("Error deleting order: {}", orderId, e);
            throw new RuntimeException("Cannot delete order", e);
        }
    }

    public Order.OrderStats getStats() {
        String sql = "SELECT " +
                "COUNT(*) as total_orders, " +
                "COALESCE(SUM(total_amount), 0) as total_revenue, " +
                "COALESCE(AVG(total_amount), 0) as average_order, " +
                "COUNT(CASE WHEN status = 'DELIVERED' THEN 1 END) as delivered_count, " +
                "COUNT(CASE WHEN status = 'PENDING' THEN 1 END) as pending_count " +
                "FROM orders";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                long totalOrders = rs.getLong("total_orders");
                BigDecimal totalRevenue = rs.getBigDecimal("total_revenue");
                BigDecimal averageOrder = rs.getBigDecimal("average_order");
                long deliveredCount = rs.getLong("delivered_count");
                long pendingCount = rs.getLong("pending_count");

                return new Order.OrderStats(totalOrders, totalRevenue, averageOrder,
                        deliveredCount, pendingCount);
            }
        } catch (SQLException e) {
            logger.error("Error getting order stats", e);
            throw new RuntimeException("Cannot get order statistics", e);
        }

        return new Order.OrderStats(0, BigDecimal.ZERO, BigDecimal.ZERO, 0, 0);
    }

    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        Long customerId = rs.getLong("customer_id");
        Order.OrderStatus status = Order.OrderStatus.valueOf(rs.getString("status"));
        BigDecimal totalAmount = rs.getBigDecimal("total_amount");
        String deliveryAddress = rs.getString("delivery_address");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();

        return new Order(id, customerId, status, totalAmount, deliveryAddress,
                createdAt, updatedAt);
    }
}
