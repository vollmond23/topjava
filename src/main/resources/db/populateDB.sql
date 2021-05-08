DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2021-04-29 08:00', 'Завтрак админа', 1000, 100001),
       ('2021-04-29 13:00', 'Обед админа', 800, 100001),
       ('2021-04-29 20:00', 'Ужин админа', 200, 100001),
       ('2021-04-29 08:00', 'Завтрак юзера', 1000, 100000),
       ('2021-04-29 13:00', 'Обед юзера', 800, 100000),
       ('2021-04-29 20:00', 'Ужин юзера', 300, 100000);