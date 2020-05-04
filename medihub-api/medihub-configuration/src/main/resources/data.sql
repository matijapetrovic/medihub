insert into tests (id, message) values (1, 'Hello world from database');

insert into authority (id, name) values (1, 'ROLE_PATIENT');
insert into authority (id, name) values (2, 'ROLE_DOCTOR');
insert into authority (id, name) values (3, 'ROLE_NURSE');
insert into authority (id, name) values (4, 'ROLE_CLINIC_ADMIN');
insert into authority (id, name) values (5, 'ROLE_CLINIC_CENTER_ADMIN');

insert into account (id, email, password, password_changed) values (1, 'admin@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false);
insert into account (id, email, password, first_name, last_name, address, password_changed) values (2, 'a@gmail.com','$2a$10$sDokUwr/iMtuvtHNlxQk7ey1cbs877yXckksUtbpw/0c1.At6pNHu', 'marko', 'markov', 'aa', false);
insert into account (id, email, password, first_name, last_name, address, password_changed) values (3, 'b@gmail.com','$2a$10$XPPm7IJJo1x8X6jKxSxYNefG9nirIjI8lJQDxvtNIgKyevPiE1Qx2', 'dragan', 'arsic', 'aa', false);

insert into account_authority (user_id, authority_id) values (1, 5);
insert into account_authority (user_id, authority_id) values (2, 2);
insert into account_authority (user_id, authority_id) values (3, 2);

insert into medical_record (id) values (1);
insert into medical_record (id) values (2);

insert into patient (id, insurance_number, account_id, medical_record_id) values (1, '14115151555', 2, 1);
insert into patient (id, insurance_number, account_id, medical_record_id) values (2, '14115151252', 3, 2);