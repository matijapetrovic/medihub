insert into authority (id, name) values (1, 'ROLE_PATIENT');
insert into authority (id, name) values (2, 'ROLE_DOCTOR');
insert into authority (id, name) values (3, 'ROLE_NURSE');
insert into authority (id, name) values (4, 'ROLE_CLINIC_ADMIN');
insert into authority (id, name) values (5, 'ROLE_CLINIC_CENTER_ADMIN');

insert into account (email, password, country, password_changed) values ('patient@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Serbia', true);
insert into account (email, password, first_name, last_name, address, password_changed) values ('doctor@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Dragan', 'Arsic', 'aa', true);
insert into account (email, password, first_name, last_name, address, password_changed) values ('nurse@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Marko', 'markov', 'aa', false);
insert into account (email, password, first_name, last_name, address, password_changed) values ('clinicadmin@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Dusan', 'Susic', 'aa', true);
insert into account (email, password, password_changed) values ('admin@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false);
insert into account (email, password, first_name, last_name, address, password_changed, telephone_number) values ('d1@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Marko', 'markov', 'aa', true, '06951521616');
insert into account (email, password, first_name, last_name, address, password_changed, telephone_number) values ('d2@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Marijan', 'Gagic', 'aa', false, '06951521616');

insert into account (email, password, first_name, last_name, address, city, country, password_changed) values ('p1@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Andrej', 'Petrovic', 'Ulica 1', 'Beograd', 'Serbia', 1);
insert into account (email, password, first_name, last_name, address, city, country, password_changed) values ('p2@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Milan', 'Milanovic', 'Ulica 2', 'Novi Sad', 'Serbia', 1);

insert into account_authority (user_id, authority_id) values (1, 1);
insert into account_authority (user_id, authority_id) values (2, 2);
insert into account_authority (user_id, authority_id) values (3, 3);
insert into account_authority (user_id, authority_id) values (4, 4);
insert into account_authority (user_id, authority_id) values (5, 5);
insert into account_authority (user_id, authority_id) values (6, 2);
insert into account_authority (user_id, authority_id) values (7, 2);
insert into account_authority (user_id, authority_id) values (8, 1);
insert into account_authority (user_id, authority_id) values (9, 1);

insert into patient (insurance_number, account_id) values ('14115151555', 8);
insert into patient (insurance_number, account_id) values ('14115151252', 9);

insert into medical_record (patient_id, height, weight, blood_type, rh_positive, left_dioptry, right_dioptry) values (1, 190, 79, 'A', true, 0.5, 0.75);
insert into medical_record (patient_id, height, weight, blood_type, rh_positive) values (2, 170, 55, 'O', false);

insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (1, 'Penicillin', 'MILD');
insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (1, 'Cats', 'SEVERE');
insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (1, 'Dogs', 'MODERATE');

insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 1', 'Ulica Zmaj Jovina 20', 'Novi Sad', 'Serbia', 'asgadg', 3.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 2', 'Ulica kneza Mihaila', 'Beograd', 'Serbia', 'asgadg', 0.0, 0);

insert into clinic_room (name, number, clinic_id) values ('soba1', 1, 1);
insert into clinic_room (name, number, clinic_id) values ('soba2', 2, 1);
insert into clinic_room (name, number, clinic_id) values ('soba3', 3, 1);
insert into clinic_room (name, number, clinic_id) values ('soba4', 4, 1);

insert into clinic_admin(account, clinic) values (4, 1);

insert into appointment_type(name) values ('Pregeld uha');
insert into appointment_type(name) values ('Pregled grla');
insert into appointment_type(name) values ('Pregled nosa');

insert into medical_doctor (account_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization) values (2, 1, '06:00:00', '14:00:00', 8, 0.0, 0, 3);
insert into medical_doctor (account_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count,specialization) values (6, 1, '06:00:00', '14:00:00', 8, 0.0, 0, 1);
insert into medical_doctor (account_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count,specialization) values (7, 2, '18:00:00', '01:00:00', 7, 0.0, 0, 2);

insert into appointment (date, time, clinic_room_id, patient_id, doctor_id) values ('2020-10-10', '12:00:00', 1, 1, 1);
insert into appointment (date, time, clinic_room_id, patient_id, doctor_id) values ('2020-10-12', '13:00:00', 1, 1, 1);
insert into appointment (date, time, clinic_room_id, patient_id, doctor_id) values ('2020-10-20', '07:00:00', 1, 1, 2);

insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 1, 500.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 2, 1500.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 3, 2000.0);

insert into clinic_room_schedule (id, clinic_room_id, date) values (1, 1, '2020-10-10');
insert into clinic_room_schedule (id, clinic_room_id, date) values (2, 2, '2020-12-10');
insert into clinic_room_schedule (id, clinic_room_id, date) values (3, 3, '2020-12-10');

-- insert into clinic_room_schedule_item (schedule_id, time) values (1, '00:00:00');
-- insert into clinic_room_schedule_item (schedule_id, time) values (1, '01:00:00');
-- insert into clinic_room_schedule_item (schedule_id, time) values (1, '23:00:00');
-- insert into clinic_room_schedule_item (schedule_id, time) values (2, '00:00:00');
-- insert into clinic_room_schedule_item (schedule_id, time) values (2, '01:00:00');
-- insert into clinic_room_schedule_item (schedule_id, time) values (2, '23:00:00');
-- insert into clinic_room_schedule_item (schedule_id, time) values (3, '01:00:00');
-- insert into clinic_room_schedule_item (schedule_id, time) values (3, '23:00:00');

insert into medical_doctor_schedule (doctor_id, date, available) values (2, '2020-10-10', true);
insert into medical_doctor_schedule (doctor_id, date, available) values (2, '2020-10-11', true);
insert into medical_doctor_schedule (doctor_id, date, available) values (2, '2020-05-22', true);
insert into medical_doctor_schedule (doctor_id, date, available) values (2, '2020-04-29', true);
insert into medical_doctor_schedule (doctor_id, date, available) values (2, '2020-03-29', true);

-- insert into medical_doctor_appointment_schedule_item (id, appointment_id, schedule_id, time, schedule_item_type) values (1, 1, 1, '20:00:00', 1);

insert into appointment (patient_id, doctor_id, clinic_room_id) values (1, 2, 1);

-- insert into appointment_request (doctor, patient, price, date, time) values (1, 1, 500, '2020-10-10', '01:00:00');
-- insert into appointment_request (doctor, patient, price, date, time) values (1, 1, 1000, '2020-10-10', '00:00:00');
-- insert into appointment_request (doctor, patient, price, date, time) values (1, 1, 2500, '2020-10-10', '23:00:00');
-- insert into appointment_request (doctor, patient, price, date, time) values (1, 1, 1500, '2020-03-10', '13:00:00');
-- insert into appointment_request (doctor, patient, price, date, time) values (1, 1, 6000, '2020-03-10', '14:00:00');

insert into diagnosis (name) values ('Konjuktivitis');
insert into diagnosis (name) values ('Prelom ruke');
insert into drug (name) value ('Aspirin');
insert into drug (name) value ('Andol');

insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Idemo niiis najjace je bilo najjace', 1, 1);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Sad bas i nije heh', 2, 2);

create trigger after_clinic_review_insert
    after insert on clinic_review for each row
    update clinic c set rating = (select avg(rating) from clinic_review  where clinic_id= c.id),
                        review_count = (select count(rating) from clinic_review where clinic_id=c.id)
    where id = NEW.clinic_id;

create trigger after_doctor_review_insert
    after insert on doctor_review for each row
    update medical_doctor md set rating = (select avg(rating) from doctor_review where doctor_id=md.id),
                        review_count = (select count(rating) from doctor_review where doctor_id=md.id)
    where id = NEW.doctor_id;