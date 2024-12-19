#drop database samsdb;
#Create database  samsdb;
USE samsdb;
delete FROM Users; #clear all data from table
#ALTER TABLE Users AUTO_INCREMENT = 1;
#reset persist;

INSERT INTO Users (username, password, primary_role, firstname, lastname, email, phone, address) VALUES
    ('adam_admin@mail.com', 'password', 'administrator', 'Adam_fn', 'Adam_ln', 'adam_admin@mail.com', '1234567890', '123 adam street');
INSERT INTO Users (username, password, primary_role, firstname, lastname, email, phone, address) VALUES
    ('patricia_pcc@mail.com', 'password', 'pcc', 'Patricia_fn', 'Patricia_ln', 'patricia_pcc@mail.com', '1234567890', '123 patricia street');
INSERT INTO Users (username, password, primary_role, firstname, lastname, email, phone, address) VALUES
    ('pamela_pcm@mail.com', 'password', 'pcm', 'Pamela_fn', 'Pamela_ln', 'pamela_pcm@mail.com', '1234567890', '123 pamela street');
INSERT INTO Users (username, password, primary_role, firstname, lastname, email, phone, address) VALUES
    ('tsuki_submitter@mail.com', 'password', 'submitter', 'Tsuki_fn', 'Tuski_ln', 'tsuki_submitter@mail.com', '1234567890', '123 tsuki street');

INSERT INTO Users (username, password, primary_role, secondary_role, firstname, lastname, email, phone, address) VALUES
    ('multi_patricia_pcc@mail.com', 'password', 'pcc','submitter', 'Patricia_fn', 'Patricia_ln', 'multi_patricia_pcc@mail.com', '1234567890', '123 patricia street');
INSERT INTO Users (username, password, primary_role, secondary_role, firstname, lastname, email, phone, address) VALUES
    ('multi_pamela_pcm@mail.com', 'password', 'pcm', 'pcc','Pamela_fn', 'Pamela_ln', 'multi_pamela_pcm@mail.com', '1234567890', '123 pamela street');
INSERT INTO Users (username, password, primary_role, secondary_role, firstname, lastname, email, phone, address) VALUES
    ('multi_tsuki_submitter@mail.com', 'password', 'submitter', secondary_role='pcm', 'Tsuki_fn', 'Tuski_ln', 'multi_tsuki_pcm@mail.com', '1234567890', '123 tsuki street');

#C:\ProgramData\MySQL\MySQL Server 8.0\Uploads
# update users set pdfTest=LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/TEST.pdf') where username = 'tsuki_submitter@mail.com';


delete from papers where userid = 4;
SELECT @id :=  users.userId From users where username = 'tsuki_submitter@mail.com';
SELECT @content :=  users.userId From users where username = 'tsuki_submitter@mail.com';


INSERT INTO deadlines ( deadlineType, isDeadlinePassed, deadline) VALUES
    ('paper', 0, '2024-01-23 12:45:56');

SELECT @deadid := deadlineId From deadlines where deadlineType = 'paper';



INSERT INTO papers (deadlineId, isSubmitted, userId,authors, title,  format,version, paperContent, fileName ) VALUES
    ( @deadid, 1, @id, 'Bob and Joe', 'The Conference Paper', 'PDF', '11-30-2023 1053PM', 'NULL', 'TCP_001');


update papers set isSubmitted=1;



update papers set paperContent=LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/TCP.pdf') where userId = @id;

select * from deadlines;
select * from users;
select * from papers;

# show variables like "secure_file_priv";

SELECT username, userid From users;

SELECT paperId,isSubmitted,authors,title,format,version,filename FROM Papers WHERE userId = '4';

SELECT * FROM Papers WHERE userId = '4';