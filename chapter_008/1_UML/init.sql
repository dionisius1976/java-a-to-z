INSERT INTO user_roles (name) VALUES ('Admin');
INSERT INTO user_roles (name) VALUES ('Seller');
INSERT INTO user_roles (name) VALUES ('Buyer');

INSERT INTO user_permissions (name) VALUES ('Search');
INSERT INTO user_permissions (name) VALUES ('Copy');
INSERT INTO user_permissions (name) VALUES ('Edit');

INSERT INTO role_permissions (role_id, user_permission_id) VALUES (1,1);
INSERT INTO role_permissions (role_id, user_permission_id) VALUES (1,2);
INSERT INTO role_permissions (role_id, user_permission_id) VALUES (1,3);
INSERT INTO role_permissions (role_id, user_permission_id) VALUES (2,1);
INSERT INTO role_permissions (role_id, user_permission_id) VALUES (2,2);
INSERT INTO role_permissions (role_id, user_permission_id) VALUES (3,1);

INSERT INTO users (name, user_role_id) VALUES ('Ivanov',1);
INSERT INTO users (name, user_role_id) VALUES ('Petrov',2);
INSERT INTO users (name, user_role_id) VALUES ('Sidorov',2);
INSERT INTO users (name, user_role_id) VALUES ('Kolosov',3);
INSERT INTO users (name, user_role_id) VALUES ('Alexeev',3);

INSERT INTO order_states (name) VALUES ('Created');
INSERT INTO order_states (name) VALUES ('Pending');
INSERT INTO order_states (name) VALUES ('In process');
INSERT INTO order_states (name) VALUES ('Closed');

INSERT INTO order_categories (name) VALUES ('Bug');
INSERT INTO order_categories (name) VALUES ('Task');

INSERT INTO orders (name, create_date, user_id, categorie_id,
	state_id) VALUES ('Task from admin', current_timestamp, 1, 2, 1);
INSERT INTO orders (name, create_date, user_id, categorie_id,
	state_id) VALUES ('Bug from admin', current_timestamp, 1, 1, 2);
INSERT INTO orders (name, create_date, user_id, categorie_id,
	state_id) VALUES ('Task from seller', current_timestamp, 2, 2, 3);
INSERT INTO orders (name, create_date, user_id, categorie_id,
	state_id) VALUES ('Bug from buyer', current_timestamp, 3, 1, 4);
	
INSERT INTO order_files (name, content, order_id) VALUES ('Task', NULL, 1);
INSERT INTO order_files (name, content, order_id) VALUES ('Data', NULL, 1);
INSERT INTO order_files (name, content, order_id) VALUES ('Description', NULL, 2);
INSERT INTO order_files (name, content, order_id) VALUES ('Seller_task', NULL, 3);
INSERT INTO order_files (name, content, order_id) VALUES ('Bug_description', NULL, 4);
	
INSERT INTO order_comments (name, create_date, order_id) VALUES ('Urgent task!', current_timestamp, 1);
INSERT INTO order_comments (name, create_date, order_id) VALUES ('See additional data in attached files.', current_timestamp, 1);
INSERT INTO order_comments (name, create_date, order_id) VALUES ('Common seller task.', current_timestamp, 3);
INSERT INTO order_comments (name, create_date, order_id) VALUES ('Must be fixed.', current_timestamp, 2);
INSERT INTO order_comments (name, create_date, order_id) VALUES ('Must be fixed.', current_timestamp, 4);


