CREATE DATABASE  IF NOT EXISTS root;
USE root;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS utilizador;
CREATE TABLE utilizador (
  email varchar(256) NOT NULL,
  password varchar(256) DEFAULT NULL,
  editor tinyint(1) DEFAULT NULL,
  PRIMARY KEY (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS genero;
CREATE TABLE genero (
  nome varchar(256) NOT NULL,
  PRIMARY KEY (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 SET character_set_client = utf8mb4 ;
DROP TABLE IF EXISTS editora;
CREATE TABLE editora (
  nome varchar(256) NOT NULL,
  PRIMARY KEY (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS artista;
CREATE TABLE artista (
  nome varchar(256) NOT NULL,
  tipo varchar(256) DEFAULT NULL,
  PRIMARY KEY (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 SET character_set_client = utf8mb4 ;
DROP TABLE IF EXISTS compositor;
CREATE TABLE compositor (
  nome varchar(256) DEFAULT NULL,
  artista_nome varchar(256) NOT NULL,
  PRIMARY KEY (artista_nome),
  CONSTRAINT compositor_fk1 FOREIGN KEY (artista_nome) REFERENCES artista(nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
DROP TABLE IF EXISTS concerto;
CREATE TABLE concerto (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  data date DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS concerto_artista;
CREATE TABLE concerto_artista (
  concerto_id bigint(20) NOT NULL,
  artista_nome varchar(256) NOT NULL,
  PRIMARY KEY (concerto_id,artista_nome),
  KEY concerto_artista_fk2 (artista_nome),
  CONSTRAINT concerto_artista_fk1 FOREIGN KEY (concerto_id) REFERENCES concerto (id),
  CONSTRAINT concerto_artista_fk2 FOREIGN KEY (artista_nome) REFERENCES artista (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS album;
CREATE TABLE album (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(256) DEFAULT NULL,
  data_lancamento date DEFAULT NULL,
  pontuacao float DEFAULT NULL,
  genero_nome varchar(256) NOT NULL,
  editora_nome varchar(256) NOT NULL,
  artista_nome varchar(256) NOT NULL,
  PRIMARY KEY (id),
  KEY album_fk1 (genero_nome),
  KEY album_fk2 (editora_nome),
  KEY album_fk3 (artista_nome),
  CONSTRAINT album_fk1 FOREIGN KEY (genero_nome) REFERENCES genero (nome),
  CONSTRAINT album_fk2 FOREIGN KEY (editora_nome) REFERENCES editora (nome),
  CONSTRAINT album_fk3 FOREIGN KEY (artista_nome) REFERENCES artista (nome)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;

 SET NAMES utf8 ;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS musica;
CREATE TABLE musica (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(256) NOT NULL,
  data_lancamento date DEFAULT NULL,
  letra text,
  path_ficheiro varchar(256) DEFAULT NULL,
  artista_nome varchar(256) NOT NULL,
  compositor_artista_nome varchar(256) NOT NULL,
  PRIMARY KEY (id,nome),
  KEY musica_fk1 (artista_nome),
  KEY musica_fk2 (compositor_artista_nome),
  CONSTRAINT musica_fk1 FOREIGN KEY (artista_nome) REFERENCES artista (nome),
  CONSTRAINT musica_fk2 FOREIGN KEY (compositor_artista_nome) REFERENCES compositor (artista_nome)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 DROP TABLE IF EXISTS album_musica;
CREATE TABLE album_musica (
  album_id bigint(20) NOT NULL,
  musica_id bigint(20) NOT NULL,
  musica_nome varchar(256) NOT NULL,
  PRIMARY KEY (musica_id,musica_nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 SET character_set_client = utf8mb4 ;
  DROP TABLE IF EXISTS critica;
CREATE TABLE critica (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  pontuacao int(11) DEFAULT NULL,
  justificacao varchar(4096) DEFAULT NULL,
  utilizador_email varchar(256) NOT NULL,
  album_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  KEY critica_fk1 (utilizador_email),
  KEY critica_fk2 (album_id),
  CONSTRAINT critica_fk1 FOREIGN KEY (utilizador_email) REFERENCES utilizador (email),
  CONSTRAINT critica_fk2 FOREIGN KEY (album_id) REFERENCES album (id)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS playlist;
CREATE TABLE playlist (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(256) DEFAULT NULL,
  tipo tinyint(1) DEFAULT NULL,
  utilizador_email varchar(256) NOT NULL,
  PRIMARY KEY (id),
  KEY playlist_fk1 (utilizador_email),
  CONSTRAINT playlist_fk1 FOREIGN KEY (utilizador_email) REFERENCES utilizador (email)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


 SET character_set_client = utf8mb4 ;
 DROP TABLE IF EXISTS playlist_musica;
CREATE TABLE playlist_musica (
  playlist_id bigint(20) NOT NULL,
  musica_id bigint(20) NOT NULL,
  musica_nome varchar(256) NOT NULL,
  PRIMARY KEY (playlist_id,musica_id,musica_nome),
  KEY playlist_musica_fk2 (musica_id),
  CONSTRAINT playlist_musica_fk1 FOREIGN KEY (playlist_id) REFERENCES playlist (id),
  CONSTRAINT playlist_musica_fk2 FOREIGN KEY (musica_id) REFERENCES musica (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;