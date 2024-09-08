INSERT INTO countries (name) VALUES ('Egypt'), ('Libya'), ('Sudan'), ('Turkey');

INSERT INTO cities (name, country_id) VALUES ('Alexandria', 1);

INSERT INTO industries (name) VALUES ('industry 1'), ('industry 2');

INSERT INTO sizes (name) VALUES ('sm'), ('m'), ('lg'), ('xl'), ('xxl');

INSERT INTO colors (hex_code, name)
VALUES
  ('#FF0000', 'Red'),
  ('#00FF00', 'Green'),
  ('#0000FF', 'Blue'),
  ('#FFFF00', 'Yellow'),
  ('#00FFFF', 'Cyan');

INSERT INTO
  materials_specifications_names (name, type)
VALUES
  ('Cling', 'TEXT'),
  ('Coating, Film', 'TEXT'),
  ('Color', 'COLOR');

INSERT INTO materials ( name, description, image)
VALUES
  ('Cardboard', 'A lightweight, flexible material made from wood pulp.', 'image'),
  ('Plastic', 'A versatile material used in a wide range of applications, including packaging, electronics, and construction.', 'image');

INSERT INTO
  materials_specifications (material_id, specification_id, value)
VALUES
  (1, 1, 'Good'),
  (1, 2, 'Low'),
  (2, 1, 'Good'),
  (2, 2, 'Low');

INSERT INTO categories (name, image) VALUES ('category 1', 'image'), ('category 2', 'image');

INSERT INTO plans (name, price_per_month, price_per_year, discount, is_popular, account_type, products_number_to_create, priority_number, status)
VALUES ('free', 0, 0, 0, false, 'COMPANY', 20, 3, 'ACTIVE'), ('free', 0, 0, 0, false, 'CLIENT', null, 3, 'ACTIVE');

INSERT INTO users (id, enabled, locked, app_register_method, email, password, type)
VALUES
('46e29bb7-37f9-4d2e-ba1d-6199cf4b37f7', true, false, 'EMAIL', 'admin@ambalaj.com', '$2a$10$X3OCsW0CqGFlod6rNMYq5euCDRyWRtuWx5gHwz0Z7/E8DgDKTpZxy', 'ADMIN'),
('46e29bb7-37f9-4d2e-ba1d-6199cf4b37f6', true, false, 'EMAIL', 'mg97@gmail.com', '$2a$10$X3OCsW0CqGFlod6rNMYq5euCDRyWRtuWx5gHwz0Z7/E8DgDKTpZxy', 'COMPANY');

INSERT INTO admins (user_id, first_name, last_name, phone_number, role)
VALUES ('46e29bb7-37f9-4d2e-ba1d-6199cf4b37f7', 'Mohamed', 'Gamal', '01000000', 'ADMIN');

INSERT INTO companies (city_id, id, user_id, name, address, description, tax_number, website)
VALUES (1, 1, '46e29bb7-37f9-4d2e-ba1d-6199cf4b37f6', 'MG97', 'address', 'description', '1111111', 'https://www.mg97.com');

INSERT INTO company_industry (company_id, industry_id) VALUES (1, 1), (1, 2);
INSERT INTO company_category (company_id, category_id) VALUES (1, 1), (1, 2);
INSERT INTO company_phone_numbers (company_id, phone_number) VALUES (1, +201093141111), (1, +201093141112);

INSERT INTO subscriptions (user_id, plan_id, created_at, start_date, end_date, status)
VALUES ('46e29bb7-37f9-4d2e-ba1d-6199cf4b37f6', 1, NOW(), NOW(), (NOW() + INTERVAL '30 days')::timestamp, 'ACTIVE');

INSERT INTO company_products_number_to_create (company_id, products_number)
VALUES (1, 20);