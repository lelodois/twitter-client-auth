INSERT INTO AUTHUSER (username, password, activated)
VALUES ('user', '$2a$10$r0RFDmpneBVryx.ihHK9gu6FFJQi4nTxQUqzdSTvrPpaKZMxigqpy', true);

INSERT INTO USER_AUTHORITY (username, authority) 
VALUES('user', 'ROLE_USER');
