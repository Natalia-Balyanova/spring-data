create table if not exists products (id bigserial primary key, title varchar(255), price int, secret_key varchar(255));

insert into products (title, price, secret_key)
values
('Bread', 100, 'qwerty'),
('Apple', 100, 'qwerty'),
('Milk', 70, 'qwerty'),
('Eggs', 100, 'qwerty'),
('Fish', 600, 'qwerty'),
('Coffee', 500, 'qwerty'),
('Tea', 300, 'qwerty'),
('Butter', 200, 'qwerty'),
('Butter Maxi', 450, 'qwerty'),
('Banana', 70, 'qwerty'),
('Orange juice', 100, 'qwerty'),
('Cheese', 1200, 'qwerty'),
('Tomato', 200, 'qwerty'),
('Cucumber', 150, 'qwerty'),
('Potato', 50, 'qwerty'),
('Pasta', 100, 'qwerty'),
('Dumplings', 700, 'qwerty'),
('Corn', 70, 'qwerty'),
('Cookie', 100, 'qwerty'),
('Salmon', 1200, 'qwerty');