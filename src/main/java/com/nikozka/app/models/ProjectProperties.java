package com.nikozka.app.models;

public class ProjectProperties {
    private final String user;
    private final String password;
    private final String jdbcUrl;

    public ProjectProperties(String user, String password, String jdbcUrl) {
        this.user = user;
        this.password = password;
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }
}
