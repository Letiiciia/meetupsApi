CREATE TABLE IF NOT EXISTS  registration (
    registration_id INTEGER NOT NULL AUTO_INCREMENT,
    person_name VARCHAR(128) NOT NULL,
    date_of_registration DATE NOT NULL,
    registration VARCHAR NOT NULL,
    PRIMARY KEY (registration_id)
);

INSERT INTO registration (registration_id, person_name, date_of_registration, registration) VALUES('1', 'Leticia Lima', sysdate, '001');