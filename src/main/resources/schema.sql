create table person
(
    id            long auto_increment primary key,
    email         varchar(100) unique not null,
    username      varchar(100) unique not null,
    password      varchar             not null,
    role          varchar             not null,
    year_of_birth int check (year_of_birth > 1915)
);

create table work_place
(
    id           long auto_increment primary key,
    item_name    varchar(100) not null,
    description  text         not null,
    is_available boolean default true,
    img_url      varchar      not null
);

create table reservation
(
    id            long auto_increment primary key,
    work_place_id int references work_place (id) on delete cascade,
    renter_id     int references person (id) on delete cascade,
    rent_day      date check (rent_day >= current_date) not null,
    time_from     time                                  not null,
    time_to       time                                  not null,
    is_payed      boolean default false,
    is_confirmed  boolean default false,
    is_actual     boolean default true,
    total_price   decimal                               not null,
    foreign key (work_place_id) references work_place (id),
    foreign key (renter_id) references person (id)
);

create table rating
(
    id              long auto_increment primary key,
    work_place_id   long references work_place (id) on delete cascade,
    number_of_using int not null default 0,
    foreign key (work_place_id) references work_place (id)
);

create table drink
(
    id          long auto_increment primary key,
    name        varchar(100)              not null unique,
    price       decimal check (price > 0) not null,
    ingredients varchar(200)              not null,
    img_url     varchar(200) unique
);