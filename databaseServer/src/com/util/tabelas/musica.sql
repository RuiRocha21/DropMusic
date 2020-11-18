CREATE DATABASE  IF NOT EXISTS `root`;
USE `root`;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS `musica`;
CREATE TABLE `musica` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(256) NOT NULL,
  `data_lancamento` date DEFAULT NULL,
  `letra` text,
  `path_ficheiro` varchar(256) DEFAULT NULL,
  `artista_nome` varchar(256) NOT NULL,
  `compositor_artista_nome` varchar(256) NOT NULL,
  PRIMARY KEY (`id`,`nome`),
  KEY `musica_fk1` (`artista_nome`),
  KEY `musica_fk2` (`compositor_artista_nome`),
  CONSTRAINT `musica_fk1` FOREIGN KEY (`artista_nome`) REFERENCES `artista` (`nome`),
  CONSTRAINT `musica_fk2` FOREIGN KEY (`compositor_artista_nome`) REFERENCES `compositor` (`artista_nome`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

