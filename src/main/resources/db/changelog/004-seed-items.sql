--liquibase formatted sql
--changeset lab9:004
INSERT INTO items (name, price, quantity, manufacturer_id) VALUES
 ('Laptop A', 1200, 10, (SELECT id FROM countries WHERE code='CN')),
 ('Phone B', 800, 25, (SELECT id FROM countries WHERE code='CN')),
 ('Car Part C', 150, 100, (SELECT id FROM countries WHERE code='DE')),
 ('Camera D', 600, 15, (SELECT id FROM countries WHERE code='JP')),
 ('Wheat E', 20, 500, (SELECT id FROM countries WHERE code='US'));
