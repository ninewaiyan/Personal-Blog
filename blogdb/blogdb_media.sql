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
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `path` text NOT NULL,
  `post_id` bigint DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `media_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `media`
--

LOCK TABLES `media` WRITE;
/*!40000 ALTER TABLE `media` DISABLE KEYS */;
INSERT INTO `media` VALUES (1,'https://via.placeholder.com/728x300.png?text=Post+Image+2',1,'2024-09-19'),(2,'https://via.placeholder.com/728x300.png?text=Post+Image+2',1,'2024-09-19'),(3,'https://via.placeholder.com/728x300.png?text=Post+Image+2',1,'2024-09-19'),(4,'images (1)_a20b79c6-b2b9-4ed9-ae8d-0d874b3ce025.jpg',5,'2024-09-19'),(5,'images_2baffee4-88bc-4a50-9b54-48700100abf3.jpg',5,'2024-09-19'),(6,'download_86d4249d-9f75-4937-860a-f4bca96e4da8.jpg',11,'2024-09-19'),(7,'images_a8b76f3b-103d-4273-bd35-0cc41b297106.jpg',12,'2024-09-19'),(8,'download_039ac423-2e2c-458b-a1f1-9c3e28b0db67.jpg',12,'2024-09-19'),(9,'download_764d9100-d50b-438c-b51d-8c49ed92d520.jpg',13,'2024-09-19'),(10,'images - Copy_b57e59c2-6087-4675-bff9-54e36e57f807.jpg',14,'2024-09-19'),(11,'images_93ebbb6b-6030-421a-ba31-d9e6e9dafd0a.jpg',14,'2024-09-19'),(12,'download_c0a397b4-566d-4de3-ba78-4b2ce5d37400.jpg',15,'2024-09-19'),(13,'images (1) - Copy_1a8d9806-edf1-4bdf-b054-2a74c2afd1fa.jpg',16,'2024-09-19'),(14,'images (1)_e61fa191-eddf-4d5f-be68-11f68713cd8b.jpg',16,'2024-09-19'),(15,'images - Copy_9c16b7a3-3fad-4842-8106-2447b3fdbb89.jpg',16,'2024-09-19'),(16,'images_7b44f4be-4c36-434d-8a35-a1f134920eee.jpg',16,'2024-09-19'),(17,'video_2024-09-19_12-56-17_e963a9e4-988a-413b-86c0-343fec699ab7.mp4',17,'2024-09-19'),(18,'video_2024-09-19_12-55-45_0bde0183-4c81-4f6c-a1b5-20bb16349cdd.mp4',17,'2024-09-19'),(19,'images (2)_ae8edc59-5c8e-4123-a347-8397bb9ec9cd.jpg',19,NULL),(20,'download (2)_bb186a2b-6125-45c6-b964-b92f0886ae31.jpg',19,NULL),(21,'download (1)_394ba70e-9988-4550-ae3e-bc75a10ad742.jpg',19,NULL),(22,'download (5)_dca88df2-c7ac-45de-9966-8aa3ba3820b3.jpg',20,NULL),(23,'download (4)_da131090-b591-44b4-ae28-e5f52be73ae3.jpg',20,NULL),(24,'download (3)_bc0a817e-2c6d-4cbf-baf5-7ba863fb8c08.jpg',20,NULL),(25,'images (2)_a650a818-874a-4130-b515-edc9bb8e76f9.jpg',23,'2024-09-27'),(26,'download (1)_17c6e1c5-5a80-463a-a77a-9f7fcbf8e042.jpg',24,'2024-09-27'),(27,'download (1)_74688692-b3f7-4082-b741-d69484b5740e.jpg',25,NULL),(28,'download (1)_104a21da-2584-460d-b8f0-74c864db0b58.jpg',26,NULL),(29,'images (1) - Copy_cd32f810-f5cc-4aaa-a1e8-994208859d92.jpg',28,NULL),(30,'download (3)_48b17819-4a00-4e50-9051-69b37f272e42.jpg',37,NULL),(31,'video_2024-09-19_12-56-17_73a9b601-414c-4d4b-90bc-0d7b0909decd.mp4',38,'2024-09-26'),(32,'photo_2024-07-25_11-18-53_20ca6f56-6436-4cea-80db-0b2294298247.jpg',39,NULL),(33,'video_2024-09-19_12-56-17_5c1345e4-c3ec-4156-b9f8-70fb414d457c.mp4',40,'2024-09-21'),(34,'Pirates_Of_The_Caribbean_Dead_Men_Tell_No_Tales_2017_MM_Sub_a71db7b7-d98d-4266-b2f8-9fe61efc4a10.mp4',41,'2024-09-26'),(35,'download (5)_46ac21ac-6b30-4bd1-b43d-d7ccf8f45a5c.jpg',42,'2024-09-27'),(36,'download (4)_907143a1-9e05-4d72-a20c-4e44b9aa3d0a.jpg',42,'2024-09-27'),(37,'download (5)_bd63adb3-9b98-4ab8-a065-851f2aa59766.jpg',43,'2024-09-27'),(38,'download (4)_43c4d572-e1f2-4e67-bfe4-ca73d18fc85a.jpg',43,'2024-09-27'),(39,'download (5)_27fc3aad-417a-482c-b45f-0db2142b1851.jpg',44,NULL),(40,'download (4)_88127ba5-a31d-4eb1-8c45-d27238b869b0.jpg',44,NULL),(41,'download (5)_446212c6-af69-4219-8b14-b8c0c4a51435.jpg',45,'2024-09-27'),(42,'download (4)_813298a3-eb03-4eb0-a591-9c9fc4d18b93.jpg',45,'2024-09-27'),(43,'video_2024-09-27_02-06-34_e761b874-947e-42fa-8ea5-5cc5c5f1df9b.mp4',46,NULL),(44,'download (28)_ed577ec3-acbe-43c7-9c64-af02b12b6cf6.jpg',48,NULL),(45,'download (27)_97191aa5-8b19-4c13-81b8-e32bbaf087e6.jpg',48,NULL),(46,'download (31)_aa97d186-0eb2-4dfa-b202-8e2d12445385.jpg',49,NULL),(47,'download (30)_c508c708-03e3-4b50-bcac-f6bb51a14eca.jpg',49,NULL),(48,'images (20)_149dab6e-5ec7-4eb3-86f5-5b7ab1a36f2c.jpg',50,NULL),(49,'images (19)_4d34d827-163b-4e44-b2ee-0cb52ac66f4d.jpg',50,NULL),(51,'video_2024-09-27_02-09-53_2c5e7f80-847c-47cf-9823-8e4605635e1c.mp4',53,NULL),(52,'video_2024-09-27_02-06-34_bd7c8ec7-2d2e-4c7c-8bd9-8b69f092b5dd.mp4',53,NULL),(54,'images_20dc1c5c-47ce-4704-bfdd-e72bfec0de3d.jpg',58,NULL),(55,'download (3)_6560e4f7-aec8-4065-998e-6266dee061e4.jpg',58,NULL),(56,'download (5)_766836ce-ed73-489c-ba5e-1be5bc94e723.jpg',59,NULL),(57,'images (1)_75fc607a-74dc-44e7-bae8-6f4e4e63f770.jpg',59,NULL),(58,'download (8)_2bae14a7-f872-4b1f-bb64-c96acee1cb53.jpg',60,NULL),(59,'download (7)_ff016504-a1f6-4af8-b0c0-7a6d8aa548d0.jpg',60,NULL),(60,'download (11)_2520a9a5-bd43-424e-b4ec-ec52571bbb45.jpg',61,NULL),(61,'download (10)_ae21914b-33a9-425b-9adb-742391ce35cb.jpg',61,NULL);
/*!40000 ALTER TABLE `media` ENABLE KEYS */;
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
