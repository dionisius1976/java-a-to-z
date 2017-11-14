CREATE DATABASE carAds;

CREATE TABLE cars(
	id SERIAL PRIMARY KEY,
	brand VARCHAR(100),
	model VARCHAR(100),
	transmission VARCHAR(50),
	engine REAL,
	year INTEGER
);

CREATE TABLE users(
	id SERIAL PRIMARY KEY,
	login VARCHAR(100) UNIQUE ,
	password VARCHAR(100),
	name VARCHAR(100),
	surname VARCHAR(100),
	tel_number VARCHAR (50) UNIQUE,
	create_date TIMESTAMP
);

CREATE TABLE ads(
	id SERIAL PRIMARY KEY,
	description VARCHAR(500),
	price INTEGER,
	sold BOOLEAN,
	create_date TIMESTAMP,
	user_id INTEGER REFERENCES users(id),
	car_id INTEGER REFERENCES cars(id)
);

INSERT INTO cars(brand, model, transmission, engine, year)
    VALUES ('Mersedes', 'S600', 'auto', 6.0, 2003);
INSERT INTO cars(brand, model, transmission, engine, year)
    VALUES ('VAZ', '21099', 'manual', 1.5, 2000);

INSERT INTO users(login, password, name, surname, tel_number, create_date)
    VALUES ('1', '1', 'Ivan', 'Petrov', '+79213452525', NULL);
INSERT INTO users(login, password, name, surname, tel_number, create_date)
    VALUES ('2', '2', 'Oleg','Smirnov', '+79112337786', NULL);

INSERT INTO ads(description, price, sold, create_date, user_id, car_id)
    VALUES ('Hi!', 300000, FALSE , NULL, 1, 1);
INSERT INTO ads(description, price, sold, create_date, user_id, car_id)
		VALUES ('Hello!', 50000, FALSE , NULL, 2, 2);




