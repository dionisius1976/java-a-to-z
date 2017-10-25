CREATE TABLE users (
  id SERIAL PRIMARY KEY,
	login VARCHAR(100) UNIQUE ,
	password VARCHAR(100),
	name VARCHAR(100),
	surname VARCHAR(100),
	tel_number VARCHAR (50) UNIQUE
);