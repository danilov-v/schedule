--liquibase formatted sql  logicalFilePath:changesAfterInitData.h2.sql

--changeset eldar:ADD_DURATION_COLUMN_INTO_EVENT
ALTER TABLE EVENT ADD DURATION INT;

--changeset eldar:SET_DURATION_COLUMN_INTO_EVENT
UPDATE EVENT SET DURATION = DATEDIFF(DAY, DATE_FROM, DATE_TO);

--changeset eldar:MAKE_DURATION_COLUMN_NOT_NULL
ALTER TABLE EVENT ALTER COLUMN DURATION INT NOT NULL;

--changeset eldar:DROP_DATE_TO_COLUMN_IN_EVENT
ALTER TABLE EVENT DROP COLUMN DATE_TO;

--changeset eldar:ADD_FLAG_COLUMN_INTO_UNIT
ALTER TABLE UNIT ADD FLAG VARCHAR(255);

--changeset eldar:ADD_EXISTS_COLUMN_INTO_UNIT
ALTER TABLE UNIT ADD "EXISTS" BOOLEAN DEFAULT TRUE NOT NULL;

--changeset gonchar:REVERT_DATE_TO__ADD DATE_TO COLUMN
ALTER TABLE EVENT ADD DATE_TO date;

--changeset gonchar:REVERT_DATE_TO__SET_DATE_TO_COLUMN
UPDATE EVENT SET DATE_TO = DATEADD(DAY, DURATION, DATE_FROM);

--changeset gonchar:REVERT_DATE_TO__MAKE_DATE_TO_COLUMN_NOT_NULL
ALTER TABLE EVENT ALTER COLUMN DATE_TO date NOT NULL;

--changeset gonchar:REVERT_DATE_TO__DROP_DATE_TO_COLUMN
ALTER TABLE EVENT DROP COLUMN DURATION;

--changeset gonchar:RENAME_COLUMN_EXISTS_TO_ACTIVE
alter table UNIT alter column "EXISTS" rename to PLANNED;

--changeset gonchar:CHANGE_PRIMARY_KEY__add_CALENDAR_ID
alter table CALENDAR
    add CALENDAR_ID BIGINT auto_increment;

--changeset gonchar:CHANGE_PRIMARY_KEY__DROP_PRIMARY_KEY
alter table CALENDAR drop primary key;

--changeset gonchar:CHANGE_PRIMARY_KEY__ADD_CONSTRAINT_CALENDAR_pk
alter table CALENDAR
    add constraint CALENDAR_pk
        primary key (CALENDAR_ID);

--changeset eldar:ADD_LOCATION_NAME_COLUMN
ALTER TABLE EVENT ADD LOCATION_NAME VARCHAR(255) DEFAULT 'default' NOT NULL;

--changeset eldar:ADD_LOCATION_TYPE_COLUMN
ALTER TABLE EVENT ADD LOCATION_TYPE VARCHAR(255) DEFAULT 'STATICAL' NOT NULL;

--changeset eldar:ADD_PLANNED_COLUMN
ALTER TABLE EVENT ADD PLANNED BOOLEAN DEFAULT FALSE NOT NULL;

--changeset eldar:ADD_CALENDAR_ID_COLUMN
ALTER TABLE UNIT ADD CALENDAR_ID BIGINT DEFAULT 1 NOT NULL;

--changeset eldar:ADD_UNIT__CALENDAR__CONSTRAINT
ALTER TABLE UNIT ADD CONSTRAINT UNIT__CALENDAR__CONSTRAINT FOREIGN KEY (CALENDAR_ID) REFERENCES CALENDAR (CALENDAR_ID) ON UPDATE CASCADE ON DELETE CASCADE;