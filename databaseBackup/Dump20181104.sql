-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: meeting_schedule
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `availabilities`
--

DROP TABLE IF EXISTS `availabilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `availabilities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meeting_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `timezone_id` int(11) NOT NULL,
  `day_id` int(11) NOT NULL,
  `availability` varchar(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `availability_memebers_fk_idx` (`member_id`),
  KEY `availability_meeting_fk_idx` (`meeting_id`),
  KEY `availability_timezone_fk_idx` (`timezone_id`),
  KEY `availability_day_fk_idx` (`day_id`),
  CONSTRAINT `availability_day_fk` FOREIGN KEY (`day_id`) REFERENCES `days` (`id`),
  CONSTRAINT `availability_meeting_fk` FOREIGN KEY (`meeting_id`) REFERENCES `meetings` (`id`),
  CONSTRAINT `availability_memebers_fk` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`),
  CONSTRAINT `availability_timezone_fk` FOREIGN KEY (`timezone_id`) REFERENCES `timezones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `availabilities`
--

LOCK TABLES `availabilities` WRITE;
/*!40000 ALTER TABLE `availabilities` DISABLE KEYS */;
/*!40000 ALTER TABLE `availabilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `days`
--

DROP TABLE IF EXISTS `days`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `days` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `date_UNIQUE` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `days`
--

LOCK TABLES `days` WRITE;
/*!40000 ALTER TABLE `days` DISABLE KEYS */;
/*!40000 ALTER TABLE `days` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meetings`
--

DROP TABLE IF EXISTS `meetings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `meetings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `date` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `completed` varchar(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meetings`
--

LOCK TABLES `meetings` WRITE;
/*!40000 ALTER TABLE `meetings` DISABLE KEYS */;
/*!40000 ALTER TABLE `meetings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meetings_members`
--

DROP TABLE IF EXISTS `meetings_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `meetings_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `meeting_id` int(11) NOT NULL,
  `attended` varchar(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `meeting_members_fk1_idx` (`member_id`),
  KEY `meetings_members_fk2_idx` (`meeting_id`),
  CONSTRAINT `meetings_members_fk1` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`),
  CONSTRAINT `meetings_members_fk2` FOREIGN KEY (`meeting_id`) REFERENCES `meetings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meetings_members`
--

LOCK TABLES `meetings_members` WRITE;
/*!40000 ALTER TABLE `meetings_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `meetings_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `possible_meetings`
--

DROP TABLE IF EXISTS `possible_meetings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `possible_meetings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meeting_id` int(11) NOT NULL,
  `day_id` int(11) NOT NULL,
  `timezone_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `possible_meeting_day_fk_idx` (`day_id`),
  KEY `possible_meeting_fk_idx` (`meeting_id`),
  KEY `possible_meeting_timezones_fk_idx` (`timezone_id`),
  CONSTRAINT `possible_meeting_day_fk` FOREIGN KEY (`day_id`) REFERENCES `days` (`id`),
  CONSTRAINT `possible_meeting_fk` FOREIGN KEY (`meeting_id`) REFERENCES `meetings` (`id`),
  CONSTRAINT `possible_meeting_timezones_fk` FOREIGN KEY (`timezone_id`) REFERENCES `timezones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `possible_meetings`
--

LOCK TABLES `possible_meetings` WRITE;
/*!40000 ALTER TABLE `possible_meetings` DISABLE KEYS */;
/*!40000 ALTER TABLE `possible_meetings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `possible_meetings_members`
--

DROP TABLE IF EXISTS `possible_meetings_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `possible_meetings_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `possible_meeting_id` int(11) NOT NULL,
  `attending` varchar(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `members_possible_meetings_fk1_idx` (`member_id`),
  KEY `members_possible_meetings_fk2_idx` (`possible_meeting_id`),
  CONSTRAINT `members_possible_meetings_fk1` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`),
  CONSTRAINT `members_possible_meetings_fk2` FOREIGN KEY (`possible_meeting_id`) REFERENCES `possible_meetings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `possible_meetings_members`
--

LOCK TABLES `possible_meetings_members` WRITE;
/*!40000 ALTER TABLE `possible_meetings_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `possible_meetings_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timezones`
--

DROP TABLE IF EXISTS `timezones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `timezones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timezones`
--

LOCK TABLES `timezones` WRITE;
/*!40000 ALTER TABLE `timezones` DISABLE KEYS */;
INSERT INTO `timezones` VALUES (1,'09:00:00','10:00:00'),(2,'10:00:00','11:00:00'),(3,'11:00:00','12:00:00'),(4,'12:00:00','13:00:00'),(5,'13:00:00','14:00:00'),(6,'14:00:00','15:00:00'),(7,'15:00:00','16:00:00'),(8,'16:00:00','17:00:00'),(9,'17:00:00','18:00:00'),(10,'18:00:00','19:00:00'),(11,'19:00:00','20:00:00'),(12,'20:00:00','21:00:00'),(13,'09:00:00','11:00:00'),(14,'10:00:00','12:00:00'),(15,'11:00:00','13:00:00'),(16,'12:00:00','14:00:00'),(17,'13:00:00','15:00:00'),(18,'14:00:00','16:00:00'),(19,'15:00:00','17:00:00'),(20,'16:00:00','18:00:00'),(21,'17:00:00','19:00:00'),(22,'18:00:00','20:00:00'),(23,'19:00:00','21:00:00'),(24,'09:00:00','12:00:00'),(25,'10:00:00','13:00:00'),(26,'11:00:00','14:00:00'),(27,'12:00:00','15:00:00'),(28,'13:00:00','16:00:00'),(29,'14:00:00','17:00:00'),(30,'15:00:00','18:00:00'),(31,'16:00:00','19:00:00'),(32,'17:00:00','20:00:00'),(33,'18:00:00','21:00:00'),(34,'09:00:00','13:00:00'),(35,'10:00:00','14:00:00'),(36,'11:00:00','15:00:00'),(37,'12:00:00','16:00:00'),(38,'13:00:00','17:00:00'),(39,'14:00:00','18:00:00'),(40,'15:00:00','19:00:00'),(41,'16:00:00','20:00:00'),(42,'17:00:00','21:00:00'),(43,'09:30:00','10:30:00'),(44,'10:30:00','11:30:00'),(45,'11:30:00','12:30:00'),(46,'12:30:00','13:30:00'),(47,'13:30:00','14:30:00'),(48,'14:30:00','15:30:00'),(49,'15:30:00','16:30:00'),(50,'16:30:00','17:30:00'),(51,'17:30:00','18:30:00'),(52,'18:30:00','19:30:00'),(53,'19:30:00','20:30:00'),(54,'20:30:00','21:30:00'),(55,'09:30:00','11:30:00'),(56,'10:30:00','12:30:00'),(57,'11:30:00','13:30:00'),(58,'12:30:00','14:30:00'),(59,'13:30:00','15:30:00'),(60,'14:30:00','16:30:00'),(61,'15:30:00','17:30:00'),(62,'16:30:00','18:30:00'),(63,'17:30:00','19:30:00'),(64,'18:30:00','20:30:00'),(65,'19:30:00','21:30:00'),(66,'09:30:00','12:30:00'),(67,'10:30:00','13:30:00'),(68,'11:30:00','14:30:00'),(69,'12:30:00','15:30:00'),(70,'13:30:00','16:30:00'),(71,'14:30:00','17:30:00'),(72,'15:30:00','18:30:00'),(73,'16:30:00','19:30:00'),(74,'17:30:00','20:30:00'),(75,'18:30:00','21:30:00'),(76,'09:30:00','13:30:00'),(77,'10:30:00','14:30:00'),(78,'11:30:00','15:30:00'),(79,'12:30:00','16:30:00'),(80,'13:30:00','17:30:00'),(81,'14:30:00','18:30:00'),(82,'15:30:00','19:30:00'),(83,'16:30:00','20:30:00'),(84,'17:30:00','21:30:00');
/*!40000 ALTER TABLE `timezones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','admin','1234','admin@admin.gr');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-04  2:39:59
