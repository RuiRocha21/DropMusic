CREATE DATABASE  IF NOT EXISTS `root` ;
USE `root`;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
DROP TABLE IF EXISTS `concerto`;
CREATE TABLE `concerto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



