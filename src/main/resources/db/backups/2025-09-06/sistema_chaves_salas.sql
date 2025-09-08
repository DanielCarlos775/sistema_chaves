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
-- Table structure for table `salas`
--

DROP TABLE IF EXISTS `salas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salas` (
  `id_sala` int NOT NULL AUTO_INCREMENT,
  `id_predio` int NOT NULL COMMENT 'FK para o prédio ao qual a sala pertence',
  `nome` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nome ou número da sala (ex: 101, Laboratório 1)',
  `descricao` text COLLATE utf8mb4_unicode_ci COMMENT 'Descrição adicional sobre a sala',
  `capacidade` int DEFAULT NULL COMMENT 'Capacidade máxima da sala (em pessoas)',
  `ativo` tinyint DEFAULT '1' COMMENT '1=Ativo, 0=Inativo',
  `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro',
  `data_atualizacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data da última atualização',
  PRIMARY KEY (`id_sala`),
  UNIQUE KEY `unique_sala_predio` (`id_predio`,`nome`),
  KEY `fk_sala_predio1_idx` (`id_predio`),
  CONSTRAINT `fk_salas_predio` FOREIGN KEY (`id_predio`) REFERENCES `predios` (`id_predio`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salas`
--

LOCK TABLES `salas` WRITE;
/*!40000 ALTER TABLE `salas` DISABLE KEYS */;
INSERT INTO `salas` VALUES (1,1,'101','Sala de aula 101',40,1,'2025-09-07 01:02:50','2025-09-07 01:02:50'),(2,1,'102','Sala de aula 102',35,1,'2025-09-07 01:02:50','2025-09-07 01:02:50'),(3,2,'Lab1','Laboratório de Informática 1',25,1,'2025-09-07 01:02:50','2025-09-07 01:02:50');
/*!40000 ALTER TABLE `salas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-06 23:24:33
