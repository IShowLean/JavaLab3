CREATE TABLE IF NOT EXISTS friend
(
    cat_id1 serial NOT NULL,
    cat_id2 serial NOT NULL,
    PRIMARY KEY (cat_id1, cat_id2)
);