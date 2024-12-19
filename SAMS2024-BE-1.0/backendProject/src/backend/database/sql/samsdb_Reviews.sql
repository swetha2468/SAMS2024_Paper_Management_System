-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: localhost    Database: samsdb
-- ------------------------------------------------------
-- Server version	8.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Reviews`
--

DROP TABLE IF EXISTS `Reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Reviews` (
  `reviewId` int NOT NULL AUTO_INCREMENT,
  `paperId` int NOT NULL,
  `reviewer` int NOT NULL,
  `rater1` int NOT NULL,
  `rater2` int NOT NULL,
  `rater3` int NOT NULL,
  `deadline` int NOT NULL,
  `templateId` int NOT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `rev_paper_idx` (`paperId`),
  KEY `rev_user_idx` (`reviewer`),
  KEY `rev_rater1_idx` (`rater1`),
  KEY `rev_rater2_idx` (`rater2`),
  KEY `rev_rater3_idx` (`rater3`),
  KEY `rev_dl_idx` (`deadline`),
  KEY `rev_rvtemp_idx` (`templateId`),
  CONSTRAINT `rev_dl` FOREIGN KEY (`deadline`) REFERENCES `Deadlines` (`deadlineId`),
  CONSTRAINT `rev_paper` FOREIGN KEY (`paperId`) REFERENCES `Papers` (`paperId`),
  CONSTRAINT `rev_rater1` FOREIGN KEY (`rater1`) REFERENCES `Users` (`userId`),
  CONSTRAINT `rev_rater2` FOREIGN KEY (`rater2`) REFERENCES `Users` (`userId`),
  CONSTRAINT `rev_rater3` FOREIGN KEY (`rater3`) REFERENCES `Users` (`userId`),
  CONSTRAINT `rev_temp` FOREIGN KEY (`templateId`) REFERENCES `Templates` (`templateId`),
  CONSTRAINT `rev_user` FOREIGN KEY (`reviewer`) REFERENCES `Users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reviews`
--

LOCK TABLES `Reviews` WRITE;
/*!40000 ALTER TABLE `Reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reviews` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-30 22:26:34
