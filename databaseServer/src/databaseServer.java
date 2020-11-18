import com.util.ConnectionConfiguration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

import static com.util.ConnectionConfiguration.scriptSQL;

public class databaseServer {
    static Connection conn = null;
    public static void main(String[] args) throws SQLException {

        int numero=0;
        try {
            conn = ConnectionConfiguration.getConnection();
            if(conn != null){
                System.out.println("ligacao estabelecida");
                String path = System.getProperty ("user.dir");
                //path.replaceAll("[^a-zA-Z0-9 -]","/");
                //System.out.println("caminho " + path  +"/src/com/util/tabelas/utilizador.sql");

                scriptSQL(path  +"\\src\\com\\util\\tabelas\\criar_tabelas.sql");
                scriptSQL(path  +"\\src\\com\\util\\tabelas\\inserirDadosTabelas.sql");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            int serverPort = 7000;
            System.out.println("A Escuta no Porto 7000");
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("LISTEN SOCKET="+listenSocket);
            while(true) {
                Socket clientSocket = listenSocket.accept(); // BLOQUEANTE
                System.out.println("CLIENT_SOCKET (created at accept())="+clientSocket);
                numero ++;
                new ligacaoTCP(clientSocket, numero,conn);
            }
        }catch(IOException e) {
            System.out.println("Listen:" + e.getMessage());
        }

    }
}


class ligacaoTCP extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    int thread_number;

    public ligacaoTCP (Socket aClientSocket, int numero, Connection conn) {
        thread_number = numero;
        try{
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        }catch(IOException e){System.out.println("Connection:" + e.getMessage());}
    }
    //=============================
    public void run(){
        String resposta = "";
        try{
            while(true){
                //an echo server
                String data = in.readUTF();
                String verifica[];

                System.out.println("T["+thread_number + "] Recebeu: "+data);
                verifica=data.split(" ");

                switch (verifica[0]) {
                    case "SELECT":
                        databaseServer.conn.setAutoCommit(false);
                        Statement ps = databaseServer.conn.createStatement();
                        ResultSet result = ps.executeQuery(data);
                        databaseServer.conn.commit();
                        verifica = data.split(" ");
                        if (verifica[1].equals("email")) {
                            if (result.next()) {
                                resposta = "aceite";

                            } else {
                                resposta = "recusado";

                            }
                        } else {
                            ResultSetMetaData meta = result.getMetaData();
                            int colCount = meta.getColumnCount();
                            int[] spacing = new int[colCount];
                            //fazer cabecalho da pesquisa
                            for (int i = 0; i < colCount; i++) {
                                //System.out.print('|');
                                String colName = meta.getColumnName(i + 1);
                                spacing[i] = meta.getColumnDisplaySize(i + 1);
                                resposta += colName;
                                //System.out.print(colName);
                                //System.out.print("   ");
                                resposta += "   ";
                            }
                            resposta += "\n";
                            //System.out.println();
                            //imprimir queries
                            while (result.next()) {

                                for (int i = 1; i <= colCount; i++) {
                                    resposta += result.getString(i) + " ";
                                }
                                resposta += "\n";
                            }
                        }

                        out.writeUTF(resposta);
                        resposta = "";
                        break;
                    case "INSERT": {
                        databaseServer.conn.setAutoCommit(false);
                        Statement preparedStmt = databaseServer.conn.createStatement();
                        preparedStmt.executeUpdate(data);
                        databaseServer.conn.commit();
                        resposta = "insercao efectuada com sucesso";
                        System.out.println("a enviar " + resposta);
                        out.writeUTF(resposta);
                        resposta = "";
                        break;
                    }
                    case "UPDATE": {
                        databaseServer.conn.setAutoCommit(false);
                        Statement preparedStmt = databaseServer.conn.createStatement();
                        preparedStmt.executeUpdate(data);
                        databaseServer.conn.commit();
                        resposta = "updade feito com sucesso";
                        System.out.println("a enviar " + resposta);
                        out.writeUTF(resposta);
                        resposta = "";
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}