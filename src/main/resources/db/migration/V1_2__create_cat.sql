CREATE TABLE IF NOT EXISTS cat (
    cat_id serial NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL,
    birthday date,
    type varchar(64),
    color varchar(64),
    owner_id int NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES owner(owner_id)
);