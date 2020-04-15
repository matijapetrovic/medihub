insert into tests (id, message) values (1, 'Hello world from database');

insert into authorities (id, name) values (1, 'ROLE_PATIENT');
insert into authorities (id, name) values (2, 'ROLE_DOCTOR');
insert into authorities (id, name) values (3, 'ROLE_NURSE');
insert into authorities (id, name) values (4, 'ROLE_CLINIC_ADMIN');
insert into authorities (id, name) values (5, 'ROLE_CLINIC_CENTER_ADMIN');

insert into accounts (id, email, password, password_changed) values (1, 'admin@gmail.com', '$2a$10$4pYGWyCOxqmIo3OkFIXEweRzvbf6JQdiRZrZz8aRNDp8hbjy9pPxu', false);

insert into account_authority (user_id, authority_id) values (1, 5);