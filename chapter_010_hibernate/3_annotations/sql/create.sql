CREATE TYPE state_type AS ENUM ('sold', 'actual');

CREATE TYPE trans_type AS ENUM ('manual', 'robot', 'auto');

CREATE TABLE IF NOT EXISTS cars(
	id SERIAL PRIMARY KEY,
	brand VARCHAR(100) NOT NULL,
	model VARCHAR(100) NOT NULL,
	transmission trans_type,
	engine REAL NOT NULL,
	year INTEGER NOT NULL CHECK (year > 1900),
	price INTEGER CHECK (price > -1)
);

CREATE TABLE IF NOT EXISTS users(
	id SERIAL PRIMARY KEY,
	login VARCHAR(100) NOT NULL UNIQUE ,
	password VARCHAR(100) NOT NULL,
	name VARCHAR(100) NOT NULL,
	surname VARCHAR(100) NOT NULL,
	tel_number VARCHAR(50) NOT NULL,
	create_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ads(
	id SERIAL PRIMARY KEY,
	description VARCHAR(500),
	state state_type,
	create_date TIMESTAMP,
	user_id INTEGER REFERENCES users(id) ON UPDATE NO ACTION ON DELETE NO ACTION,
	car_id INTEGER REFERENCES cars(id) ON DELETE CASCADE
);


