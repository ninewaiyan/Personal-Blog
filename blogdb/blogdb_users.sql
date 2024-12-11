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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `role` varchar(255) NOT NULL,
  `enable` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Nova','Tech','novatech99','novatech99@gmail.com','1000:fffe05db8271ff311c3c73f7e454767c:42c63691155204cdcea614f98ca3c6b591bcee4924317462d011e6f5973ad7b4519c005a8761508b11999ae553df41833c1a40bf737f28f21c19ca1ab78daa3b','admin',1),(13,'nine','nine','nineWW','ninew@gmail.com','1000:fffe05db8271ff311c3c73f7e454767c:42c63691155204cdcea614f98ca3c6b591bcee4924317462d011e6f5973ad7b4519c005a8761508b11999ae553df41833c1a40bf737f28f21c19ca1ab78daa3b','user',1),(14,'Nwe','Zar Win','nwezarwin','nwezarwin@gmail.com','1000:bb6e78143e16f3d7edf663b62d4319e4:6739a6520e0e60061c13c0d17e5c01b440e0d1c88a1f4b5bb423f47229661e6b4e7fb2130129b6905007c3b4b7666a66570780e1087f2d6423951da7c2117a36','user',1),(15,'nine','nine','nineWWW','nineww@gmail.com','1000:ef7b64078c0019851865b8ff24cafca7:8e972c78be2e46025d6417c9dbd862dda4a80b497b2685764110fab88f5447bccdb1586989eff0e677ddbd628710e1688a216fac722f01c4c6a3a853c1a321ea','user',1),(16,'nine ','nine','abce','a@gmail.com','1000:1f0862cbc11596c0a621275204ff5b0f:2861d6472a594771ad0ee11241f18488c7c082d86ca5ed877d8b5e53035923811eaf6368bdae60cb581bb6ded797aff918553ce637d00a08d7700d9be05bbde6','user',1);
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

-- Dump completed on 2024-12-11  9:41:46
