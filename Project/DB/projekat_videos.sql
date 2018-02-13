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
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `videos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) NOT NULL,
  `name` varchar(45) NOT NULL,
  `thumbnail` varchar(128) DEFAULT NULL,
  `description` longtext,
  `visibility` tinyint(4) NOT NULL,
  `comments` tinyint(4) NOT NULL,
  `rate` tinyint(4) NOT NULL,
  `blocked` tinyint(4) NOT NULL,
  `views` int(11) NOT NULL,
  `date` date NOT NULL,
  `owner` varchar(32) NOT NULL,
  `deleted` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fkVideoOwner_idx` (`owner`),
  CONSTRAINT `fkVideoOwner` FOREIGN KEY (`owner`) REFERENCES `users` (`userName`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (1,'KttDVks6kV8','KttDVks6kV8','http://www.the3pointconversion.com/wp-content/uploads/2017/06/01-02-Kings-starting-lineup.jpg','Desc',1,1,1,0,4,'2018-02-13','User1',0),(2,'KttDVks6kV8','KttDVks6kV8','http://www.the3pointconversion.com/wp-content/uploads/2017/06/01-02-Kings-starting-lineup.jpg','Desc',2,1,1,0,0,'2018-02-13','User2',0),(16,'KttDVks6kV8','KttDVks6kV8','http://www.the3pointconversion.com/wp-content/uploads/2017/06/01-02-Kings-starting-lineup.jpg','Desc',0,1,1,0,14,'2018-02-13','User3',0),(17,'y6120QOlsfU','y6120QOlsfU','','',0,1,1,0,24,'2018-03-13','User1',0),(18,'dQw4w9WgXcQ','dQw4w9WgXcQ','','',0,1,1,0,34,'2018-01-13','User1',0),(19,'s9Hb-hpkDYc','s9Hb-hpkDYc','','',0,0,1,0,44,'2018-04-13','User1',0),(20,'zDebdnDG6jA','zDebdnDG6jA','','',1,1,1,0,6,'2018-02-13','User1',0);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
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
