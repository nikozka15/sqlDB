package com.nikozka.app.repositories;

import com.nikozka.app.exeptions.DatabaseException;
import com.nikozka.app.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoreRepository {
    private static final Logger log = LoggerFactory.getLogger(StoreRepository.class);
    Connection connection;

    public StoreRepository(Connection connection) {
        this.connection = connection;
    }

    public Result getStoreWithBiggestStockInCategory(String productType) {
        String storeWithBiggestStockInCategory = "SELECT s.name as street, n.value as number, c.name as city, SUM(st.quantity) as total " +
                "FROM address a INNER JOIN street s ON s.street_id = a.street_id " +
                "INNER JOIN number n ON n.number_id = a.number_id " +
                "INNER JOIN city c ON c.city_id = a.city_id " +
                "INNER JOIN shop sh ON sh.address_id = a.address_id " +
                "INNER JOIN stock st ON st.shop_id = sh.shop_id " +
                "INNER JOIN product p ON p.product_id = st.product_id " +
                "INNER JOIN product_category pc ON pc.category_id = p.category_id " +
                "WHERE pc.name = ? " +
                "GROUP BY s.name, n.value, c.name " +
                "ORDER BY total DESC " +
                "LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(storeWithBiggestStockInCategory)) {
            preparedStatement.setString(1, productType);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                log.info("Store with the biggest stock in category is retrieved");
                return new Result(resultSet.getInt("number"), resultSet.getString("street"),
                        resultSet.getString("city"), resultSet.getInt("total"));
            } else {
                log.error("No store found with the specified category");
                throw new SQLException("No store found with the specified category");
            }
        } catch (SQLException e) {
            log.error("Error retrieving store with the biggest stock in category: ", e);
            throw new DatabaseException("Error retrieving store with the biggest stock in category: ", e);
        }
    }
}
