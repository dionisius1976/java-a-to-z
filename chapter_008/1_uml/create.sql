CREATE TABLE music_type (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE address (id SERIAL PRIMARY KEY,	zip_code VARCHAR(50), country VARCHAR(50), city VARCHAR(100), street VARCHAR(100), house VARCHAR(50), flat VARCHAR(50)
);

CREATE TABLE role (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	name VARCHAR(100),
	surname VARCHAR(100),
	login VARCHAR(100),
	password VARCHAR(100),
	address_id INTEGER REFERENCES address(id),
	role_id INTEGER REFERENCES role(id),
	create_date TIMESTAMP
);

CREATE TABLE user_music (
	user_id INTEGER REFERENCES users(id),
	music_type_id INTEGER REFERENCES music_type(id)
);