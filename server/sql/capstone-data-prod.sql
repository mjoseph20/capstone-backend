USE capstone;

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
	();
    
INSERT INTO `Episodes` (`id`, `season`, `episode_number`, `title`, `description`, `air_date`, `rating`, `show_id`)
	VALUES 
	();
    
INSERT INTO `Cast_Members` (`id`, `name`, `professional_title`, `bio`, `birthdate`, `random_fact`, `user_id`) 
	VALUES 
	();
    
INSERT INTO `Episode_Member` (`id`, `episode_id`, `cast_member_id`, `episode_score`) 
	VALUES 
	();
    
INSERT INTO `Registered_Users` (`id`, `username`, `password`, `name`, `email`, `score`) 
	VALUES 
	();