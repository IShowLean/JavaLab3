CREATE TABLE IF NOT EXISTS owner (
    owner_id serial NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL,
    birthday date
);