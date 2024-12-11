-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: blogdb
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `image` text,
  `delete_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,'Cyber ','download (4)_477fe9f6-45f2-4722-a644-c84992aa4514.jpg','2024-09-26'),(2,'Cyper','download (3)_0377eff6-feeb-474a-98fd-37af28a89373.jpg','2024-09-26'),(3,'Test','download (5)_d675be7b-b88f-48c9-977a-190bd6253aef.jpg','2024-09-21'),(4,'Python','images (3)_d27285f8-3d78-4a85-bdd6-4bacd0a6a6b3.jpg','2024-09-27'),(5,'Java','download (6)_c8c41fb0-0b35-488a-8e7b-729603452f91.jpg','2024-09-27'),(6,'Python','images (3)_3aaec881-6e00-48c9-8725-765e0acd31e9.jpg','2024-09-27'),(7,'apple','download (5)_afcc2e09-affa-4164-9707-9c364b985357.jpg','2024-09-27'),(8,'apple','download (5)_2a079f8d-2a9f-4910-9167-c835e5a98485.jpg','2024-09-27'),(9,'Python','images (3)_3636f1fa-d6d0-47d1-b203-db84c61e488e.jpg',NULL),(10,'Java','download (6)_c6298920-7cbe-4676-b152-055824706b1f.jpg',NULL),(11,'Java Script','download (7)_c1827d85-96c1-4c44-98c6-7f3f09c9d1b8.jpg','2024-09-27'),(12,'Java Script','images (4)_3b6199be-b729-4186-b9f9-cfe1490d6c46.jpg',NULL),(13,'C ','images (5)_368509d7-ade6-40d3-b047-5b8f7fe9a75a.jpg',NULL),(14,'PHP','download (10)_7b427180-116d-442e-8e5d-8b13b3343e12.jpg',NULL),(15,'C ++','download (8)_5ccd4916-59b4-49ac-9bed-717eb78b68ff.jpg',NULL),(16,'Laravel','images (6)_c70dcacf-07a6-4e97-a844-1c4bab7e6b95.jpg',NULL),(17,'Angular','download (1)_51d6a4b1-c0f4-4e53-b4f1-974d7c745a6c.png',NULL),(18,'React','download_ca28cb9d-c102-4eff-a793-ab4ec25c9239.png',NULL),(19,'Vue','download (2)_5dbf8515-8a93-4d90-98f6-38da76d46d07.png',NULL),(20,'Basic Web Development','download (11)_4654e17a-1983-47a2-b1da-0337ec74146d.jpg',NULL),(21,'Basic Networking ','download (13)_7016526d-0e39-410a-a3f4-7435d90ecfdb.jpg',NULL),(22,'Cyber Security','download (12)_79a5c7b7-eb8a-4fb8-bbec-1fda3d8812ce.jpg',NULL),(23,'CCNA','download (3)_911733da-2a5a-4703-838a-281a8d354af2.png',NULL),(24,'Spring Framewrok','download (4)_7d868bc2-22c8-4d1f-b789-50d13c73fbc5.png',NULL),(25,'JSP Servlet','download (5)_e01c082d-e79d-49dc-86dc-d1d757140ebd.png',NULL);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-11  9:41:46
