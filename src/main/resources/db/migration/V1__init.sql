create table if not exists products (id bigserial primary key, title varchar(255), price int);

insert into products (title, price)
values
('Bread', 100),
('Apple', 100),
('Milk', 70),
('Eggs', 100),
('Fish', 600),
('Coffee', 500),
('Tea', 300),
('Butter', 200),
('Butter Maxi', 450),
('Banana', 70),
('Orange juice', 100),
('Cheese', 1200),
('Tomato', 200),
('Cucumber', 150),
('Potato', 50),
('Pasta', 100),
('Dumplings', 700),
('Corn', 70),
('Cookie', 100),
('Salmon', 1200);