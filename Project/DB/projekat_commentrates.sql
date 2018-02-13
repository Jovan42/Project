-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: projekat
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `commentrates`
--

DROP TABLE IF EXISTS `commentrates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commentrates` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `like` tinyint(4) NOT NULL,
  `date` date NOT NULL,
  `owner` varchar(32) NOT NULL,
  `commentID` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkOwnerOfCommetnRate_idx` (`owner`),
  KEY `fkCommentRated_idx` (`commentID`),
  CONSTRAINT `fkCommentRated` FOREIGN KEY (`commentID`) REFERENCES `comments` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkOwnerOfCommetnRate` FOREIGN KEY (`owner`) REFERENCES `users` (`userName`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentrates`
--

LOCK TABLES `commentrates` WRITE;
/*!40000 ALTER TABLE `commentrates` DISABLE KEYS */;
INSERT INTO `commentrates` VALUES (9,1,'2018-02-13','User1',11),(10,1,'2018-02-13','User2',11),(11,0,'2018-02-13','User3',11),(12,1,'2018-02-13','User1',12),(13,1,'2018-02-13','User2',12),(14,1,'2018-02-13','User3',12),(15,0,'2018-02-13','User1',13),(16,0,'2018-02-13','User2',13),(17,0,'2018-02-13','User3',13),(18,1,'2018-02-13','User1',14),(19,1,'2018-02-13','User2',14),(20,0,'2018-02-13','User3',14),(21,1,'2018-02-13','User1',15),(22,1,'2018-02-13','User2',15),(23,1,'2018-02-13','User3',15),(24,0,'2018-02-13','User1',16),(25,0,'2018-02-13','User2',16),(26,0,'2018-02-13','User3',16),(27,1,'2018-02-13','User1',17),(28,1,'2018-02-13','User2',17),(29,0,'2018-02-13','User3',17),(30,1,'2018-02-13','User1',18),(31,1,'2018-02-13','User2',18),(32,1,'2018-02-13','User3',18),(33,0,'2018-02-13','User1',19),(34,0,'2018-02-13','User2',19),(35,0,'2018-02-13','User3',19),(36,1,'2018-02-13','User1',20),(37,1,'2018-02-13','User2',20),(38,0,'2018-02-13','User3',20),(39,1,'2018-02-13','User1',21),(40,1,'2018-02-13','User2',21),(41,1,'2018-02-13','User3',21),(42,0,'2018-02-13','User1',22),(43,0,'2018-02-13','User2',22),(44,0,'2018-02-13','User3',22),(45,1,'2018-02-13','User1',23),(46,1,'2018-02-13','User2',23),(47,0,'2018-02-13','User3',23),(48,1,'2018-02-13','User1',24),(49,1,'2018-02-13','User2',24),(50,1,'2018-02-13','User3',24),(51,0,'2018-02-13','User1',25),(52,0,'2018-02-13','User2',25),(53,0,'2018-02-13','User3',25),(54,1,'2018-02-13','User1',26),(55,1,'2018-02-13','User2',26),(56,0,'2018-02-13','User3',26),(57,1,'2018-02-13','User1',27),(58,1,'2018-02-13','User2',27),(59,1,'2018-02-13','User3',27),(60,0,'2018-02-13','User1',28),(61,0,'2018-02-13','User2',28),(62,0,'2018-02-13','User3',28);
/*!40000 ALTER TABLE `commentrates` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-13  5:12:37
