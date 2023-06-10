ALTER TABLE reservations
    ADD COLUMN hotel_id INT NOT NULL,
    ADD CONSTRAINT fk_reservations_hotel
        FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id);
