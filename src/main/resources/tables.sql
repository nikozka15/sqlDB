CREATE TABLE city
(
    city_id SERIAL PRIMARY KEY,
    name    VARCHAR(50) NOT NULL
);
CREATE TABLE street
(
    street_id SERIAL PRIMARY KEY,
    name      VARCHAR(50) NOT NULL
);
CREATE TABLE number
(
    number_id SERIAL PRIMARY KEY,
    value     INT NOT NULL
);
CREATE TABLE address
(
    address_id SERIAL PRIMARY KEY,
    city_id    INT REFERENCES city (city_id),
    street_id  INT REFERENCES street (street_id),
    number_id  INT REFERENCES number (number_id)
);
CREATE TABLE shop
(
    shop_id    SERIAL PRIMARY KEY,
    address_id INT REFERENCES address (address_id)
);
CREATE TABLE product_category
(
    category_id SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL
);
CREATE TABLE product
(
    product_id  INT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    category_id INT REFERENCES product_category (category_id)
);

CREATE TABLE stock
(
    shop_id    INT REFERENCES shop (shop_id),
    product_id INT REFERENCES product (product_id),
    quantity   INT,
    PRIMARY KEY (shop_id, product_id)
);