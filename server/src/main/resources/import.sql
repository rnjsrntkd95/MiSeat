INSERT INTO TEAM(sn, team_code, created_date_time, last_modified_date_time) VALUES (1, 777777, now(), now());

INSERT INTO WORKER (user_id, name, nickname, email, password, phone, team_sn, created_date_time, last_modified_date_time) VALUES ('kks1023', '권구상' ,'구상이', 'kks1023@midasin.com', 'pass', '010-9508-2526', 1, now(), now());

INSERT INTO GRID_MAP (sn, x_size, y_size, team_sn) VALUES (1, 3, 3, 1);

INSERT INTO GRID_MAP_SEAT(seat_type, seat_number, x, y, grid_map_sn) VALUES('FREE', 1, 0, 0, 1);
INSERT INTO GRID_MAP_SEAT(seat_type, seat_number, x, y, grid_map_sn) VALUES('FREE', 2, 0, 1, 1);
INSERT INTO GRID_MAP_SEAT(seat_type, seat_number, x, y, grid_map_sn) VALUES('FREE', 3, 0, 2, 1);
INSERT INTO GRID_MAP_SEAT(seat_type, seat_number, x, y, grid_map_sn) VALUES('FREE', 4, 1, 0, 1);
INSERT INTO GRID_MAP_SEAT(seat_type, seat_number, x, y, grid_map_sn) VALUES('FREE', 5, 1, 1, 1);
INSERT INTO GRID_MAP_SEAT(seat_type, seat_number, x, y, grid_map_sn) VALUES('FREE', 6, 1, 2, 1);
INSERT INTO GRID_MAP_SEAT(seat_type, seat_number, x, y, grid_map_sn) VALUES('FREE', 7, 2, 0, 1);
INSERT INTO GRID_MAP_SEAT(seat_type, seat_number, x, y, grid_map_sn) VALUES('FREE', 8, 2, 1, 1);
INSERT INTO GRID_MAP_SEAT(seat_type, seat_number, x, y, grid_map_sn) VALUES('FREE', 9, 2, 2, 1);
