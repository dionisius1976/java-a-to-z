CREATE TABLE transmissions (
	id SERIAL PRIMARY KEY,
	name VARCHAR(20)
);

CREATE TABLE bodies (
	id SERIAL PRIMARY KEY,
	name VARCHAR(20)
);

CREATE TABLE engines (
	id SERIAL PRIMARY KEY,
	name VARCHAR(20)
);

CREATE TABLE cars (
	car_id SERIAL PRIMARY KEY,
	name VARCHAR(20),
	trans_id INTEGER REFERENCES transmissions(id),
	body_id INTEGER REFERENCES bodies(id),
	engine_id INTEGER REFERENCES engines(id)
);
