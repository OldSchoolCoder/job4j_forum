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
