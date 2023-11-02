package com.nikozka.app;

import com.nikozka.app.models.ProjectProperties;
import com.nikozka.app.repositories.ProductRepository;
import com.nikozka.app.repositories.StockRepository;
import com.nikozka.app.repositories.StoreRepository;
import jakarta.validation.Validator;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class ApplicationRunner {
    public static final String FILE_NAME = "local.properties";
    private static final Logger log = LoggerFactory.getLogger(ApplicationRunner.class);
    private static final int TARGET_QUANTITY = 250_000;

    private ApplicationRunner() {
    }

    public static void run() {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        propertiesLoader.loadProperties(FILE_NAME);
        ProjectProperties properties = ProjectPropertiesFactory.createFromProperties(propertiesLoader);

        String productType = System.getProperty("Тип_товару", "М'які меблі");

        StopWatch watch = new StopWatch();
        Random random = new Random();
        Validator validator = Config.createValidator();

        DatabaseConfiguration configuration = new DatabaseConfiguration(properties);
        Connection connection;
        try {
            connection = configuration.getConnection();

            log.info("Database Connected ..");

            TableFiller tableFiller = new TableFiller(connection);
            tableFiller.fillTales();

            ProductRepository productRepository = new ProductRepository(connection);
            watch.start();
            productRepository.insertProducts(new ProductGenerator(validator).generateProducts(TARGET_QUANTITY), TARGET_QUANTITY);
            watch.stop();
            log.info("Time of insertion of 250_000 products: {}", watch.getTime());
            watch.reset();

            StockRepository stockRepository = new StockRepository(connection, random);
            watch.start();
            stockRepository.insertStock(TARGET_QUANTITY);
            watch.stop();
            log.info("Time of insertion 250_000 products to 12 stores: {}", watch.getTime());
            watch.reset();

            StoreRepository storeRepository = new StoreRepository(connection);
            watch.start();
            Result result = storeRepository.getStoreWithBiggestStockInCategory(productType);
            watch.stop();
            log.info("The store: {} has the biggest quantity of products {} in CATEGORY: {}",
                    result.getNumber() + " " + result.getStreet() + ", " + result.getCity(),
                    result.getTotal(),
                    productType
            );
            log.info("Time of execution without indexes: {}", watch.getTime());
            watch.reset();

            createIndexes(connection);

            watch.start();
            result = storeRepository.getStoreWithBiggestStockInCategory(productType);
            watch.stop();
            log.info("The store: {} has the biggest quantity of products {} in CATEGORY: {}",
                    result.getNumber() + " " + result.getStreet() + ", " + result.getCity(),
                    result.getTotal(),
                    productType
            );
            log.info("Time of execution with indexes: {}", watch.getTime());
            watch.reset();

            connection.close();
        } catch (Exception e) {
            log.error("Error", e);
        }
    }

    private static void createIndexes(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.addBatch("CREATE INDEX IF NOT EXISTS idx_stock_fks ON stock (product_id, shop_id)");
            statement.addBatch("CREATE INDEX IF NOT EXISTS idx_product ON product (category_id)");

            statement.executeBatch();
            log.info("Indexes was created");
        } catch (SQLException e) {
            log.warn("Indexes was not created", e);
        }
    }
}
