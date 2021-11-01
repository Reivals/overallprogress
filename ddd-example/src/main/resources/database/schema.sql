CREATE TABLE IF NOT EXISTS Product (
    id serial CONSTRAINT id primary key,
    name varchar(255) not null,
    code varchar(100) not null
);