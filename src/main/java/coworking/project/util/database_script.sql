create table person
(
    person_id       serial primary key,
    email         varchar(100) unique not null,
    username      varchar(100) unique not null,
    password      varchar             not null,
    role          varchar             not null,
    year_of_birth int check ( year_of_birth > 1915 )
);

insert into person(email, username, password, role, year_of_birth)
values ('admin@gmail.com', 'admin', 'password', 'ROLE_ADMIN', 2000);