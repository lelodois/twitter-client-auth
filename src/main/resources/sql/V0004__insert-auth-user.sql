
DELETE FROM oauth_client_details;

INSERT INTO AUTHUSER (username, email, password, activated)
SELECT * FROM (SELECT 'admin', 'admin@admin.com', '$2a$10$r0RFDmpneBVryx.ihHK9gu6FFJQi4nTxQUqzdSTvrPpaKZMxigqpy', true) AS tmp
WHERE NOT EXISTS (
    SELECT username FROM AUTHUSER WHERE username = 'admin'
) LIMIT 1;

INSERT INTO AUTHORITY (name)
SELECT * FROM (SELECT 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM AUTHORITY WHERE name = 'ROLE_USER'
) LIMIT 1;

INSERT INTO AUTHORITY (name)
SELECT * FROM (SELECT 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM AUTHORITY WHERE name = 'ROLE_ADMIN'
) LIMIT 1;

INSERT INTO USER_AUTHORITY (username, authority)
SELECT * FROM (SELECT 'admin', 'ROLE_USER') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM USER_AUTHORITY WHERE username = 'admin' and authority = 'ROLE_USER'
) LIMIT 1;

INSERT INTO USER_AUTHORITY (username, authority)
SELECT * FROM (SELECT 'admin', 'ROLE_ADMIN') AS tmp
WHERE NOT EXISTS (
    SELECT username, authority FROM USER_AUTHORITY WHERE username = 'admin' and authority = 'ROLE_ADMIN'
) LIMIT 1;