-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 05, 2015 at 07:29 AM
-- Server version: 5.5.32
-- PHP Version: 5.3.10-1ubuntu3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: 'monopoly'
--

-- --------------------------------------------------------

--
-- Table structure for table 'actionspace'
--

CREATE TABLE IF NOT EXISTS actionspace (
  space_id int(11) NOT NULL,
  game_id int(11) NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (space_id,game_id),
  KEY game_id (game_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE actionspace:
--   game_id
--       game -> game_id
--

-- --------------------------------------------------------

--
-- Table structure for table 'bank'
--

CREATE TABLE IF NOT EXISTS bank (
  bank_id int(11) NOT NULL AUTO_INCREMENT,
  number_houses int(11) NOT NULL,
  PRIMARY KEY (bank_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table 'bankaccount'
--

CREATE TABLE IF NOT EXISTS bankaccount (
  bankaccount_id int(11) NOT NULL AUTO_INCREMENT,
  bank_id int(11) NOT NULL,
  player_id int(11) NOT NULL,
  cash_balance int(11) NOT NULL,
  net_worth int(11) NOT NULL,
  PRIMARY KEY (bankaccount_id),
  KEY bank_id (bank_id),
  KEY player_id (player_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- RELATIONS FOR TABLE bankaccount:
--   player_id
--       player -> player_id
--   bank_id
--       bank -> bank_id
--

-- --------------------------------------------------------

--
-- Table structure for table 'game'
--

CREATE TABLE IF NOT EXISTS game (
  game_id int(11) NOT NULL AUTO_INCREMENT,
  bank_id int(11) NOT NULL,
  gameboard_id int(11) NOT NULL,
  whos_turn_player_id int(11) DEFAULT NULL,
  PRIMARY KEY (game_id),
  UNIQUE KEY bank_id (bank_id),
  UNIQUE KEY gameboard_id (gameboard_id),
  KEY whos_turn_player_id (whos_turn_player_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- RELATIONS FOR TABLE game:
--   whos_turn_player_id
--       player -> player_id
--   bank_id
--       bank -> bank_id
--   gameboard_id
--       gameboard -> gameboard_id
--

-- --------------------------------------------------------

--
-- Table structure for table 'gameboard'
--

CREATE TABLE IF NOT EXISTS gameboard (
  gameboard_id int(11) NOT NULL,
  image_file_name varchar(45) NOT NULL,
  PRIMARY KEY (gameboard_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table 'gameboard'
--

INSERT INTO gameboard (gameboard_id, image_file_name) VALUES
(0, 'classic_game_board.png');

-- --------------------------------------------------------

--
-- Table structure for table 'player'
--

CREATE TABLE IF NOT EXISTS player (
  player_id int(11) NOT NULL AUTO_INCREMENT,
  user_id int(11) NOT NULL,
  token_id int(11) NOT NULL,
  game_id int(11) NOT NULL,
  space_id int(11) NOT NULL,
  bankrupt int(11) NOT NULL,
  active_player int(11) NOT NULL,
  PRIMARY KEY (player_id),
  KEY user_id (user_id),
  KEY token_id (token_id),
  KEY game_id (game_id),
  KEY space_id (space_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- RELATIONS FOR TABLE player:
--   game_id
--       game -> game_id
--   user_id
--       `user` -> user_id
--   token_id
--       token -> token_id
--

-- --------------------------------------------------------

--
-- Table structure for table 'railroad'
--

CREATE TABLE IF NOT EXISTS railroad (
  space_id int(11) NOT NULL,
  game_id int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  purchase_price int(11) NOT NULL,
  base_rent int(11) NOT NULL,
  mortgage_amount int(11) NOT NULL,
  has_mortgage int(11) NOT NULL,
  bank_owner_id int(11) DEFAULT NULL,
  player_owner_id int(11) DEFAULT NULL,
  PRIMARY KEY (space_id,game_id),
  KEY bank_owner_id (bank_owner_id),
  KEY player_owner_id (player_owner_id),
  KEY game_id (game_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE railroad:
--   player_owner_id
--       player -> player_id
--   game_id
--       game -> game_id
--   bank_owner_id
--       bank -> bank_id
--

-- --------------------------------------------------------

--
-- Table structure for table 'realestate'
--

CREATE TABLE IF NOT EXISTS realestate (
  space_id int(11) NOT NULL,
  game_id int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  color_group varchar(45) NOT NULL,
  purchase_price int(11) NOT NULL,
  number_houses int(11) NOT NULL,
  number_for_monopoly int(11) NOT NULL,
  monopoly_rent int(11) NOT NULL,
  rent_0_houses int(11) NOT NULL,
  rent_1_houses int(11) NOT NULL,
  rent_2_houses int(11) NOT NULL,
  rent_3_houses int(11) NOT NULL,
  rent_4_houses int(11) NOT NULL,
  rent_5_houses int(11) NOT NULL,
  cost_of_a_house int(11) NOT NULL,
  mortgage_amount int(11) NOT NULL,
  has_mortgage int(11) NOT NULL,
  player_owner_id int(11) DEFAULT NULL,
  bank_owner_id int(11) DEFAULT NULL,
  PRIMARY KEY (space_id,game_id),
  KEY player_owner_id (player_owner_id),
  KEY bank_owner_id (bank_owner_id),
  KEY game_id (game_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE realestate:
--   bank_owner_id
--       bank -> bank_id
--   game_id
--       game -> game_id
--   player_owner_id
--       player -> player_id
--

-- --------------------------------------------------------

--
-- Table structure for table 'token'
--

CREATE TABLE IF NOT EXISTS token (
  token_id int(11) NOT NULL AUTO_INCREMENT,
  image_file_name varchar(45) NOT NULL,
  PRIMARY KEY (token_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumping data for table 'token'
--

INSERT INTO token (token_id, image_file_name) VALUES
(1, 'blue_token.png'),
(2, 'canon.png'),
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
(18, 'thimble.png'),
(19, 'wheelbarel.png'),
(20, 'white_token.png'),
(21, 'yellow_token.png');

-- --------------------------------------------------------

--
-- Table structure for table 'user'
--

CREATE TABLE IF NOT EXISTS `user` (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  first_name varchar(45) NOT NULL,
  last_name varchar(45) NOT NULL,
  user_name varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  player_id int(11) DEFAULT NULL,
  PRIMARY KEY (user_id),
  KEY player_id (player_id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- RELATIONS FOR TABLE `user`:
--   player_id
--       player -> player_id
--

--
-- Dumping data for table 'user'
--

INSERT INTO `user` (user_id, first_name, last_name, user_name, `password`, player_id) VALUES
(4, 'sfsu', 'sfsu', 'sfsu', 'sfsu', NULL),
(5, 'csc', 'csc', 'csc', 'csc', NULL);

-- --------------------------------------------------------

--
-- Table structure for table 'utility'
--

CREATE TABLE IF NOT EXISTS utility (
  space_id int(11) NOT NULL,
  game_id int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  purchase_price int(11) NOT NULL,
  base_rent int(11) NOT NULL,
  number_for_monopoly int(11) NOT NULL,
  monopoly_rent int(11) NOT NULL,
  mortgage_amount int(11) NOT NULL,
  has_mortgage int(11) NOT NULL,
  bank_owener_id int(11) DEFAULT NULL,
  player_owner_id int(11) DEFAULT NULL,
  PRIMARY KEY (space_id,game_id),
  KEY bank_owener_id (bank_owener_id),
  KEY player_owner_id (player_owner_id),
  KEY game_id (game_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELATIONS FOR TABLE utility:
--   game_id
--       game -> game_id
--   bank_owener_id
--       bank -> bank_id
--   player_owner_id
--       player -> player_id
--

--
-- Constraints for dumped tables
--

--
-- Constraints for table `actionspace`
--
ALTER TABLE `actionspace`
  ADD CONSTRAINT actionspace_ibfk_1 FOREIGN KEY (game_id) REFERENCES game (game_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `bankaccount`
--
ALTER TABLE `bankaccount`
  ADD CONSTRAINT bankaccount_ibfk_2 FOREIGN KEY (player_id) REFERENCES player (player_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT bankaccount_ibfk_1 FOREIGN KEY (bank_id) REFERENCES bank (bank_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT game_ibfk_3 FOREIGN KEY (whos_turn_player_id) REFERENCES player (player_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT game_ibfk_1 FOREIGN KEY (bank_id) REFERENCES bank (bank_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT game_ibfk_2 FOREIGN KEY (gameboard_id) REFERENCES gameboard (gameboard_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT player_ibfk_3 FOREIGN KEY (game_id) REFERENCES game (game_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT player_ibfk_1 FOREIGN KEY (user_id) REFERENCES `user` (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT player_ibfk_2 FOREIGN KEY (token_id) REFERENCES token (token_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `railroad`
--
ALTER TABLE `railroad`
  ADD CONSTRAINT railroad_ibfk_3 FOREIGN KEY (player_owner_id) REFERENCES player (player_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT railroad_ibfk_1 FOREIGN KEY (game_id) REFERENCES game (game_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT railroad_ibfk_2 FOREIGN KEY (bank_owner_id) REFERENCES bank (bank_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `realestate`
--
ALTER TABLE `realestate`
  ADD CONSTRAINT realestate_ibfk_3 FOREIGN KEY (bank_owner_id) REFERENCES bank (bank_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT realestate_ibfk_1 FOREIGN KEY (game_id) REFERENCES game (game_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT realestate_ibfk_2 FOREIGN KEY (player_owner_id) REFERENCES player (player_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT user_ibfk_1 FOREIGN KEY (player_id) REFERENCES player (player_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `utility`
--
ALTER TABLE `utility`
  ADD CONSTRAINT utility_ibfk_1 FOREIGN KEY (game_id) REFERENCES game (game_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT utility_ibfk_2 FOREIGN KEY (bank_owener_id) REFERENCES bank (bank_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT utility_ibfk_3 FOREIGN KEY (player_owner_id) REFERENCES player (player_id) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
