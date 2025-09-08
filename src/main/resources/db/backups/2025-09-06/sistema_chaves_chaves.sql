-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: sistema_chaves
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `chaves`
--

DROP TABLE IF EXISTS `chaves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chaves` (
  `id_chave` int NOT NULL AUTO_INCREMENT,
  `id_sala` int NOT NULL COMMENT 'Relacionamento 1:1 com sala. Cada sala tem no máximo 1 chave associada',
  `codigo` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Código único da chave (exemplo: "CH001", "CH002")',
  `descricao` text COLLATE utf8mb4_unicode_ci,
  `ativo` tinyint DEFAULT '1',
  `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_chave`),
  UNIQUE KEY `id_sala_UNIQUE` (`id_sala`),
  UNIQUE KEY `unique_codigo_chave` (`codigo`,`id_chave`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  KEY `fk_chave_sala1_idx` (`id_sala`),
  CONSTRAINT `fk_chave_sala1` FOREIGN KEY (`id_sala`) REFERENCES `salas` (`id_sala`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chaves`
--

LOCK TABLES `chaves` WRITE;
/*!40000 ALTER TABLE `chaves` DISABLE KEYS */;
INSERT INTO `chaves` VALUES (1,1,'CH001','Chave da sala 101',1,'2025-09-07 01:04:26','2025-09-07 01:04:26'),(2,2,'CH002','Chave da sala 102',1,'2025-09-07 01:04:26','2025-09-07 01:04:26'),(3,3,'CH003','Chave do Lab1',1,'2025-09-07 01:04:26','2025-09-07 01:04:26');
/*!40000 ALTER TABLE `chaves` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-06 23:24:34
