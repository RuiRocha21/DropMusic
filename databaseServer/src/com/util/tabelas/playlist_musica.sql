CREATE DATABASE  IF NOT EXISTS `root` ;
USE `root`;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS `playlist_musica`;
CREATE TABLE `playlist_musica` (
  `playlist_id` bigint(20) NOT NULL,
  `musica_id` bigint(20) NOT NULL,
  `musica_nome` varchar(256) NOT NULL,
  PRIMARY KEY (`playlist_id`,`musica_id`,`musica_nome`),
  KEY `playlist_musica_fk2` (`musica_id`),
  CONSTRAINT `playlist_musica_fk1` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`),
  CONSTRAINT `playlist_musica_fk2` FOREIGN KEY (`musica_id`) REFERENCES `musica` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


