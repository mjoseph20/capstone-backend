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

INSERT INTO `Shows` (`id`, `name`, `image_url`, `start_date`, `end_date`, `rating`, `creator`, `genre`, `storyline`, `production_company`) 
	VALUES
	(1,'American Idol', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/american_idol_show.jpg', '2002-06-11', '2023-05-21', 8.5, 'Simon Fuller', 'Music Competition', 'Aspiring singers compete for a recording contract.', 'Fremantle'),
(2,'America\'s Got Talent', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/americas_got_talent_show.jpg', '2006-06-21', '2023-09-20', 8.0, 'Simon Cowell', 'Talent Show', 'Contestants showcase various talents to win a cash prize.', 'Syco Entertainment'),
(3,'Big Brother', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/big_brother_show.png', '2000-07-05', '2023-09-27', 7.5, 'John de Mol', 'Reality Competition', 'Housemates live together and compete for a cash prize.', 'Endemol Shine Group'),
(4,'The Voice', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/the_voice_show.jpg', '2011-04-26', '2023-12-19', 8.2, 'John de Mol', 'Music Competition', 'Singers compete in blind auditions and battles.', 'Talpa Media'),
(5,'Survivor', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/survivor_show.jpg', '2000-05-31', '2023-12-20', 8.3, 'Charlie Parsons', 'Reality Competition', 'Contestants survive on a remote island and compete in challenges.', 'Mark Burnett Productions'),
(6,'Dancing with the Stars', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/dancing_with_the_stars_show.jpg', '2005-06-01', '2023-11-21', 7.8, 'BBC Worldwide', 'Dance Competition', 'Celebrities pair with professional dancers to compete.', 'BBC Studios'),
(7,'MasterChef', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/master_chef_show.jpg', '2010-07-27', '2023-09-20', 8.1, 'Franc Roddam', 'Cooking Competition', 'Amateur chefs compete in cooking challenges.', 'Shine America'),
(8,'The Real Housewives', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/rhoa_show.jpg', '2006-03-21', '2023-12-20', 6.9, 'Scott Dunlop', 'Reality TV', 'Follows the lives of affluent women in various cities.', 'Bravo'),
(9,'Love Is Blind', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/love_is_blind_show.jpg', '2020-02-13', '2023-10-04', 7.4, 'Chris Coelen', 'Dating Show', 'Singles form connections without seeing each other.', 'Kinetic Content'),
(10,'The Traitors', 'https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/the_traitors_show.jpg', '2023-01-12', '2023-03-02', 7.7, 'Stephen Lambert', 'Reality Competition', 'Contestants play a game of deception and strategy.', 'Studio Lambert');

    
INSERT INTO `Episodes` ( `image_url`, `season`, `episode_number`, `title`, `description`, `air_date`, `rating`, `show_id`)
	VALUES 
	('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/american_idol_episode.webp', 1, 1, 'Auditions', 'Aspiring singers audition for the judges.', '2002-06-11', 8.5, 1),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/american_idol_episode.webp', 1, 2, 'Hollywood Week', 'Contestants compete in Hollywood Week.', '2002-06-18', 8.4, 1),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/american_idol_episode.webp', 1, 3, 'Top 10 Performances', 'Top 10 contestants perform live.', '2002-06-25', 8.6, 1),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/americas_got_talent_episode.jpg', 1, 1, 'Auditions', 'Contestants showcase their talents.', '2006-06-21', 8.0, 2),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/americas_got_talent_episode.jpg', 1, 2, 'Judge Cuts', 'Judges select the best acts.', '2006-06-28', 7.9, 2),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/americas_got_talent_episode.jpg', 1, 3, 'Live Shows', 'Top acts perform live.', '2006-07-05', 8.1, 2),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/big_brother_episode.jpg', 1, 1, 'Premiere', 'Housemates enter the house.', '2000-07-05', 7.5, 3),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/big_brother_episode.jpg', 1, 2, 'First Eviction', 'First housemate is evicted.', '2000-07-12', 7.4, 3),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/big_brother_episode.jpg', 1, 3, 'Head of Household', 'Competition for Head of Household.', '2000-07-19', 7.6, 3),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/the_voice_episode.jpg', 1, 1, 'Blind Auditions', 'Singers audition for the coaches.', '2011-04-26', 8.2, 4),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/the_voice_episode.jpg', 1, 2, 'Battle Rounds', 'Contestants compete in battles.', '2011-05-03', 8.1, 4),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/the_voice_episode.jpg', 1, 3, 'Live Shows', 'Top contestants perform live.', '2011-05-10', 8.3, 4),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/survivor_episode.jpg', 1, 1, 'Marooned', 'Contestants are stranded on an island.', '2000-05-31', 8.3, 5),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/survivor_episode.jpg', 1, 2, 'Quest for Fire', 'Teams compete for fire.', '2000-06-07', 8.2, 5),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/survivor_episode.jpg', 1, 3, 'Outwit, Outplay, Outlast', 'Contestants strategize to survive.', '2000-06-14', 8.4, 5),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/dancing_with_the_stars_episode.jpg', 1, 1, 'Premiere', 'Celebrities perform their first dances.', '2005-06-01', 7.8, 6),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/dancing_with_the_stars_episode.jpg', 1, 2, 'Second Round', 'Celebrities perform their second dances.', '2005-06-08', 7.7, 6),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/dancing_with_the_stars_episode.jpg', 1, 3, 'Elimination', 'First elimination of the season.', '2005-06-15', 7.9, 6),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/master_chef_episode.jpg', 1, 1, 'Auditions', 'Amateur chefs audition for the judges.', '2010-07-27', 8.1, 7),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/master_chef_episode.jpg', 1, 2, 'Mystery Box Challenge', 'Contestants face a mystery box challenge.', '2010-08-03', 8.0, 7),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/master_chef_episode.jpg', 1, 3, 'Elimination Challenge', 'First elimination challenge.', '2010-08-10', 8.2, 7),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/rhoa_episode.jpg', 1, 1, 'Meet the Housewives', 'Introduction to the housewives.', '2006-03-21', 6.9, 8),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/rhoa_episode.jpg', 1, 2, 'Drama Unfolds', 'First major drama among the housewives.', '2006-03-28', 6.8, 8),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/rhoa_episode.jpg', 1, 3, 'Social Event', 'Housewives attend a social event.', '2006-04-04', 7.0, 8),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/love_is_blind_episode.jpg', 1, 1, 'The Pods', 'Singles meet in the pods.', '2020-02-13', 7.4, 9),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/love_is_blind_episode.jpg', 1, 2, 'First Dates', 'Couples go on their first dates.', '2020-02-20', 7.3, 9),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/love_is_blind_episode.jpg', 1, 3, 'Engagements', 'Couples get engaged.', '2020-02-27', 7.5, 9),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/the_traitors_episode.jpg', 1, 1, 'The Game Begins', 'Contestants start the game.', '2023-01-12', 7.7, 10),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/the_traitors_episode.jpg', 1, 2, 'Deception', 'First round of deception.', '2023-01-19', 7.6, 10),
('https://mj-capstone-bucket.s3.us-east-1.amazonaws.com/images/the_traitors_episode.jpg', 1, 3, 'Elimination', 'First elimination of the season.', '2023-01-26', 7.8, 10);
    
INSERT INTO `Cast_Members` ( `name`, `professional_title`, `bio`, `birth_date`, `random_fact`) 
	VALUES 
	('Kelly Clarkson', 'Singer', 'Winner of the first season of American Idol.', '1982-04-24', 'First winner of American Idol.'),
('Simon Cowell', 'Judge', 'Music executive and judge on American Idol.', '1959-10-07', 'Known for his blunt critiques.'),
('Ryan Seacrest', 'Host', 'Host of American Idol.', '1974-12-24', 'Also hosts a popular radio show.'),
('Heidi Klum', 'Judge', 'Model and judge on America\'s Got Talent.', '1973-06-01', 'Also hosts Project Runway.'),
('Howie Mandel', 'Judge', 'Comedian and judge on America\'s Got Talent.', '1955-11-29', 'Known for his role on Deal or No Deal.'),
('Terry Crews', 'Host', 'Host of America\'s Got Talent.', '1968-07-30', 'Also known for his acting career.'),
('Julie Chen', 'Host', 'Host of Big Brother.', '1970-01-06', 'Also hosts The Talk.'),
('Dan Gheesling', 'Contestant', 'Winner of Big Brother season 10.', '1983-09-01', 'Known for his strategic gameplay.'),
('Rachel Reilly', 'Contestant', 'Winner of Big Brother season 13.', '1984-10-16', 'Also appeared on The Amazing Race.'),
('Blake Shelton', 'Coach', 'Country singer and coach on The Voice.', '1976-06-18', 'Known for his hit songs.'),
('Kelly Clarkson', 'Coach', 'Singer and coach on The Voice.', '1982-04-24', 'Winner of American Idol season 1.'),
('Carson Daly', 'Host', 'Host of The Voice.', '1973-06-22', 'Also hosts a late-night talk show.'),
('Jeff Probst', 'Host', 'Host of Survivor.', '1962-11-04', 'Also known for his role on Rock & Roll Jeopardy.'),
('Richard Hatch', 'Contestant', 'Winner of Survivor season 1.', '1961-04-08', 'Known for his strategic gameplay.'),
('Sandra Diaz-Twine', 'Contestant', 'Winner of Survivor seasons 7 and 20.', '1974-07-30', 'Only contestant to win twice.'),
('Tom Bergeron', 'Host', 'Host of Dancing with the Stars.', '1955-05-06', 'Also hosted America\'s Funniest Home Videos.'),
('Carrie Ann Inaba', 'Judge', 'Judge on Dancing with the Stars.', '1968-01-05', 'Also known for her role on The Talk.'),
('Bruno Tonioli', 'Judge', 'Judge on Dancing with the Stars.', '1955-11-25', 'Also known for his role on Strictly Come Dancing.'),
('Gordon Ramsay', 'Judge', 'Celebrity chef and judge on MasterChef.', '1966-11-08', 'Known for his fiery personality.'),
('Joe Bastianich', 'Judge', 'Restaurateur and judge on MasterChef.', '1968-09-17', 'Also known for his role on MasterChef Junior.'),
('Christina Tosi', 'Judge', 'Pastry chef and judge on MasterChef.', '1981-11-09', 'Founder of Milk Bar.'),
('Lisa Vanderpump', 'Housewife', 'Cast member of The Real Housewives of Beverly Hills.', '1960-09-15', 'Owns several restaurants.'),
('NeNe Leakes', 'Housewife', 'Cast member of The Real Housewives of Atlanta.', '1967-12-13', 'Also known for her acting career.'),
('Teresa Giudice', 'Housewife', 'Cast member of The Real Housewives of New Jersey.', '1972-05-18', 'Known for her dramatic moments.'),
('Nick Lachey', 'Host', 'Host of Love Is Blind.', '1973-11-09', 'Former member of 98 Degrees.'),
('Vanessa Lachey', 'Host', 'Host of Love Is Blind.', '1980-11-09', 'Also known for her acting career.'),
('Lauren Speed', 'Contestant', 'Contestant on Love Is Blind season 1.', '1987-06-16', 'Married Cameron Hamilton.'),
('Alan Cumming', 'Host', 'Host of The Traitors.', '1965-01-27', 'Also known for his acting career.'),
('Cirie Fields', 'Contestant', 'Contestant on The Traitors season 1.', '1970-07-18', 'Also known for her role on Survivor.'),
('Arie Luyendyk Jr.', 'Contestant', 'Contestant on The Traitors season 1.', '1981-09-18', 'Also known for his role on The Bachelor.');
    
INSERT INTO `Episode_Member` ( `episode_id`, `cast_member_id`, `episode_score`) 
	VALUES 
	(1, 1, 9),
(1, 2, 8),
(2, 1, 9),
(2, 2, 8),
(3, 1, 9),
(3, 2, 8),
(4, 4, 8),
(4, 5, 7),
(5, 4, 8),
(5, 5, 7),
(6, 4, 8),
(6, 5, 7),
(7, 7, 7),
(7, 8, 6),
(8, 7, 7),
(8, 8, 6),
(9, 7, 7),
(9, 8, 6),
(10, 10, 8),
(10, 11, 7),
(11, 10, 8),
(11, 11, 7),
(12, 10, 8),
(12, 11, 7),
(13, 13, 8),
(13, 14, 7),
(14, 13, 8),
(14, 14, 7),
(15, 13, 8),
(15, 14, 7),
(16, 16, 7),
(16, 17, 6),
(17, 16, 7),
(17, 17, 6),
(18, 16, 7),
(18, 17, 6),
(19, 19, 8),
(19, 20, 7),
(20, 19, 8),
(20, 20, 7),
(21, 19, 8),
(21, 20, 7),
(22, 22, 6),
(22, 23, 5),
(23, 22, 6),
(23, 23, 5),
(24, 22, 6),
(24, 23, 5),
(25, 25, 7),
(25, 26, 6),
(26, 25, 7),
(26, 26, 6),
(27, 25, 7),
(27, 26, 6),
(28, 28, 8),
(28, 29, 7),
(29, 28, 8),
(29, 29, 7),
(30, 28, 8),
(30, 29, 7);
    
INSERT INTO `Registered_Users` (`id`, `username`, `password`, `name`, `email`, `score`) 
	VALUES 
	(1, 'myUsername', 'myPassword', 'name', 'myName@email.com', 100);
    
    SET SQL_SAFE_UPDATES = 1;