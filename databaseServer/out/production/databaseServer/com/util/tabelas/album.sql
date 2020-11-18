CREATE DATABASE  IF NOT EXISTS `root`;
USE `root`;





 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(256) DEFAULT NULL,
  `data_lancamento` date DEFAULT NULL,
  `pontuacao` float DEFAULT NULL,
  `genero_nome` varchar(256) NOT NULL,
  `editora_nome` varchar(256) NOT NULL,
  `artista_nome` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `album_fk1` (`genero_nome`),
  KEY `album_fk2` (`editora_nome`),
  KEY `album_fk3` (`artista_nome`),
  CONSTRAINT `album_fk1` FOREIGN KEY (`genero_nome`) REFERENCES `genero` (`nome`),
  CONSTRAINT `album_fk2` FOREIGN KEY (`editora_nome`) REFERENCES `editora` (`nome`),
  CONSTRAINT `album_fk3` FOREIGN KEY (`artista_nome`) REFERENCES `artista` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;






