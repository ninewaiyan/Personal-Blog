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
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `video` text NOT NULL,
  `course_id` bigint NOT NULL,
  `delete_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id_idx` (`course_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (4,'Club','video_2024-09-19_12-56-17_920f5796-e762-4a0a-bd85-3273a0aa13ca.mp4',1,'2024-09-21'),(5,'Ko Ko','video_2024-09-19_12-55-45_194740b6-191a-407a-993c-2e5599a00d69.mp4',2,'2024-09-21'),(6,'Test','video_2024-09-19_12-55-45_1dcb00eb-a05c-4c40-b722-a18f915d1d65.mp4',1,'2024-09-21'),(7,'Test','video_2024-09-19_12-55-45_1d955d65-8bf8-40b0-9fe0-835a397f56a8.mp4',1,'2024-09-21'),(8,'Love Story','Pirates_Of_The_Caribbean_Dead_Men_Tell_No_Tales_2017_MM_Sub_0d767673-da5a-4b51-88cd-43502131f8d9.mp4',2,'2024-09-26'),(9,'You belong with me','Pirates_Of_The_Caribbean_Dead_Men_Tell_No_Tales_2017_MM_Sub_8ca15365-23b0-495f-b87a-4bb8525ef19c.mp4',2,NULL),(10,'apple','video_2024-09-19_12-56-17_7084be33-4856-48a3-bd20-20946764bcb2.mp4',2,NULL),(11,'Python Lesson 1','video_2024-09-27_02-06-34_b1947913-f7fb-4241-b6e9-eecd040feba9.mp4',9,'2024-09-27'),(12,'Python Lesson 2','video_2024-09-27_02-06-34_d96a0b79-878c-4a0e-9c65-48d7738be806.mp4',9,'2024-09-27'),(13,'Python Lesson 3','video_2024-09-27_02-06-34_6dee0ed1-be1d-4e99-b530-4f876ef908bd.mp4',9,'2024-09-27'),(14,'Python Lesson 1','video_2024-09-27_02-09-53_6281df0c-894d-4ae6-acad-1036940eea7f.mp4',9,NULL),(15,'Python Lesson 2','video_2024-09-27_02-09-53_87fc6c89-69a3-4712-8384-4a59f6ecaa70.mp4',9,NULL),(16,'Python Lesson 3','video_2024-09-27_02-09-53_daa07f70-09d1-4534-9fd5-98f52fc2839e.mp4',9,NULL),(17,'Python Lesson 4','video_2024-09-27_02-09-53_870394a5-adba-41f8-9488-b7ef02fd8561.mp4',9,NULL),(18,'Python Lesson 5','video_2024-09-27_02-09-53_5ef19724-0c6c-4e86-aa74-72aa93983ef3.mp4',9,NULL),(19,'Python Lesson 6','video_2024-09-27_02-09-53_dfd82de0-4e8f-40b6-a3e5-6aa5754bc4d7.mp4',9,NULL),(20,'Java Basic','video_2024-09-27_02-06-34_51921f83-b379-4a63-93f5-13663509bbec.mp4',10,NULL),(21,'Java Lesson 2','video_2024-09-27_02-06-34_b6743d55-1f93-44b8-a316-4eaed6dbdff1.mp4',10,NULL),(22,'java Lesson 3','video_2024-09-27_02-06-34_1f2989ef-65c9-4ba2-b02f-a462c8241116.mp4',10,NULL),(23,'Basic Concept (1)','video_2024-09-27_02-09-53_2407ece3-c6c6-4755-824a-ffc99954e4e0.mp4',25,NULL),(24,'Variables','video_2024-09-27_02-06-34_c46ee4c0-1dcd-4b55-b494-7b6be4022bf5.mp4',25,NULL),(25,'Datatypes','video_2024-09-27_02-09-53_dec7108f-d98b-48da-ac68-decde0a4fdb8.mp4',25,'2024-11-14');
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

-- Dump completed on 2024-12-11  9:41:46
