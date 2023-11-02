SELECT s.name as street, n.value as number, c.name as city,
       SUM(st.quantity) as total
FROM address a
INNER JOIN street s ON s.street_id = a.street_id
INNER JOIN number n ON n.number_id = a.number_id
INNER JOIN city c ON c.city_id = a.city_id
INNER JOIN shop sh ON sh.address_id = a.address_id
INNER JOIN stock st ON st.shop_id = sh.shop_id
INNER JOIN product p ON p.product_id = st.product_id
INNER JOIN product_category pc ON pc.category_id = p.category_id
WHERE pc.name = '?'
GROUP BY s.name, n.value, c.name
ORDER BY total DESC
LIMIT 1;
