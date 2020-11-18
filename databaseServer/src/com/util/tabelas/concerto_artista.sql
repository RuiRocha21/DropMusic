CREATE DATABASE  IF NOT EXISTS `root` ;
USE `root`;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS `concerto_artista`;
CREATE TABLE `concerto_artista` (
  `concerto_id` bigint(20) NOT NULL,
  `artista_nome` varchar(256) NOT NULL,
  PRIMARY KEY (`concerto_id`,`artista_nome`),
  KEY `concerto_artista_fk2` (`artista_nome`),
  CONSTRAINT `concerto_artista_fk1` FOREIGN KEY (`concerto_id`) REFERENCES `concerto` (`id`),
  CONSTRAINT `concerto_artista_fk2` FOREIGN KEY (`artista_nome`) REFERENCES `artista` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


