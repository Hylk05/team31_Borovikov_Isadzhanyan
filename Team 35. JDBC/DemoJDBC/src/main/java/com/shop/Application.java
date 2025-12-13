package com.shop;

import com.shop.config.DatabaseConfig;
import com.shop.dao.OrderDAO;
import com.shop.model.Order;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        try {
            logger.info("════════════════════════════════════════");
            logger.info("  JDBC E-Commerce Application");
            logger.info("════════════════════════════════════════");

            DataSource dataSource = DatabaseConfig.getDataSource();
            logger.info("HikariCP pool инициализирован");

            logger.info("Запуск миграций БД...");
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource)
                    .locations("classpath:db/migration")
                    .load();
            int migrationsExecuted = flyway.migrate().migrationsExecuted;
            logger.info("Миграции завершены: {} выполнено", migrationsExecuted);

            logger.info("\nДемонстрация операций с заказами\n");
            OrderDAO orderDAO = new OrderDAO(dataSource);

            logger.info("Создание заказа:");
            Order order = new Order(
                    1L,
                    new BigDecimal("1599.99"),
                    "ул. Пушкина, д. 10, Москва"
            );
            long orderId = orderDAO.save(order);
            logger.info("  Заказ создан: ID={}\n", orderId);

            logger.info("Чтение заказа:");
            Order found = orderDAO.findById(orderId).orElse(null);
            if (found != null) {
                logger.info("  Найден заказ: {}\n", found);
            }

            logger.info("Обновление статуса:");
            orderDAO.updateStatus(orderId, Order.OrderStatus.CONFIRMED);
            logger.info("  Статус заказа обновлен: CONFIRMED\n");

            logger.info("Получение заказов клиента:");
            List<Order> customerOrders = orderDAO.findByCustomerId(1L);
            logger.info("  Найдено {} заказов\n", customerOrders.size());

            logger.info("Статистика:");
            Order.OrderStats stats = orderDAO.getStats();
            logger.info("   {}\n", stats);

            logger.info("Удаление заказа:");
            orderDAO.delete(orderId);
            logger.info("Заказ удален\n");

            logger.info("════════════════════════════════════════");
            logger.info("  Демонстрация завершена успешно!");
            logger.info("════════════════════════════════════════");

        } catch (Exception e) {
            logger.error("Fatal error during execution", e);
            System.exit(1);
        } finally {
            DatabaseConfig.closeDataSource();
            logger.info("Application shutdown complete");
        }
    }
}
