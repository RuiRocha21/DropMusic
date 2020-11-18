CREATE DATABASE  IF NOT EXISTS `root` ;
USE `root`;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(256) DEFAULT NULL,
  `tipo` tinyint(1) DEFAULT NULL,
  `utilizador_email` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `playlist_fk1` (`utilizador_email`),
  CONSTRAINT `playlist_fk1` FOREIGN KEY (`utilizador_email`) REFERENCES `utilizador` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


