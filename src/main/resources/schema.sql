create table person
(
    id            int auto_increment primary key,
    email         varchar(100) unique not null,
    username      varchar(100) unique not null,
    password      varchar             not null,
    role          varchar             not null,
    year_of_birth int check (year_of_birth > 1915)
);

create table work_place
(
    id             int auto_increment primary key,
    item_name      varchar(100) not null,
    description    text         not null,
    price_per_hour decimal check (price_per_hour > 0),
    is_available   boolean default true
);

create table reservation
(
    id            int auto_increment primary key,
    work_place_id int references work_place (id) on delete cascade,
    renter_id     int references person (id) on delete cascade,
    rent_day      date check (rent_day >= current_date) not null,
    time_from     time check (time_from > '09:00:00')   not null,
    time_to       time check (time_to < '18:00:00')     not null,
    is_payed      boolean default false,
    is_confirmed  boolean default false,
    total_price   decimal                               not null,
    foreign key (work_place_id) references work_place (id),
    foreign key (renter_id) references person (id)
);