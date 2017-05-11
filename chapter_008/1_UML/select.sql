-- Selects all not closed orders

SELECT o.name AS order_name, o.create_date AS order_create_date, u.name AS user_name, 
oc.name AS order_catrgory_name, s.name AS order_state_name FROM orders AS o
INNER JOIN users AS u ON o.user_id = u.id
INNER JOIN order_categories AS oc ON o.categorie_id = oc.id
INNER JOIN order_states AS s ON o.state_id = s.id
WHERE s.name <> 'Closed';


-- Selects all admin's orders

SELECT o.name AS order_name, o.create_date AS order_create_date, u.name AS user_name, 
oc.name AS order_category_name, s.name AS order_state_name FROM orders AS o
INNER JOIN users AS u ON o.user_id = u.id
INNER JOIN order_categories AS oc ON o.categorie_id = oc.id
INNER JOIN order_states AS s ON o.state_id = s.id
WHERE u.user_role_id = (SELECT ur.id FROM user_roles AS ur WHERE ur.name = 'Admin');


