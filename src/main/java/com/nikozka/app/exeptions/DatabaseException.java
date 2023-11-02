package com.nikozka.app.exeptions;

import java.sql.SQLException;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message, SQLException cause) {
        super(message, cause);
    }
}
