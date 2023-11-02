package com.nikozka.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// LOCAL
// Time of insertion 250_000 products to 12 stores: 90_090
//  Time of execution without indexes: 208
//  Time of execution with indexes: 19

// Local to RDS
// Time of insertion 250_000 products to 12 stores: 237_646
// Time of execution without indexes: 551
// Time of execution with indexes: 113 (execution: 30 ms, fetching: 83 ms)
public class App {
        private static final Logger log = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        log.info("Start of application");
        ApplicationRunner.run();
        log.info("End of application");
    }
}
