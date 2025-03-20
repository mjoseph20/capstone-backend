drop database if exists capstone_test;
create database capstone_test;
use capstone_test;

CREATE TABLE IF NOT EXISTS `Shows` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name` varchar(50) NOT NULL,
	`start_date` datetime NOT NULL,
	`end_date` datetime NOT NULL,
	`rating` decimal(10,0) NOT NULL,
	`creator` varchar(50) NOT NULL,
	`genre` varchar(50) NOT NULL,
	`storyline` varchar(255) NOT NULL,
	`production_company` varchar(50) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Episodes` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`season` int NOT NULL,
	`episode_number` int NOT NULL,
	`title` varchar(50) NOT NULL,
	`description` varchar(255) NOT NULL,
	`air_date` date NOT NULL,
	`rating` decimal(10,0) NOT NULL,
	`show_id` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Cast_Members` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`name` varchar(50) NOT NULL,
	`professional_title` varchar(50) NOT NULL,
	`bio` varchar(255) NOT NULL,
	`birth_date` date NOT NULL,
	`random_fact` varchar(255) NOT NULL,
	`user_id` int NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Episode_Member` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`episode_id` int NOT NULL,
	`cast_member_id` int NOT NULL,
	`episode_score` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `Registered_Users` (
	`id` int AUTO_INCREMENT NOT NULL UNIQUE,
	`username` varchar(50) NOT NULL UNIQUE,
	`password` varchar(15) NOT NULL,
	`name` varchar(50) NOT NULL,
	`email` varchar(50) NOT NULL,
    `score` int DEFAULT 0,
	PRIMARY KEY (`id`)
);

ALTER TABLE `Episodes` ADD CONSTRAINT `Episodes_fk7` FOREIGN KEY (`show_id`) REFERENCES `Shows`(`id`);
ALTER TABLE `Cast_Members` ADD CONSTRAINT `Cast_Members_fk6` FOREIGN KEY (`user_id`) REFERENCES `Registered_Users`(`id`) ON DELETE SET NULL;
ALTER TABLE `Episode_Member` ADD CONSTRAINT `Episode_Member_fk1` FOREIGN KEY (`episode_id`) REFERENCES `Episodes`(`id`);
ALTER TABLE `Episode_Member` ADD CONSTRAINT `Episode_Member_fk2` FOREIGN KEY (`cast_member_id`) REFERENCES `Cast_Members`(`id`);

delimiter //
create procedure set_known_good_state()
begin
    delete from `Episode_Member`;
    alter table `Episode_Member` auto_increment = 1;
    delete from `Cast_Members`;
    alter table `Cast_Members` auto_increment = 1;
	delete from `Episodes`;
    alter table `Episodes` auto_increment = 1;
    delete from `Shows`;
    alter table `Shows` auto_increment = 1;
    delete from `Registered_Users`;
    alter table `Registered_Users` auto_increment = 1;

    insert into `Registered_Users` (`id`, `username`, `password`, `name`, `email`, `score`) 
		values 
        ('1', 'user1', 'password1', 'User One', 'userone@email.com', '0'),
		('2', 'user2', 'password2', 'User Two', 'usertwo@email.com', '0'),
		('3', 'user3', 'password3', 'User Three', 'userthree@email.com', '0');
        
	insert into `Shows` (`id`, `name`, `start_date`, `end_date`, `rating`, `creator`, `genre`, `storyline`, `production_company`) 
		values
        ('1', 'The Office', '2005-03-24', '2013-05-16', '8', 'Greg Daniels', 'Comedy', 'A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium.', 'Deedle-Dee Productions'), 
		('2', 'Breaking Bad', '2008-01-20', '2013-09-29', '9', 'Vince Gilligan', 'Crime', 'A high school chemistry teacher turned meth producer partners with a former student to secure his family''s future.', 'High Bridge Productions'), 
		('3', 'Friends', '1994-09-22', '2004-05-06', '8', 'David Crane', 'Comedy', 'Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.', 'Bright/Kauffman/Crane Productions');
    
    insert into `Episodes` (`id`, `season`, `episode_number`, `title`, `description`, `air_date`, `rating`, `show_id`)
		values 
        ('1', '1', '1', 'Pilot', 'A documentary crew arrives at the offices of Dunder Mifflin to observe the employees and learn about modern management.', '2005-03-24', '8', '1'),
		('2', '1', '2', 'Diversity Day', 'Michael''s offensive behavior prompts the company to host a diversity training seminar.', '2005-03-29', '8', '1'),
		('3', '1', '1', 'Pilot', 'A high school chemistry teacher turned meth producer partners with a former student to secure his family''s future.', '2008-01-20', '9', '2'),
		('4', '1', '2', 'Cat''s in the Bag...', 'Walter and Jesse attempt to tie up loose ends.', '2008-01-27', '9', '2'),
		('5', '1', '1', 'The One Where Monica Gets a Roommate', 'Monica''s high school friend Rachel moves in with her after leaving her fiancé.', '1994-09-22', '8', '3');
    
    insert into `Cast_Members` (`id`, `name`, `professional_title`, `bio`, `birth_date`, `random_fact`, `user_id`) 
		values 
        ('1', 'Steve Carell', 'Actor', 'Steve Carell, one of America''s most versatile comics, was born Steven John Carell on August 16, 1962, in Concord, Massachusetts.', '1962-08-16', 'He is of Italian (from his maternal grandfather), German, and Polish descent.', '1'), 
		('2', 'Bryan Cranston', 'Actor', 'Bryan Cranston is an American actor, voice actor, producer, director, and screenwriter.', '1956-03-07', 'He is of German, Austrian, and Irish descent.', '2'),
		('3', 'Jennifer Aniston', 'Actress', 'Jennifer Joanna Aniston was born on February 11, 1969, in Sherman Oaks, Los Angeles, California.', '1969-02-11', 'She is of Greek descent.', '3');
    
    insert into `Episode_Member` (`id`, `episode_id`, `cast_member_id`, `episode_score`) 
		values 
        ('1', '1', '1', '8'),
		('2', '2', '1', '8'),
		('3', '3', '2', '9'),
		('4', '4', '2', '9'),
		('5', '5', '3', '8');

end //
delimiter ;

