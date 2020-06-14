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

insert into account (email, password, password_changed, activated) values ('patient@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('doctor@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('nurse@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('clinicadmin@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('admin@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false, true);
insert into account (email, password, password_changed, activated) values ('d1@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', true, true);
insert into account (email, password, password_changed, activated) values ('d2@gmail.com','$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false, true);

insert into account (email, password, password_changed, activated) values ('p1@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 1, true);
insert into account (email, password, password_changed, activated) values ('p2@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 1, true);
insert into account (email, password, password_changed, activated) values ('d3@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 1, true);

insert into account_authority (user_id, authority_id) values (1, 1);
insert into account_authority (user_id, authority_id) values (2, 2);
insert into account_authority (user_id, authority_id) values (3, 3);
insert into account_authority (user_id, authority_id) values (4, 4);
insert into account_authority (user_id, authority_id) values (5, 5);
insert into account_authority (user_id, authority_id) values (6, 2);
insert into account_authority (user_id, authority_id) values (7, 2);
insert into account_authority (user_id, authority_id) values (8, 1);
insert into account_authority (user_id, authority_id) values (9, 1);

insert into personal_info (account_id, country ) values (1, 'Serbia');
insert into personal_info (first_name, last_name, address, account_id) values ('Dragan', 'Arsic', 'aa', 2);
insert into personal_info (first_name, last_name, address, account_id) values ('Marko', 'markov', 'aa', 3);
insert into personal_info (first_name, last_name, address, account_id) values ('Dusan', 'Susic', 'aa', 4);
insert into personal_info (account_id) values (5);
insert into personal_info (first_name, last_name, address, telephone_number, account_id ) values ('Marko', 'markov', 'aa', '06951521615', 6);
insert into personal_info (first_name, last_name, address, telephone_number, account_id) values ('Marijan', 'Gagic', 'aa', '06951521616' , 7);
insert into personal_info (first_name, last_name, address, city, country, account_id) values ('Andrej', 'Petrovic', 'Ulica 1', 'Beograd', 'Serbia', 8);
insert into personal_info (first_name, last_name, address, city, country, account_id) values ('Milan', 'Milanovic', 'Ulica 2', 'Novi Sad', 'Serbia', 9);


insert into patient (insurance_number, personal_info_id) values ('14115151555', 8);
insert into patient (insurance_number, personal_info_id) values ('14115151252', 9);

insert into medical_record (patient_id, height, weight, blood_type, rh_positive, left_dioptry, right_dioptry) values (1, 190, 79, 'A', true, 0.5, 0.75);
insert into medical_record (patient_id, height, weight, blood_type, rh_positive) values (2, 170, 55, 'O', false);

insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (1, 'Penicillin', 'MILD');
insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (1, 'Cats', 'SEVERE');
insert into medical_record_allergy_mapping (medical_record_id, allergy_name, allergy_level) values (1, 'Dogs', 'MODERATE');


insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 1', 'Ulica Zmaj Jovina 20', 'Novi Sad', 'Serbia', 'asgadg', 0.0, 0);
insert into clinic (name, address, city, country, description, rating, review_count) values ('Klinika 2', 'Ulica kneza Mihaila', 'Beograd', 'Serbia', 'asgadg', 0.0, 0);

insert into clinic_room (name, number, clinic_id, deleted) values ('soba1', 1, 1, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba2', 2, 2, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba3', 3, 2, false);
insert into clinic_room (name, number, clinic_id, deleted) values ('soba4', 4, 1, false);

insert into clinic_admin(personal_info_id, clinic) values (4, 1);

insert into appointment_type(name) values ('Pregeld uha');
insert into appointment_type(name) values ('Pregled grla');
insert into appointment_type(name) values ('Pregled nosa');

insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count, specialization, archived) values (2, 1, '20:00:00', '06:00:00', 8, 0.0, 0, 3, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count,specialization, archived) values (6, 1, '20:00:00', '06:00:00', 8, 0.0, 0, 1, false);
insert into medical_doctor (personal_info_id, clinic, working_time_from, working_time_to, working_hours, rating, review_count,specialization, archived) values (7, 2, '18:00:00', '01:00:00', 7, 0.0, 0, 2, false);

insert into medical_nurse (personal_info_id, clinic, working_time_from, working_time_to) values (3, 1, '06:00:00', '14:00:00');

insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (51, CURRENT_TIMESTAMP , 1, 1, 1, 200);
insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (52, '2020-10-12 13:00:00', 1, 1, 1, 3242);
insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (10000, 52, 1, '2020-10-12 13:00:00', 1);

insert into appointment (id, start_time, clinic_room_id, patient_id, doctor_id, price) values (53, '2020-12-12 07:00:00', 1, 1, 1, 42443);
insert into appointment (id, patient_id, doctor_id, clinic_room_id, start_time, price) values (54, 1, 1, 1, '2020-10-10 20:00:00', 523);

insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 1, 500.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 2, 1500.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (1, 3, 2000.0);
insert into clinic_appointment_type_mapping (clinic_id, appointment_type_id, price) values (2, 2, 3000.0);

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


insert into medical_doctor_appointment_schedule_item (id, appointment_id, doctor_id, start_time, schedule_item_type) values (1, 51, 1, CURRENT_TIMESTAMP, 1);

-- insert into appointment_request (doctor, patient, price, date, time) values (1, 1, 1000, '2020-10-10', '00:00:00');
-- insert into appointment_request (doctor, patient, price, date, time) values (1, 1, 2500, '2020-10-10', '23:00:00');
-- insert into appointment_request (doctor, patient, price, date, time) values (1, 1, 1500, '2020-03-10', '13:00:00');
-- insert into appointment_request (doctor, patient, price, date, time) values (1, 1, 6000, '2020-03-10', '14:00:00');

insert into diagnosis (name) values ('Konjuktivitis');
insert into diagnosis (name) values ('Prelom ruke');
insert into drug (name) value ('Aspirin');
insert into drug (name) value ('Andol');

insert into finished_appointment (description, appointment_id, diagnosis_id) values ('Sad bas i nije heh', 51, 2);
insert into finished_appointment (description, appointment_id, diagnosis_id) values ('aaa', 53, 2);

insert into predefined_appointment (doctor_id, start_time, duration, price, clinic_room_id, appointment_type_id, discount) values (1, '2020-06-15 00:00:00', 3.0, 500.0, 1, 1, 0.2);
insert into medical_doctor_predefined_appointment_schedule_item (id, doctor_id, start_time, schedule_item_type, predefined_appointment_id) values (10000, 1, '2020-05-06 15:00:00', 5, 1);

insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (1, null, 1, 0);
insert into prescriptions (drug_id, medical_nurse_id, finished_appointment_id, version) values (2, 1, 1, 0);

insert into clinic_review (clinic_id, patient_id, rating, can_review) values (1, 1, 1.0, false);
insert into clinic_review (clinic_id, patient_id, rating, can_review) values (2, 1, 3.0, true);

insert into doctor_review (doctor_id, patient_id, rating, can_review) values (3, 1, 4.5, false);

insert into appointment_request (doctor, patient, price, start_time, type) values (3, 1, 200, '2020-06-15 15:00:00', 'APPOINTMENT');
insert into appointment_request (doctor, patient, price, start_time, type) values (2, 1, 200, '2020-06-17 09:00:00', 'OPERATION');

insert into registration_request (email, password, first_name, last_name, address, city, country, telephone_number, insurance_number) values ('p20@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', 'Andrej', 'Petrovic', 'Ulica 1', 'Beograd', 'Serbia', '0601231233', '14115151553');

insert into leave_request (start_date, end_date, doctor_id, schedule_item_type) values ('2020-06-15 15:00:00', '2020-06-17 15:00:00', 1, 1);