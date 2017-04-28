CREATE TABLE user_roles (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE user_permissions (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE role_permissions (
	role_id INTEGER REFERENCES user_roles(id),
	user_permission_id INTEGER REFERENCES user_permissions(id)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	user_role_id INTEGER REFERENCES user_roles(id)
);

CREATE TABLE order_states (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE order_categories (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE order_files (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	content BYTEA
);

CREATE TABLE orders (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	create_date TIMESTAMP,
	user_id INTEGER REFERENCES users(id),
	categorie_id INTEGER REFERENCES order_categories(id),
	state_id INTEGER REFERENCES order_states(id),
	order_file_id INTEGER REFERENCES order_files(id)
);

CREATE TABLE oredr_comments (
	id SERIAL PRIMARY KEY,
	name TEXT,
	create_date timestamp,
	order_id INTEGER REFERENCES orders(id)
);