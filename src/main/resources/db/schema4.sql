create table posts
(
    id          serial primary key,
    name        varchar(2000),
    description text,
    created     timestamp without time zone not null default now()
);

insert into posts (name, description)
values ('I need a doctor !!! Help me please !!! ',
        'Burger is my dream !!! ');

CREATE TABLE authorities
(
    id        serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users
(
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);

insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true,
        '$2a$10$ctNb0BZ2diodD2OYzL0y3u3EzHcb0OzERdcA3y28v8h2edkeZSv3W',
        (select id from authorities where authority = 'ROLE_ADMIN'));