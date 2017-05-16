-- outputs all cars

SELECT c.name, t.name, b.name, e.name FROM cars AS c
INNER JOIN transmissions AS t ON c.trans_id = t.id
INNER JOIN bodies AS b ON c.body_id = b.id
INNER JOIN engines AS e ON c.engine_id = e.id;

-- outputs all unused transmissions

SELECT t.name FROM transmissions AS t  
LEFT OUTER JOIN cars AS c ON c.trans_id = t.id
WHERE c.trans_id IS null;

-- outputs all unused bodies

SELECT b.name FROM bodies AS b  
LEFT OUTER JOIN cars AS c ON c.trans_id = b.id
WHERE c.trans_id IS null;

-- outputs all unused engines

SELECT e.name FROM engines AS e
LEFT OUTER JOIN cars AS c ON c.trans_id = e.id
WHERE c.trans_id IS null;