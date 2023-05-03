create table appuser (
    id int NOT NULL AUTO_INCREMENT,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    email varchar(50) NOT NULL,
    phone_number varchar(12) NOT NULL,
    username varchar(30) NOT NULL,
    password varchar(250) NOT NULL,
    PRIMARY KEY (id)
)