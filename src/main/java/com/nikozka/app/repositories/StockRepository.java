package com.nikozka.app.repositories;

import com.nikozka.app.exeptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class StockRepository {
    private static final Logger log = LoggerFactory.getLogger(StockRepository.class);
    private final Connection connection;
    private final Random random;

    public StockRepository(Connection connection, Random random) {
        this.connection = connection;
        this.random = random;
    }

    public void insertStock(int quantity) {
        String insertStock = "INSERT INTO stock (shop_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatementForInsert = connection.prepareStatement(insertStock)) {
            int counter = 1;
            while (counter <= quantity) {
                for (int i = 1; i < 13; i++) {
                    preparedStatementForInsert.setInt(1, i);
                    preparedStatementForInsert.setInt(2, counter);
                    preparedStatementForInsert.setInt(3,  random.nextInt(20));
                    preparedStatementForInsert.addBatch();
                }
                counter++;
                if (counter % 3000 == 0) {
                    preparedStatementForInsert.executeBatch();
                    log.info("Batch for stock is executed");
                }
            }
            preparedStatementForInsert.executeBatch();
        } catch (SQLException e) {
            log.error("Error while inserting stock", e);
            throw new DatabaseException("Error while inserting stock", e);
        }
    }
}
