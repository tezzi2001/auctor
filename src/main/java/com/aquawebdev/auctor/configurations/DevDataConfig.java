package com.aquawebdev.auctor.configurations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@Profile("dev")
public class DevDataConfig {
    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.driver-class-name}")
    private String DCN;
    @Value("${spring.datasource.username}")
    private String USERNAME;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DCN);
        dataSource.setUrl(URL);
        dataSource.setPassword(PASSWORD);
        dataSource.setUsername(USERNAME);
        return dataSource;
    }
}
