insert into roles(id, rolename) values (0, 'USER'), (1, 'ADMIN'), (2, 'BEHANDELAAR');
insert into users(id, username, password) values (0, 'ADMIN', '$2a$10$nFpBJtQSwdneAM67MrPv5O54Q0T4GZyTIiyfCzcdkQvFyh4zQ2qWK');
insert into users_roles(users_id, roles_id) values (0, 1);

--Entries for testdata below
-- Test Users --
insert into users(id, username, password) values (3, 'Testuser1', 'Welkom01');
insert into users(id, username, password) values (4, 'Testuser2', 'Welkom01');
insert into users(id, username, password) values (5, 'Testuser3', 'Welkom01');
-- Add roles to users --
insert into users_roles(users_id, roles_id) values (3, 0);
insert into users_roles(users_id, roles_id) values (4, 2);
insert into users_roles(users_id, roles_id) values (5, 1);

-- Teacher Entries --
insert into Teachers(teacher_id, first_name, last_name, task_amount) values (1, 'testTeacher1', 'lasLow1', 2);
insert into Teachers(teacher_id, first_name, last_name, task_amount) values (2, 'testTeacher2', 'nottingham', 3);
insert into Teachers(teacher_id, first_name, last_name, task_amount) values (3, 'testTeacher3', 'london', 0);
insert into Teachers(teacher_id, first_name, last_name, task_amount) values (4, 'testTeacher4', 'solstis', 15);
insert into Teachers(teacher_id, first_name, last_name, task_amount) values (5, 'testTeacher5', 'glashow', 2);
insert into Teachers(teacher_id, first_name, last_name, task_amount) values (6, 'testTeacher6', 'Arnhem', 0);

-- Afwezig Entries --
insert into Afwezig(afwezig_id, reason, start_date, end_date, teacher_Id) values (1, 'vacation', '2024-02-06', '2024-02-16', 5);
insert into Afwezig(afwezig_id, reason, start_date, end_date, teacher_Id) values (2, 'private', '2024-01-10', '2024-01-10', 6);

-- Parent Entries --
insert into Parents(parent_id, first_name, last_name, phone_number, address, country_of_origin, spoken_language) values (1, 'testParent1', 'Groningen', '0612345678', 'ringelweg 1','Nederland', 'Nederlands');
insert into Parents(parent_id, first_name, last_name, phone_number, address, country_of_origin, spoken_language) values (2, 'testParent2', 'Leeuwarden', '0631232358', 'Desideriusstraat 20','Nederland', 'Nederlands');
insert into Parents(parent_id, first_name, last_name, phone_number, address, country_of_origin, spoken_language) values (3, 'testParent3', 'Den Burg', '069281735', 'Applicatiestraat 10','Nederland', 'Nederlands');
insert into Parents(parent_id, first_name, last_name, phone_number, address, country_of_origin, spoken_language) values (4, 'testParent4', 'Cocksdorp', '0637293846', 'rapunzelweg 120','Servie', 'Nederlands');
insert into Parents(parent_id, first_name, last_name, phone_number, address, country_of_origin, spoken_language) values (5, 'testParent5', 'Nijmegen', '0678549628', 'De Koog 3','Nederland', 'Nederlands');
insert into Parents(parent_id, first_name, last_name, phone_number, address, country_of_origin, spoken_language) values (6, 'testParent6', 'Doesburg', '0646321987', 'Einsteinlaan 210','Nederland', 'Nederlands');
insert into Parents(parent_id, first_name, last_name, phone_number, address, country_of_origin, spoken_language) values (7, 'testParent7', 'Rheden', '0648615368', 'Jupiterweg 20','Engeland', 'Engels');
insert into Parents(parent_id, first_name, last_name, phone_number, address, country_of_origin, spoken_language) values (8, 'testParent8', 'Amsterdam', '0645136975', 'Databankweg 2','Brazilie', 'Portugees');

-- Child Entries --
insert into Childs(child_id, first_name, last_name, dob, address, country_of_origin, spoken_language, allergies, starting_date, parent_id) values (1, 'testChild1', 'Nijmegen', '2020-02-13', 'linglaan 20, 7218AB, Apeldoorn', 'Nederland', 'Nederlands', '["Gluten", "Ananas"]',' 2024-02-13', 5);
insert into Childs(child_id, first_name, last_name, dob, address, country_of_origin, spoken_language, allergies, starting_date, parent_id) values (2, 'testChild2', 'Doesburg', '2020-01-05', 'rapunzelweg 19, 8910AB, Doesburg', 'Nederland', 'Nederlands', '["Noten"]', '2024-01-23', 6);
insert into Childs(child_id, first_name, last_name, dob, address, country_of_origin, spoken_language, allergies, starting_date, parent_id) values (3, 'testChild3', 'Rheden', '2020-03-12', 'regulantweg 10, 2311PB, Horst', 'Engeland', 'Engels', '[]', '2024-03-29', 7);
insert into Childs(child_id, first_name, last_name, dob, address, country_of_origin, spoken_language, allergies, starting_date, parent_id) values (4, 'testChild4', 'Amsterdam', '2020-02-19', 'recallaan 190, 1402CD, Nijmegen', 'Brazilie', 'Portugees', '["Zuivel"]', '2024-02-01', 8);

-- Task Entries --
insert into Tasks(task_id, status, due_date, assigned, teacher_id, child_id) values (1, 'NEW', '2024-03-10', TRUE, 1, 1);
insert into Tasks(task_id, status, due_date, assigned, teacher_id, child_id) values (2, 'PICKEDUP', '2024-03-21', TRUE, 2, 2);
insert into Tasks(task_id, status, due_date, assigned, teacher_id, child_id) values (3, 'OVERDUE', '2024-01-08', FALSE, 3, 3);
insert into Tasks(task_id, status, due_date, assigned, teacher_id, child_id) values (4, 'CLOSED', '2024-05-20', TRUE, 4, 4);

-- Opmerking Entries --
insert into Opmerkingen(opmerkingen_id, date_of_contact, opmerking, task_id) values (1, '2024-08-25', 'Meer middelen nodig', 4);
insert into Opmerkingen(opmerkingen_id, date_of_contact, opmerking, task_id) values (2, '2024-03-13', 'Vertraging door leveranciers', 3);
insert into Opmerkingen(opmerkingen_id, date_of_contact, opmerking, task_id) values (3, '2024-08-16', 'Meer middelen nodig', 1);
insert into Opmerkingen(opmerkingen_id, date_of_contact, opmerking, task_id) values (4, '2024-06-24', 'Klant is tevreden', 2);

-- File Entries --
-- Stap 1: Zet oid om naar hexadecimale tekenreeks
ALTER TABLE Files
ALTER COLUMN data TYPE text;

-- Stap 2: Cast de hexadecimale tekenreeks naar bytea
UPDATE Files
SET data = decode('48656C6C6F20576F726C64', 'hex');

-- Stap 3: Verander de kolomtype naar bytea
ALTER TABLE Files
ALTER COLUMN data TYPE bytea USING data::bytea;

-- Stap 4: Voeg de nieuwe rij toe met gegevens voor de "data" kolom in hexadecimale vorm
INSERT INTO Files(id, file_name, file_type, data, parent_type, parent_id)
VALUES (1, 'welkom', '.txt', decode('48656C6C6F20576F726C64', 'hex'), 'Opmerking', 2);
