INSERT INTO role (id, description, name) VALUES (1, 'Admin role', 'ROLE_ADMIN');
INSERT INTO role (id, description, name) VALUES (2, 'Client role', 'ROLE_CLIENT');
INSERT INTO role (id, description, name) VALUES (3, 'Restaurateur role', 'ROLE_RESTAURATEUR');

-- pass same as login

INSERT INTO user(username, password, id_role) VALUES
 ('admin', '$2a$11$70Xpp7/vQsnlZNZMQSx/6OHusrhf8dY8B5YoR6N7TfwNxrMJp4OcK', 1);

INSERT INTO user(username, password,id_role) VALUES
('client', '$2a$11$TNfx5rjp9ks.C96Nsht0HO50UZtR8YVl3UEEac6Xcu494z8kKUXXa', 2);

INSERT INTO user(username, password,id_role) VALUES
 ('restaurateur', '$2a$11$qZd1vIxiyzJ.1q3XY4in5.TW5jteWh0Y1wKq8HfqL1Ichcjuvqxo6', 3);
