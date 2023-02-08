INSERT INTO TEAM(sn, team_code, created_date_time, last_modified_date_time) VALUES (1, 777777, now(), now());
INSERT INTO GRID_MAP (x_size, y_size, team_sn) VALUES (10, 10, 1);

INSERT INTO WORKER (name, nickname, email, password, phone, team_sn, created_date_time, last_modified_date_time) VALUES ('권구상', '구상이', 'kks1023@midasin.com', 'pass', '010-9508-2526', 1, now(), now());