USE capstone;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM `Shows`;
ALTER TABLE `Shows` AUTO_INCREMENT = 1;
DELETE FROM `Episodes`;
ALTER TABLE `Episodes` AUTO_INCREMENT = 1;
DELETE FROM `Cast_Members`;
ALTER TABLE `Cast_Members` AUTO_INCREMENT = 1;
DELETE FROM `Episode_Member`;
ALTER TABLE `Episode_Member` AUTO_INCREMENT = 1;
DELETE FROM `Registered_Users`;
ALTER TABLE `Registered_Users` AUTO_INCREMENT = 1;

INSERT INTO `Shows` (`id`, `name`, `start_date`, `end_date`, `rating`, `creator`, `genre`, `storyline`, `production_company`) 
	VALUES
	('1', 'The Office', '2005-03-24', '2013-05-16', '8', 'Greg Daniels', 'Comedy', 'A mockumentary on a group of typical office workers, where the workday consists of ego clashes, inappropriate behavior, and tedium.', 'Deedle-Dee Productions'), 
		('2', 'Breaking Bad', '2008-01-20', '2013-09-29', '9', 'Vince Gilligan', 'Crime', 'A high school chemistry teacher turned meth producer partners with a former student to secure his family''s future.', 'High Bridge Productions'), 
		('3', 'Friends', '1994-09-22', '2004-05-06', '8', 'David Crane', 'Comedy', 'Follows the personal and professional lives of six twenty to thirty-something-year-old friends living in Manhattan.', 'Bright/Kauffman/Crane Productions');
    
INSERT INTO `Episodes` (`id`, `season`, `episode_number`, `title`, `description`, `air_date`, `rating`, `show_id`)
	VALUES 
	('1', '1', '1', 'Pilot', 'A documentary crew arrives at the offices of Dunder Mifflin to observe the employees and learn about modern management.', '2005-03-24', '8', '1'),
		('2', '1', '2', 'Diversity Day', 'Michael''s offensive behavior prompts the company to host a diversity training seminar.', '2005-03-29', '8', '1'),
		('3', '1', '1', 'Pilot', 'A high school chemistry teacher turned meth producer partners with a former student to secure his family''s future.', '2008-01-20', '9', '2'),
		('4', '1', '2', 'Cat''s in the Bag...', 'Walter and Jesse attempt to tie up loose ends.', '2008-01-27', '9', '2'),
		('5', '1', '1', 'The One Where Monica Gets a Roommate', 'Monica''s high school friend Rachel moves in with her after leaving her fiancé.', '1994-09-22', '8', '3');
    
INSERT INTO `Cast_Members` (`id`, `name`, `professional_title`, `bio`, `birth_date`, `random_fact`, `is_active`) 
	VALUES 
	('1', 'Steve Carell', 'Actor', 'Steve Carell, one of America''s most versatile comics, was born Steven John Carell on August 16, 1962, in Concord, Massachusetts.', '1962-08-16', 'He is of Italian (from his maternal grandfather), German, and Polish descent.', true), 
		('2', 'Bryan Cranston', 'Actor', 'Bryan Cranston is an American actor, voice actor, producer, director, and screenwriter.', '1956-03-07', 'He is of German, Austrian, and Irish descent.', false),
		('3', 'Jennifer Aniston', 'Actress', 'Jennifer Joanna Aniston was born on February 11, 1969, in Sherman Oaks, Los Angeles, California.', '1969-02-11', 'She is of Greek descent.', true);
    
INSERT INTO `Episode_Member` (`id`, `episode_id`, `cast_member_id`, `episode_score`) 
	VALUES 
	('1', '1', '1', '8'),
		('2', '2', '1', '8'),
		('3', '3', '2', '9'),
		('4', '4', '2', '9'),
		('5', '5', '3', '8');
    
INSERT INTO `Registered_Users` (`id`, `username`, `password`, `name`, `email`, `score`) 
	VALUES 
	('1', 'myUsername', 'myPassword', 'name', 'myName@email.com', '100');
    
    SET SQL_SAFE_UPDATES = 1;