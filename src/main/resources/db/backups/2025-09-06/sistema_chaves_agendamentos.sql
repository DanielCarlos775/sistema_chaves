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
-- Table structure for table `agendamentos`
--

DROP TABLE IF EXISTS `agendamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agendamentos` (
  `id_agendamento` int NOT NULL AUTO_INCREMENT,
  `id_sala` int NOT NULL,
  `id_usuario_solicitante` int NOT NULL COMMENT 'Quem fez o agendamento (ADM, COORDENADOR ou ADMINISTRATIVO)',
  `id_professor` int DEFAULT NULL COMMENT 'Beneficiário: se for um professor',
  `id_pessoa_autorizada` int DEFAULT NULL COMMENT 'Beneficiário: se for uma pessoa autorizada',
  `data_inicial` date NOT NULL,
  `data_final` date NOT NULL,
  `periodo` enum('MANHA','TARDE','NOITE','INTEGRAL') COLLATE utf8mb4_unicode_ci NOT NULL,
  `observacoes` text COLLATE utf8mb4_unicode_ci,
  `status` enum('ATIVO','CANCELADO','CONCLUIDO') COLLATE utf8mb4_unicode_ci DEFAULT 'ATIVO',
  `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_atualizacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_agendamento`),
  KEY `fk_agendamento_usuario1_idx` (`id_usuario_solicitante`),
  KEY `fk_agendamento_pessoa_autorizada1_idx` (`id_pessoa_autorizada`),
  KEY `fk_agendamento_sala_idx` (`id_sala`),
  KEY `fk_agendamento_professor_idx` (`id_professor`),
  CONSTRAINT `fk_agendamento_pessoa_autorizada1` FOREIGN KEY (`id_pessoa_autorizada`) REFERENCES `pessoas_autorizadas` (`id_pessoa`),
  CONSTRAINT `fk_agendamento_professor` FOREIGN KEY (`id_professor`) REFERENCES `professores` (`id_professor`),
  CONSTRAINT `fk_agendamento_sala` FOREIGN KEY (`id_sala`) REFERENCES `salas` (`id_sala`),
  CONSTRAINT `fk_agendamento_usuario_solicitante` FOREIGN KEY (`id_usuario_solicitante`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agendamentos`
--

LOCK TABLES `agendamentos` WRITE;
/*!40000 ALTER TABLE `agendamentos` DISABLE KEYS */;
INSERT INTO `agendamentos` VALUES (1,1,2,1,NULL,'2025-06-03','2025-06-03','MANHA',NULL,'ATIVO','2025-09-07 01:09:40','2025-09-07 01:09:40'),(2,2,3,NULL,NULL,'2025-06-03','2025-06-03','TARDE',NULL,'ATIVO','2025-09-07 01:27:12','2025-09-07 01:27:12'),(3,1,3,NULL,NULL,'2025-06-03','2025-06-03','MANHA',NULL,'ATIVO','2025-09-07 01:34:17','2025-09-07 01:34:17'),(4,2,3,NULL,NULL,'2025-06-03','2025-06-03','NOITE',NULL,'ATIVO','2025-09-07 01:56:38','2025-09-07 01:56:38');
/*!40000 ALTER TABLE `agendamentos` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trg_agendamento_unico` BEFORE INSERT ON `agendamentos` FOR EACH ROW BEGIN
    DECLARE conflito INT;

    -- Verifica se já existe um agendamento ativo ou concluído na mesma sala, data e período
    SELECT COUNT(*)
    INTO conflito
    FROM agendamentos
    WHERE id_sala = NEW.id_sala
      AND periodo = NEW.periodo
      AND (
            (NEW.data_inicial BETWEEN data_inicial AND data_final)
            OR
            (NEW.data_final BETWEEN data_inicial AND data_final)
            OR
            (data_inicial BETWEEN NEW.data_inicial AND NEW.data_final)
          )
      AND status IN ('ATIVO', 'CONCLUIDO');

    IF conflito > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Erro: já existe um agendamento para esta sala nesse período e data.';
    END IF;
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

-- Dump completed on 2025-09-06 23:24:32
