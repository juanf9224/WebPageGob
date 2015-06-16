CREATE DATABASE  IF NOT EXISTS `ticketsmanagement` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ticketsmanagement`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: ticketsmanagement
-- ------------------------------------------------------
-- Server version	5.6.17

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
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_info_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `login_info_id` bigint(20) NOT NULL DEFAULT '0',
  `department_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_info_id`,`login_info_id`,`department_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `login_info_id_fk_idx` (`login_info_id`),
  KEY `department_id_fk_idx` (`department_id`),
  CONSTRAINT `department_id_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `login_info_id_fk` FOREIGN KEY (`login_info_id`) REFERENCES `login_info` (`login_info_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'juan','juanfh.24.6@hotmail.com','2014-12-15 20:23:41',1,1),(2,'Gabriel Baez','gabriel04@gmail.com','2014-12-15 20:23:41',2,1),(3,'Jean Baez','jbaez@gmail.com','2014-12-15 20:23:41',3,1),(4,'Marcos Reynoso','marcosrs@gmail.com','2014-12-15 20:23:41',4,1),(5,'Brey Henriquez','brey@gmail.com','2014-12-15 20:23:41',5,1),(6,'Rudy Fernandez','rudy@gmail.com','2014-12-15 20:23:41',6,6),(7,'klk','klk@gmail.com','2014-12-15 20:23:41',7,3),(8,'kl','kl@gmail.com','2014-12-15 20:23:41',8,3),(9,'esungenioteorico','esungeioteorico@gmail.com','2014-12-15 20:23:41',9,1),(10,'Patricia Curiel','pat06@gmail.com','2014-12-22 23:38:14',20,3),(11,'Lidia Quiñones','lidia@gmail.com','2014-12-26 06:14:00',21,5),(12,'Manuel Baez','manuel@gmail.com','2014-12-26 19:46:58',22,1),(13,'Ramon Villanueva','ramon@gmail.com','2014-12-27 04:49:16',23,1),(14,'Teresa Herrera','teresa@gmail.com','2014-12-27 05:01:19',24,2),(15,'Joselin Gonzalez','josy@gmail.com','2014-12-27 05:11:10',25,2),(16,'Laura Quinones','laura@gmail.com','2014-12-27 05:46:31',26,4),(17,'Juan Frias','frias@gmail.com','2014-12-27 06:08:28',27,5),(18,'Rayner Baez','ray@gmail.com','2014-12-27 06:10:11',28,2),(19,'Lourdes Gomez','luu@gmail.com','2014-12-27 06:15:02',29,6);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-29 21:23:34
