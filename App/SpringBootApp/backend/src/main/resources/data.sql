
-- Insertar roles de usuario si no existen:
INSERT INTO roles (id, name) 
SELECT 1, 'ADMIN' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');
INSERT INTO roles (id, name) 
SELECT 2, 'USER' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'USER');

-- Asignar el rol ADMIN al usuario 'admin' si no está asignado:
INSERT INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM usuarios u, roles r 
WHERE u.username = 'admin' AND r.name = 'ADMIN'
AND NOT EXISTS (SELECT 1 FROM user_roles ur WHERE ur.user_id = u.id AND ur.role_id = r.id);