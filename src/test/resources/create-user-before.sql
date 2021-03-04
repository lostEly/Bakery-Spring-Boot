insert into role(role_id, role_name) values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

insert into userr(userr_id, userr_last_name, login, userr_name, password, role_id, email, enabled) values
(1, 'test_lname', 'test_login', 'test_name', '$2a$10$xySmumD0l7bixC99G0fRH.x9Pq81v3ZpwB4UQCLwIqCtjxKNnVPRu',
 1, 'testt@gmail.com', true),
(2, 'test2_lname', 'test2_login', 'test_name2', '$2a$10$xySmumD0l7bixC99G0fRH.x9Pq81v3ZpwB4UQCLwIqCtjxKNnVPRu',
    2, 'testt2@gmail.com', true),
(3, 'adminlastname', 'admin1', 'adminname', '$2a$10$9rwPng.qtiRtV8OKYdAq6usGwje9nHGXmgXKTUozW2CxOiMhbeXw2',
    1, 'admin@gmail.com', true);


