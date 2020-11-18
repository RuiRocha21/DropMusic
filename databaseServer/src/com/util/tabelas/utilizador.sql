CREATE DATABASE  IF NOT EXISTS `root`;
USE `root`;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS `utilizador`;
CREATE TABLE `utilizador` (
  `email` varchar(256) NOT NULL,
  `password` varchar(256) DEFAULT NULL,
  `editor` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



