select * from user_tables;

select 'DROP TABLE ' || table_name || ' CASCADE CONSTRAINT;' from user_tables;

DELETE FROM TKT;
DELETE FROM TRN;
DELETE FROM SMS;
UPDATE ENT_ID_GEN SET CURR_ID=0;
