-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: nba
-- ------------------------------------------------------
-- Server version	8.0.15

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
-- Table structure for table `match_substitution_history`
--

DROP TABLE IF EXISTS `match_substitution_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `match_substitution_history` (
  `match_substitution_history_id` int(11) NOT NULL AUTO_INCREMENT,
  `match_id` int(11) DEFAULT NULL,
  `leaving_player_id` int(11) DEFAULT NULL,
  `substitution_time` varchar(45) DEFAULT NULL,
  `entering_player_id` int(11) DEFAULT NULL,
  `substitution_reason_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`match_substitution_history_id`),
  KEY `match_id2` (`match_id`),
  KEY `substitution_reason_id_idx` (`substitution_reason_id`),
  KEY `leaving_player_id_idx` (`leaving_player_id`),
  KEY `entering_player_id_idx` (`entering_player_id`),
  CONSTRAINT `match_id2` FOREIGN KEY (`match_id`) REFERENCES `matches` (`match_id`),
  CONSTRAINT `substitution_reason_id` FOREIGN KEY (`substitution_reason_id`) REFERENCES `substitution_reasons` (`reason_id`),
  CONSTRAINT `leaving_player_id` FOREIGN KEY (`leaving_player_id`) REFERENCES `players` (`player_id`),
  CONSTRAINT `entering_player_id` FOREIGN KEY (`entering_player_id`) REFERENCES `players` (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_substitution_history`
--

LOCK TABLES `match_substitution_history` WRITE;
/*!40000 ALTER TABLE `match_substitution_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_substitution_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-24  1:20:06
