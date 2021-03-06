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
