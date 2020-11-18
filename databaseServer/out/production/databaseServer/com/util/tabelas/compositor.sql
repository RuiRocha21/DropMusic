CREATE DATABASE  IF NOT EXISTS `root` ;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
DROP TABLE IF EXISTS `compositor`;
CREATE TABLE `compositor` (
  `nome` varchar(256) DEFAULT NULL,
  `artista_nome` varchar(256) NOT NULL,
  PRIMARY KEY (`artista_nome`),
  CONSTRAINT `compositor_fk1` FOREIGN KEY (`artista_nome`) REFERENCES `artista` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



