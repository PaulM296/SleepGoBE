DROP TABLE IF EXISTS reservations;
CREATE TABLE reservations (
    reservation_id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    price int NOT NULL,
    status varchar(255),
    PRIMARY KEY (reservation_id),
    FOREIGN KEY (user_id) REFERENCES appuser(id)
);