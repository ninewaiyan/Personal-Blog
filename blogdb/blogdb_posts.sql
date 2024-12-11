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
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `create_time` datetime NOT NULL,
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'First Test','This is Testing','2024-08-12 03:22:32','2024-09-19 11:15:56'),(2,'EDIT','EDIT is success','2024-08-12 03:22:32','2024-09-19 19:05:47'),(3,'Thrird','This is Third Test','2024-08-12 03:22:32','2024-09-17 00:32:06'),(4,'Test','This is testing','2024-09-19 03:52:20','2024-09-19 19:08:38'),(5,'Test','This is testing','2024-09-19 03:55:20','2024-09-19 19:06:13'),(6,'This is testing','This is testing','2024-09-19 11:16:52','2024-09-19 19:06:09'),(7,'Testing','This is testing','2024-09-19 11:36:21','2024-09-19 19:06:05'),(8,'Test','This is testing','2024-09-19 11:39:50','2024-09-19 19:06:01'),(11,'Apple','This is apple','2024-09-19 11:43:05','2024-09-19 19:05:55'),(12,'AAAA','BBBB','2024-09-19 11:45:39','2024-09-19 19:05:51'),(13,'Orange','This is orange','2024-09-19 11:52:58','2024-09-19 19:05:41'),(14,'Testing','This is testing','2024-09-19 11:56:35','2024-09-19 19:05:37'),(15,'This is tesgin','This is tesging','2024-09-19 12:04:10','2024-09-19 19:05:34'),(16,'Test','This is testing','2024-09-19 12:45:01','2024-09-19 19:05:29'),(17,'Class GO !!!!!!!','FUN !!!!!!!!!!1','2024-09-19 12:58:41','2024-09-19 19:05:24'),(19,'Can you Buy I phone 16 ?','There\'s no iPhone 16 \'supercycle\', according to famed purveyor of Apple-related rumors Ming-Chi Kuo. The analyst has shared his data compiled from a supply chain survey and pre-order results from Apple\'s official websites.\r\n\r\nAnd the numbers paint a bleak picture for Apple. Pre-orders for the iPhone 16 series in the first weekend are estimated to be about 37 million units, down 12.7% year-on-year compared to the iPhone 15 family\'s first-weekend sales. And this is regardless of the fact that first-weekend sales for the iPhone 16 and iPhone 16 Plus were up year-on-year.Demand for the iPhone 16 Pro and Pro Max is lower than expected, despite Apple having been very optimistic about the Pro Max\'s outlook and growing shipments before pre-orders started (compared to the 15 Pro Max).\r\n\r\nKuo speculates that the disappointing performance of the Pro models has to do with the fact that Apple Intelligence features aren\'t launching with them this Friday, on September 20, and will instead be delivered in batches, starting in October and going all the way into next year. There\'s also said to be intense competition in the Chinese market which is impacting iPhone demand over there.\r\n\r\nAll of this said, Kuo doesn\'t anticipate any cuts in production, at least not yet, saying the Pro models\' sales will improve once Apple Intelligence is out. The holiday shopping season could also help.','2024-09-19 19:11:12',NULL),(20,'Popular Hacking Tool','The Flipper Zero is the ultimate multi-tool for pentesters, geeks, ethical hackers and hardware hobbyists alike. One pocket-sized device combines multiple tools: RFID, RF, Infrared, HID emulation, GPIO, Hardware debugging, 1-Wire, Bluetooth, Wifi and more.\r\n\r\nInspired by great open-source projects: Proxmark, HydraNFC, Rubber Ducky, pwnagotchi - the Flipper Zero manages to pack serious functionality into a tiny, professionally manufactured device - and stays true to its Open Source roots.\r\n\r\nEntirely independent, the Flipper Zero requires no external computer or hardware to function - everything is driven by the its 5-way navigation button and LCD screen. When connected to a computer or the included Android / iOS apps, the Flipper can be extended, modified, upgraded and reflashed according to your needs.\r\n\r\nWith over two years of meticulous design, prototyping and iteration, the Flipper Zero is a mature platform, ready to use out of the box, meeting the needs of professionals and enthusiasts alike.\r\n\r\n\r\nRF Transceiver\r\nThe Flipper Zero contains a fully-controllable RF platform, based on the TI CC1101 chip, allowing for RF Signal capture, analysis and transmission.\r\n\r\nThe Flipper Zero has a built-in library of common remote control algorithms for capturing, decoding and analysing and replaying signals.\r\n\r\nUsers have full access to the radio sub-system, allowing custom RF modules to be easily built\r\n\r\nThe Flipper Zero allows for Reading, Writing and Emulation of both High-Frequency (13.56MHz) and Low-Frequency (125KHz) RFID tags.\r\n\r\nLow Frequency tags can be easily read, saved, cloned to a new card, or exported for analysis.\r\n\r\nMultiple High Frequency protocols are supported, and the Flipper Zero can even sniff authentification nonces to extract unknown MIFARE keys.\r\n\r\nOpen Source & Extendable\r\nThe Flipper Zero is 100% Open-Source & Open-Hardware, with firmware source, schematics, cross-platform SDKs and Desktop & Mobile tools available.\r\n\r\nAll hardware and firmware components of the device are available, allowing for complete customisation - from quick Arduino plugins to pluggable hardware modules.','2024-09-19 19:32:14',NULL),(23,'Hi','I Want to Told you','2024-09-19 19:52:56','2024-09-27 01:24:05'),(24,'Hi','Hi','2024-09-19 19:56:36','2024-09-27 01:23:56'),(25,'Hi','Hi','2024-09-19 19:57:04',NULL),(26,'Hi','Hi','2024-09-19 19:57:08',NULL),(27,'Hello','Testing','2024-09-19 19:59:46',NULL),(28,'Hi','Testing','2024-09-19 20:00:53',NULL),(30,'Hi','This is testing','2024-09-19 20:02:41',NULL),(31,'Hi','Test','2024-09-19 20:35:36',NULL),(32,'Hi','Test','2024-09-19 20:37:03','2024-09-30 14:40:20'),(33,'Hi','Test','2024-09-19 20:53:00',NULL),(34,'Hi','Test update....','2024-09-19 21:03:32',NULL),(35,'Hi','Test','2024-09-19 21:03:41',NULL),(36,'Hi','Test','2024-09-19 21:16:38',NULL),(37,'Hi','Test','2024-09-19 21:46:32',NULL),(38,'Testing','Testing','2024-09-21 12:14:22','2024-09-26 19:42:58'),(39,'apple','apple','2024-09-21 12:15:02',NULL),(40,'Testing','Testing','2024-09-21 12:15:23','2024-09-21 12:21:45'),(41,'Tiger','apple','2024-09-26 19:41:15','2024-09-26 19:42:52'),(42,'apple','ball','2024-09-26 19:44:26','2024-09-27 09:45:04'),(43,'apple','ball','2024-09-26 19:45:07','2024-09-27 01:24:17'),(44,'test','test','2024-09-26 19:56:21',NULL),(45,'apple','apple','2024-09-26 19:57:18','2024-09-27 01:24:10'),(46,'Well Come To My Vlog !!','I want to learn Cyber Security !!','2024-09-27 02:21:53',NULL),(47,'Next Project ?','Next Project , Next Project ','2024-09-27 02:23:00',NULL),(48,'Inn Lay ','How beautiful is that place .','2024-09-27 02:28:04',NULL),(49,'Mountains','Let go !!','2024-09-27 02:30:34',NULL),(50,'Well Come to My Blog','I am very Happy','2024-09-27 09:36:33',NULL),(52,'Do you know this movie?','I like so much.','2024-09-27 09:41:06','2024-11-14 17:51:49'),(53,'Welcome To my Blog','How Beautiful you are .\r\n','2024-09-27 09:42:39',NULL),(54,'Do','I like this very much.','2024-09-27 09:44:09','2024-11-14 17:31:02'),(58,'My Family',' I love them so much.','2024-11-14 17:33:00',NULL),(59,'My Channel',' Do you want to learn something form me?','2024-11-14 17:36:06',NULL),(60,'Business',' Do you want to start  a business?','2024-11-14 17:38:27',NULL),(61,'News',' Do you want to watch a news?','2024-11-14 17:40:07',NULL),(62,'You Content',' Create you content.','2024-11-14 19:02:05','2024-11-14 19:02:32'),(63,'Your Content ',' Create you own content. :-) \r\n\r\nMy content is always for you. :-)','2024-11-14 19:03:14','2024-11-14 19:03:58');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
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
