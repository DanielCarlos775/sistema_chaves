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
-- Table structure for table `coordenador_professor`
--

DROP TABLE IF EXISTS `coordenador_professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coordenador_professor` (
  `id_coordenador_professor` int NOT NULL AUTO_INCREMENT,
  `id_coordenador` int NOT NULL COMMENT 'ID do coordenador (usuário)',
  `id_professor` int NOT NULL COMMENT 'ID do professor vinculado',
  `data_vinculacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Data da vinculação',
  `ativo` tinyint DEFAULT '1' COMMENT '1=Ativo, 0=Inativo',
  PRIMARY KEY (`id_coordenador_professor`),
  UNIQUE KEY `unique_coordenador_professor` (`id_coordenador`,`id_professor`),
  KEY `fk_usuario_id_coordenador_idx` (`id_coordenador`),
  KEY `fk_professor_id_professor_idx` (`id_professor`),
  CONSTRAINT `fk_coordenador_professor_usuario` FOREIGN KEY (`id_coordenador`) REFERENCES `usuarios` (`id_usuario`) ON UPDATE CASCADE,
  CONSTRAINT `fk_professor_professor_professor` FOREIGN KEY (`id_professor`) REFERENCES `professores` (`id_professor`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordenador_professor`
--

LOCK TABLES `coordenador_professor` WRITE;
/*!40000 ALTER TABLE `coordenador_professor` DISABLE KEYS */;
INSERT INTO `coordenador_professor` VALUES (1,2,1,'2025-09-07 01:03:54',1),(2,2,2,'2025-09-07 01:03:54',1);
/*!40000 ALTER TABLE `coordenador_professor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-06 23:24:35
