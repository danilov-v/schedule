--liquibase formatted sql  logicalFilePath:initData.h2.sql

--changeset gonchar:EVENT_TYPE__DATA-1
INSERT INTO EVENT_TYPE (TYPE_ID, COLOR, DESCRIPTION, TYPE_CODE) VALUES (39, '#ff5555', 'Мобилизация', NULL);
INSERT INTO EVENT_TYPE (TYPE_ID, COLOR, DESCRIPTION, TYPE_CODE) VALUES (40, '#ffb2b6', 'Развертывание', NULL);
INSERT INTO EVENT_TYPE (TYPE_ID, COLOR, DESCRIPTION, TYPE_CODE) VALUES (42, '#67ecc0', 'Развод', NULL);
INSERT INTO EVENT_TYPE (TYPE_ID, COLOR, DESCRIPTION, TYPE_CODE) VALUES (43, '#33b7ec', 'Поверка', NULL);
INSERT INTO EVENT_TYPE (TYPE_ID, COLOR, DESCRIPTION, TYPE_CODE) VALUES (44, '#fff292', 'Проверка', NULL);
INSERT INTO EVENT_TYPE (TYPE_ID, COLOR, DESCRIPTION, TYPE_CODE) VALUES (50, '#d7ffab', 'Разведка', NULL);

--changeset gonchar:UNIT__DATA-2
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (1, NULL, 'Система управления 1', 1);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (6, NULL, 'СУ 3', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (201, 1, 'Орган управления 1.1', 2);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (251, 201, 'Пункт управления 1.1.1', 3);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (350, 251, 'Подразделение 1.1.1.1', 4);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (450, NULL, 'СУ 5', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (451, NULL, 'СУ 6', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (452, NULL, 'СУ 7', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (453, NULL, 'СУ 8', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (454, NULL, 'СУ 9', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (455, NULL, 'СУ 10', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (456, 452, 'ПУ 7.1', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (457, 451, 'ПУ 6.1', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (458, 457, 'Подразделение 6.1.1', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (459, 457, 'Подразделение 6.1.2', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (482, 450, 'Подразделение 5.1', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (483, NULL, 'Подразделение 2', NULL);
INSERT INTO UNIT (UNIT_ID, PARENT_ID, TITLE, UNIT_LEVEL) VALUES (484, 450, 'Подразделение 5.2', NULL);

--changeset gonchar:EVENT__DATA-3
INSERT INTO EVENT (EVENT_ID, DATE_FROM, NOTE, UNIT_ID, EVENT_TYPE_ID, DATE_TO) VALUES (989, '2019-08-19', 'развётрываниеразвётрываниеdrop database', 350, 39, '2019-08-25');
INSERT INTO EVENT (EVENT_ID, DATE_FROM, NOTE, UNIT_ID, EVENT_TYPE_ID, DATE_TO) VALUES (990, '2019-08-26', 'развётрывание', 350, 40, '2019-08-31');
INSERT INTO EVENT (EVENT_ID, DATE_FROM, NOTE, UNIT_ID, EVENT_TYPE_ID, DATE_TO) VALUES (993, '2019-08-19', 'drop database
', 6, 43, '2019-09-01');
INSERT INTO EVENT (EVENT_ID, DATE_FROM, NOTE, UNIT_ID, EVENT_TYPE_ID, DATE_TO) VALUES (994, '2019-08-27', 'развётрывание', 484, 44, '2019-09-26');
INSERT INTO EVENT (EVENT_ID, DATE_FROM, NOTE, UNIT_ID, EVENT_TYPE_ID, DATE_TO) VALUES (998, '2019-08-25', 'развётрываниеразвётрывание', 458, 42, '2019-08-31');
INSERT INTO EVENT (EVENT_ID, DATE_FROM, NOTE, UNIT_ID, EVENT_TYPE_ID, DATE_TO) VALUES (1000, '2019-08-18', '192019201920', 482, 50, '2019-10-05');

--changeset gonchar:EVENT_DURATION__DATA-4
INSERT INTO EVENT_DURATION (DURATION, UNIT_ID, EVENT_TYPE_ID) VALUES (6, 350, 39);
INSERT INTO EVENT_DURATION (DURATION, UNIT_ID, EVENT_TYPE_ID) VALUES (5, 350, 40);
INSERT INTO EVENT_DURATION (DURATION, UNIT_ID, EVENT_TYPE_ID) VALUES (13, 6, 43);
INSERT INTO EVENT_DURATION (DURATION, UNIT_ID, EVENT_TYPE_ID) VALUES (9, 484, 43);
INSERT INTO EVENT_DURATION (DURATION, UNIT_ID, EVENT_TYPE_ID) VALUES (30, 484, 44);
INSERT INTO EVENT_DURATION (DURATION, UNIT_ID, EVENT_TYPE_ID) VALUES (7, 482, 39);
INSERT INTO EVENT_DURATION (DURATION, UNIT_ID, EVENT_TYPE_ID) VALUES (6, 458, 42);
INSERT INTO EVENT_DURATION (DURATION, UNIT_ID, EVENT_TYPE_ID) VALUES (48, 482, 50);

--changeset gonchar:PERIOD__DATA-5
INSERT INTO PERIOD (PERIOD_ID, NAME, START_DATE, END_DATE) VALUES (1, 'Period 1', '2019-08-25', '2020-09-23');

--changeset gonchar:USER__DATA-6 runOnChange:true
delete from USER where LOGIN = 'user' or LOGIN = 'superuser';
INSERT INTO USER (LOGIN, PASSWORD, ROLE) VALUES ('user', '$2a$10$N2X1UXEOMBS2Zc3pSH6RcOVMFe4jWy2q0nE8aFRfazAuxua0ztEZe', 'USER');
INSERT INTO USER (LOGIN, PASSWORD, ROLE) VALUES ('superuser', '$2a$10$QeYXY5JCyeIkZLsVfcPsTOu6bwM/xr64gg8C.c2uVdNHldPlJZOQ.', 'SUPERUSER');

--changeset gonchar:CALENDAR__DATA-7
INSERT INTO CALENDAR (SHIFT, IS_ASTRONOMICAL, NAME) VALUES (0, true, 'Календарь 1')