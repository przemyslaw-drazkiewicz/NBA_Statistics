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
INSERT INTO `help` VALUES (1,'/MainView.fxml',_binary 'Enter username and password and click \'Sign in\' to log into a system.\nIf You don\'t have an account, click \'Sign up.\' '),(2,'/AdminView.fxml',_binary 'To change user privileges select him from the combo-box or manually enter his username. Then choose his new role and click apply.'),(3,'/Preseason/AddMatchView.fxml',_binary 'To add match select host and away team and enter date. It is necessary to obey the following rules: \n1. Teams cannot be the same. \n2. Date must have correct format: YYYY-MM-DD. \n3. Date must be between start and end season.'),(4,'/Preseason/AddPlayerView.fxml',_binary 'To add player enter player\'s data like: first name, last name, height, weight and date of birth. Select team and image of the player. \nIt is necessary to fill all fields in a correct format (date: YYYY-MM-DD, height and weight in floating point numbers).'),(5,'/Preseason/AddSeasonView.fxml',_binary 'There are two options: \n1. Add new season - enter name, start and end date of the season. The newly created season will be saved to the database.\n2. Go to the existing season - select season and go to the preseason view.'),(6,'/Preseason/AddTeamView.fxml',_binary 'To add team enter name of team and location, select image, conference and division. If the conference is selected, divisions from a given conference will be shown. \nIt is necessary to fill all fields.'),(7,'/Preseason/SelectionView.fxml',_binary 'You can add match, team and player, also updating player is available.'),(8,'/Preseason/UpdatePlayerView.fxml',_binary 'First thing to update player is to search name of player in \'Player\' field and click \'Ok\' button. After clicking this button all fields will be filled with data about the player. Now change what is needed:\n1. Team - team can only be changed once in the season. \n2. Image - must be changed if changing team. \n3. Height and weight must be floating point numbers.'),(9,'/Statistician.fxml',_binary 'to do'),(10,'/Statistician2.fxml',_binary 'to do'),(11,'/Statistician3.fxml',_binary 'to do'),(12,'/DataViewer.fxml',_binary 'View contains all the information about specific season and prints raports about them. There are four options:\n1.View player\'s archivements in selected season (number of points,blocks etc.)\n2.List of top 10 shooters in selected season (most points scored)\n3.Chosen team roster in selected season\n4.Chosen team match schedule for selected season'),(13,'/ListOfTeamPlayersView.fxml',_binary 'To view a team roster in specific season select season in the first check-box and team in the second and press \'Select\'. '),(14,'/ListOfTop10ShootersViewer.fxml',_binary 'To view top 10 shooters, select specific season and press \'Select\'.'),(15,'/SelectData.fxml',_binary 'To view player\'s archivements:\n1.Select player and click \'OK\'\n2.Select season in which chosen player played and click select.\nTo generate this report to the file, click File->Save.'),(16,'/Timetable.fxml',_binary 'To view a team match schedule in specific season select season in the first check-box and team in the second and press \'Select\'. '),(17,'/AccountView.fxml',_binary 'Depending on your privileges You can: \n\n1. Enter preseason data - add seasons, teams, players (or update) and matches.\n2. View data - generate raports like \'player\'s achievements\'. \n3. Collect statistics during the match. \n4. Change user\'s privileges.'),(18,'/RegisterView.fxml',_binary 'Enter username and password, repeat password for safety and click \'Create account\'.\nYour account will be created with default privileges (to view data).\' ');
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

-- Dump completed on 2019-06-29 12:00:23
