drop database if exists funfit_database;
DROP USER 'funfitadmin'@'localhost';
create database funfit_database;
use funfit_database;
CREATE USER 'funfitadmin'@'localhost' IDENTIFIED BY 'mid-SUAVE2';
GRANT ALL PRIVILEGES ON funfit_database.* TO 'funfitadmin'@'localhost';

use funfit_database;

create table Batch(
	bid int PRIMARY KEY AUTO_INCREMENT,
	batch_group ENUM('Morning', 'Evening') NOT NULL,
	batch_name ENUM('Zumba', 'Zumba Gold') NOT NULL,
	batch_date_time datetime NOT NULL
);

create table Participant(
	pid int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	name varchar(256) NOT NULL,
	phone varchar(16) NOT NULL,
	email varchar(256) NOT NULL,
	bid int,
	CONSTRAINT fk_participant FOREIGN KEY (bid)
	REFERENCES Batch(bid)
);

show tables;
describe Batch;
describe Participant;

insert into Batch
	(batch_group, batch_name, batch_date_time)
	Values
	('Morning', 'Zumba', '2024-06-17 08:00:00'),
	('Evening', 'Zumba', '2024-06-17 20:00:00'),
	('Morning', 'Zumba Gold', '2024-06-18 08:00:00'),
	('Evening', 'Zumba Gold', '2024-06-18 20:00:00'),
	('Morning', 'Zumba', '2024-06-19 08:00:00'),
	('Evening', 'Zumba', '2024-06-19 20:00:00')
;

insert into Participant
	(name, phone, email, bid)
	Values
	('King Rauru', '999-548-4545', 'krauru@zonaisecretstones.com', 3),
	('Queen Sonia', '999-415-5789', 'qsonia@zonaisecretstones.com', 3),
	('Mineru', '999-214-4576', 'mineru@zonaisecretstones.com', 4),
	('Sidon', '157-446-4657', 'sidon@zorasdomain.com', 1),
	('Tulin', '648-478-4541', 'tulin@ritovillage.com', 1),
	('Riju', '697-524-5415', 'riju@gerudotown.com', 2),
	('Yunobo', '245-448-6984', 'yunobo@goroncity.com', 2)	
;

show databases;

select DATABASE() as Current_Database;

select user, host from mysql.user;

show grants for 'funfitadmin'@'localhost';

show GLOBAL VARIABLES LIKE 'PORT';

show tables;

describe Batch;
describe Participant;

select * from Batch;
select * from Participant;

