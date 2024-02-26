insert into roles(rolename) values ('USER'), ('ADMIN'), ('BEHANDELAAR');
insert into users(username, password) values ('ADMIN', '$2a$10$nFpBJtQSwdneAM67MrPv5O54Q0T4GZyTIiyfCzcdkQvFyh4zQ2qWK');
insert into users_roles(users_username, roles_rolename) values ('ADMIN', 'ADMIN');

--Entries for testdata below
-- Test Users --
insert into users(username, password) values ('Testuser1', 'Welkom01')
insert into users(username, password) values ('Testuser2', 'Welkom01')
insert into users(username, password) values ('Testuser3', 'Welkom01')
-- Add roles to users --
insert into users_roles(users_username, roles_rolename) values ('Testuser1', 'USER')
insert into users_roles(users_username, roles_rolename) values ('Testuser2', 'BEHANDELAAR')
insert into users_roles(users_username, roles_rolename) values ('Testuser3', 'ADMIN')

-- Teacher Entries --
insert into Teachers(id, firstName, lastName, taskAmount) values (1, 'testTeacher1', 'lasLow1', 2)
insert into Teachers(id, firstName, lastName, taskAmount) values (2, 'testTeacher2', 'nottingham', 3)
insert into Teachers(id, firstName, lastName, taskAmount) values (3, 'testTeacher3', 'london', 0)
insert into Teachers(id, firstName, lastName, taskAmount) values (4, 'testTeacher4', 'solstis' 15)
insert into Teachers(id, firstName, lastName, taskAmount) values (5, 'testTeacher5', 'glashow', 2)
insert into Teachers(id, firstName, lastName, taskAmount) values (6, 'testTeacher6', 'Arnhem', 0)

-- Afwezig Entries --
insert into Afwezig(id, reason, startDate, endDate, teacherId) values (1, 'vacation', 2024-02-06, 2024-02-16, 5)
insert into Afwezig(id, reason, startDate, endDate, teacherId) values (2, 'private', 2024-01-10, 2024-01-10, 6)

-- Parent Entries --
insert into Parents(id, firstName, lastName, phoneNumber, address, countryOfOrigin, spokenLanguage) values (1, 'testParent1', 'Groningen', '0612345678', 'Nederland', 'Nederlands')
insert into Parents(id, firstName, lastName, phoneNumber, address, countryOfOrigin, spokenLanguage) values (2, 'testParent2', 'Leeuwarden', '0631232358', 'Nederland', 'Nederlands')
insert into Parents(id, firstName, lastName, phoneNumber, address, countryOfOrigin, spokenLanguage) values (3, 'testParent3', 'Den Burg', '069281735', 'Nederland', 'Nederlands')
insert into Parents(id, firstName, lastName, phoneNumber, address, countryOfOrigin, spokenLanguage) values (4, 'testParent4', 'Cocksdorp', '0637293846', 'Servie', 'Nederlands')
insert into Parents(id, firstName, lastName, phoneNumber, address, countryOfOrigin, spokenLanguage) values (5, 'testParent5', 'Nijmegen', '0678549628', 'Nederland', 'Nederlands')
insert into Parents(id, firstName, lastName, phoneNumber, address, countryOfOrigin, spokenLanguage) values (6, 'testParent6', 'Doesburg', '0646321987', 'Nederland', 'Nederlands')
insert into Parents(id, firstName, lastName, phoneNumber, address, countryOfOrigin, spokenLanguage) values (7, 'testParent7', 'Rheden', '0648615368', 'Engeland', 'Engels')
insert into Parents(id, firstName, lastName, phoneNumber, address, countryOfOrigin, spokenLanguage) values (8, 'testParent8', 'Amsterdam', '0645136975', 'Brazilie', 'Portugees')

-- Child Entries --
insert into Childs(id, firstName, lastName, dob, address, countryOfOrigin, spokenLanguage, Allergies, startingDate, parentId) values (1, 'testChild1', 'Nijmegen', 2020-02-13, 'linglaan 20, 7218AB, Apeldoorn', 'Nederland', 'Nederlands', '["Gluten", "Ananas"]', 2024-02-13, 5)
insert into Childs(id, firstName, lastName, dob, address, countryOfOrigin, spokenLanguage, Allergies, startingDate, parentId) values (2, 'testChild2', 'Doesburg', 2020-01-05, 'rapunzelweg 19, 8910AB, Doesburg', 'Nederland', 'Nederlands', '["Noten"]', 2024-01-23, 6)
insert into Childs(id, firstName, lastName, dob, address, countryOfOrigin, spokenLanguage, Allergies, startingDate, parentId) values (3, 'testChild3', 'Rheden', 2020-03-12, 'regulantweg 10, 2311PB, Horst', 'Engeland', 'Engels', '[]', 2024-03-29, 7)
insert into Childs(id, firstName, lastName, dob, address, countryOfOrigin, spokenLanguage, Allergies, startingDate, parentId) values (4, 'testChild4', 'Amsterdam', 2020-02-19, 'recallaan 190, 1402CD, Nijmegen', 'Brazilie', 'Portugees', '["Zuivel"]', 2024-02-01, 8)

-- Task Entries --
insert into Task(id, status, dueDate, assigned, teacherId, childId) values (1, 'NEW', 2024-03-10, TRUE, 1, 1)
insert into Task(id, status, dueDate, assigned, teacherId, childId) values (1, 'PICKEDUP', 2024-03-21, TRUE, 2, 2)
insert into Task(id, status, dueDate, assigned, teacherId, childId) values (1, 'OVERDUE', 2024-01-08, FALSE, 3, 3)
insert into Task(id, status, dueDate, assigned, teacherId, childId) values (1, 'CLOSED', 2024-05-20, TRUE, 4, 4)

-- Opmerking Entries --
insert into Opmerkingen(id, dateOfContact, opmerking, taskId) values (1, 2024-08-25, 'Meer middelen nodig', 4);
insert into Opmerkingen(id, dateOfContact, opmerking, taskId) values (2, 2024-03-13, 'Vertraging door leveranciers', 3);
insert into Opmerkingen(id, dateOfContact, opmerking, taskId) values (3, 2024-08-16, 'Meer middelen nodig', 1);
insert into Opmerkingen(id, dateOfContact, opmerking, taskId) values (4, 2024-06-24, 'Klant is tevreden', 2);

-- File Entries --
insert into Files(id, fileName, fileType, data, parentType, parentId) values (1, 'welkom', '.txt', 0x48656C6C6F20576F726C64, 'Opmerking', 2)