ALTER TABLE authentication_session
    DROP FOREIGN KEY authentication_session_ibfk_1;

ALTER TABLE authentication_session
    ADD CONSTRAINT authentication_session_ibfk_1
        FOREIGN KEY (user_id)
            REFERENCES appuser(id)
            ON DELETE CASCADE;