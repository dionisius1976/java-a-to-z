INSERT INTO order_comments (name, create_date) VALUES ('Urgent', current_timestamp);
INSERT INTO order_comments (name, create_date) VALUES ('Common', current_timestamp);
INSERT INTO order_comments (name, create_date) VALUES ('Important', current_timestamp);
INSERT INTO order_comments (name, create_date) VALUES ('Unimportant', current_timestamp);

INSERT INTO order_states (name) VALUES ('Created');
INSERT INTO order_states (name) VALUES ('Pending');
INSERT INTO order_states (name) VALUES ('In process');
INSERT INTO order_states (name) VALUES ('Closed');

INSERT INTO order_categories (name) VALUES ('Bug');
INSERT INTO order_categories (name) VALUES ('Task');

INSERT INTO user_roles (name) VALUES ('Admin');
INSERT INTO user_roles (name) VALUES ('Seller');
INSERT INTO user_roles (name) VALUES ('Buyer');

INSERT INTO permissions (name) VALUES ('Search');
INSERT INTO permissions (name) VALUES ('Copy');
INSERT INTO permissions (name) VALUES ('Edit');

INSERT INTO users (name, user_role_id) VALUES ('Ivanov',1);
INSERT INTO users (name, user_role_id) VALUES ('Petrov',2);
INSERT INTO users (name, user_role_id) VALUES ('Sidorov',2);
INSERT INTO users (name, user_role_id) VALUES ('Kolosov',3);
INSERT INTO users (name, user_role_id) VALUES ('Alexeev',3);

INSERT INTO role_permissions (role_id, permission_id) VALUES (1,1);
INSERT INTO role_permissions (role_id, permission_id) VALUES (1,2);
INSERT INTO role_permissions (role_id, permission_id) VALUES (1,3);
INSERT INTO role_permissions (role_id, permission_id) VALUES (2,1);
INSERT INTO role_permissions (role_id, permission_id) VALUES (2,2);
INSERT INTO role_permissions (role_id, permission_id) VALUES (3,1);

INSERT INTO orders (name, create_date, user_id, categorie_id, comment_id,
	state_id, order_file_id) VALUES ('Task from admin', current_timestamp, 1, 2, 1, 1, 1);
INSERT INTO orders (name, create_date, user_id, categorie_id, comment_id,
	state_id, order_file_id) VALUES ('Bug from admin', current_timestamp, 1, 1, 3, 2, 1);
INSERT INTO orders (name, create_date, user_id, categorie_id, comment_id,
	state_id, order_file_id) VALUES ('Task from seller', current_timestamp, 2, 2, 2, 3, 2);
INSERT INTO orders (name, create_date, user_id, categorie_id, comment_id,
	state_id, order_file_id) VALUES ('Bug from buyer', current_timestamp, 5, 1, 4, 4, 3);



