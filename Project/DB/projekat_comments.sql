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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `date` date NOT NULL,
  `owner` varchar(32) NOT NULL,
  `videoID` int(11) NOT NULL,
  `deleted` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fkCommentOwenr_idx` (`owner`),
  KEY `fkVideoCommented_idx` (`videoID`),
  CONSTRAINT `fkCommentOwenr` FOREIGN KEY (`owner`) REFERENCES `users` (`userName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkVideoCommented` FOREIGN KEY (`videoID`) REFERENCES `videos` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (11,'Komentar 1','2018-02-13','User1',18,0),(12,'Komentar 2','2018-02-14','User1',18,0),(13,'Komentar 3','2018-02-15','User1',18,0),(14,'Komentar 1','2018-02-13','User1',1,0),(15,'Komentar 2','2018-02-14','User1',1,0),(16,'Komentar 3','2018-02-15','User1',1,0),(17,'Komentar 1','2018-02-16','User1',16,0),(18,'Komentar 2','2018-02-13','User1',16,0),(19,'Komentar 3','2018-02-17','User1',16,0),(20,'Komentar 1','2018-02-13','User1',19,0),(21,'Komentar 2','2018-02-14','User1',19,0),(22,'Komentar 3','2018-02-15','User1',19,0),(23,'Komentar 1','2018-02-16','User1',17,0),(24,'Komentar 2','2018-02-18','User1',17,0),(25,'Komentar 3','2018-02-15','User1',17,0),(26,'Komentar 1','2018-02-11','User1',20,0),(27,'Komentar 2','2018-02-15','User1',20,0),(28,'Komentar 3','2018-02-19','User1',20,0);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-13  5:12:38
