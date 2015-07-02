-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 20, 2015 at 03:03 AM
-- Server version: 5.5.32
-- PHP Version: 5.3.10-1ubuntu3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `student_cnielsen`
--
USE `monopoly`;
-- --------------------------------------------------------

--
-- Table structure for table `actionspace_constants`
--

DROP TABLE IF EXISTS `actionspace_constants`;
CREATE TABLE IF NOT EXISTS `actionspace_constants` (
  `space_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`space_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `actionspace_constants`
--

INSERT INTO `actionspace_constants` (`space_id`, `name`) VALUES
(0, 'GO'),
(2, 'Community Chest'),
(4, 'Income Tax'),
(7, 'Chance'),
(10, 'Jail Or Just Visiting'),
(17, 'Community Chest'),
(20, 'Free Parking'),
(22, 'Chance'),
(30, 'Go To Jail'),
(33, 'Community Chest'),
(36, 'Chance'),
(38, 'Luxury Tax');

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
CREATE TABLE IF NOT EXISTS `bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `number_houses` int(11) NOT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `bankaccount`
--

DROP TABLE IF EXISTS `bankaccount`;
CREATE TABLE IF NOT EXISTS `bankaccount` (
  `bankaccount_id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `cash_balance` int(11) NOT NULL,
  `net_worth` int(11) NOT NULL,
  PRIMARY KEY (`bankaccount_id`),
  KEY `bank_id` (`bank_id`),
  KEY `player_id` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `chancecards`
--

DROP TABLE IF EXISTS `chancecards`;
CREATE TABLE IF NOT EXISTS `chancecards` (
  `card_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `communitychestcards`
--

DROP TABLE IF EXISTS `communitychestcards`;
CREATE TABLE IF NOT EXISTS `communitychestcards` (
  `card_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
CREATE TABLE IF NOT EXISTS `game` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_id` int(11) DEFAULT NULL,
  `whos_turn_player_id` int(11) DEFAULT NULL,
  `gameboard_image_file` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`game_id`),
  KEY `whos_turn_player_id` (`whos_turn_player_id`),
  KEY `game_ibfk_1` (`bank_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`game_id`, `bank_id`, `whos_turn_player_id`, `gameboard_image_file`) VALUES
(1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `gameboard_images`
--

DROP TABLE IF EXISTS `gameboard_images`;
CREATE TABLE IF NOT EXISTS `gameboard_images` (
  `gameboard_id` int(11) NOT NULL AUTO_INCREMENT,
  `image_file_name` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`gameboard_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `gameboard_images`
--

INSERT INTO `gameboard_images` (`gameboard_id`, `image_file_name`, `name`) VALUES
(1, 'classic_game_board.png', 'Classic'),
(2, 'blank_game_board.png', 'Blank');

-- --------------------------------------------------------

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
CREATE TABLE IF NOT EXISTS `player` (
  `player_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `token_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL DEFAULT '1',
  `space_id` int(11) NOT NULL DEFAULT '0',
  `spectator` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`player_id`),
  KEY `user_id` (`user_id`),
  KEY `token_id` (`token_id`),
  KEY `game_id` (`game_id`),
  KEY `space_id` (`space_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `railroad_constants`
--

DROP TABLE IF EXISTS `railroad_constants`;
CREATE TABLE IF NOT EXISTS `railroad_constants` (
  `space_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `purchase_price` int(11) NOT NULL,
  `base_rent` int(11) NOT NULL,
  `mortgage_amount` int(11) NOT NULL,
  PRIMARY KEY (`space_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `railroad_constants`
--

INSERT INTO `railroad_constants` (`space_id`, `name`, `purchase_price`, `base_rent`, `mortgage_amount`) VALUES
(5, 'Reading Railroad', 200, 25, 100),
(15, 'Pennsylvania Railroad', 200, 25, 100),
(25, 'B. & O. Railroad', 200, 25, 100),
(35, 'Short Line Railroad', 200, 25, 100);

-- --------------------------------------------------------

--
-- Table structure for table `railroad_game_data`
--

DROP TABLE IF EXISTS `railroad_game_data`;
CREATE TABLE IF NOT EXISTS `railroad_game_data` (
  `space_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `has_mortgage` int(11) NOT NULL DEFAULT '0',
  `player_owner_id` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`space_id`,`game_id`),
  KEY `player_owner_id` (`player_owner_id`),
  KEY `game_id` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `realestate_constants`
--

DROP TABLE IF EXISTS `realestate_constants`;
CREATE TABLE IF NOT EXISTS `realestate_constants` (
  `space_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `color_group` varchar(45) NOT NULL,
  `purchase_price` int(11) NOT NULL,
  `number_for_monopoly` int(11) NOT NULL,
  `monopoly_rent` int(11) NOT NULL,
  `rent_0_houses` int(11) NOT NULL,
  `rent_1_houses` int(11) NOT NULL,
  `rent_2_houses` int(11) NOT NULL,
  `rent_3_houses` int(11) NOT NULL,
  `rent_4_houses` int(11) NOT NULL,
  `rent_5_houses` int(11) NOT NULL,
  `cost_of_a_house` int(11) NOT NULL,
  `mortgage_amount` int(11) NOT NULL,
  PRIMARY KEY (`space_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `realestate_constants`
--

INSERT INTO `realestate_constants` (`space_id`, `name`, `color_group`, `purchase_price`, `number_for_monopoly`, `monopoly_rent`, `rent_0_houses`, `rent_1_houses`, `rent_2_houses`, `rent_3_houses`, `rent_4_houses`, `rent_5_houses`, `cost_of_a_house`, `mortgage_amount`) VALUES
(1, 'Mediterranean Avenue', 'Brown', 60, 2, 4, 2, 10, 30, 90, 160, 250, 50, 30),
(3, 'Baltic Avenue', 'Brown', 60, 2, 8, 4, 20, 60, 180, 320, 450, 50, 30),
(6, 'Oriental Avenue', 'Light Blue', 100, 3, 12, 6, 30, 90, 270, 400, 550, 50, 50),
(8, 'Vermont Avenue', 'Light Blue', 100, 3, 12, 6, 30, 90, 270, 400, 550, 50, 50),
(9, 'Connecticut Avenue', 'Light Blue', 120, 3, 16, 8, 40, 100, 300, 450, 600, 50, 60),
(11, 'St. Charles Place', 'Pink', 140, 3, 20, 10, 50, 150, 450, 625, 750, 100, 70),
(13, 'States Avenue', 'Pink', 140, 3, 20, 10, 50, 150, 450, 625, 750, 100, 70),
(14, 'Virginia Avenue', 'Pink', 160, 3, 24, 12, 60, 180, 500, 700, 900, 100, 80),
(16, 'St. James Place', 'Orange', 180, 3, 28, 14, 70, 200, 550, 750, 950, 100, 90),
(18, 'Tennessee Avenue', 'Orange', 180, 3, 28, 14, 70, 200, 550, 750, 950, 100, 90),
(19, 'New York Avenue', 'Orange', 200, 3, 32, 16, 80, 220, 600, 800, 1000, 100, 100),
(21, 'Kentucky Avenue', 'Red', 220, 3, 36, 18, 90, 250, 700, 875, 1050, 150, 110),
(23, 'Indiana Avenue', 'Red', 220, 3, 36, 18, 90, 250, 700, 875, 1050, 150, 110),
(24, 'Illinois Avenue', 'Red', 240, 3, 40, 20, 100, 300, 750, 925, 1100, 150, 120),
(26, 'Atlantic Avenue', 'Yellow', 260, 3, 44, 22, 110, 330, 800, 975, 1150, 150, 130),
(27, 'Ventnor Avenue', 'Yellow', 260, 3, 44, 22, 110, 330, 800, 975, 1150, 150, 130),
(29, 'Marvin Gardens', 'Yellow', 280, 3, 48, 24, 120, 360, 850, 1025, 1200, 150, 140),
(31, 'Pacific Avenue', 'Green', 300, 3, 52, 26, 130, 390, 900, 1100, 1275, 200, 150),
(32, 'North Carolina Avenue', 'Green', 300, 3, 52, 26, 130, 390, 900, 1100, 1275, 200, 150),
(34, 'Pennsylvania Avenue', 'Green', 320, 3, 56, 28, 150, 450, 1000, 1200, 1400, 200, 160),
(37, 'Park Place', 'Dark Blue', 350, 2, 70, 35, 175, 500, 1100, 1300, 1500, 200, 175),
(39, 'Boardwalk', 'Dark Blue', 400, 2, 100, 50, 200, 600, 1400, 1700, 2000, 200, 200);

-- --------------------------------------------------------

--
-- Table structure for table `realestate_game_data`
--

DROP TABLE IF EXISTS `realestate_game_data`;
CREATE TABLE IF NOT EXISTS `realestate_game_data` (
  `space_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `number_houses` int(11) NOT NULL,
  `has_mortgage` int(11) NOT NULL DEFAULT '0',
  `player_owner_id` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`space_id`,`game_id`),
  KEY `player_owner_id` (`player_owner_id`),
  KEY `game_id` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `token_images`
--

DROP TABLE IF EXISTS `token_images`;
CREATE TABLE IF NOT EXISTS `token_images` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `image_file_name` varchar(45) NOT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `token_images`
--

INSERT INTO `token_images` (`token_id`, `image_file_name`) VALUES
(1, 'blue_token.png'),
(3, 'car.png'),
(4, 'cat.png'),
(5, 'dog.png'),
(6, 'gold_car.png'),
(7, 'green_token.png'),
(8, 'guitar.png'),
(9, 'hat.png'),
(10, 'helicopter.png'),
(11, 'horse.png'),
(12, 'iron.png'),
(13, 'purple_token.png'),
(14, 'red_token.png'),
(15, 'robot.png'),
(16, 'ship.png'),
(17, 'shoe.png'),
(19, 'wheelbarel.png'),
(20, 'white_token.png'),
(21, 'yellow_token.png');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `player_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `player_id` (`player_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `first_name`, `last_name`, `user_name`, `password`, `player_id`) VALUES
(4, 'sfsu', 'sfsu', 'sfsu', 'sfsu', NULL),
(5, 'csc', 'csc', 'csc', 'csc', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `utility_constants`
--

DROP TABLE IF EXISTS `utility_constants`;
CREATE TABLE IF NOT EXISTS `utility_constants` (
  `space_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `purchase_price` int(11) NOT NULL,
  `mortgage_amount` int(11) NOT NULL,
  PRIMARY KEY (`space_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utility_constants`
--

INSERT INTO `utility_constants` (`space_id`, `name`, `purchase_price`, `mortgage_amount`) VALUES
(12, 'Electric Company', 150, 75),
(28, 'Water Works', 150, 75);

-- --------------------------------------------------------

--
-- Table structure for table `utility_game_data`
--

DROP TABLE IF EXISTS `utility_game_data`;
CREATE TABLE IF NOT EXISTS `utility_game_data` (
  `space_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `has_mortgage` int(11) NOT NULL DEFAULT '0',
  `player_owner_id` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`space_id`,`game_id`),
  KEY `player_owner_id` (`player_owner_id`),
  KEY `game_id` (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bankaccount`
--
ALTER TABLE `bankaccount`
  ADD CONSTRAINT `bankaccount_ibfk_1` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`bank_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `bankaccount_ibfk_2` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`bank_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `game_ibfk_3` FOREIGN KEY (`whos_turn_player_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `player_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `player_ibfk_2` FOREIGN KEY (`token_id`) REFERENCES `token_images` (`token_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `player_ibfk_3` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `railroad_game_data`
--
ALTER TABLE `railroad_game_data`
  ADD CONSTRAINT `railroad_game_data_ibfk_5` FOREIGN KEY (`space_id`) REFERENCES `railroad_constants` (`space_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `railroad_game_data_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `railroad_game_data_ibfk_3` FOREIGN KEY (`player_owner_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `realestate_game_data`
--
ALTER TABLE `realestate_game_data`
  ADD CONSTRAINT `realestate_game_data_ibfk_3` FOREIGN KEY (`space_id`) REFERENCES `realestate_constants` (`space_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `realestate_game_data_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `realestate_game_data_ibfk_2` FOREIGN KEY (`player_owner_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `utility_game_data`
--
ALTER TABLE `utility_game_data`
  ADD CONSTRAINT `utility_game_data_ibfk_4` FOREIGN KEY (`space_id`) REFERENCES `utility_constants` (`space_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `utility_game_data_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `utility_game_data_ibfk_3` FOREIGN KEY (`player_owner_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
