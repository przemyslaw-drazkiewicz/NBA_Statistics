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
-- Table structure for table `help`
--

DROP TABLE IF EXISTS `help`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `help` (
  `help_id` int(11) NOT NULL,
  `view` varchar(50) DEFAULT NULL,
  `text_help` blob,
  PRIMARY KEY (`help_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `help`
--

LOCK TABLES `help` WRITE;
/*!40000 ALTER TABLE `help` DISABLE KEYS */;
INSERT INTO `help` VALUES (1,'/MainView.fxml',_binary 'Enter username and password and click \'Sing in\' to log into a system.\nIf You don\'t have an account, click \'Sign up.\' '),(2,'/AdminView.fxml',_binary 'to do'),(3,'/Preseason/AddMatchView.fxml',_binary 'To add match select host and away team and enter date. It is necessary to observe the following rules: \n1. Teams cannot be the same. \n2. Date must have correct format: YYYY-MM-DD. \n3. Date must be date between start and end season.'),(4,'/Preseason/AddPlayerView.fxml',_binary 'To add player enter player\'s data like: first, last name, height, weight and date of birth. Select team and image of the player. \nIt is necessary to fill all fields in a correct format (date: YYYY-MM-DD, height and weight in floating point numbers).'),(5,'/Preseason/AddSeasonView.fxml',_binary 'There is two options: \n1. Add new season - enter name, start and end date of the season. The newly created season will be saved to the database.\n2. Go to the existing season - select season and go to the preseason view.'),(6,'/Preseason/AddTeamView.fxml',_binary 'To add team enter name of team and location, select image, conference and division. If the conference is selected, divisions from a given conference will be shown. \nIt is necessary to fill all fields.'),(7,'/Preseason/SelectionView.fxml',_binary 'You can add match, team and player, also updating player is available.'),(8,'/Preseason/UpdatePlayerView.fxml',_binary 'First thing to update player is search name of player in \'Player\' field and click \'Ok\' button. After click this button all fields will fill by data of searching player. Now can change all is needed:\n1. Team - team can change only one in the season. \n2. Image - if don\'t change image, team mustn\'t change too. \n3. Height and weight must be floating point numbers.'),(9,'/Statistician.fxml',_binary 'to do'),(10,'/Statistician2.fxml',_binary 'to do'),(11,'/Statistician3.fxml',_binary 'to do'),(12,'/DataViewer.fxml',_binary 'to do'),(13,'/ListOfTeamPlayersView.fxml',_binary 'to do'),(14,'/ListOfTop10ShootersViewer.fxml',_binary 'to do'),(15,'/SelectData.fxml',_binary 'to do'),(16,'/Timetable.fxml',_binary 'to do'),(17,'/AccountView.fxml',_binary 'Depending on your privileges You can: \n\n1. Enter preseason data - add seasons, teams, players (or update) and matches.\n2. View data - generate raport like \'player\'s achievements\'. \n3. Collect statistics during the match. \n4. Change user\'s privileges.'),(18,'/RegisterView.fxml',_binary 'Enter username and password, repeat password for safty and click \'Create account\'.\nYour account will be create with defatult privileges (to view data).');
/*!40000 ALTER TABLE `help` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-28 19:44:47
