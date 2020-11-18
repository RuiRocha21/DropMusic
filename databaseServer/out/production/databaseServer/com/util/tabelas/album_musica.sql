CREATE DATABASE  IF NOT EXISTS `root`;
USE `root`;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS `album_musica`;
CREATE TABLE `album_musica` (
  `album_id` bigint(20) NOT NULL,
  `musica_id` bigint(20) NOT NULL,
  `musica_nome` varchar(256) NOT NULL,
  PRIMARY KEY (`musica_id`,`musica_nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



