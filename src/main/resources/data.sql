INSERT INTO countries (name) VALUES ('Egypt'), ('Libya'), ('Sudan'), ('Turkey');
INSERT INTO cities (name, country_id) VALUES ('Alexandria', 1);
INSERT INTO industries (name) VALUES ('industry 1'), ('industry 2');

INSERT INTO categories (name, image) VALUES ('category 1', 'image'), ('category 2', 'image');

INSERT INTO users (id, enabled, locked, app_register_method, email, password, type)
VALUES ('46e29bb7-37f9-4d2e-ba1d-6199cf4b37f7', true, false, 'EMAIL', 'admin@ambalaj.com', '$2a$10$X3OCsW0CqGFlod6rNMYq5euCDRyWRtuWx5gHwz0Z7/E8DgDKTpZxy', 'ADMIN');

INSERT INTO admins(user_id, first_name, last_name, phone_number, role)
VALUES ('46e29bb7-37f9-4d2e-ba1d-6199cf4b37f7', 'Mohamed', 'Gamal', '01000000', 'ADMIN');

INSERT INTO materials_specifications_names (name, type)
VALUES ('specification 1', 'TEXT'), ('specification 2', 'TEXT'), ('specification 3', 'COLOR');

INSERT INTO plans (name, price_per_month, price_per_year, discount, is_popular, account_type, products_number_to_create, priority_number, status)
VALUES ('free', 0, 0, 0, false, 'COMPANY', 20, 3, 'ACTIVE');

INSERT INTO plans (name, price_per_month, price_per_year, discount, is_popular, account_type, products_number_to_create, priority_number, status)
VALUES ('free', 0, 0, 0, false, 'CLIENT', null, 3, 'ACTIVE');