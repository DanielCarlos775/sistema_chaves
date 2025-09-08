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
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Nome completo do usuário',
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Email para login e contato',
  `senha` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Hash da senha (usar SHA2 ou bcrypt na aplicação)',
  `tipo_usuario` enum('ADM','COORDENADOR','ADMINISTRATIVO','GUARITA') COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Tipo do usuário no sistema',
  `id_predio` int DEFAULT NULL COMMENT 'Prédio de trabalho (caso aplicável)',
  `id_sala` int DEFAULT NULL COMMENT 'Sala de trabalho (se aplicável - usado por administrativos)',
  `ativo` tinyint DEFAULT '1' COMMENT '1=Ativo, 0=Inativo',
  `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro',
  `data_atualizacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data da última atualização',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_predio_id_predio_idx` (`id_predio`),
  KEY `fk_sala_id_sala_idx` (`id_sala`),
  CONSTRAINT `fk_usuario_predio` FOREIGN KEY (`id_predio`) REFERENCES `predios` (`id_predio`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_usuario_sala` FOREIGN KEY (`id_sala`) REFERENCES `salas` (`id_sala`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Administrador Geral','admin@sistema.com','hash_senha','ADM',3,NULL,1,'2025-09-07 01:02:54','2025-09-07 01:02:54'),(2,'Coordenador João','joao@sistema.com','hash_senha','COORDENADOR',1,NULL,1,'2025-09-07 01:02:54','2025-09-07 01:02:54'),(3,'Secretária Maria','maria@sistema.com','hash_senha','ADMINISTRATIVO',1,NULL,1,'2025-09-07 01:02:54','2025-09-07 01:02:54'),(4,'Porteiro José','jose@sistema.com','hash_senha','GUARITA',3,NULL,1,'2025-09-07 01:02:54','2025-09-07 01:02:54');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_usuario_update` BEFORE UPDATE ON `usuarios` FOR EACH ROW BEGIN
    INSERT INTO logs_sistema (id_usuario, acao, tabela_afetada, id_registro, dados_anteriores, dados_novos)
    VALUES (
        OLD.id_usuario,
        'UPDATE',
        'usuarios',
        OLD.id_usuario,
        JSON_OBJECT('nome', OLD.nome, 'email', OLD.email, 'tipo_usuario', OLD.tipo_usuario),
        JSON_OBJECT('nome', NEW.nome, 'email', NEW.email, 'tipo_usuario', NEW.tipo_usuario)
    );
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-06 23:24:33
