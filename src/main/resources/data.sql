insert into countries (name) values ('Egypt'), ('Libya'), ('Sudan'), ('Turkey');
insert into cities (name, country_id) values ('Alexandria', 1);
insert into industries (name) values ('industry 1'), ('industry 2');

INSERT INTO users (id, enabled, locked, app_register_method, email, password, type)
VALUES ('46e29bb7-37f9-4d2e-ba1d-6199cf4b37f7', true, false, 'EMAIL', 'admin@ambalaj.com', '$2a$10$X3OCsW0CqGFlod6rNMYq5euCDRyWRtuWx5gHwz0Z7/E8DgDKTpZxy', 'ADMIN');

INSERT INTO admins(user_id, first_name, last_name, phone_number, role)
VALUES ('46e29bb7-37f9-4d2e-ba1d-6199cf4b37f7', 'Mohamed', 'Gamal', '01000000', 'ADMIN');

insert into materials_specifications_names (name, type) values ('specification 1', 'TEXT'), ('specification 2', 'TEXT'), ('specification 3', 'COLOR');
