insert into authority (id, name) values (1, 'ROLE_PATIENT');
insert into authority (id, name) values (2, 'ROLE_DOCTOR');
insert into authority (id, name) values (3, 'ROLE_NURSE');
insert into authority (id, name) values (4, 'ROLE_CLINIC_ADMIN');
insert into authority (id, name) values (5, 'ROLE_CLINIC_CENTER_ADMIN');

insert into account (email, password, password_changed) values ('admin@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false);
insert into account (email, password, first_name, last_name, address, password_changed) values ('a@gmail.com','$2a$10$sDokUwr/iMtuvtHNlxQk7ey1cbs877yXckksUtbpw/0c1.At6pNHu', 'Marko', 'markov', 'aa', false); -- adminadmin1
insert into account (email, password, first_name, last_name, address, password_changed) values ('b@gmail.com','$2a$10$XPPm7IJJo1x8X6jKxSxYNefG9nirIjI8lJQDxvtNIgKyevPiE1Qx2', 'Dragan', 'Arsic', 'aa', false);
insert into account (email, password, first_name, last_name, address, password_changed) values ('c@gmail.com','$2a$10$sDokUwr/iMtuvtHNlxQk7ey1cbs877yXckksUtbpw/0c1.At6pNHu', 'Dusan', 'Susic', 'aa', false); -- adminadmin1
insert into account (email, password, password_changed) values ('patient@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true);


insert into account_authority (user_id, authority_id) values (1, 5);
insert into account_authority (user_id, authority_id) values (2, 2);
insert into account_authority (user_id, authority_id) values (3, 2);
insert into account_authority (user_id, authority_id) values (4, 4);
insert into account_authority (user_id, authority_id) values (5, 1);

insert into medical_record (id) values (1);
insert into medical_record (id) values (2);

insert into patient (insurance_number, account_id, medical_record_id) values ('14115151555', 2, 1);
insert into patient (insurance_number, account_id, medical_record_id) values ('14115151252', 3, 2);