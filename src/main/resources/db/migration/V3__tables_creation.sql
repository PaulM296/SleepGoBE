DROP TABLE IF EXISTS hotel;
CREATE TABLE hotel (
    hotel_id int NOT NULL AUTO_INCREMENT,
    hotel_name varchar(255) NOT NULL,
    hotel_description varchar(255) NOT NULL,
    phone_number varchar(30) NOT NULL,
    email varchar(255) NOT NULL,
    PRIMARY KEY (hotel_id)
);

DROP TABLE IF EXISTS room;
CREATE TABLE room (
    room_id int NOT NULL AUTO_INCREMENT,
    hotel_id int NOT NULL,
    room_type varchar(255) NOT NULL,
    price int NOT NULL,
    availability boolean NOT NULL,
    PRIMARY KEY (room_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);

DROP TABLE IF EXISTS room_features;
CREATE TABLE room_features (
    feature_id int NOT NULL AUTO_INCREMENT,
    room_id int NOT NULL,
    balcony boolean NOT NULL,
    air_conditioning boolean NOT NULL,
    kitchenette boolean NOT NULL,
    hairdryer boolean NOT NULL,
    tv boolean NOT NULL,
    PRIMARY KEY (feature_id),
    FOREIGN KEY (room_id) REFERENCES room(room_id)
);

DROP TABLE IF EXISTS amenities;
CREATE TABLE amenities (
    amenity_id int NOT NULL AUTO_INCREMENT,
    hotel_id int NOT NULL,
    pool boolean NOT NULL,
    fitness boolean NOT NULL,
    restaurant boolean NOT NULL,
    PRIMARY KEY (amenity_id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);

DROP TABLE IF EXISTS hotel_location;
CREATE TABLE hotel_location (
    id int NOT NULL AUTO_INCREMENT,
    hotel_id int NOT NULL,
    country varchar(255) NOT NULL,
    city varchar(255) NOT NULL,
    address varchar(255) NOT NULL,
    zip_code varchar(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id)
);

DROP TABLE IF EXISTS reviews;
CREATE TABLE reviews (
    review_id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    hotel_id int,
    room_id int,
    review_text TEXT,
    PRIMARY KEY (review_id),
    FOREIGN KEY (user_id) REFERENCES appuser(id),
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id),
    FOREIGN KEY (room_id) REFERENCES room(room_id)
);



