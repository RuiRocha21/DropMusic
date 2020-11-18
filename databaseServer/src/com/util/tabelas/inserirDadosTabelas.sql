

INSERT INTO root.utilizador(email,password,editor) VALUES ("admin","nova",1);
INSERT INTO root.utilizador(email,password,editor) VALUES ("armenio","1234",0);
INSERT INTO root.utilizador(email,password,editor) VALUES ("asdff","asdsf",0);
INSERT INTO root.utilizador(email,password,editor) VALUES ("machado","machado",0);
INSERT INTO root.utilizador(email,password,editor) VALUES ("pedro","0112",0);
INSERT INTO root.utilizador(email,password,editor) VALUES ("rerererere","rerererere",0);
INSERT INTO root.utilizador(email,password,editor) VALUES ("rui","rui",0);


INSERT INTO root.genero(nome) VALUES ("electropop");
INSERT INTO root.genero(nome) VALUES ("pop");
INSERT INTO root.genero(nome) VALUES ("rock alternativo");





INSERT INTO root.editora(nome) VALUES ("domino records");
INSERT INTO root.editora(nome) VALUES ("universal music");


INSERT INTO root.artista(nome,tipo) VALUES ("a puppet show named Julio","grupo");
INSERT INTO root.artista(nome,tipo) VALUES ("alex_turner","musico");
INSERT INTO root.artista(nome,tipo) VALUES ("arcade_fire","grupo");
INSERT INTO root.artista(nome,tipo) VALUES ("artic_monkies","grupo");
INSERT INTO root.artista(nome,tipo) VALUES ("julian_casablancas","musico");
INSERT INTO root.artista(nome,tipo) VALUES ("luis alcaide","musico");
INSERT INTO root.artista(nome,tipo) VALUES ("metronomy","grupo");
INSERT INTO root.artista(nome,tipo) VALUES ("mount","musico");
INSERT INTO root.artista(nome,tipo) VALUES ("novo","musico");
INSERT INTO root.artista(nome,tipo) VALUES ("novo2","musico");
INSERT INTO root.artista(nome,tipo) VALUES ("os outros","grupo");
INSERT INTO root.artista(nome,tipo) VALUES ("the_strokes","grupo");
INSERT INTO root.artista(nome,tipo) VALUES ("win_butler","musico");


INSERT INTO root.compositor(nome,artista_nome) VALUES ("alex_turner","alex_turner");
INSERT INTO root.compositor(nome,artista_nome) VALUES("julian_casablancas","julian_casablancas");
INSERT INTO root.compositor(nome,artista_nome) VALUES("el pampadao","luis alcaide");
INSERT INTO root.compositor(nome,artista_nome) VALUES("mount","mount");
INSERT INTO root.compositor(nome,artista_nome) VALUES("teste","novo");
INSERT INTO root.compositor(nome,artista_nome) VALUES("testes","novo2");
INSERT INTO root.compositor(nome,artista_nome) VALUES("win_butler","win_butler");



INSERT INTO root.concerto(id,data) VALUES (1,"2018-01-01");
INSERT INTO root.concerto(id,data) VALUES (2,"2010-01-02");
INSERT INTO root.concerto(id,data) VALUES (3,"2016-05-21");
INSERT INTO root.concerto(id,data) VALUES (4,"2018-05-20");
INSERT INTO root.concerto(id,data) VALUES (5,"2018-05-23");
INSERT INTO root.concerto(id,data) VALUES (6,"2018-05-30");
INSERT INTO root.concerto(id,data) VALUES (14,"2016-02-05");
INSERT INTO root.concerto(id,data) VALUES (15,"2000-05-01");
INSERT INTO root.concerto(id,data) VALUES (16,"2000-05-01");



INSERT INTO root.album(id,nome,data_lancamento,pontuacao, genero_nome,editora_nome,artista_nome) VALUES (1,"am","2013-10-10",7.75,"rock alternativo","domino records","artic_monkies");
INSERT INTO root.album(id,nome,data_lancamento,pontuacao, genero_nome,editora_nome,artista_nome) VALUES (2,"reflektor","2013-08-28",7,"rock alternativo","universal music","arcade_fire");
INSERT INTO root.album(id,nome,data_lancamento,pontuacao, genero_nome,editora_nome,artista_nome) VALUES (3,"love letters","2014-07-24",9,"electropop","universal music","metronomy");
INSERT INTO root.album(id,nome,data_lancamento,pontuacao, genero_nome,editora_nome,artista_nome) VALUES (4,"humbug","2007-08-19",5.67,"rock alternativo","domino records","artic_monkies");
INSERT INTO root.album(id,nome,data_lancamento,pontuacao, genero_nome,editora_nome,artista_nome) VALUES (9,"teste","2018-12-21",NULL,"rock alternativo","domino records","artic_monkies");



INSERT INTO root.musica(id,nome,data_lancamento,letra,path_ficheiro,artista_nome,compositor_artista_nome) VALUES (1,"R U Mine?","2013-10-10","nova letra nesta musica",NULL,"artic_monkies","alex_turner");
INSERT INTO root.musica(id,nome,data_lancamento,letra,path_ficheiro,artista_nome,compositor_artista_nome) VALUES (2,"I Want It All","2007-08-19","Adventure seeker on an empty street",NULL,"artic_monkies","alex_turner");
INSERT INTO root.musica(id,nome,data_lancamento,letra,path_ficheiro,artista_nome,compositor_artista_nome) VALUES (3,"Reflektor","2013-08-28","Trapped in a prism, in a prism of light",NULL,"arcade_fire","win_butler");
INSERT INTO root.musica(id,nome,data_lancamento,letra,path_ficheiro,artista_nome,compositor_artista_nome) VALUES (4,"Afterlife","2013-08-28","aqui vai disto","c:DropMusicarcade fireafterlife.mp3","arcade_fire","win_butler");
INSERT INTO root.musica(id,nome,data_lancamento,letra,path_ficheiro,artista_nome,compositor_artista_nome) VALUES (5,"We Exist","2013-08-28","They re walking around",NULL,"arcade_fire","win_butler");
INSERT INTO root.musica(id,nome,data_lancamento,letra,path_ficheiro,artista_nome,compositor_artista_nome) VALUES (6,"Reservoir","2014-07-24","I heard you made the hull of a boat downtown",NULL,"metronomy","mount");
INSERT INTO root.musica(id,nome,data_lancamento,letra,path_ficheiro,artista_nome,compositor_artista_nome) VALUES (7,"Love Letters","2014-07-24","Love letters, all I see",NULL,"metronomy","mount");
INSERT INTO root.musica(id,nome,data_lancamento,letra,path_ficheiro,artista_nome,compositor_artista_nome) VALUES (39,"jgjgjgjgj","2017-02-02","sdadsadsd","sdsdasdsd","artic_monkies","alex_turner");



INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (1,1,"Do I Wanna Know?");
INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (1,1,"R U Mine?");
INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (1,2,"I Want It All");
INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (2,3,"Reflektor");
INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (2,4,"Afterlife");
INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (2,5,"We Exist");
INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (3,6,"Reservoir");
INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (3,7,"Love Letters");
INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (3,8,"The Upsetter");
INSERT INTO root.album_musica(album_id, musica_id, musica_nome) VALUES (1,39,"jgjgjgjgj");



INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES (1,8,"um dos melhores albuns dos artic monkies","rui",1);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES (2,9,"bom som para desfrutar de um momento de relache","rui",2);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(3,8,"bom som dos \"macacos do artico\"","pedro",4);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(4,9,"excelente som para ouvir a fazer o projecto de BD","armenio",3);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(5,1,"podia ser melhor","armenio",3);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(6,5,"e muito fraquinho","admin",1);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(10,9,"muito bom","admin",1);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(11,9,"album muito bom","rui",1);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(12,9,"muito bom","rui",1);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(13,6,"bom som","rui",4);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(14,5,"qualidade de som deixa muito a quem das especativas","rui",2);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(15,4,"som mediano","rui",2);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(16,3,"a qualidade nao e boa","rui",4);
INSERT INTO root.critica(id,pontuacao,justificacao,utilizador_email,album_id) VALUES(17,1,"assim assim","rui",4);



INSERT INTO root.concerto_artista(concerto_id,artista_nome) VALUES (1,"arcade_fire");
INSERT INTO root.concerto_artista(concerto_id,artista_nome) VALUES (4,"arcade_fire");
INSERT INTO root.concerto_artista(concerto_id,artista_nome) VALUES (2,"artic_monkies");
INSERT INTO root.concerto_artista(concerto_id,artista_nome) VALUES (5,"artic_monkies");
INSERT INTO root.concerto_artista(concerto_id,artista_nome) VALUES (6,"artic_monkies");
INSERT INTO root.concerto_artista(concerto_id,artista_nome) VALUES (2,"luis alcaide");
INSERT INTO root.concerto_artista(concerto_id,artista_nome) VALUES (4,"luis alcaide");
INSERT INTO root.concerto_artista(concerto_id,artista_nome) VALUES (15,"luis alcaide");
INSERT INTO root.concerto_artista(concerto_id,artista_nome) VALUES (3,"metronomy");



INSERT INTO root.playlist(id,nome,tipo,utilizador_email) VALUES (1,"rei das ramisturas",1,"rui");
INSERT INTO root.playlist(id,nome,tipo,utilizador_email) VALUES (2,"os puros",0,"admin");
INSERT INTO root.playlist(id,nome,tipo,utilizador_email) VALUES (3,"testeBD",1,"rui");
INSERT INTO root.playlist(id,nome,tipo,utilizador_email) VALUES (4,"os da bola",0,"pedro");



INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (1,1,"R U Mine?");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (2,1,"R U Mine?");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (2,4,"Afterlife");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (3,4,"Afterlife");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (2,5,"We Exist");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (3,5,"We Exist");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (4,5,"We Exist");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (2,6,"Reservoir");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (3,6,"Reservoir");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (4,6,"Reservoir");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (1,7,"Love Letters");
INSERT INTO root.playlist_musica(playlist_id,musica_id,musica_nome) VALUES (4,7,"Love Letters");