insert into roles(rolename) values ('USER'), ('ADMIN');
insert into users(username, password) values ('calvin', 'password');
insert into users_roles(users_username, roles_rolename) values ('calvin', 'ADMIN')