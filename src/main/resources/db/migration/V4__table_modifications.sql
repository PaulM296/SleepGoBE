ALTER TABLE room
ADD COLUMN balcony boolean NOT NULL,
ADD COLUMN air_conditioning boolean NOT NULL,
ADD COLUMN kitchenette boolean NOT NULL,
ADD COLUMN hairdryer boolean NOT NULL,
ADD COLUMN tv boolean NOT NULL;

DROP TABLE IF EXISTS room_features;

ALTER TABLE hotel
ADD COLUMN country varchar(255) NOT NULL,
ADD COLUMN city varchar(255) NOT NULL,
ADD COLUMN address varchar(255) NOT NULL,
ADD COLUMN zip_code varchar(255) NOT NULL;

DROP TABLE IF EXISTS hotel_location;