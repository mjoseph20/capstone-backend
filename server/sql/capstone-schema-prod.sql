drop database if exists capstone;
create database capstone;
use capstone;

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
    `image_url` varchar(255) NOT NULL,
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
    `image_url` varchar(255) NOT NULL,
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
	`is_active` boolean DEFAULT FALSE,
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
ALTER TABLE `Episode_Member` ADD CONSTRAINT `Episode_Member_fk1` FOREIGN KEY (`episode_id`) REFERENCES `Episodes`(`id`);
ALTER TABLE `Episode_Member` ADD CONSTRAINT `Episode_Member_fk2` FOREIGN KEY (`cast_member_id`) REFERENCES `Cast_Members`(`id`);
