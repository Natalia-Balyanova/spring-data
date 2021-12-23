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

create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    serial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

CREATE TABLE authorities (
  id   serial,
  name varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_authorities (
    user_id             bigint not null,
    authority_id        int not null,
    primary key (user_id, authority_id),
    foreign key (user_id) references users (id),
    foreign key (authority_id) references authorities (id)
);

insert into authorities (name)
values ('ADD_PRODUCT'), ('DELETE_ORDER'), ('ADMINISTRATE'), ('DELETE_USER');

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_SUPERADMIN');


insert into users (username, password, email)
values
('max', '$2a$12$yGAFikKAKK/RKnPzQJvJ1uhWkmz/V6mbovQiYWzIsX0nTXz4miwni', 'max@gmail.com'), ---100
('bob', '$2a$12$.k8QQw0D5cF6LeKSVIzYhOSV9qwMjsIqlpS.KWzgjZAafEyhJvAxq', 'bob@gmail.com'), ---200
('admin', '$2a$12$U7YGhR3hmlqKPoDCM3C1Jux5f0VQwtLPMDrXxl6d3RyFEAtqXf3ze', 'admin@gmail.com'), ---111
('superadmin', '$2a$12$k.J0kmjVS6KluXe2VuWYxeMdnKK5JpEpaXppHctSkOpDJxF5kfPau', 'superadmin@gmail.com'); ---777

insert into users_roles (user_id, role_id)
values
(1, 1), (2, 1), (3, 2), (4, 3);

insert into users_authorities (user_id, authority_id)
values (1, 1), (2, 1), (3, 2), (4, 2), (3, 3), (4, 3), (3, 4), (4, 4);