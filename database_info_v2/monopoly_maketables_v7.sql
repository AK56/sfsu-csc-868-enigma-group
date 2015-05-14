-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: monopoly
-- ------------------------------------------------------
-- Server version	5.6.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actionspace_constants`
--

DROP TABLE IF EXISTS `actionspace_constants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actionspace_constants` (
  `space_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`space_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actionspace_constants`
--

LOCK TABLES `actionspace_constants` WRITE;
/*!40000 ALTER TABLE `actionspace_constants` DISABLE KEYS */;
INSERT INTO `actionspace_constants` VALUES (0,'GO'),(2,'Community Chest'),(4,'Income Tax'),(7,'Chance'),(10,'Jail Or Just Visiting'),(17,'Community Chest'),(20,'Free Parking'),(22,'Chance'),(30,'Go To Jail'),(33,'Community Chest'),(36,'Chance'),(38,'Luxury Tax');
/*!40000 ALTER TABLE `actionspace_constants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `number_houses` int(11) NOT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bankaccount`
--

DROP TABLE IF EXISTS `bankaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bankaccount` (
  `bankaccount_id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  `cash_balance` int(11) NOT NULL,
  `net_worth` int(11) NOT NULL,
  PRIMARY KEY (`bankaccount_id`),
  KEY `bank_id` (`bank_id`),
  KEY `player_id` (`player_id`),
  CONSTRAINT `bankaccount_ibfk_1` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`bank_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `bankaccount_ibfk_2` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankaccount`
--

LOCK TABLES `bankaccount` WRITE;
/*!40000 ALTER TABLE `bankaccount` DISABLE KEYS */;
/*!40000 ALTER TABLE `bankaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chancecards`
--

DROP TABLE IF EXISTS `chancecards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chancecards` (
  `card_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`card_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chancecards`
--

LOCK TABLES `chancecards` WRITE;
/*!40000 ALTER TABLE `chancecards` DISABLE KEYS */;
/*!40000 ALTER TABLE `chancecards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `communitychestcards`
--

DROP TABLE IF EXISTS `communitychestcards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `communitychestcards` (
  `card_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `communitychestcards`
--

LOCK TABLES `communitychestcards` WRITE;
/*!40000 ALTER TABLE `communitychestcards` DISABLE KEYS */;
/*!40000 ALTER TABLE `communitychestcards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_id` int(11) DEFAULT NULL,
  `whos_turn_player_id` int(11) DEFAULT NULL,
  `gameboard_image_file` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`game_id`),
  KEY `whos_turn_player_id` (`whos_turn_player_id`),
  KEY `game_ibfk_1` (`bank_id`),
  CONSTRAINT `game_ibfk_1` FOREIGN KEY (`bank_id`) REFERENCES `bank` (`bank_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `game_ibfk_3` FOREIGN KEY (`whos_turn_player_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,NULL,NULL,NULL);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
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
  KEY `space_id` (`space_id`),
  CONSTRAINT `player_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `player_ibfk_2` FOREIGN KEY (`token_id`) REFERENCES `token_images` (`token_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `player_ibfk_3` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `railroad_constants`
--

DROP TABLE IF EXISTS `railroad_constants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `railroad_constants` (
  `space_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `purchase_price` int(11) NOT NULL,
  `base_rent` int(11) NOT NULL,
  `mortgage_amount` int(11) NOT NULL,
  PRIMARY KEY (`space_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `railroad_constants`
--

LOCK TABLES `railroad_constants` WRITE;
/*!40000 ALTER TABLE `railroad_constants` DISABLE KEYS */;
INSERT INTO `railroad_constants` VALUES (5,'Reading Railroad',200,25,100),(15,'Pennsylvania Railroad',200,25,100),(25,'B. & O. Railroad',200,25,100),(35,'Short Line Railroad',200,25,100);
/*!40000 ALTER TABLE `railroad_constants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `railroad_game_data`
--

DROP TABLE IF EXISTS `railroad_game_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `railroad_game_data` (
  `space_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `has_mortgage` int(11) NOT NULL DEFAULT '0',
  `player_owner_id` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`space_id`,`game_id`),
  KEY `player_owner_id` (`player_owner_id`),
  KEY `game_id` (`game_id`),
  CONSTRAINT `railroad_game_data_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `railroad_game_data_ibfk_3` FOREIGN KEY (`player_owner_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `railroad_game_data`
--

LOCK TABLES `railroad_game_data` WRITE;
/*!40000 ALTER TABLE `railroad_game_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `railroad_game_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `realestate_constants`
--

DROP TABLE IF EXISTS `realestate_constants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `realestate_constants` (
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realestate_constants`
--

LOCK TABLES `realestate_constants` WRITE;
/*!40000 ALTER TABLE `realestate_constants` DISABLE KEYS */;
INSERT INTO `realestate_constants` VALUES (1,'Mediterranean Avenue','Brown',60,2,4,2,10,30,90,160,250,50,30),(3,'Baltic Avenue','Brown',60,2,8,4,20,60,180,320,450,50,30),(6,'Oriental Avenue','Light Blue',100,3,12,6,30,90,270,400,550,50,50),(8,'Vermont Avenue','Light Blue',100,3,12,6,30,90,270,400,550,50,50),(9,'Connecticut Avenue','Light Blue',120,3,16,8,40,100,300,450,600,50,60),(11,'St. Charles Place','Pink',140,3,20,10,50,150,450,625,750,100,70),(13,'States Avenue','Pink',140,3,20,10,50,150,450,625,750,100,70),(14,'Virginia Avenue','Pink',160,3,24,12,60,180,500,700,900,100,80),(16,'St. James Place','Orange',180,3,28,14,70,200,550,750,950,100,90),(18,'Tennessee Avenue','Orange',180,3,28,14,70,200,550,750,950,100,90),(19,'New York Avenue','Orange',200,3,32,16,80,220,600,800,1000,100,100),(21,'Kentucky Avenue','Red',220,3,36,18,90,250,700,875,1050,150,110),(23,'Indiana Avenue','Red',220,3,36,18,90,250,700,875,1050,150,110),(24,'Illinois Avenue','Red',240,3,40,20,100,300,750,925,1100,150,120),(26,'Atlantic Avenue','Yellow',260,3,44,22,110,330,800,975,1150,150,130),(27,'Ventnor Avenue','Yellow',260,3,44,22,110,330,800,975,1150,150,130),(29,'Marvin Gardens','Yellow',280,3,48,24,120,360,850,1025,1200,150,140),(31,'Pacific Avenue','Green',300,3,52,26,130,390,900,1100,1275,200,150),(32,'North Carolina Avenue','Green',300,3,52,26,130,390,900,1100,1275,200,150),(34,'Pennsylvania Avenue','Green',320,3,56,28,150,450,1000,1200,1400,200,160),(37,'Park Place','Dark Blue',350,2,70,35,175,500,1100,1300,1500,200,175),(39,'Boardwalk','Dark Blue',400,2,100,50,200,600,1400,1700,2000,200,200);
/*!40000 ALTER TABLE `realestate_constants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `realestate_game_data`
--

DROP TABLE IF EXISTS `realestate_game_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `realestate_game_data` (
  `space_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `number_houses` int(11) NOT NULL,
  `has_mortgage` int(11) NOT NULL DEFAULT '0',
  `player_owner_id` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`space_id`,`game_id`),
  KEY `player_owner_id` (`player_owner_id`),
  KEY `game_id` (`game_id`),
  CONSTRAINT `realestate_game_data_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `realestate_game_data_ibfk_2` FOREIGN KEY (`player_owner_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `realestate_game_data`
--

LOCK TABLES `realestate_game_data` WRITE;
/*!40000 ALTER TABLE `realestate_game_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `realestate_game_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_images`
--

DROP TABLE IF EXISTS `token_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token_images` (
  `token_id` int(11) NOT NULL AUTO_INCREMENT,
  `image_file_name` varchar(45) NOT NULL,
  PRIMARY KEY (`token_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_images`
--

LOCK TABLES `token_images` WRITE;
/*!40000 ALTER TABLE `token_images` DISABLE KEYS */;
INSERT INTO `token_images` VALUES (1,'blue_token.png'),(3,'car.png'),(4,'cat.png'),(5,'dog.png'),(6,'gold_car.png'),(7,'green_token.png'),(8,'guitar.png'),(9,'hat.png'),(10,'helicopter.png'),(11,'horse.png'),(12,'iron.png'),(13,'purple_token.png'),(14,'red_token.png'),(15,'robot.png'),(16,'ship.png'),(17,'shoe.png'),(19,'wheelbarel.png'),(20,'white_token.png'),(21,'yellow_token.png');
/*!40000 ALTER TABLE `token_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `player_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `player_id` (`player_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`player_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'sfsu','sfsu','sfsu','sfsu',NULL),(5,'csc','csc','csc','csc',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utility_constants`
--

DROP TABLE IF EXISTS `utility_constants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utility_constants` (
  `space_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `purchase_price` int(11) NOT NULL,
  `mortgage_amount` int(11) NOT NULL,
  PRIMARY KEY (`space_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utility_constants`
--

LOCK TABLES `utility_constants` WRITE;
/*!40000 ALTER TABLE `utility_constants` DISABLE KEYS */;
INSERT INTO `utility_constants` VALUES (12,'Electric Company',150,75),(28,'Water Works',150,75);
/*!40000 ALTER TABLE `utility_constants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utility_game_data`
--

DROP TABLE IF EXISTS `utility_game_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utility_game_data` (
  `space_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `has_mortgage` int(11) NOT NULL DEFAULT '0',
  `player_owner_id` int(11) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`space_id`,`game_id`),
  KEY `player_owner_id` (`player_owner_id`),
  KEY `game_id` (`game_id`),
  CONSTRAINT `utility_game_data_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `game` (`game_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `utility_game_data_ibfk_3` FOREIGN KEY (`player_owner_id`) REFERENCES `player` (`player_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utility_game_data`
--

LOCK TABLES `utility_game_data` WRITE;
/*!40000 ALTER TABLE `utility_game_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `utility_game_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-13 19:30:59
