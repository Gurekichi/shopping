package com.group.shopping.config;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

public class FlywayConfig {
	@Bean
    public FlywayMigrationStrategy strategy() {
        return flyway -> {
            // flyway_schema_historyの初期化
            flyway.clean();
            // マイグレーション実行
            flyway.migrate();
        };
    }
}
