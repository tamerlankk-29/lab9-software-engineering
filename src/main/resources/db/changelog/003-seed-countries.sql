--liquibase formatted sql
--changeset lab9:003
INSERT INTO countries (name, code) VALUES
 ('Kazakhstan', 'KZ'),
 ('United States', 'US'),
 ('Germany', 'DE'),
 ('China', 'CN'),
 ('Japan', 'JP');
