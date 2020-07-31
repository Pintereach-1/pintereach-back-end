package com.lambdaschool.pintereach.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;
@Configuration
public class DataSourceConfig
{
    //@Value("${spring.datasource.url}")
    //private String dbUrl;
    @Bean
    public DataSource dataSource()
    {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if (dbUrl != null) {
            return DataSourceBuilder.create()
                    .url(dbUrl)
                    .driverClassName("org.postgresql.Driver")
                    .build();
        } else {
            return DataSourceBuilder.create()
                    .username("sa")
                    .password("")
                    .url("jdbc:h2:mem:testdb")
                    .driverClassName("org.h2.Driver")
                    .build();
        }
    }
}
