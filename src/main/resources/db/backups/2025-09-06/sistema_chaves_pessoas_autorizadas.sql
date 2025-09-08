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
-- Table structure for table `pessoas_autorizadas`
--

DROP TABLE IF EXISTS `pessoas_autorizadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoas_autorizadas` (
  `id_pessoa` int NOT NULL AUTO_INCREMENT,
  `id_usuario_responsavel` int NOT NULL COMMENT 'ID do usuário administrativo responsável por essa pessoa autorizada',
  `nome` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nome completo da pessoa autorizada',
  `cpf` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'CPF da pessoa autorizada',
  `telefone` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Telefone de contato',
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Email para contato',
  `ativo` tinyint NOT NULL DEFAULT '1' COMMENT '1=Ativo, 0=Inativo',
  `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro',
  `data_atualizacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data da última atualização',
  PRIMARY KEY (`id_pessoa`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  KEY `fk_id_usuario_resposave_idx` (`id_usuario_responsavel`),
  CONSTRAINT `fk_pessoas_autorizadas_usuario` FOREIGN KEY (`id_usuario_responsavel`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoas_autorizadas`
--

LOCK TABLES `pessoas_autorizadas` WRITE;
/*!40000 ALTER TABLE `pessoas_autorizadas` DISABLE KEYS */;
INSERT INTO `pessoas_autorizadas` VALUES (1,3,'Pedro Lima','333.333.333-33','99999-9999',NULL,1,'2025-09-07 01:04:07','2025-09-07 01:04:07');
/*!40000 ALTER TABLE `pessoas_autorizadas` ENABLE KEYS */;
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
