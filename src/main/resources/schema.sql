create table person
(
    id     int auto_increment primary key,
    email         varchar(100) unique not null,
    username      varchar(100) unique not null,
    password      varchar             not null,
    role          varchar             not null,
    year_of_birth int check (year_of_birth > 1915)
);
