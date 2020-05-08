insert into authority (id, name) values (1, 'ROLE_PATIENT');
insert into authority (id, name) values (2, 'ROLE_DOCTOR');
insert into authority (id, name) values (3, 'ROLE_NURSE');
insert into authority (id, name) values (4, 'ROLE_CLINIC_ADMIN');
insert into authority (id, name) values (5, 'ROLE_CLINIC_CENTER_ADMIN');

insert into account (email, password, country, password_changed) values ('patient@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Serbia', true);
insert into account (email, password, first_name, last_name, address, password_changed) values ('doctor@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Dragan', 'Arsic', 'aa', false);
insert into account (email, password, first_name, last_name, address, password_changed) values ('nurse@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Marko', 'markov', 'aa', false);
insert into account (email, password, first_name, last_name, address, password_changed) values ('clinicadmin@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Dusan', 'Susic', 'aa', false);
insert into account (email, password, password_changed) values ('admin@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false); -- adminadmin1
insert into account (email, password, first_name, last_name, address, password_changed, telephone_number) values ('d1@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Marko', 'markov', 'aa', false, '06951521616');
insert into account (email, password, first_name, last_name, address, password_changed, telephone_number) values ('d2@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Marijan', 'Gagic', 'aa', false, '06951521616');

insert into account_authority (user_id, authority_id) values (1, 1);
insert into account_authority (user_id, authority_id) values (2, 2);
insert into account_authority (user_id, authority_id) values (3, 3);
insert into account_authority (user_id, authority_id) values (4, 4);
insert into account_authority (user_id, authority_id) values (5, 5);
insert into account_authority (user_id, authority_id) values (6, 2);
insert into account_authority (user_id, authority_id) values (7, 2);

insert into clinic (name, address, city, country, description) values ('Klinika 1', 'asf', 'adgsdg', 'Serbia', 'asgadg');
insert into clinic (name, address, city, country, description) values ('Klinika 2', 'asf', 'adgsdg', 'Serbia', 'asgadg');

insert into clinic_admin(account, clinic) values (4, 1);

insert into medical_record (id) values (1);
insert into medical_record (id) values (2);

insert into working_calendar (id) values (1);
insert into working_calendar (id) values (2);

insert into patient (insurance_number, account_id, medical_record_id) values ('14115151555', 3  , 1);
insert into patient (insurance_number, account_id, medical_record_id) values ('14115151551', 2  , 2);
insert into medical_doctor (account_id, working_calendar, clinic, working_time_from, working_time_to) values (6, 1, 1, '06:00:00', '14:00:00');
insert into medical_doctor (account_id, working_calendar, clinic, working_time_from, working_time_to) values (7, 2, 2, '18:00:00', '01:00:00');

insert into appointment_type(name) values ('type1');
insert into appointment_type(name) values ('type2');
insert into appointment_type(name) values ('type3');