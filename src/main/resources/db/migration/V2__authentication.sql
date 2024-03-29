CREATE TABLE authentication_session (
    id int NOT NULL AUTO_INCREMENT,
    user_id int NOT NULL,
    username varchar(255) NOT NULL,
    token varchar(255) NOT NULL,
    created_at timestamp,
    PRIMARY KEY (id),
    INDEX (user_id),
    FOREIGN KEY (user_id) REFERENCES appuser(id)
);