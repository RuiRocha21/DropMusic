CREATE DATABASE  IF NOT EXISTS `root`;
USE `root`;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
  DROP TABLE IF EXISTS `critica`;
CREATE TABLE `critica` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pontuacao` int(11) DEFAULT NULL,
  `justificacao` varchar(4096) DEFAULT NULL,
  `utilizador_email` varchar(256) NOT NULL,
  `album_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `critica_fk1` (`utilizador_email`),
  KEY `critica_fk2` (`album_id`),
  CONSTRAINT `critica_fk1` FOREIGN KEY (`utilizador_email`) REFERENCES `utilizador` (`email`),
  CONSTRAINT `critica_fk2` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



