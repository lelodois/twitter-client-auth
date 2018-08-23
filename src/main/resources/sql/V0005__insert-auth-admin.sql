INSERT INTO AUTHUSER (username, password, activated)
VALUES ('admin', '$2a$10$r0RFDmpneBVryx.ihHK9gu6FFJQi4nTxQUqzdSTvrPpaKZMxigqpy', true);

INSERT INTO USER_AUTHORITY (username, authority) 
VALUES('admin', 'ROLE_USER');

INSERT INTO USER_AUTHORITY (username, authority) 
VALUES('admin', 'ROLE_ADMIN');
