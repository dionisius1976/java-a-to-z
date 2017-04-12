CREATE TABLE orders (
	id serial primary key,
	name varchar(50),
	create_date timestamp,
	user_id integer references users(id),
	categorie_id integer references order_categories(id),
	comment_id integer references order_comments(id),
	state_id integer references order_states(id)
);

CREATE TABLE order_comments (
	id serial primary key,
	name text,
	create_date timestamp
);

CREATE TABLE order_states (
	id serial primary key,
	name varchar(50)
);

CREATE TABLE order_categories (
	id serial primary key,
	name varchar(50)
);

CREATE TABLE order_files (
	id serial primary key,
	name varchar(50),
	content bytea,
	order_id integer references orders(id)
);

CREATE TABLE users (
	id serial primary key,
	name varchar(50),
	user_role_id integer references user_roles
	
);

CREATE TABLE user_roles (
	id serial primary key,
	name varchar(50)
);

CREATE TABLE role_permissions (
	role_id integer references user_roles(id),
	permission_id integer references permissions(id)
);

CREATE TABLE permissions (
	id serial primary key,
	name varchar(50)
);