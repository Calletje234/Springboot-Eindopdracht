insert into roles(rolename) values ('USER'), ('ADMIN'), ('BEHANDELAAR');
insert into users(username, password) values ('ADMIN', '$2a$10$nFpBJtQSwdneAM67MrPv5O54Q0T4GZyTIiyfCzcdkQvFyh4zQ2qWK');
insert into users_roles(users_username, roles_rolename) values ('ADMIN', 'ADMIN');
