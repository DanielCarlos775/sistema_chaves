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
-- Table structure for table `movimentacoes`
--

DROP TABLE IF EXISTS `movimentacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimentacoes` (
  `id_movimentacao` int NOT NULL AUTO_INCREMENT,
  `id_agendamento` int NOT NULL,
  `id_chave` int NOT NULL,
  `id_usuario_guarita` int NOT NULL,
  `id_usuario` int DEFAULT NULL,
  `id_professor` int DEFAULT NULL,
  `id_pessoa` int DEFAULT NULL,
  `tipo_movimentacao` enum('CHECK-IN','CHECK-OUT') COLLATE utf8mb4_unicode_ci NOT NULL,
  `data_hora` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `observacoes` text COLLATE utf8mb4_unicode_ci,
  `id_biometria` int DEFAULT NULL,
  PRIMARY KEY (`id_movimentacao`),
  KEY `fk_check-in-out_agendamento1_idx` (`id_agendamento`),
  KEY `fk_movimentacoes_chave_idx` (`id_chave`),
  KEY `fk_movimentacoes_usuario_guarita_idx` (`id_usuario_guarita`),
  KEY `fk_usuario_id_idx` (`id_usuario`),
  KEY `fk_movimentacoes_id_professor_idx` (`id_professor`),
  KEY `fk_movimentacoes_id_pessoa_idx` (`id_pessoa`),
  KEY `fk_movimentacoes_id_biometria_idx` (`id_biometria`),
  CONSTRAINT `fk_movimentacoes_agendamento` FOREIGN KEY (`id_agendamento`) REFERENCES `agendamentos` (`id_agendamento`),
  CONSTRAINT `fk_movimentacoes_chave` FOREIGN KEY (`id_chave`) REFERENCES `chaves` (`id_chave`),
  CONSTRAINT `fk_movimentacoes_id_biometria` FOREIGN KEY (`id_biometria`) REFERENCES `biometria` (`id_biometria`),
  CONSTRAINT `fk_movimentacoes_id_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoas_autorizadas` (`id_pessoa`),
  CONSTRAINT `fk_movimentacoes_id_professor` FOREIGN KEY (`id_professor`) REFERENCES `professores` (`id_professor`),
  CONSTRAINT `fk_movimentacoes_id_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `fk_movimentacoes_usuario_guarita` FOREIGN KEY (`id_usuario_guarita`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `movimentacoes_chk_1` CHECK ((((`id_usuario` is not null) and (`id_professor` is null) and (`id_pessoa` is null)) or ((`id_usuario` is null) and (`id_professor` is not null) and (`id_pessoa` is null)) or ((`id_usuario` is null) and (`id_professor` is null) and (`id_pessoa` is not null))))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimentacoes`
--

LOCK TABLES `movimentacoes` WRITE;
/*!40000 ALTER TABLE `movimentacoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `movimentacoes` ENABLE KEYS */;
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
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `before_movimentacoes_insert` BEFORE INSERT ON `movimentacoes` FOR EACH ROW BEGIN
    DECLARE ultima_mov ENUM('CHECK-IN','CHECK-OUT');

    SELECT tipo_movimentacao
    INTO ultima_mov
    FROM movimentacoes
    WHERE id_chave = NEW.id_chave
    ORDER BY data_hora DESC
    LIMIT 1;

    IF (NEW.tipo_movimentacao = 'CHECK-OUT' AND ultima_mov = 'CHECK-OUT') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Chave já devolvida, não pode fazer outro CHECK-OUT';
    ELSEIF (NEW.tipo_movimentacao = 'CHECK-IN' AND (ultima_mov IS NULL OR ultima_mov = 'CHECK-IN')) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Chave está fora, não pode fazer CHECK-IN';
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

-- Dump completed on 2025-09-06 23:24:34
