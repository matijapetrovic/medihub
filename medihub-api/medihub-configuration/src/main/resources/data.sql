create trigger after_clinic_review_update
    after update on clinic_review for each row
    update clinic c set rating = (select ifnull(avg(rating), 0.0) from clinic_review  where clinic_id= c.id and rating is not null),
                        review_count = (select count(rating) from clinic_review where clinic_id=c.id and rating is not null)
    where id = NEW.clinic_id;

create trigger after_clinic_review_insert
    after insert on clinic_review for each row
    update clinic c set rating = (select ifnull(avg(rating), 0.0) from clinic_review  where clinic_id= c.id and rating is not null),
                        review_count = (select count(rating) from clinic_review where clinic_id=c.id and rating is not null)
    where id = NEW.clinic_id;


create trigger after_doctor_review_update
    after update on doctor_review for each row
    update medical_doctor md set rating = (select ifnull(avg(rating), 0.0)from doctor_review where doctor_id=md.id and rating is not null),
                                 review_count = (select count(rating) from doctor_review where doctor_id=md.id and rating is not null)
    where id = NEW.doctor_id;

create trigger after_doctor_review_insert
    after insert on doctor_review for each row
    update medical_doctor md set rating = (select ifnull(avg(rating), 0.0) from doctor_review where doctor_id=md.id and rating is not null),
                                 review_count = (select count(rating) from doctor_review where doctor_id=md.id and rating is not null)
    where id = NEW.doctor_id;

insert into authority (id, name) values (1, 'ROLE_PATIENT');
insert into authority (id, name) values (2, 'ROLE_DOCTOR');
insert into authority (id, name) values (3, 'ROLE_NURSE');
insert into authority (id, name) values (4, 'ROLE_CLINIC_ADMIN');
insert into authority (id, name) values (5, 'ROLE_CLINIC_CENTER_ADMIN');

insert into account (email, password, password_changed, activated) values ('p1@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p2@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p3@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p4@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p5@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p6@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p7@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p8@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p9@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p10@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p11@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p12@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p13@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('p14@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, false);
insert into account (email, password, password_changed, activated) values ('p15@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, false);

insert into account (email, password, password_changed, activated) values ('d1a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d1b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d1c@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d1d@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d1e@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d1f@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d2a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d2b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d2c@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d2d@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d2e@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d2f@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d2g@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d3a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d3b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d3c@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d3d@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d3e@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d4a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d4b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d4c@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d5a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d5b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d5c@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d6a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d6b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d6c@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d6d@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d7a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d9a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d9b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d9c@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d10a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d10b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d10c@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d10d@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d10e@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d10f@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false, true);

insert into account (email, password, password_changed, activated) values ('n1a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n1b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n2a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n3a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n4a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n5a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n6a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n7a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n8a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n9a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('n10a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false, true);


insert into account (email, password, password_changed, activated) values ('ca1a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca1b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca2a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca3a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca4a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca5a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca5b@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca6a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca7a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca8a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca9a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('ca10a@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false, true);

insert into account (email, password, password_changed, activated) values ('a1@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('a2@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false, true);

insert into account_authority (user_id, authority_id) values (1, 1);
insert into account_authority (user_id, authority_id) values (2, 1);
insert into account_authority (user_id, authority_id) values (3, 1);
insert into account_authority (user_id, authority_id) values (4, 1);
insert into account_authority (user_id, authority_id) values (5, 1);
insert into account_authority (user_id, authority_id) values (6, 1);
insert into account_authority (user_id, authority_id) values (7, 1);
insert into account_authority (user_id, authority_id) values (8, 1);
insert into account_authority (user_id, authority_id) values (9, 1);
insert into account_authority (user_id, authority_id) values (10, 1);
insert into account_authority (user_id, authority_id) values (11, 1);
insert into account_authority (user_id, authority_id) values (12, 1);
insert into account_authority (user_id, authority_id) values (13, 1);
insert into account_authority (user_id, authority_id) values (14, 1);
insert into account_authority (user_id, authority_id) values (15, 1);

insert into account_authority (user_id, authority_id) values (16, 2);
insert into account_authority (user_id, authority_id) values (17, 2);
insert into account_authority (user_id, authority_id) values (18, 2);
insert into account_authority (user_id, authority_id) values (19, 2);
insert into account_authority (user_id, authority_id) values (20, 2);
insert into account_authority (user_id, authority_id) values (21, 2);
insert into account_authority (user_id, authority_id) values (22, 2);
insert into account_authority (user_id, authority_id) values (23, 2);
insert into account_authority (user_id, authority_id) values (24, 2);
insert into account_authority (user_id, authority_id) values (25, 2);
insert into account_authority (user_id, authority_id) values (26, 2);
insert into account_authority (user_id, authority_id) values (27, 2);
insert into account_authority (user_id, authority_id) values (28, 2);
insert into account_authority (user_id, authority_id) values (29, 2);
insert into account_authority (user_id, authority_id) values (30, 2);
insert into account_authority (user_id, authority_id) values (31, 2);
insert into account_authority (user_id, authority_id) values (32, 2);
insert into account_authority (user_id, authority_id) values (33, 2);
insert into account_authority (user_id, authority_id) values (34, 2);
insert into account_authority (user_id, authority_id) values (35, 2);
insert into account_authority (user_id, authority_id) values (36, 2);
insert into account_authority (user_id, authority_id) values (37, 2);
insert into account_authority (user_id, authority_id) values (38, 2);
insert into account_authority (user_id, authority_id) values (39, 2);
insert into account_authority (user_id, authority_id) values (40, 2);
insert into account_authority (user_id, authority_id) values (41, 2);
insert into account_authority (user_id, authority_id) values (42, 2);
insert into account_authority (user_id, authority_id) values (43, 2);
insert into account_authority (user_id, authority_id) values (44, 2);
insert into account_authority (user_id, authority_id) values (45, 2);
insert into account_authority (user_id, authority_id) values (46, 2);
insert into account_authority (user_id, authority_id) values (47, 2);
insert into account_authority (user_id, authority_id) values (48, 2);
insert into account_authority (user_id, authority_id) values (49, 2);
insert into account_authority (user_id, authority_id) values (50, 2);
insert into account_authority (user_id, authority_id) values (51, 2);
insert into account_authority (user_id, authority_id) values (52, 2);
insert into account_authority (user_id, authority_id) values (53, 2);

insert into account_authority (user_id, authority_id) values (54, 3);
insert into account_authority (user_id, authority_id) values (55, 3);
insert into account_authority (user_id, authority_id) values (56, 3);
insert into account_authority (user_id, authority_id) values (57, 3);
insert into account_authority (user_id, authority_id) values (58, 3);
insert into account_authority (user_id, authority_id) values (59, 3);
insert into account_authority (user_id, authority_id) values (60, 3);
insert into account_authority (user_id, authority_id) values (61, 3);
insert into account_authority (user_id, authority_id) values (62, 3);
insert into account_authority (user_id, authority_id) values (63, 3);
insert into account_authority (user_id, authority_id) values (64, 3);

insert into account_authority (user_id, authority_id) values (65, 4);
insert into account_authority (user_id, authority_id) values (66, 4);
insert into account_authority (user_id, authority_id) values (67, 4);
insert into account_authority (user_id, authority_id) values (68, 4);
insert into account_authority (user_id, authority_id) values (69, 4);
insert into account_authority (user_id, authority_id) values (70, 4);
insert into account_authority (user_id, authority_id) values (71, 4);
insert into account_authority (user_id, authority_id) values (72, 4);
insert into account_authority (user_id, authority_id) values (73, 4);
insert into account_authority (user_id, authority_id) values (74, 4);
insert into account_authority (user_id, authority_id) values (75, 4);
insert into account_authority (user_id, authority_id) values (76, 4);

insert into account_authority (user_id, authority_id) values (77, 5);
insert into account_authority (user_id, authority_id) values (78, 5);

insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksa', 'Nikolic', 'Jug Bogdana', 'Nis', 'Serbia', '062719612', 1);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Aleksandrovic', 'Jug Bogdana', 'Beograd', 'Serbia', '062719612', 2);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Djordje', 'Aleksandrovic', 'Dositejeva', 'Novi Sad', 'Serbia', '062719612', 3);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Mirko', 'Bodroza', 'Alekse Santica', 'Beograd', 'Serbia', '0604212612', 4);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksandar', 'Aleksic', 'Fruskogorska', 'Beograd', 'Serbia', '0614212678', 5);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Aleksic', 'Vuka Karadzica', 'Nis', 'Serbia', '0614213612', 6);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Djordjevic', 'Jug Bogdana', 'Novi Sad', 'Serbia', '065230612', 7);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Mirkovic', 'Jug Bogdana', 'Novi Sad', 'Serbia', '062719612', 8);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Marijana', 'Bodroza', 'Jug Bogdana', 'Nis', 'Serbia', '0614213612', 9);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Vukasinovic', 'Svetog Save', 'Beograd', 'Serbia', '0614213612', 10);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Djordje', 'Draganic', 'Sime Milosevica', 'Beograd', 'Serbia', '0614212678', 11);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksandar', 'Aleksandrovic', 'Jug Bogdana', 'Novi Sad', 'Serbia', '06943452612', 12);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksandar', 'Draganic', 'Alekse Santica', 'Nis', 'Serbia', '0644512612', 13);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Mirko', 'Djordjevic', 'Sime Milosevica', 'Novi Sad', 'Serbia', '065230612', 14);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Mirko', 'Aleksandrovic', 'Dositejeva', 'Nis', 'Serbia', '06943452612', 15);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Bodroza', 'Svetog Save', 'Beograd', 'Serbia', '065230612', 16);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Djordje', 'Dinkic', 'Tekelijina', 'Novi Sad', 'Serbia', '061232612', 17);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Mirko', 'Dinkic', 'Svetog Save', 'Nis', 'Serbia', '0614212678', 18);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Djordjevic', 'Jug Bogdana', 'Novi Sad', 'Serbia', '061232612', 19);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Marijana', 'Draganic', 'Alekse Santica', 'Novi Sad', 'Serbia', '0614212678', 20);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vesna', 'Aleksandrovic', 'Dositejeva', 'Nis', 'Serbia', '0614213612', 21);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Marijana', 'Dinkic', 'Tekelijina', 'Novi Sad', 'Serbia', '0604212612', 22);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Aleksic', 'Vuka Karadzica', 'Nis', 'Serbia', '065230612', 23);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vesna', 'Nikolic', 'Svetog Save', 'Beograd', 'Serbia', '061232612', 24);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Danijela', 'Aleksic', 'Svetog Save', 'Beograd', 'Serbia', '0614213612', 25);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksandar', 'Dinkic', 'Jug Bogdana', 'Novi Sad', 'Serbia', '061232612', 26);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Danijela', 'Djordjevic', 'Jug Bogdana', 'Nis', 'Serbia', '0644512612', 27);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Danijela', 'Vukasinovic', 'Svetog Save', 'Nis', 'Serbia', '065230612', 28);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksa', 'Nikolic', 'Vuka Karadzica', 'Beograd', 'Serbia', '0614213612', 29);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Danijela', 'Aleksic', 'Dositejeva', 'Novi Sad', 'Serbia', '0614212678', 30);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vesna', 'Mirkovic', 'Jug Bogdana', 'Novi Sad', 'Serbia', '0614213612', 31);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Aleksandrovic', 'Jug Bogdana', 'Novi Sad', 'Serbia', '0614212678', 32);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Aleksandrovic', 'Vuka Karadzica', 'Novi Sad', 'Serbia', '06943452612', 33);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Aleksic', 'Tekelijina', 'Novi Sad', 'Serbia', '065230612', 34);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Nikolic', 'Sime Milosevica', 'Nis', 'Serbia', '065230612', 35);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksa', 'Djordjevic', 'Vuka Karadzica', 'Beograd', 'Serbia', '061232612', 36);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Djordje', 'Bodroza', 'Svetog Save', 'Novi Sad', 'Serbia', '065230612', 37);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Danijela', 'Mirkovic', 'Jug Bogdana', 'Beograd', 'Serbia', '062719612', 38);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Dinkic', 'Alekse Santica', 'Beograd', 'Serbia', '0614212678', 39);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Draganic', 'Dositejeva', 'Beograd', 'Serbia', '062719612', 40);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksa', 'Bodroza', 'Alekse Santica', 'Nis', 'Serbia', '0614213612', 41);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksa', 'Vukasinovic', 'Tekelijina', 'Nis', 'Serbia', '0644512612', 42);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vesna', 'Aleksandrovic', 'Dositejeva', 'Beograd', 'Serbia', '0614213612', 43);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Aleksic', 'Svetog Save', 'Novi Sad', 'Serbia', '0614213612', 44);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Draganic', 'Alekse Santica', 'Beograd', 'Serbia', '0604212612', 45);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Vukasinovic', 'Vuka Karadzica', 'Novi Sad', 'Serbia', '06943452612', 46);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Nikolic', 'Jug Bogdana', 'Novi Sad', 'Serbia', '0604212612', 47);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Aleksic', 'Dositejeva', 'Beograd', 'Serbia', '0614213612', 48);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Bodroza', 'Svetog Save', 'Nis', 'Serbia', '06943452612', 49);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Djordje', 'Aleksic', 'Tekelijina', 'Nis', 'Serbia', '0604212612', 50);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksandar', 'Mirkovic', 'Sime Milosevica', 'Novi Sad', 'Serbia', '06943452612', 51);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksandar', 'Mirkovic', 'Sime Milosevica', 'Novi Sad', 'Serbia', '0614213612', 52);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Marijana', 'Dinkic', 'Alekse Santica', 'Beograd', 'Serbia', '0614212678', 53);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksa', 'Draganic', 'Sime Milosevica', 'Nis', 'Serbia', '061232612', 54);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Djordje', 'Djordjevic', 'Fruskogorska', 'Novi Sad', 'Serbia', '062719612', 55);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksa', 'Draganic', 'Fruskogorska', 'Novi Sad', 'Serbia', '0614212678', 56);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Djordjevic', 'Vuka Karadzica', 'Beograd', 'Serbia', '065230612', 57);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Draganic', 'Svetog Save', 'Nis', 'Serbia', '0604212612', 58);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Nikolic', 'Alekse Santica', 'Nis', 'Serbia', '06943452612', 59);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Nikolic', 'Svetog Save', 'Beograd', 'Serbia', '0644512612', 60);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Danijela', 'Vukasinovic', 'Jug Bogdana', 'Beograd', 'Serbia', '06943452612', 61);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Danijela', 'Aleksandrovic', 'Sime Milosevica', 'Nis', 'Serbia', '065230612', 62);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Draganic', 'Alekse Santica', 'Nis', 'Serbia', '0644512612', 63);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Djordje', 'Aleksic', 'Dositejeva', 'Beograd', 'Serbia', '061232612', 64);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragana', 'Djordjevic', 'Tekelijina', 'Novi Sad', 'Serbia', '065230612', 65);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Draganic', 'Sime Milosevica', 'Nis', 'Serbia', '061232612', 66);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Danijela', 'Nikolic', 'Svetog Save', 'Novi Sad', 'Serbia', '061232612', 67);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vuki', 'Aleksandrovic', 'Sime Milosevica', 'Novi Sad', 'Serbia', '062719612', 68);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Vesna', 'Aleksandrovic', 'Alekse Santica', 'Beograd', 'Serbia', '065230612', 69);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Marijana', 'Aleksandrovic', 'Alekse Santica', 'Nis', 'Serbia', '0604212612', 70);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksandar', 'Mirkovic', 'Tekelijina', 'Nis', 'Serbia', '061232612', 71);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Djordjevic', 'Alekse Santica', 'Beograd', 'Serbia', '061232612', 72);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Marijana', 'Djordjevic', 'Tekelijina', 'Beograd', 'Serbia', '065230612', 73);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Aleksic', 'Vuka Karadzica', 'Beograd', 'Serbia', '065230612', 74);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Danijela', 'Aleksandrovic', 'Jug Bogdana', 'Beograd', 'Serbia', '065230612', 75);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Nikolic', 'Fruskogorska', 'Beograd', 'Serbia', '06943452612', 76);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Aleksa', 'Vukasinovic', 'Dositejeva', 'Nis', 'Serbia', '061232612', 77);
insert into personal_info (first_name, last_name, address, city, country, telephone_number, account_id ) values ('Dragan', 'Djordjevic', 'Vuka Karadzica', 'Nis', 'Serbia', '0614213612', 78);


insert into patient (insurance_number, personal_info_id) values ('15231235610', 1);
insert into patient (insurance_number, personal_info_id) values ('15231235611', 2);
insert into patient (insurance_number, personal_info_id) values ('15231235612', 3);
insert into patient (insurance_number, personal_info_id) values ('15231235613', 4);
insert into patient (insurance_number, personal_info_id) values ('15231235614', 5);
insert into patient (insurance_number, personal_info_id) values ('15231235615', 6);
insert into patient (insurance_number, personal_info_id) values ('15231235616', 7);
insert into patient (insurance_number, personal_info_id) values ('15231235617', 8);
insert into patient (insurance_number, personal_info_id) values ('15231235618', 9);
insert into patient (insurance_number, personal_info_id) values ('15231235619', 10);
insert into patient (insurance_number, personal_info_id) values ('15231235620', 11);
insert into patient (insurance_number, personal_info_id) values ('15231235621', 12);
insert into patient (insurance_number, personal_info_id) values ('15231235622', 13);
insert into patient (insurance_number, personal_info_id) values ('15231235623', 14);
insert into patient (insurance_number, personal_info_id) values ('15231235624', 15);


insert into medical_record (patient_id, height, weight, blood_type, rh_positive, left_dioptry, right_dioptry) values (1, 190, 79, 'A', true, 0.5, 0.75);
insert into medical_record (patient_id) values (2);
insert into medical_record (patient_id) values (3);
insert into medical_record (patient_id) values (4);
insert into medical_record (patient_id) values (5);
insert into medical_record (patient_id) values (6);
insert into medical_record (patient_id) values (7);
insert into medical_record (patient_id) values (8);
insert into medical_record (patient_id) values (9);
insert into medical_record (patient_id) values (10);
insert into medical_record (patient_id) values (11);
insert into medical_record (patient_id) values (12);
insert into medical_record (patient_id) values (13);
insert into medical_record (patient_id) values (14);
insert into medical_record (patient_id) values (15);

insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (1, 'Penicillin', 'MILD');
insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (1, 'Cats', 'SEVERE');
insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (1, 'Dogs', 'MODERATE');

insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (2, 'Dogs', 'SEVERE');

insert into appointment_type (name) values ('Pregled uha');
insert into appointment_type (name) values ('Pregled grla');
insert into appointment_type (name) values ('Pregled nosa');
insert into appointment_type (name) values ('Pregled srca');
insert into appointment_type (name) values ('Pregled pluca');
insert into appointment_type (name) values ('Operacija srca');
insert into appointment_type (name) values ('Operacija kuka');

insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 1', 'Jug Bogdana', 'Beograd', 'Serbia', 'Quisque quis efficitur justo. Cras id
dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec, tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar eros.', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 2', 'Jug Bogdana', 'Beograd', 'Serbia', 'Quisque quis efficitur justo. Cras id
dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec, tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar eros.', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 3', 'Svetog Save', 'Beograd', 'Serbia', 'Quisque quis efficitur justo. Cras id
dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec, tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar eros.', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 4', 'Fruskogorska', 'Beograd', 'Serbia', 'Quisque quis efficitur justo. Cras id dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec,
tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar
eros.', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 5', 'Jug Bogdana', 'Beograd', 'Serbia', 'Quisque quis efficitur justo. Cras id
dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec, tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar eros.', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 6', 'Tekelijina', 'Beograd', 'Serbia', 'Quisque quis efficitur justo. Cras id dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec, tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar eros.', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 7', 'Dositejeva', 'Nis', 'Serbia', 'Quisque quis efficitur justo. Cras id dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec, tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar eros.', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 8', 'Alekse Santica', 'Beograd', 'Serbia', 'Quisque quis efficitur justo. Cras
id dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec, tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar eros.', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 9', 'Fruskogorska', 'Beograd', 'Serbia', 'Quisque quis efficitur justo. Cras id dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec,
tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar
eros.', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 10', 'Dositejeva', 'Novi Sad', 'Serbia', 'Quisque quis efficitur justo. Cras id dignissim sem, vitae consectetur felis. Aliquam eu eros congue, suscipit erat nec,
tincidunt elit. Integer hendrerit mollis finibus. Sed at pharetra erat, ut pulvinar
eros.', 0.0, 0);

insert into clinic_admin (personal_info_id, clinic_id) values (65,1);
insert into clinic_admin (personal_info_id, clinic_id) values (66,1);
insert into clinic_admin (personal_info_id, clinic_id) values (67,2);
insert into clinic_admin (personal_info_id, clinic_id) values (68,3);
insert into clinic_admin (personal_info_id, clinic_id) values (69,4);
insert into clinic_admin (personal_info_id, clinic_id) values (70,5);
insert into clinic_admin (personal_info_id, clinic_id) values (71,5);
insert into clinic_admin (personal_info_id, clinic_id) values (72,6);
insert into clinic_admin (personal_info_id, clinic_id) values (73,7);
insert into clinic_admin (personal_info_id, clinic_id) values (74,8);
insert into clinic_admin (personal_info_id, clinic_id) values (75,9);
insert into clinic_admin (personal_info_id, clinic_id) values (76,10);

insert into clinic_room (name, number, clinic_id, deleted) values ('soba1a', 1, 1, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba2a', 1, 2, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba3a', 1, 3, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba4a', 1, 4, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba5a', 1, 5, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba6a', 1, 6, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba7a', 1, 7, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba8a', 1, 8, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba9a', 1, 9, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba10a', 1, 10, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba1b', 2, 1, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba1c', 3, 1, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba2b', 2, 2, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba2c', 3, 2, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba2d', 4, 2, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba2e', 5, 2, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba3b', 2, 3, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba3c', 3, 3, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba4b', 2, 4, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba4c', 3, 4, false);

insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (16, 1, '3:00', '13:00', 10, 0.0, 0, 3, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (17, 1, '7:00', '19:00', 12, 0.0, 0, 4, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (18, 1, '2:00', '10:00', 8, 0.0, 0, 3, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (19, 1, '1:00', '7:00', 6, 0.0, 0, 4, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (20, 1, '4:00', '13:00', 9, 0.0, 0, 6, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (21, 1, '17:00', '5:00', 12, 0.0, 0, 6, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (22, 2, '5:00', '13:00', 8, 0.0, 0, 5, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (23, 2, '14:00', '23:00', 9, 0.0, 0, 6, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (24, 2, '1:00', '11:00', 10, 0.0, 0, 1, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (25, 2, '17:00', '2:00', 9, 0.0, 0, 2, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (26, 2, '22:00', '8:00', 10, 0.0, 0, 6, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (27, 2, '20:00', '4:00', 8, 0.0, 0, 5, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (28, 2, '4:00', '11:00', 7, 0.0, 0, 4, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (29, 3, '5:00', '11:00', 6, 0.0, 0, 6, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (30, 3, '20:00', '7:00', 11, 0.0, 0, 2, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (31, 3, '5:00', '15:00', 10, 0.0, 0, 2, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (32, 3, '1:00', '13:00', 12, 0.0, 0, 1, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (33, 3, '15:00', '21:00', 6, 0.0, 0, 4, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (34, 4, '1:00', '7:00', 6, 0.0, 0, 4, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (35, 4, '16:00', '1:00', 9, 0.0, 0, 1, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (36, 4, '10:00', '20:00', 10, 0.0, 0, 1, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (37, 5, '9:00', '20:00', 11, 0.0, 0, 3, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (38, 5, '19:00', '3:00', 8, 0.0, 0, 2, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (39, 5, '7:00', '18:00', 11, 0.0, 0, 6, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (40, 6, '21:00', '7:00', 10, 0.0, 0, 7, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (41, 6, '8:00', '18:00', 10, 0.0, 0, 3, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (42, 6, '12:00', '23:00', 11, 0.0, 0, 7, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (43, 6, '0:00', '9:00', 9, 0.0, 0, 4, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (44, 7, '13:00', '1:00', 12, 0.0, 0, 4, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (45, 9, '1:00', '13:00', 12, 0.0, 0, 7, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (46, 9, '6:00', '13:00', 7, 0.0, 0, 3, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (47, 9, '1:00', '9:00', 8, 0.0, 0, 2, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (48, 10, '11:00', '18:00', 7, 0.0, 0, 5, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (49, 10, '2:00', '10:00', 8, 0.0, 0, 2, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (50, 10, '14:00', '1:00', 11, 0.0, 0, 1, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (51, 10, '12:00', '21:00', 9, 0.0, 0, 1, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (52, 10, '1:00', '7:00', 6, 0.0, 0, 7, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (53, 10, '10:00', '21:00', 11, 0.0, 0, 3, false);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 3, 2500.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 4, 1000.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 6, 1200.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 5, 1300.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 2, 1800.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 1, 1600.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 7, 500.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (2, 5, 2300.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (2, 6, 800.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (2, 1, 2100.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (2, 2, 2800.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (2, 4, 1700.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (3, 6, 2200.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (3, 2, 900.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (3, 1, 600.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (3, 4, 1300.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (4, 4, 2100.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (4, 1, 500.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (5, 3, 2700.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (5, 2, 2600.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (5, 6, 1600.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (6, 7, 700.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (6, 3, 500.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (6, 4, 2100.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (7, 4, 2800.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (9, 7, 3000.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (9, 3, 600.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (9, 2, 2200.0);

insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (54, 1, '8:00', '17:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (55, 1, '9:00', '16:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (56, 2, '18:00', '4:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (57, 3, '13:00', '0:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (58, 4, '10:00', '21:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (59, 5, '10:00', '19:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (60, 6, '18:00', '4:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (61, 7, '10:00', '17:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (62, 8, '23:00', '8:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (63, 9, '5:00', '17:00');
insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (64, 1, '1:00', '7:00');


insert into diagnosis (name) values ('Konjuktivitis');
insert into diagnosis (name) values ('Prelom ruke');
insert into diagnosis (name) values ('Bronhitis');
insert into diagnosis (name) values ('Grip');
insert into diagnosis (name) values ('Kardiovaskularna bolest');
insert into diagnosis (name) values ('Devijacija nosa');
insert into diagnosis (name) values ('Aritmija');

insert into drug (name) value ('Aspirin');
insert into drug (name) value ('Andol');
insert into drug (name) value ('Brufen');
insert into drug (name) value ('Bensedin');
insert into drug (name) value ('Amoksicilin');
insert into drug (name) value ('Doksiciklin');
insert into drug (name) value ('Fervex');
insert into drug (name) value ('Nimulid');


insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999999, '2020-03-03 02:00:00', 1, 1, 1, 2000.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999999, 1);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (1, null, 1, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999998, '2020-03-03 03:00:00', 1, 2, 1, 2000.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999998, 1);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (2, 1, 2, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999997, '2020-03-03 04:00:00', 1, 3, 1, 1000.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999997, 1);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (3, 1, 3, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999996, '2020-03-03 05:00:00', 1, 1, 1, 2000.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999996, 1);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (1, null, 4, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999995, '2020-03-03 06:00:00', 1, 4, 1, 2000.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999995, 1);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (2, 1, 5, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999994, '2020-03-03 07:00:00', 1, 5, 1, 2500.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999994, 2);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (5, null, 6, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999993, '2020-03-03 08:00:00', 1, 6, 1, 2000.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999993, 2);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (4, 1, 7, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999992, '2020-03-03 09:00:00', 1, 1, 7, 2300.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999992, 2);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (3, null, 1, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999991, '2020-04-10 02:00:00', 1, 2, 1, 2000.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999991, 2);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (1, 1, 2, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999990, '2020-04-10 03:00:00', 1, 1, 1, 2200.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999990, 2);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (6, null, 1, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999989, '2020-04-10 04:00:00', 1, 2, 1, 2000.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999989, 3);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (5, 1, 2, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999988, '2020-04-10 05:00:00', 1, 1, 1, 2100.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999988, 3);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (2, null, 8, 0);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999987, '2020-04-10 06:00:00', 1, 1, 1, 2000.0);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Opis pregleda lorem ipsum sit dolor nesto', 9999987, 3);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (3, 1, 9, 0);

insert into clinic_review (clinic_id, patient_id, rating, can_review) values (1, 1, 2.0, true);
insert into clinic_review (clinic_id, patient_id, rating, can_review) values (1, 2, 3.5, true);

insert into clinic_review (clinic_id, patient_id, rating, can_review) values (1, 3, 5.0, false);
insert into clinic_review (clinic_id, patient_id, rating, can_review) values (1, 4, 3.0, true);

insert into clinic_review (clinic_id, patient_id, rating, can_review) values (1, 5, 4.5, false);
insert into clinic_review (clinic_id, patient_id, rating, can_review) values (2, 1, 2.5, true);


insert into doctor_review (doctor_id, patient_id, rating, can_review) values (1, 1, 4.5, true);
insert into doctor_review (doctor_id, patient_id, rating, can_review) values (2, 1, 3.5, false);
insert into doctor_review (doctor_id, patient_id, rating, can_review) values (1, 2, 4.0, true);
insert into doctor_review (doctor_id, patient_id, rating, can_review) values (1, 3, 2.5, false);
insert into doctor_review (doctor_id, patient_id, rating, can_review) values (2, 5, 1.0, false);
insert into doctor_review (doctor_id, patient_id, rating, can_review) values (2, 7, 4.0, false);


insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-14 22:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-14 23:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-15 00:00:00');

insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-14 22:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-14 23:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 02:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 03:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 04:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 05:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 06:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 07:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 08:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 09:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 10:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 11:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 12:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 13:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 14:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 15:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 16:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 17:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 18:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 19:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 20:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 21:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 22:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-15 23:00:00');

insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-14 22:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-14 23:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 02:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 03:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 04:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 05:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 06:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 07:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 08:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 09:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 10:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 11:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 12:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 13:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 14:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 15:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 16:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 17:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 18:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 19:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 20:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 21:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 22:00:00');
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-15 23:00:00');


insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (10, '2020-06-24 22', 1800.0, 2, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999099, 10, '2020-06-24 22', 5, 1);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-24 22');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (17, '2020-06-28 20', 1100.0, 3, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999098, 17, '2020-06-28 20', 5, 2);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (3, '2020-06-28 20');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (14, '2020-06-28 06', 600.0, 3, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999097, 14, '2020-06-28 06', 5, 3);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (3, '2020-06-28 06');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (3, '2020-06-20 00', 900.0, 1, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999096, 3, '2020-06-20 00', 5, 4);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-20 00');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (6, '2020-06-28 18', 800.0, 2, 1, 0.1, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999095, 6, '2020-06-28 18', 5, 5);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-28 18');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (18, '2020-06-20 20', 1900.0, 4, 1, 0.0, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999094, 18, '2020-06-20 20', 5, 6);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-20 20');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (6, '2020-06-23 02', 1900.0, 2, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999093, 6, '2020-06-23 02', 5, 7);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-23 02');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (7, '2020-06-29 20', 1000.0, 2, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999092, 7, '2020-06-29 20', 5, 8);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-29 20');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (15, '2020-06-22 00', 900.0, 3, 1, 0.3, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999091, 15, '2020-06-22 00', 5, 9);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (3, '2020-06-22 00');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (4, '2020-06-24 06', 1500.0, 1, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999090, 4, '2020-06-24 06', 5, 10);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-24 06');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (3, '2020-06-29 22', 600.0, 1, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999089, 3, '2020-06-29 22', 5, 11);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-29 22');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (20, '2020-06-29 15', 1300.0, 4, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999088, 20, '2020-06-29 15', 5, 12);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-29 15');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (18, '2020-06-20 20', 1700.0, 4, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999087, 18, '2020-06-20 20', 5, 13);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-20 20');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (12, '2020-06-25 15', 600.0, 2, 1, 0.1, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999086, 12, '2020-06-25 15', 5, 14);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-25 15');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (20, '2020-06-21 06', 1400.0, 4, 1, 0.3, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999085, 20, '2020-06-21 06', 5, 15);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-21 06');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (13, '2020-06-23 00', 900.0, 3, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999084, 13, '2020-06-23 00', 5, 16);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (3, '2020-06-23 00');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (2, '2020-06-21 10', 500.0, 1, 1, 0.0, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999083, 2, '2020-06-21 10', 5, 17);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-21 10');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (18, '2020-06-22 01', 500.0, 4, 1, 0.1, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999082, 18, '2020-06-22 01', 5, 18);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-22 01');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (3, '2020-06-29 11', 1700.0, 1, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999081, 3, '2020-06-29 11', 5, 19);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-29 11');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (16, '2020-06-22 04', 1900.0, 3, 1, 0.3, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999080, 16, '2020-06-22 04', 5, 20);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (3, '2020-06-22 04');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (18, '2020-06-21 23', 1500.0, 4, 1, 0.0, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999079, 18, '2020-06-21 23', 5, 21);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-21 23');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (16, '2020-06-26 02', 1600.0, 3, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999078, 16, '2020-06-26 02', 5, 22);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (3, '2020-06-26 02');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (9, '2020-06-28 18', 1700.0, 2, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999077, 9, '2020-06-28 18', 5, 23);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-28 18');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (2, '2020-06-23 08', 1500.0, 1, 1, 0.0, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999076, 2, '2020-06-23 08', 5, 24);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-23 08');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (4, '2020-06-22 20', 1200.0, 1, 1, 0.3, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999075, 4, '2020-06-22 20', 5, 25);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-22 20');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (10, '2020-06-28 05', 500.0, 2, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999074, 10, '2020-06-28 05', 5, 26);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-28 05');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (10, '2020-06-29 07', 1600.0, 2, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999073, 10, '2020-06-29 07', 5, 27);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-29 07');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (9, '2020-06-23 00', 1100.0, 2, 1, 0.3, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999072, 9, '2020-06-23 00', 5, 28);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-23 00');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (8, '2020-06-26 04', 1700.0, 2, 1, 0.1, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999071, 8, '2020-06-26 04', 5, 29);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-26 04');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (6, '2020-06-25 09', 500.0, 2, 1, 0.1, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999070, 6, '2020-06-25 09', 5, 30);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-25 09');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (19, '2020-06-23 17', 1900.0, 4, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999069, 19, '2020-06-23 17', 5, 31);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-23 17');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (3, '2020-06-22 10', 1200.0, 1, 1, 0.3, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999068, 3, '2020-06-22 10', 5, 32);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-22 10');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (9, '2020-06-20 15', 1300.0, 2, 1, 0.0, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999067, 9, '2020-06-20 15', 5, 33);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-20 15');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (18, '2020-06-26 17', 600.0, 4, 1, 0.4, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999066, 18, '2020-06-26 17', 5, 34);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-26 17');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (15, '2020-06-24 19', 700.0, 3, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999065, 15, '2020-06-24 19', 5, 35);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (3, '2020-06-24 19');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (5, '2020-06-24 08', 500.0, 1, 1, 0.1, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999064, 5, '2020-06-24 08', 5, 36);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-24 08');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (6, '2020-06-26 09', 1000.0, 2, 1, 0.2, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999063, 6, '2020-06-26 09', 5, 37);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-26 09');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (18, '2020-06-21 06', 1300.0, 4, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999062, 18, '2020-06-21 06', 5, 38);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-21 06');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (4, '2020-06-29 09', 600.0, 1, 1, 0.4, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999061, 4, '2020-06-29 09', 5, 39);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-29 09');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (2, '2020-06-22 22', 1800.0, 1, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999060, 2, '2020-06-22 22', 5, 40);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-22 22');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (16, '2020-06-27 09', 1100.0, 3, 1, 0.3, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999059, 16, '2020-06-27 09', 5, 41);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (3, '2020-06-27 09');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (11, '2020-06-28 22', 1200.0, 2, 1, 0.1, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999058, 11, '2020-06-28 22', 5, 42);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-28 22');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (11, '2020-06-29 11', 1100.0, 2, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999057, 11, '2020-06-29 11', 5, 43);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-29 11');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (1, '2020-06-25 20', 1800.0, 1, 1, 0.3, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999056, 1, '2020-06-25 20', 5, 44);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-25 20');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (18, '2020-06-21 23', 1700.0, 4, 1, 0.0, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999055, 18, '2020-06-21 23', 5, 45);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (4, '2020-06-21 23');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (12, '2020-06-27 04', 1500.0, 2, 1, 0.0, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999054, 12, '2020-06-27 04', 5, 46);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-27 04');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (4, '2020-06-23 12', 1900.0, 1, 1, 0.1, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999053, 4, '2020-06-23 12', 5, 47);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-23 12');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (2, '2020-06-27 15', 1600.0, 1, 1, 0.0, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999052, 2, '2020-06-27 15', 5, 48);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-27 15');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (3, '2020-06-21 06', 1400.0, 1, 1, 0.5, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999051, 3, '2020-06-21 06', 5, 49);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (1, '2020-06-21 06');
insert into predefined_appointment (doctor_id, start_time, price, clinic_room_id, appointment_type_id, discount, duration) values (10, '2020-06-23 10', 900.0, 2, 1, 0.3, 1.0);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (999050, 10, '2020-06-23 10', 5, 50);
insert into clinic_room_schedule_item (clinic_room_id, start_time) values (2, '2020-06-23 10');


insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999986, '2020-08-10 06:00:00', 1, 1, 1, 2000.0);
insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (1001, 9999986, 1, '2020-08-10 06:00:00', 1);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999985, '2020-08-10 07:00:00', 1, 1, 1, 2000.0);
insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (1002, 9999985, 1, '2020-08-10 07:00:00', 2);
-- insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999984, '2021-08-10 05:00:00', 1, 1, 1, 5600.0);
-- insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (1003, 9999984, 1, '2021-08-10 05:00:00', 3);
-- insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999983, '2020-08-11 03:00:00', 1, 1, 1, 4000.0);
-- insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (1004, 9999983, 1, '2020-08-11 03:00:00', 4);
-- insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999982, '2020-08-10 05:00:00', 1, 1, 1, 35000.0);
-- insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (1005, 9999982, 1, '2020-08-10 05:00:00',5);
-- insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999981, '2020-08-11 06:00:00', 1, 1, 1, 35000.0);
-- insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (1006, 9999981, 1, '2020-08-11 06:00:00', 6);
-- insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999980, '2020-11-10 06:00:00', 1, 1, 1, 35000.0);
-- insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (1007, 9999980, 1, '2020-11-10 06:00:00', 7);
-- insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (9999979, '2020-11-10 01:00:00', 1, 1, 1, 35000.0);
-- insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (1000, 9999979, 1, '2020-11-10 01:00:00', 8);

insert into appointment_request (doctor, patient, price, start_time, type) values (1, 1, 200, '2020-06-15 15:00:00', 'APPOINTMENT');
insert into appointment_request (doctor, patient, price, start_time, type) values (2, 1, 500, '2020-06-17 09:00:00', 'OPERATION');
insert into appointment_request (doctor, patient, price, start_time, type) values (3, 1, 2000, '2020-06-16 15:00:00', 'APPOINTMENT');
insert into appointment_request (doctor, patient, price, start_time, type) values (4, 1, 400, '2020-06-22 09:00:00', 'OPERATION');
insert into appointment_request (doctor, patient, price, start_time, type) values (4, 1, 400, '2020-07-22 09:00:00', 'OPERATION');
insert into appointment_request (doctor, patient, price, start_time, type) values (4, 1, 9000, '2020-07-22 09:00:00', 'OPERATION');
insert into appointment_request (doctor, patient, price, start_time, type) values (4, 1, 800, '2020-08-24 09:00:00', 'OPERATION');
insert into appointment_request (doctor, patient, price, start_time, type) values (4, 1, 500, '2020-09-24 09:00:00', 'APPOINTMENT');
insert into appointment_request (doctor, patient, price, start_time, type) values (4, 1, 500, '2020-09-22 09:00:00', 'APPOINTMENT');
insert into appointment_request (doctor, patient, price, start_time, type) values (4, 1, 400, '2020-08-25 09:00:00', 'OPERATION');

insert into appointment_request (doctor, patient, price, start_time, type) values (3, 1, 200, '2020-06-15 15:00:00', 'APPOINTMENT');
insert into appointment_request (doctor, patient, price, start_time, type) values (2, 1, 200, '2020-06-17 09:00:00', 'OPERATION');

insert into registration_request (email, password, first_name, last_name, address, city, country, telephone_number, insurance_number) values ('p20@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Andrej', 'Petrovic', 'Ulica 1', 'Beograd', 'Serbia', '0601231233', '14115151553');

insert into leave_request (start_date, end_date, doctor_id, schedule_item_type) values ('2020-06-15 15:00:00', '2020-06-17 15:00:00', 1, 1);