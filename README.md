
Project Title
Data Processing and Storage Benchmarking

Overview
This project focuses on efficiently processing and storing data, benchmarking the speed of various implementations on your PostgreSQL AWS instance.
Be cautious with costs and ensure timely shutdown to prevent unnecessary expenses.

Task Highlights Based on Code
Load Properties:
Load properties from the "local.properties" file using PropertiesLoader and create ProjectProperties.
Define Product Type:
Define the product type either from the command line (System.getProperty) or use the default value "М'які меблі".
Database Connection Setup:
Establish a connection to the database using DatabaseConfiguration and log the successful connection.
Table Filling:
Utilize TableFiller to fill tables in the database with necessary data.
Product Insertion:
Insert 250,000 products into the database using ProductRepository and log the time taken for insertion.
Stock Insertion:
Insert 250,000 products into 12 stores using StockRepository and log the time taken for insertion.
Retrieve Store with Most Stock in Category:
Get the store with the highest quantity of a specific product category using StoreRepository and log the result along with execution time (without indexes).
Create Indexes:
Create necessary indexes to optimize queries.
Retrieve Store with Most Stock in Category (With Indexes):
Repeat the store retrieval with indexes and log the result along with execution time.
Close Database Connection:
Ensure proper closure of the database connection.
Error Handling:
Log any errors encountered during the process.

How to Run
Configure your PostgreSQL AWS instance credentials.
Add "local.properties" file with credentials(user, password, jdbcUrl).
Create all tables using tables.sql file.
Run the App class.
