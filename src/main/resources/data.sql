insert into person(email, username, password, role, year_of_birth)
values ('developer@gmail.com', 'developer', '$2a$10$jG5C252jUtKbdm/AGGl4yub/qbIStoOOm80bUIqo4MHddNabD0lJy',
        'ROLE_DEVELOPER', 2000);

insert into person(email, username, password, role, year_of_birth)
values ('admin@gmail.com', 'admin', '$2a$10$ZxrvhkIoyFCQ7WXqE4tXJuq5AZYKHDTakWIBM8YFcSF4Jlhr2C2Km',
        'ROLE_ADMIN', 2000);

insert into person(email, username, password, role, year_of_birth)
values ('demo06478@gmail.com', 'user', '$2a$10$2jKKw/RZhuLSX8DnjVK3QeFxHCjZQzphUTfTP5/chWYdcFEewA3OS',
        'ROLE_USER', 2000);

insert into work_place(item_name, description, price_per_hour)
values ('Робоче місце № 1', 'Комфортний стіл та стілець для роботи', 100.0);

insert into work_place(item_name, description, price_per_hour, is_available)
values ('Робоче місце № 2', 'Комфортний стіл та стілець для роботи', 100.0, false);

insert into work_place(item_name, description, price_per_hour)
values ('Робоче місце № 3', 'Комфортний стіл та стілець для роботи', 100.0);

insert into reservation(work_place_id, renter_id, rent_day, time_from, time_to, total_price, is_payed)
values (2, 3, current_date, current_time, current_time + 2 minute, 100, false);