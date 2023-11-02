package com.nikozka.app;

import com.nikozka.app.models.ProjectProperties;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConfiguration {
   ProjectProperties properties;

    public DatabaseConfiguration(ProjectProperties properties) {
        this.properties = properties;
    }

    public Connection getConnection() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(properties.getJdbcUrl());
        dataSource.setUser(properties.getUser());
        dataSource.setPassword(properties.getPassword());
        return dataSource.getConnection();
    }
}
