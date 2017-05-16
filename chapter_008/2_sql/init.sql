INSERT INTO transmissions (name) VALUES ('manual');
INSERT INTO transmissions (name) VALUES ('auto');
INSERT INTO transmissions (name) VALUES ('robot');
INSERT INTO transmissions (name) VALUES ('variator');

INSERT INTO bodies (name) VALUES ('sedan');
INSERT INTO bodies (name) VALUES ('coupe');
INSERT INTO bodies (name) VALUES ('van');
INSERT INTO bodies (name) VALUES ('cabriolet');
INSERT INTO bodies (name) VALUES ('sport');

INSERT INTO engines (name) VALUES ('diesel 1.8');
INSERT INTO engines (name) VALUES ('diesel 2.0');
INSERT INTO engines (name) VALUES ('diesel 2.5');
INSERT INTO engines (name) VALUES ('petrol 1.6');
INSERT INTO engines (name) VALUES ('petrol 1.8');
INSERT INTO engines (name) VALUES ('petrol 2.0');
INSERT INTO engines (name) VALUES ('hybrid 2.0');
INSERT INTO engines (name) VALUES ('hybrid 3.0');

INSERT INTO cars (name, trans_id, body_id, engine_id) VALUES ('BMW 318', '4', '1', '5');
INSERT INTO cars (name, trans_id, body_id, engine_id) VALUES ('BMW 320d', '2', '2', '2');
INSERT INTO cars (name, trans_id, body_id, engine_id) VALUES ('BMW z4', '1', '5', '8');