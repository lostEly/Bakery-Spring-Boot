insert into role(role_id, role_name) values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

insert into userr(userr_id, userr_last_name, login, userr_name, password, role_id, email, enabled) values
(1, 'test_lname', 'test_login', 'test_name', '$2a$10$6YG/cPzzRlKj4nwBWbneWutiIj73k6mZJZ8XIM7vccYZo93oZCBbC',
 1, 'testt@gmail.com', true),
(2, 'test2_lname', 'test2_login', 'test_name2', '$2a$10$6YG/cPzzRlKj4nwBWbneWutiIj73k6mZJZ8XIM7vccYZo93oZCBbC',
    2, 'testt2@gmail.com', true),
(3, 'adminlastname', 'admin1', 'adminname', '$2a$10$5HivB10Xg0zIttIWy9t7Oeg9HbjBUH8YgPDvDt9FfzHAy9wB4N0nK',
    1, 'admin@gmail.com', true);


