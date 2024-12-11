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
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` text NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `delete_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `post_id` (`post_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,'Hello','Apple',1,'2024-09-19'),(2,'apple','Viewer',1,'2024-09-19'),(3,'this is testing','Viewer',1,'2024-09-19'),(4,'apple','Viewer',2,'2024-09-19'),(5,'apple','Viewer',1,'2024-09-19'),(6,'apple','Viewer',2,'2024-09-19'),(7,'apple','Viewer',1,'2024-09-19'),(8,'This is my dream','Viewer',1,'2024-09-19'),(9,'This is my dream','Viewer',1,'2024-09-19'),(10,'This is my dream','Viewer',1,'2024-09-19'),(11,'Aye say bpae','Viewer',1,'2024-09-19'),(12,'apple','Viewer',1,'2024-09-19'),(13,'apple','Viewer',1,'2024-09-19'),(14,'သတိရတယ် ဗျာ','Viewer',1,'2024-09-19'),(15,'aye say bae','Viewer',1,'2024-09-19'),(16,'HHHHHHHHHHHHHHHHH','Viewer',2,'2024-09-19'),(17,'YYYYYYYYYYYY','Viewer',1,'2024-09-19'),(18,'hHHHHHHHHHHHHHHHHHHHHHHH','nine nine',1,'2024-09-19'),(19,'abcdefg','nine nine',1,'2024-09-19'),(20,'uuuuuuuuuuuuuuuuuuu','nine nine',1,'2024-09-19'),(21,'@Apple@   Hello','nine nine',1,'2024-09-19'),(22,'@Apple@   Aye say bar','Viewer',1,'2024-09-19'),(23,'Ko phyo is so handsome','Viewer',1,'2024-09-19'),(24,'Ko Phyo is SKB','nine nine',1,'2024-09-19'),(25,'Cha Cha','Nova Tech',17,'2024-09-19'),(26,'You can buy in Amazon.','Nova Tech',20,NULL),(27,'@Nova Tech@  Thankful','Nova Tech',20,NULL),(28,'apple','Nova Tech',39,'2024-09-21'),(29,'Hello','Nova Tech',39,'2024-09-21'),(30,'hi','Viewer',39,NULL),(31,'Ko Sai A Chaw Lay','Viewer',45,'2024-09-27'),(32,'apple','Nova Tech',45,'2024-09-27'),(33,'apple','Nova Tech',44,'2024-09-26'),(34,'apple','Nova Tech',45,'2024-09-27'),(35,'apple','Nova Tech',45,'2024-09-27'),(36,'Comming Soon','Viewer',47,NULL),(37,'Student Management ','Viewer',47,NULL),(38,'When is free time?','Viewer',47,NULL),(39,'I want to go','Nova Tech',48,NULL),(40,'@Viewer@ Don\'t Come  ','Nova Tech',47,NULL),(41,'I love mountain too.','Viewer',49,NULL),(42,'I love Inn Lay.','nine nine',48,NULL),(43,'@Viewer@  Hey , let go together .','nine nine',49,NULL),(44,'I like mountain .','nine nine',49,'2024-09-27'),(45,'@Viewer@  Let Go.','Nova Tech',49,NULL),(46,'I like this','Viewer',54,'2024-11-14'),(47,'I like it','Viewer',54,'2024-11-14'),(48,'hello','Viewer',54,'2024-11-14'),(49,'@Viewer@  Hello','Anonymous',54,'2024-11-14'),(50,'hello','Anonymous',54,'2024-11-14'),(51,'That Great!!','Anonymous',54,'2024-11-14'),(52,'That Greate!!','Anonymous',54,'2024-11-14'),(53,'I like that','Nova Tech',61,NULL),(54,'Happy Happy','Nova Tech',58,NULL),(55,'@Nova Tech@   really ?','Anonymous',49,NULL);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
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
