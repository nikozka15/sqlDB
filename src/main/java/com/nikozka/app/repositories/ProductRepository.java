package com.nikozka.app.repositories;

import com.nikozka.app.exeptions.DatabaseException;
import com.nikozka.app.models.ProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductRepository {
    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);

    private final Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public void insertProducts(List<ProductDTO> products, int quantity) {
        String insertProduct = "INSERT INTO product (product_id, name, category_id) VALUES (?, ?, ?)";
        try (PreparedStatement prepareStatementForProduct = connection.prepareStatement(insertProduct)){
            for (int i = 1; i <= quantity; i++) {
                ProductDTO product = products.get(i - 1);
                prepareStatementForProduct.setInt(1, i);
                prepareStatementForProduct.setString(2, product.getName());
                prepareStatementForProduct.setInt(3, product.getCategoryId());
                prepareStatementForProduct.addBatch();

                if (i % 3000 == 0) {
                    prepareStatementForProduct.executeBatch();
                    log.info("Batch with products is executed");
                }
            }
            prepareStatementForProduct.executeBatch();
        } catch (SQLException e) {
            log.error("Error while inserting product", e);
            throw new DatabaseException("Error while inserting product", e);
        }
    }
}
