DROP DATABASE `chatop`;
CREATE DATABASE `chatop`;

CREATE TABLE `USERS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `RENTALS` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE `MESSAGES` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE UNIQUE INDEX `USERS_index` ON `USERS` (`email`);

ALTER TABLE `RENTALS` ADD FOREIGN KEY (`owner_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `MESSAGES` ADD FOREIGN KEY (`rental_id`) REFERENCES `RENTALS` (`id`);


INSERT INTO USERS (email, name, password, created_at) VALUES
  ('test1@test.com', 'test1', 'test1', now()),
  ('test2@test.com', 'test2', 'test2', now()),
  ('test3@test.com', 'test3', 'test3', now());

INSERT INTO RENTALS (name, surface, price, picture, description, owner_id, created_at) VALUES
  ('Chambre cosy', 18, 250, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg', 'Chambre douillette dans maison luxueuse', 3, now()),
  ('T2 sympa', 65, 450, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg', 'Appartement Coquet', 2, now()),
  ('Loft vintage', 500, 4250, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg', 'Loft spacieux, parfait pour artistes', 1, now()),
  ('Manoir victorien', 2000, 42485, 'https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg', 'Manoir de type Victorien pour fiestas mondaines', 1, now());
  
INSERT INTO MESSAGES (rental_id, user_id, message, created_at) VALUES
  (1, 1, 'Les chiens sont-ils admis ?', now()),
  (2, 2, 'Un lit enfant est-il fourni ?', now()),
  (3, 3, 'Peut-on le louer pour une semaine ?', now()),
  (4, 1, 'Est-il admis de tourner un film dans ce manoir ?', now());