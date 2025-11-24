--liquibase formatted sql
--changeset lab9:002
CREATE TABLE IF NOT EXISTS items (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    manufacturer_id BIGINT
);

ALTER TABLE items
    ADD CONSTRAINT fk_items_manufacturer
    FOREIGN KEY (manufacturer_id) REFERENCES countries (id);
