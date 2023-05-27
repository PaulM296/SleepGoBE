ALTER TABLE reservations
    DROP FOREIGN KEY fk_user_id;

ALTER TABLE reservations
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id)
            REFERENCES appuser(id)
            ON DELETE CASCADE;
