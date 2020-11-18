CREATE DATABASE  IF NOT EXISTS `root` ;
USE `root`;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS `artista`;
CREATE TABLE `artista` (
  `nome` varchar(256) NOT NULL,
  `tipo` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


