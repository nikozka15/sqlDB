package com.nikozka.app;


import com.nikozka.app.models.ProjectProperties;

import java.util.Objects;

public class ProjectPropertiesFactory {

    private ProjectPropertiesFactory() {
    }

    public static ProjectProperties createFromProperties(PropertiesLoader properties) {
        Objects.requireNonNull(properties, "Properties must not be null");

        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String jdbcUrl = properties.getProperty("jdbcUrl");

        return new ProjectProperties(user, password, jdbcUrl);
    }
}
