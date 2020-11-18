import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class DropMusicClient {
    public static void main(String args[]) {
        // args[0] <- hostname of destination
        if (args.length == 0) {
            System.out.println("java DropMusicClient hostname");
            System.exit(0);
        }

        Socket s = null;
        int serversocket = 7000;
        try {
            // 1o passo
            String op="0";
            String email = "";
            String password= "";
            String pedido = "";
            String resposta = "";
            s = new Socket(args[0], serversocket);

            System.out.println("SOCKET=" + s);
            menuEntrada();
            // 2o passo
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            InputStreamReader input = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(input);

            while(!op.equals("3")){
                op = reader.readLine();
                if (op.equals("1")){
                    //registar utilizador
                    System.out.println("------------REGISTAR UTILIZADOR");
                    System.out.println("introduza o email de utilizador:");
                    email = reader.readLine();
                    System.out.println("introduza a sua password:");
                    password =reader.readLine();
                    //query de inserir utilizador
                    pedido = "INSERT INTO root.utilizador (email,password,editor) VALUES ('"+email+"','"+password+"',0)";

                    System.out.println("pedido" + pedido);
                    out.writeUTF(pedido);
                    resposta = in.readUTF();
                    System.out.println("resposta" + resposta);
                    if(resposta.equals("sucesso")){
                        System.out.println("conta criada com sucesso");
                    }
                    menuEntrada();
                }else if(op.equals("2")){
                    //login
                    System.out.println("------------LOGIN");
                    System.out.println("introduza o email de utilizador:");
                    email = reader.readLine();
                    System.out.println("introduza a sua password:");
                    password =reader.readLine();
                    //query de pesquisa de utilizador
                    pedido = "SELECT email , password , editor FROM root.utilizador WHERE (root.utilizador.email = '" +email+"') and (root.utilizador.password = '"+password+"')";
                    out.writeUTF(pedido);
                    resposta = in.readUTF();
                    System.out.println(resposta);
                    if (resposta.equals("recusado")){
                        System.out.println("credenciais erradas");
                        s.close();
                    }else if(resposta.equals("aceite")){
                        System.out.println("BEM VINDO");
                        //admin
                        if(email.equals("admin")) label:
                                while (!op.equals("21")) {
                                    menuAdmin();
                                    op = reader.readLine();
                                    if (op.equals("1")) {
                                        String queryInserirArtista = inserirArtista();
                                        out.writeUTF(queryInserirArtista);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    //inserirMusicasEmAlbuns();
                                    if (op.equals("2")) {
                                        String queryInserirMusicas = inserirMusicasEmAlbuns(s);
                                        out.writeUTF(queryInserirMusicas);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("3")) {
                                        String queryInsereAlbum = inserirAlbum(s);
                                        out.writeUTF(queryInsereAlbum);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("4")) {
                                        String queryInserirConcertos = inserirConcertos(s);
                                        out.writeUTF(queryInserirConcertos);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("5")) {
                                        String queryInserirCompositores = inserirCompositor(s);
                                        out.writeUTF(queryInserirCompositores);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("6")) {//inserir criticas
                                        String queryInsereCritica = inserirCritica(s, email);
                                        out.writeUTF(queryInsereCritica);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("7")) { //inserir generos musicais
                                        String queryInserirCompositores = inserirGenero();
                                        ;
                                        out.writeUTF(queryInserirCompositores);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("8")) { //criar playlists
                                        String queryCriarPlaylists = criarPlaylists(email);
                                        out.writeUTF(queryCriarPlaylists);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);

                                    }
                                    if (op.equals("9")) { //inserir musicas em playlists
                                        String queryInserirMusicasPlaylist = inserirMusicasPlaylist(s, email);
                                        out.writeUTF(queryInserirMusicasPlaylist);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);

                                    }
                                    if (op.equals("10")) {//pesquisa de albuns
                                        menuPesquisaAlbuns();
                                        op = reader.readLine();
                                        switch (op) {
                                            case "1": {
                                                String querySQLA = pesqusiarAlbumPorNome();
                                                out.writeUTF(querySQLA);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                            case "2": {
                                                String querySQLA = pesqusiarAlbumPorArtista();
                                                out.writeUTF(querySQLA);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                            default:
                                                break label;
                                        }
                                    }
                                    if (op.equals("11")) {//pesquisa de musicas
                                        menuPesquisaMusica();
                                        op = reader.readLine();
                                        switch (op) {
                                            case "1": {
                                                String querySQL = pesqusiarMusicaPorNome();
                                                out.writeUTF(querySQL);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                            case "2": {
                                                String querySQL = pesqusiarMusicaPorAlbum();
                                                out.writeUTF(querySQL);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                            case "3": {
                                                String querySQL = pesqusiarMusicaPorArtista();
                                                out.writeUTF(querySQL);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                            default:
                                                break label;
                                        }

                                    }
                                    if (op.equals("12")) {/*pesquisas artistas*/
                                        menuPesquisaArtista();
                                        op = reader.readLine();
                                        switch (op) {
                                            case "1": {
                                                String querySQL = pesqusiarArtistaPorNome();
                                                out.writeUTF(querySQL);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                            case "2": {
                                                String querySQL = pesqusiarArtistaPorAlbum();
                                                out.writeUTF(querySQL);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                            case "3": {
                                                String querySQL = pesqusiarArtistaPorMusica();
                                                out.writeUTF(querySQL);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                            default:
                                                break label;
                                        }
                                    }
                                    if (op.equals("13")) {//pesquisar concertos
                                        String querySQL = pesqusiarConcertoPorArtista();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("14")) {//pesquisar concertos
                                        String querySQL = pesqusiarCriticasPorAlbum();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("15")) {//mudar cenas de musicas
                                        menuEditarMusicas();
                                        op = reader.readLine();
                                        switch (op) {
                                            case "1": {
                                                String queryMudarLetra = mudarLetraMusica(s);
                                                out.writeUTF(queryMudarLetra);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                            case "2": {
                                                String queryMudarPath = mudarPathMusica(s);
                                                out.writeUTF(queryMudarPath);
                                                resposta = in.readUTF();
                                                System.out.println(resposta);
                                                break;
                                            }
                                        }

                                    }
                                    if (op.equals("16")) {//mudar datas de concertos
                                        String queryUpdate = mudarDataConcertoDeUmArtista(s);
                                        out.writeUTF(queryUpdate);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("17")) {//tornar playlist publica
                                        String queryPublica = mudarPlaylistParaPublico(s, email);
                                        out.writeUTF(queryPublica);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("18")) {//tornar playlist privada
                                        String queryPrivada = mudarPlaylistParaPrivado(s, email);
                                        out.writeUTF(queryPrivada);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("19")) {//listar todas as playlists publicas
                                        String queryTodasPlaylistsPublicas = listarPlaylistsPublicas();
                                        out.writeUTF(queryTodasPlaylistsPublicas);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if(op.equals("20")){//mudar password
                                        String queryMudarPassowrd = mudarPassword(email);
                                        out.writeUTF(queryMudarPassowrd);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }
                                    if (op.equals("21")) {
                                        System.out.println("VOLTE SEMPRE");
                                        s.close();
                                        System.exit(0);
                                    }
                                }
                        else{//user
                            while (!op.equals("13")) {
                                menuUtil();
                                op = reader.readLine();
                                if(op.equals("1")){//inserir criticas
                                    String queryInsereCritica = inserirCritica(s,email);
                                    out.writeUTF(queryInsereCritica);
                                    resposta = in.readUTF();
                                    System.out.println(resposta);
                                }
                                if(op.equals("2")){
                                    menuPesquisaAlbuns();
                                    op = reader.readLine();
                                    if(op.equals("1")){
                                        String querySQL =pesqusiarAlbumPorNome();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }else if(op.equals("2")){
                                        String querySQL = pesqusiarAlbumPorArtista();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }else{
                                        break;
                                    }
                                }
                                if(op.equals("3")){
                                    menuPesquisaMusica();
                                    op = reader.readLine();
                                    if(op.equals("1")){
                                        String querySQL =pesqusiarMusicaPorNome();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }else if(op.equals("2")){
                                        String querySQL = pesqusiarMusicaPorAlbum();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }else if(op.equals("3")){
                                        String querySQL = pesqusiarMusicaPorArtista();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }else{
                                        break;
                                    }

                                }
                                if(op.equals("4")){/*pesquisas artistas*/
                                    menuPesquisaArtista();
                                    op = reader.readLine();
                                    if(op.equals("1")) {
                                        String querySQL = pesqusiarArtistaPorNome();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }else if(op.equals("2")){
                                        String querySQL = pesqusiarArtistaPorAlbum();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }else if(op.equals("3")){
                                        String querySQL = pesqusiarArtistaPorMusica();
                                        out.writeUTF(querySQL);
                                        resposta = in.readUTF();
                                        System.out.println(resposta);
                                    }else{
                                        break;
                                    }
                                }
                                if(op.equals("5")){//pesquisar conecrtos
                                    String querySQL = pesqusiarConcertoPorArtista();
                                    out.writeUTF(querySQL);
                                    resposta = in.readUTF();
                                    System.out.println(resposta);
                                }
                                if(op.equals("6")){//pesquisar criticas
                                    String querySQL = pesqusiarCriticasPorAlbum();
                                    out.writeUTF(querySQL);
                                    resposta = in.readUTF();
                                    System.out.println(resposta);
                                }
                                if(op.equals("7")){//criar playlists
                                    String queryCriarPlaylists = criarPlaylists(email);
                                    out.writeUTF(queryCriarPlaylists);
                                    resposta = in.readUTF();
                                    System.out.println(resposta);
                                }
                                if(op.equals("8")){//inserir musicas em playlists
                                    String queryInserirMusicasPlaylistUtil = inserirMusicasPlaylist(s,email);
                                    out.writeUTF(queryInserirMusicasPlaylistUtil);
                                    resposta = in.readUTF();
                                    System.out.println(resposta);
                                }
                                if(op.equals("9")){//tornar playlist publica
                                    String queryPublica = mudarPlaylistParaPublico(s,email);
                                    out.writeUTF(queryPublica);
                                    resposta = in.readUTF();
                                    System.out.println(resposta);
                                }
                                if(op.equals("10")){//tornar playlist publica
                                    String queryPrivada = mudarPlaylistParaPrivado(s,email);
                                    out.writeUTF(queryPrivada);
                                    resposta = in.readUTF();
                                    System.out.println(resposta);
                                }
                                if (op.equals("11")) {//listar todas as playlists publicas
                                    String queryTodasPlaylistsPublicas = listarPlaylistsPublicas();
                                    out.writeUTF(queryTodasPlaylistsPublicas);
                                    resposta = in.readUTF();
                                    System.out.println(resposta);
                                }
                                if(op.equals("12")){//mudar password
                                    String queryMudarPassowrd = mudarPassword(email);
                                    out.writeUTF(queryMudarPassowrd);
                                    resposta = in.readUTF();
                                    System.out.println(resposta);
                                }
                                if (op.equals("13")){
                                    System.out.println("VOLTE SEMPRE");
                                    s.close();
                                    System.exit(0);
                                }
                            }
                        }
                    }
                }
                else if(op.equals("3")){
                    System.out.println("VOLTE SEMPRE");
                    s.close();
                    System.exit(0);
                }else{
                    menuEntrada();

                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            if (s != null)
                try {
                    s.close();
                } catch (IOException e) {
                    System.out.println("close:" + e.getMessage());
                }
        }
    }
    /************************************METODOS PARA AS QUERIES******************************************************/
    /**************************************************mudar password*************************************************/
    public static String mudarPassword(String email){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String novaPassword = "";
        String queryNovaPassowrd = "";
        try {
            System.out.println("insira a nova passowrd");
            novaPassword = reader.readLine();
            queryNovaPassowrd = "UPDATE root.utilizador " +
                                 "set root.utilizador.password = '" +novaPassword+ "'" +
                                 "where root.utilizador.email = '" +email+ "'";
            return queryNovaPassowrd;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return queryNovaPassowrd;
        /*
        * UPDATE root.utilizador
            set root.utilizador.password = "0911"
            where root.utilizador.email = "rui"
        * */
    }
    /**********************************************mudar caminho de um ficheiro de musica*****************************/
    public static String mudarPathMusica(Socket s) throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String queryFinal ="";
        try{
            /*listar todas as musicas*/
            String listaMusicas = getMusicas();
            out.writeUTF(listaMusicas);
            String resposta = in.readUTF();
            System.out.println("lista de musicas");
            System.out.println(resposta);
            System.out.println("insira o nome da letra");
            String nomeMusica = reader.readLine();
            String IdMusica = getIdMusica(nomeMusica);

            out.writeUTF(IdMusica);
            String respostaIdMusica = in.readUTF();

            String r2[] = respostaIdMusica.split("\n");
            respostaIdMusica = r2[1].trim();

            System.out.println("insira o novo caminho do ficheiro da musica");
            String novoPath = reader.readLine();
            queryFinal = "UPDATE root.musica " +
                    "set root.musica.path_ficheiro = '" +novoPath+ "'" +
                    "where root.musica.id = '"+ respostaIdMusica +"'";


            return queryFinal;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return queryFinal;
    }

    /**********************************************mudar letra de uma musica******************************************/
    public static String mudarLetraMusica(Socket s) throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String queryFinal ="";
        try{
            /*listar todas as musicas*/
            String listaMusicas = getMusicas();
            out.writeUTF(listaMusicas);
            String resposta = in.readUTF();
            System.out.println("lista de musicas");
            System.out.println(resposta);
            System.out.println("insira o nome da letra");
            String nomeMusica = reader.readLine();
            String IdMusica = getIdMusica(nomeMusica);

            out.writeUTF(IdMusica);
            String respostaIdMusica = in.readUTF();

            String r2[] = respostaIdMusica.split("\n");
            respostaIdMusica = r2[1].trim();

            System.out.println("insira a noma letra");
            String novaLetra = reader.readLine();
            queryFinal = "UPDATE root.musica " +
                        "set root.musica.letra = '" +novaLetra+ "'" +
                        "where root.musica.id = '"+ respostaIdMusica +"'";


            return queryFinal;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return queryFinal;
    }
    /*********************************************MUDAR DATA DE UM CONCERTO*******************************************/
    public static String mudarDataConcertoDeUmArtista(Socket s) throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String queryFinal ="";
        try {
            System.out.println("insira o nome do artista");
            String artista = reader.readLine();
            String pedido = getConcertosArtista(artista);
            /*listar todos os concertos do artista*/
            out.writeUTF(pedido);
            String resposta = in.readUTF();
            System.out.println("concertos do artista)");
            System.out.println(resposta);
            System.out.println("insira a data a alterar do concerto");
            String dataAlterar = reader.readLine();
            String idConcertoAlterar = getIdConcertoPorArtista(artista,dataAlterar);
            out.writeUTF(idConcertoAlterar);
            String respostaID = in.readUTF();
            String r2[] = respostaID.split("\n");
            respostaID = r2[1].trim();

            System.out.println("insira nova data");
            String novaData = reader.readLine();
            queryFinal = "UPDATE root.concerto_artista,root.concerto " +
                        " set root.concerto.data = '"+ novaData+ "'" +
                        " where (root.concerto.id = '" + respostaID + "') and " +
                        " (root.concerto_artista.concerto_id = root.concerto.id) and " +
                        " (root.concerto_artista.artista_nome = '"+artista + "')";
            return queryFinal;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return queryFinal;
    }


    /*************************************mudar playlist para publico**************************************************/
    public static String mudarPlaylistParaPublico(Socket s, String util)throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String querySetToPublico = "";
        String playListsUtilizador = "";
        try {
            playListsUtilizador = listarPlayListsUtil(util);
            out.writeUTF(playListsUtilizador);
            String resposta = in.readUTF();
            System.out.println("playlists do utilizador  (0-publico / 1-privado))");
            System.out.println(resposta);

            System.out.println("nome da sua playlist a tornar publica");
            String nomePlaylist = reader.readLine();
            querySetToPublico =  setPlaylistPublica(nomePlaylist,util);

            return querySetToPublico;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return querySetToPublico;
    }

    /*************************************mudar playlist para privado*************************************************/
    public static String mudarPlaylistParaPrivado(Socket s, String util)throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String querySetToPrivado = "";
        String playListsUtilizador = "";
        try {
            playListsUtilizador = listarPlayListsUtil(util);
            out.writeUTF(playListsUtilizador);
            String resposta = in.readUTF();
            System.out.println("playlists do utilizador   (0-publico / 1-privado)");
            System.out.println(resposta);

            System.out.println("nome da sua playlist a tornar privado");
            String nomePlaylist = reader.readLine();
            querySetToPrivado =  setPlayListPrivada(nomePlaylist,util);

            return querySetToPrivado;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return querySetToPrivado;
    }


    /*********************************inserir musicas em playlists****************************************************/
    public static String inserirMusicasPlaylist(Socket s, String util) throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String queryInserirMusicasPlaylist = "";
        String playListsUtilizador = "";
        try {
            playListsUtilizador = listarPlayListsUtil(util);
            out.writeUTF(playListsUtilizador);
            String resposta = in.readUTF();
            System.out.println("playlists do utilizador");
            System.out.println(resposta);

            System.out.println("nome da sua playlist a associar musicas");
            String nomePlaylist = reader.readLine();

            String queryListagemDeMusicasNaPlaylistEscolhida = listarMusicasPlaylistDeUmUtilizador(util,nomePlaylist);
            out.writeUTF(queryListagemDeMusicasNaPlaylistEscolhida);
            String respostaListagem = in.readUTF();
            System.out.println("musicas na playlist " + nomePlaylist);
            System.out.println(respostaListagem);

            String idPlaylistAinserir = getIdPlaylist(nomePlaylist);
            out.writeUTF(idPlaylistAinserir);
            String respostaIdPlaylist = in.readUTF();

            String r2[] = respostaIdPlaylist.split("\n");
            respostaIdPlaylist = r2[1].trim();

            /*listar musicas no sistema*/
            String musicasEmSistema = getMusicas();
            out.writeUTF(musicasEmSistema);
            String respostaPedidoMusicasEmSistema = in.readUTF();
            System.out.println("musicas existentes no DropMusic");
            System.out.println(respostaPedidoMusicasEmSistema);

            System.out.println("insira o nome da musica a inserir na sua playlist");
            String musicaInserir = reader.readLine();

            String idMusica = getIdMusica(musicaInserir);
            out.writeUTF(idMusica);
            String respostaIdMusica = in.readUTF();

            String r3[] = respostaIdMusica.split("\n");
            String respostaIDmusica = r3[1].trim();

            queryInserirMusicasPlaylist = "INSERT into root.playlist_musica(root.playlist_musica.playlist_id,root.playlist_musica.musica_id,root.playlist_musica.musica_nome)" +
                                            "values('"+respostaIdPlaylist+"','"+respostaIDmusica+"','"+musicaInserir+"')";
            return queryInserirMusicasPlaylist;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return queryInserirMusicasPlaylist;
    }

    /**************************************criar playlist*************************************************************/
    public static String criarPlaylists(String util){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String queryCriarPlaylist = "";
        try {
            System.out.println("insira o nome que quer dar a sua playlist");
            String nomePlaylist = reader.readLine();
            System.out.println("insira o tipo da sua playlist (publico - 0/privado - 1)");
            String tipoPlaylist = reader.readLine();
            queryCriarPlaylist = "INSERT into root.playlist(root.playlist.id,root.playlist.nome,root.playlist.tipo,root.playlist.utilizador_email) " +
                                "values(null,'"+nomePlaylist+"','"+tipoPlaylist+"','"+util+"')";
            return queryCriarPlaylist;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryCriarPlaylist;
    }
    /******************************************inserir genero de musicas**********************************************/
    public static String inserirGenero(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String queryInserirGenero = "";
        String nomeGenero = "";
        try {
            System.out.println("insira o genero para adicionar ao banco de dados");
            nomeGenero = reader.readLine();
            queryInserirGenero = "INSERT into root.genero(root.genero.nome) values('"+nomeGenero+"')";
            return queryInserirGenero;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryInserirGenero;
    }

    /*****************************************inserir artistas********************************************************/
    public  static  String inserirArtista(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nomeArtista = "";
        String tipo = "";
        try {
            System.out.println("insira nome do artista");
            nomeArtista = reader.readLine();
            System.out.println("insira o tipo de artista (grupo ou musico(Sendo musico um artista individual))");
            tipo = reader.readLine();
            query ="INSERT INTO root.artista(root.artista.nome,root.artista.tipo) "+
                    "values('" +nomeArtista +"','"+tipo+"')";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    /**************************************inserir compositores*******************************************************/
    public static String inserirCompositor(Socket s) throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String query = "";
        String nomeCompositor  ="";
        String nomeArtista = "";
        String musicos = "";

        try {
            musicos = getArtistaPorTipo();
            out.writeUTF(musicos);
            String resposta = in.readUTF();
            System.out.println("musicos existentes no sistema");
            System.out.println(resposta);

            System.out.println("insira o nome do compositor");
            nomeCompositor = reader.readLine();
            System.out.println("insira o nome do artista existente na base de dados que quer associar a um compositor");
            nomeArtista = reader.readLine();
            query = "INSERT INTO root.compositor(root.compositor.nome, root.compositor.artista_nome) "+
                    "values('" + nomeCompositor + "','" + nomeArtista+"')";
            return query;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    /**********************************************inserir albuns******************************************************/
    public static String inserirAlbum(Socket s) throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String query="";
        String nomeAlbum = "";
        String dataLancamento  ="";
        String genero = "";
        String editora = "";
        String artista ="";

        try {
            String generos = getGenero();
            out.writeUTF(generos);
            String resposta = in.readUTF();
            System.out.println("generos existentes no sistema");
            System.out.println(resposta);

            String editoras = getEditora();
            out.writeUTF(editoras);
            resposta = in.readUTF();
            System.out.println("editoras existentes no sistema");
            System.out.println(resposta);

            String artistas = getArtistas();
            out.writeUTF(artistas);
            resposta = in.readUTF();
            System.out.println("artistas existentes no sistema");
            System.out.println(resposta);

            System.out.println("insira nome do album");
            nomeAlbum = reader.readLine();
            System.out.println("insira a data (AAAA-MM-DD)");
            dataLancamento = reader.readLine();
            System.out.println("insira o genero do album");
            genero = reader.readLine();
            System.out.println("genero de entrada" + genero);
            System.out.println("insira a editora do album");
            editora = reader.readLine();
            System.out.println("insira o artista do album");
            artista = reader.readLine();
            query = "INSERT into root.album(root.album.id,root.album.nome,root.album.data_lancamento,root.album.pontuacao, root.album.genero_nome,root.album.editora_nome,root.album.artista_nome)"+
                    "values(null,'"+nomeAlbum+"','"+dataLancamento+"',null,'"+genero+"','"+editora+"','"+artista+"')";
            return query;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    /***************************************inserir musicas em albuns**************************************************/
    public static String inserirMusicasEmAlbuns(Socket s) throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String query="";
        String queryMusicaAlbum="";
        String nomeAlbum = "";
        String nomeMusica = "";
        try {
            String albuns = getAlbuns();
            out.writeUTF(albuns);
            String resposta = in.readUTF();
            System.out.println("albuns existente no sistema");
            System.out.println(resposta);


            String artistas = getArtistas();
            out.writeUTF(artistas);
            resposta = in.readUTF();
            System.out.println("artistas existentes no sistema");
            System.out.println(resposta);

            String compositores = getcompositores();
            out.writeUTF(compositores);
            resposta = in.readUTF();
            System.out.println("compositores existentes no sistema");
            System.out.println(resposta);

            System.out.println("insira nome do album");
            nomeAlbum = reader.readLine();


            System.out.println("insira nome da musica");
            nomeMusica = reader.readLine();


            System.out.println("insira a data (AAAA-MM-DD)");
            String dataLancamento = reader.readLine();

            System.out.println("insira a letra");
            String letra = reader.readLine();

            System.out.println("o caminho do ficheiro");
            String caminho = reader.readLine();

            System.out.println("insira o artista");
            String artista = reader.readLine();

            System.out.println("insira o compositor");
            String compositor = reader.readLine();

            query = "INSERT into root.musica(root.musica.id,root.musica.nome,root.musica.data_lancamento,root.musica.letra,root.musica.path_ficheiro,root.musica.artista_nome,root.musica.compositor_artista_nome) " +
                    "values(null,'"+nomeMusica+"','"+dataLancamento+"','"+letra+"','"+caminho+"','"+artista+"','"+compositor+"')";

            out.writeUTF(query);
            resposta = in.readUTF();
            System.out.println(resposta);

            String idMusica = getIdMusica(nomeMusica);
            out.writeUTF(idMusica);
            String respostaIDMusica = in.readUTF();
            System.out.println(respostaIDMusica);

            String r[] = respostaIDMusica.split("\n");
            respostaIDMusica = r[1].trim();

            String idAlbbum = getAlbumPorId(nomeAlbum);
            out.writeUTF(idAlbbum);
            String respostaIAlbum = in.readUTF();

            String r2[] = respostaIAlbum.split("\n");
            respostaIAlbum = r2[1].trim();

            queryMusicaAlbum = "INSERT into root.album_musica(root.album_musica.album_id, root.album_musica.musica_id, root.album_musica.musica_nome) " +
                                "values("+respostaIAlbum+","+respostaIDMusica+",'"+nomeMusica.trim()+"')";

            return queryMusicaAlbum;

        }catch (IOException e) {
            e.printStackTrace();
        }
        return queryMusicaAlbum;
    }
    /*********************************************inserir concertos**************************************************/
    public static String inserirConcertos(Socket s) throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String query="";
        try {
            String artistas = getArtistas();
            out.writeUTF(artistas);
            String respostaArtistas = in.readUTF();
            System.out.println("artistas existentes no sistema");
            System.out.println(respostaArtistas);

            System.out.println("nome do artista");
            String artista = reader.readLine();

            System.out.println("insira a data do concerto (AAAA-MM-DD)");
            String data = reader.readLine();
            //insert into root.concerto(root.concerto.id,root.concerto.data) value(null,"2010-02-02")
            String queryConcerto = "INSERT into root.concerto(root.concerto.id,root.concerto.data) values(null,'"+data+"')";
            out.writeUTF(queryConcerto);
            String reply = in.readUTF();
            System.out.println(reply);

            String id = getIdConcerto(data);
            out.writeUTF(id);
            String idConcerto = in.readUTF();
            System.out.println("recebi o id ->"+idConcerto);
            String r[] = idConcerto.split("\n");
            idConcerto = r[1].trim();


            query = "INSERT into root.concerto_artista(root.concerto_artista.concerto_id,root.concerto_artista.artista_nome) "+
                    "values("+idConcerto+",'"+artista+"')";
            return query;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return query;

    }
    /**********************************************inserir critica****************************************************/
    public static String inserirCritica(Socket s, String util) throws IOException{
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        DataInputStream in = new DataInputStream(s.getInputStream());
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String queryCritica = "";
        try {
            String albuns = getAlbuns();
            out.writeUTF(albuns);
            String resposta = in.readUTF();
            System.out.println("albuns existente no sistema");
            System.out.println(resposta);

            System.out.println("insira o nome do album");
            String nomeAlbum = reader.readLine();

            System.out.println("insira a pontuacao dar ao album (0 a 9)");
            String pontuacao = reader.readLine();

            System.out.println("insira uma breve justificacao da sua opiniao sobre o album");
            String justificacao = reader.readLine();

            String idAlbbum = getAlbumPorId(nomeAlbum);
            out.writeUTF(idAlbbum);
            String respostaIAlbum = in.readUTF();

            String r2[] = respostaIAlbum.split("\n");
            respostaIAlbum = r2[1].trim();


            /*actualizar cririca no album*/
            String media = getPontuacoes(respostaIAlbum);
            out.writeUTF(media);
            String respostaMedia = in.readUTF();
            String r3[] = respostaMedia.split("\n");
            respostaMedia = r3[1].trim();
            String actaliza = actualizaPontuacaoAlbum(respostaIAlbum,respostaMedia);
            out.writeUTF(actaliza);
            String RespostaActaliza = in.readUTF();
            queryCritica = "INSERT into root.critica(root.critica.id,root.critica.pontuacao,root.critica.justificacao,root.critica.utilizador_email,root.critica.album_id) "+
                            "values(null,'"+pontuacao+"','"+justificacao+"','"+util+"','"+respostaIAlbum+"')";

            return queryCritica;
        }catch (IOException e){
            e.printStackTrace();
        }
        return queryCritica;
    }
    /*****************************************listar musicas de uma playlist*****************************************/
    public static String listarMusicasPlaylistDeUmUtilizador(String email, String nomePlaylist){
        String ListagemMusicasDeUmaPlaylist= "SELECT root.playlist.nome, root.playlist_musica.musica_nome " +
                                            "from root.playlist,root.playlist_musica " +
                                            "where root.playlist.id = root.playlist_musica.playlist_id and (root.playlist.utilizador_email = '" + email+"')" +
                                            "and (root.playlist.nome = '" + nomePlaylist+"')";
        return ListagemMusicasDeUmaPlaylist;
    }
    /***********************************************tornar playlist publica******************************************/
    public static String setPlaylistPublica(String nomePlaylist,String util){
        String queryPlaylistToPublica = "UPDATE root.playlist " +
                                        "set root.playlist.tipo = '0' " +
                                        "where (root.playlist.nome = '" + nomePlaylist+ "') and " +
                                        "(root.playlist.utilizador_email ='" + util+ "')";
        return queryPlaylistToPublica;
    }
    /******************************************tornar playlist privada************************************************/
    public static String setPlayListPrivada(String nomePlaylist, String util){
        String queryPlaylistPrivada ="UPDATE root.playlist " +
                "set root.playlist.tipo = '1' " +
                "where (root.playlist.nome = '" + nomePlaylist+"') and " +
                "(root.playlist.utilizador_email ='" + util+"')";
        return queryPlaylistPrivada;
    }


    /*****************************************actualizar pontuaçao no album******************************************/
    public static String actualizaPontuacaoAlbum(String idAlbum, String pontuacao){
        String novaPontuacao = "UPDATE root.album " +
                                "set root.album.pontuacao = '" +pontuacao + "'"+
                                "WHERE root.album.id = '"+idAlbum+"'";
        return novaPontuacao;

    }
    /***********************************************obter idConcerto por data e artista********************************/
    public static String getIdConcertoPorArtista(String artista,String data){
        String query = "SELECT root.concerto_artista.concerto_id " +
                "FROM root.concerto_artista, root.concerto " +
                "WHERE (root.concerto_artista.concerto_id = root.concerto.id) and " +
                "(root.concerto.data = '" + data+ "') and " +
        "(root.concerto_artista.artista_nome = '" + artista+ "')";
        return query;
    }
    /*****************************listar playlists publicas de todos os utilizadores *********************************/
    public static String listarPlaylistsPublicas(){
        String listarPlaylistsPublicas = "";
        listarPlaylistsPublicas = "SELECT root.playlist.nome,root.playlist.utilizador_email,root.playlist_musica.musica_nome "+
                                    "FROM root.playlist,root.playlist_musica "+
                                    "where (root.playlist.tipo = '0') and " +
                                    "(root.playlist.id = root.playlist_musica.playlist_id)";
        return  listarPlaylistsPublicas;
    }
    /***************************************listar playlists de um utilizador*****************************************/
    public static String listarPlayListsUtil(String util){
        String listaPlaylistsUtil ="SELECT root.playlist.nome,root.playlist.tipo " +
                                    "FROM root.playlist " +
                                    "where root.playlist.utilizador_email = '"+util+"'";
        return  listaPlaylistsUtil;
    }



    /******************************************get IdPlaylist********************************************************/
    public static String getIdPlaylist(String nome){
        String queryIdPlaylist = "SELECT root.playlist.id " +
                                 "FROM root.playlist " +
                                 "where root.playlist.nome = '"+nome+"'";
        return queryIdPlaylist;
    }

    /******************************************get media das criticas**********************************/
    public static String getPontuacoes(String idAlbum){
        String queryPontucoes = "SELECT ROUND(AVG(root.critica.pontuacao),2)"+
                                "FROM root.critica "+
                                "WHERE root.critica.album_id = '"+idAlbum+"'";
        return queryPontucoes;
    }
    /***********************************get concertos por artista****************************************************/
    public static String getConcertosArtista(String nome){
        String query="";
        query ="SELECT  root.concerto_artista.artista_nome, "+
                "root.concerto.data "+
                "FROM root.concerto_artista, root.concerto "+
                "WHERE (root.concerto_artista.concerto_id = root.concerto.id) and (root.concerto_artista.artista_nome = '" + nome+"')";
        return query;
    }


    /*********************************get id de um concerto**********************************************************/
    public static String getIdConcerto(String data){
        String idConcerto = "SELECT root.concerto.id " +
                            "FROM root.concerto " +
                            "WHERE root.concerto.data = '"+data+"'";
        return idConcerto;
    }


    /****************************************get id dum album********************************************************/
    public static String getIdMusica(String nome){
        String idMusica = "SELECT root.musica.id " +
                        "FROM root.musica " +
                        "where root.musica.nome = '"+nome+"'";
        return idMusica;
    }
    /**************************************************************************************************************/
    public static String getcompositores(){
        String compositores = "SELECT root.compositor.nome " +
                                "FROM root.compositor";
        return compositores;
    }

    /****************************************get albuns**************************************************************/
    public static String getAlbumPorId(String nome){
        String idAlbum = "SELECT root.album.id "+
                        "FROM root.album " +
                        "where root.album.nome = '"+nome+"'";
        return idAlbum;
    }

    public static String getAlbuns(){
        String queryAlbuns = "SELECT root.album.nome, "+
                                    "root.album.data_lancamento, "+
                                    "root.album.pontuacao, "+
                                    "root.album.genero_nome, "+
                                    "root.album.editora_nome, "+
                                    "root.album.artista_nome " +
                                    "FROM root.album";
        return queryAlbuns;
    }
    /****************************get musicas************************************************************************/
    public static String getMusicas(){
        String queryMusicas = "SELECT root.musica.nome, " +
                "root.musica.data_lancamento, " +
                "root.musica.letra, " +
                "root.musica.artista_nome, " +
                "root.musica.compositor_artista_nome " +
                "FROM root.musica";
        return queryMusicas;
    }
    /***************************************get compositores por tipo musico*****************************************/
    public static String getArtistaPorTipo(){
        String queryArtistaTipo = "SELECT * " +
                "FROM root.artista " +
                "where root.artista.tipo = 'musico'";
        return queryArtistaTipo;
    }
    /***************************************get artista**************************************************************/
    public static String getArtistas(){
        String queryEditora= "SELECT * FROM root.artista;";
        return queryEditora;
    }

    /***************************************get editora**************************************************************/
    public static String getEditora(){
        String queryEditora= "SELECT * FROM root.editora";
        return queryEditora;
    }
    /***************************************get genero*************************************************************/
    public static String getGenero(){
        String queryGenero= "SELECT * FROM root.genero";
        return queryGenero;
    }
    /***************************************selects de criticas****************************************************/
    public static String pesqusiarCriticasPorAlbum(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nome = "";

        try {
            System.out.println("insira nome do album");
            nome = reader.readLine();
            query ="SELECT root.artista.nome ,"+
                        "root.album.nome , "+
                        "root.album.genero_nome , " +
                        "root.album.data_lancamento ," +
                        "root.album.editora_nome, " +
                        "root.album.pontuacao , " +
                        "root.critica.justificacao ," +
                        "root.critica.utilizador_email "+
                    "FROM root.artista, root.album, root.critica, root.utilizador "+
                    "WHERE (root.artista.nome = root.album.artista_nome) and "+
                            "(root.critica.utilizador_email = root.utilizador.email) and " +
                            "(root.critica.album_id = root.album.id) and " +
                            "(root.album.nome =  '" + nome+"')";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    /************************************selects de concertos******************************************************/
    public static String pesqusiarConcertoPorArtista(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nome = "";

        try {
            System.out.println("insira nome do artista");
            nome = reader.readLine();
            query ="SELECT  root.concerto_artista.artista_nome, "+
                    "root.concerto.data "+
                    "FROM root.concerto_artista, root.concerto "+
                    "WHERE (root.concerto_artista.concerto_id = root.concerto.id) and (root.concerto_artista.artista_nome = '" + nome+"')";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    /*SELECT  root.concerto_artista.artista_nome, root.concerto.data
    FROM root.concerto_artista, root.concerto
    WHERE (root.concerto_artista.concerto_id = root.concerto.id) and (root.concerto_artista.artista_nome = "artic_monkies")*/
    /************************************selects de artistas*******************************************************/
    public static String pesqusiarArtistaPorMusica(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nome = "";

        try {
            System.out.println("insira nome da musica");
            nome = reader.readLine();
            query ="SELECT root.artista.nome, "+
                    "root.album.nome , "+
                    "root.album.pontuacao , "+
                    "root.album.genero_nome , "+
                    "root.album.data_lancamento ," +
                    "root.album.editora_nome ," +
                    "root.album_musica.musica_nome "+
                    "from root.artista, root.album, root.album_musica "+
                    "where (root.artista.nome = root.album.artista_nome) and (root.album.id =  root.album_musica.album_id) and root.album_musica.musica_nome ='" + nome+"'";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    public static String pesqusiarArtistaPorAlbum(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nome = "";

        try {
            System.out.println("insira nome do album");
            nome = reader.readLine();
            query ="SELECT root.artista.nome, "+
                    "root.album.nome , "+
                    "root.album.pontuacao , "+
                    "root.critica.justificacao , "+
                    "root.album.genero_nome , "+
                    "root.album.data_lancamento ," +
                    "root.album.editora_nome ," +
                    "root.album_musica.musica_nome "+
                    "from root.artista, root.album, root.album_musica, root.critica "+
                    "where (root.artista.nome = root.album.artista_nome) and (root.album.id =  root.album_musica.album_id)  and (root.critica.album_id = root.album.id) and root.album.nome = '" + nome+"'";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    public static String pesqusiarArtistaPorNome(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nome = "";

        try {
            System.out.println("insira nome do artista");
            nome = reader.readLine();
            query ="SELECT root.artista.nome, "+
                    "root.album.nome , "+
                    "root.album.pontuacao , "+
                    "root.album.genero_nome , "+
                    "root.album.data_lancamento ," +
                    "root.album.editora_nome ," +
                    "root.album_musica.musica_nome "+
                    "from root.artista, root.album, root.album_musica "+
                    "where (root.artista.nome = root.album.artista_nome) and (root.album.id =  root.album_musica.album_id) and root.album.artista_nome ='"+nome+"'";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    /***************************************selects de musicas****************************************************/
    public static String pesqusiarMusicaPorArtista(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nome = "";

        try {
            System.out.println("insira nome do artista");
            nome = reader.readLine();
            query = "SELECT * FROM root.musica where root.musica.artista_nome = '" + nome+"'";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    public static String pesqusiarMusicaPorAlbum(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nome = "";

        try {
            System.out.println("insira nome da album");
            nome = reader.readLine();
            query = "SELECT root.album_musica.musica_nome, root.album.nome FROM root.album ,root.album_musica   where (root.album_musica.album_id =  root.album.id) and root.album.nome ='" + nome+"'";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    public static String pesqusiarMusicaPorNome(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nome = "";

        try {
            System.out.println("insira nome da musica");
            nome = reader.readLine();
            query = "SELECT * FROM root.musica where root.musica.nome = '" + nome+"'";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    /************************************selects de albuns************************************************************/
    public static String pesqusiarAlbumPorNome(){
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String query="";
        String nome = "";

        try {
            System.out.println("insira nome do album");
            nome = reader.readLine();
            query = "SELECT * FROM root.album where root.album.nome = '" + nome+"'";
            return query;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }
    public static String pesqusiarAlbumPorArtista(){
        InputStreamReader inputA = new InputStreamReader(System.in);
        BufferedReader readerA = new BufferedReader(inputA);
        String queryA="";
        String nomeA = "";

        try {
            System.out.println("insira nome do album");
            nomeA = readerA.readLine();
            queryA = "SELECT * FROM root.album where root.album.artista_nome =  '" + nomeA+"'";
            return queryA;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queryA;
    }
    /*********************************sub-menus**********************************************************************/
    public static void menuEditarMusicas(){
        System.out.println("1-mudar letra da musica");
        System.out.println("2-mudar o caminho do ficheiro da musica");
    }


    public static void menuPesquisaArtista(){
        System.out.println("1-pesquisar artista por nome de artista");
        System.out.println("2-pesquisar artista por nome de album");
        System.out.println("3-pesquisar artista por nome de musica");
    }
    public static void menuPesquisaMusica(){
        System.out.println("1-pesquisar musica por nome de musica");
        System.out.println("2-pesquisar musica por nome de album");
        System.out.println("3-pesquisar musica por nome de artista");
    }
    public static void menuPesquisaAlbuns(){
        System.out.println("1-pesquisar albuns por nome de album");
        System.out.println("2-pesquisar albuns por nome de artista");
    }
    /*******************************menus principais de admin, user e entrada****************************************/
    public static void menuAdmin(){
        System.out.println("MENU ADMINSTRADOR");
        System.out.println("----------INSERCOES");
        System.out.println("1-inserir artistas");
        System.out.println("2-inserir musicas");
        System.out.println("3-inserir albuns");
        System.out.println("4-inserir concertos");
        System.out.println("5-inserir compositores");
        System.out.println("6-inserir critica");
        System.out.println("7-inserir generos");
        System.out.println("8-criar playlist");
        System.out.println("9-inserir musicas em playlists");
        System.out.println("----------PESQUISAS");
        System.out.println("10-pesquisar albuns");
        System.out.println("11-pesquisar musicas");
        System.out.println("12-pesquisar artistas");
        System.out.println("13-pesquisar concertos");
        System.out.println("14-pesquisar criticas");
        System.out.println("----------EDICOES");
        System.out.println("15-editar musicas");
        System.out.println("16-editar concertos");
        System.out.println("----------PLAYLISTS");
        System.out.println("17-tornar playlist publica");
        System.out.println("18-tornar playlist privada");
        System.out.println("19-listar todas as playlists publicas do sistema");
        System.out.println("----------CONTA PESSOAL");
        System.out.println("20-mudar password");
        System.out.println("21-sair");
    }


    public static void menuUtil(){
        System.out.println("MENU UTILIZADOR");
        System.out.println("----------CRITICAS");
        System.out.println("1-inserir critica");
        System.out.println("----------PESQUISAS");
        System.out.println("2-pesquisar albuns");
        System.out.println("3-pesquisar musicas");
        System.out.println("4-pesquisar artistas");
        System.out.println("5-pesquisar concertos");
        System.out.println("6-pesquisar criticas");
        System.out.println("----------Playlists");
        System.out.println("7-criar uma playlist");
        System.out.println("8-inserir musicas em playlists");
        System.out.println("9-tornar playlist publica");
        System.out.println("10-tornar playlist privada");
        System.out.println("11-listar todas as playlists publicas do sistema");
        System.out.println("----------CONTA PESSOAL");
        System.out.println("12-mudar password");
        System.out.println("13-sair");
    }
    public static void menuEntrada(){
        System.out.println("DROP MUSIC");
        System.out.println("1-criar conta de utilizador");
        System.out.println("2-login");
        System.out.println("3-sair");
    }
}
