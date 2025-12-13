package com.shop.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
    private static HikariDataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DatabaseConfig.class) {
                if (dataSource == null) {
                    HikariConfig config = new HikariConfig();
                    config.setJdbcUrl("jdbc:postgresql://localhost:5432/shop_db");
                    config.setUsername("postgres");
                    config.setPassword("admin");
                    config.setMaximumPoolSize(10);
                    config.setMinimumIdle(5);
                    config.setConnectionTimeout(20000);
                    config.setIdleTimeout(300000);
                    config.setMaxLifetime(1200000);
                    config.setAutoCommit(true);

                    dataSource = new HikariDataSource(config);
                    logger.info("HikariCP DataSource инициализирован: " +
                                    "pool size: {}, min idle: {}",
                            config.getMaximumPoolSize(),
                            config.getMinimumIdle());
                }
            }
        }
        return dataSource;
    }

    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            logger.info("HikariCP DataSource закрыт");
        }
    }
}
