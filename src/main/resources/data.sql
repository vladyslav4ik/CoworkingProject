insert into person(email, username, password, role, year_of_birth)
values ('developer@gmail.com', 'developer', '$2a$10$jG5C252jUtKbdm/AGGl4yub/qbIStoOOm80bUIqo4MHddNabD0lJy',
        'ROLE_DEVELOPER', 2000);

insert into person(email, username, password, role, year_of_birth)
values ('admin@gmail.com', 'admin', '$2a$10$ZxrvhkIoyFCQ7WXqE4tXJuq5AZYKHDTakWIBM8YFcSF4Jlhr2C2Km',
        'ROLE_ADMIN', 2000);

insert into person(email, username, password, role, year_of_birth)
values ('demo06478@gmail.com', 'user', '$2a$10$2jKKw/RZhuLSX8DnjVK3QeFxHCjZQzphUTfTP5/chWYdcFEewA3OS',
        'ROLE_USER', 2000);

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 1', 'Комфортний стіл та стілець, настільна лампа для роботи', '/images/workPlace1.jpg');

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 2', 'Комфортний стіл та стілець для роботи', '/images/workPlace2.jpg');

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 3', 'Комфортний стіл та стілець для роботи', '/images/workPlace3.jpg');

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 4', 'Комфортний стіл та стілець для роботи', '/images/workPlace4.jpg');

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 5', 'Комфортний стіл та стілець для роботи', '/images/workPlace5.jpg');

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 6', 'Комфортний стіл та стілець для роботи', '/images/workPlace6.jpg');

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 7', 'Комфортний стіл та стілець для роботи', '/images/workPlace7.jpg');

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 8', 'Комфортний стіл та стілець для роботи', '/images/workPlace8.jpg');

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 9', 'Комфортний стіл та стілець для роботи', '/images/workPlace9.jpg');

insert into work_place(item_name, description, img_url)
values ('Робоче місце № 10', 'Комфортний стіл та стілець для роботи', '/images/workPlace10.jpg');

insert into reservation(work_place_id, renter_id, rent_day, time_from, time_to, total_price, is_payed, is_confirmed)
values (1, 3, current_date, current_time + 1 minute, current_time + 2 minute, 100, true, true);

insert into rating(work_place_id)
values (1);
insert into rating(work_place_id, number_of_using)
values (2, 1);
insert into rating(work_place_id, number_of_using)
values (3, 1);
insert into rating(work_place_id, number_of_using)
values (4, 3);
insert into rating(work_place_id, number_of_using)
values (5, 1);
insert into rating(work_place_id, number_of_using)
values (6, 5);
insert into rating(work_place_id)
values (7);
insert into rating(work_place_id)
values (8);
insert into rating(work_place_id, number_of_using)
values (9, 9);
insert into rating(work_place_id, number_of_using)
values (10, 10);

insert into drink(name, price, ingredients)
values ('Bramble', 150,
        'Gin, lemon juice, simple syrup, creme de mure, lemon half-wheel, fresh blackberry.');

    insert into drink(name, price, ingredients)
values ('Mojito', 120,
    'Rum, mint, lime, sugar, club soda.');