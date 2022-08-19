-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 3.34.51.116    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.30-0ubuntu0.20.04.2

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (9,'user1@naver.com','user1','$2a$10$6q3N0lLUnJiTyN62ysgdA.NG7aQCYwPHqEFdiDzV8KzApOkgVMPHi','student','user1'),(10,'tnrms555@naver.com','testuser','$2a$10$tcZsrZ7XkE89obPyPTFdQO8dVhLr55oW4iJxw6xBNNSzN6u1AFHQa','student','testuser'),(247,'user@aaaa.com','user','$2a$10$o73hIt5R0SoA1tmIVaINJOYmFxwJ2swM/1JQHB42dZf3kzOd9eXxa','student','user'),(248,'wow@naver.com','consultant','$2a$10$pWJ/HiTPpP8iQoIDISzEGua8ufCtvT5E/DSgvFcZvHp0OW1g.i2tK','consultant','consultant'),(268,'user2@user.com','user2','$2a$10$4daU4zqBjfVeuhPKV.44Me1ATK2YaVsRmF9rEvFalNgIp9Bi2qjSO','student','user2'),(269,'user3@user.com','user3','$2a$10$MTvd4OEHFT9FEd4XcITtAOMt3cHlgDhpVLkk2.p3OVL25jezx5Mca','student','user3'),(270,'user4@user.com','user4','$2a$10$XpzmQhL3F9nD0pKk0xDmAOg/uXBH.y7MQ3HigzlcV8Z5d/SR4Neqm','student','user4'),(271,'user5@user.com','user5','$2a$10$cbmr9FrScu.6tBRz1Qvs0O3EiwpIP5Z7/VwQEjR4pHdnsiYBjjPVG','student','user5');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 10:51:32
