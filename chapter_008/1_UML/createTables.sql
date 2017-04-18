CREATE TABLE order_comments (
	id SERIAL PRIMARY KEY,
	name TEXT,
	create_date timestamp
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

CREATE TABLE user_roles (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);


CREATE TABLE permissions (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50)
);

CREATE TABLE role_permissions (
	role_id INTEGER REFERENCES user_roles(id),
	permission_id INTEGER REFERENCES permissions(id)
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	user_role_id INTEGER REFERENCES user_roles(id)
);

CREATE TABLE orders (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50),
	create_date TIMESTAMP,
	user_id INTEGER REFERENCES users(id),
	categorie_id INTEGER REFERENCES order_categories(id),
	comment_id INTEGER REFERENCES order_comments(id),
	state_id INTEGER REFERENCES order_states(id),
	order_file_id INTEGER REFERENCES order_files(id)
);
