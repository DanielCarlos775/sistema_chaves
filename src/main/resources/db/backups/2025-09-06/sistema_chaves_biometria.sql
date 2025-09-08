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
-- Table structure for table `biometria`
--

DROP TABLE IF EXISTS `biometria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `biometria` (
  `id_biometria` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `id_professor` int DEFAULT NULL,
  `id_pessoa` int DEFAULT NULL,
  `biometria` longblob NOT NULL COMMENT 'Aqui ficará o template da impressão digital',
  `dedo` enum('POLEGAR_DIREITO','INDICADOR_DIREITO','MEDIO_DIREITO','ANELAR_DIREITO','MINDINHO_DIREITO') COLLATE utf8mb4_unicode_ci NOT NULL,
  `ativo` tinyint DEFAULT '1',
  `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_biometria`),
  KEY `id_usuario_idx` (`id_usuario`),
  KEY `id_professor_idx` (`id_professor`),
  KEY `id_pessoa_idx` (`id_pessoa`),
  CONSTRAINT `id_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas_autorizadas` (`id_pessoa`),
  CONSTRAINT `id_professor` FOREIGN KEY (`id_professor`) REFERENCES `professores` (`id_professor`),
  CONSTRAINT `id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `biometria_chk_1` CHECK ((((`id_usuario` is not null) and (`id_professor` is null) and (`id_pessoa` is null)) or ((`id_usuario` is null) and (`id_professor` is not null) and (`id_pessoa` is null)) or ((`id_usuario` is null) and (`id_professor` is null) and (`id_pessoa` is not null))))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `biometria`
--

LOCK TABLES `biometria` WRITE;
/*!40000 ALTER TABLE `biometria` DISABLE KEYS */;
/*!40000 ALTER TABLE `biometria` ENABLE KEYS */;
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
