package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;


public class ConnectionConfiguration {
    private static Statement statement;
    static  Connection conn = null;
    static String url       = "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static String user      = "root";
    static String password  = "dbdb";
    static String schema = "root";
    public static Connection getConnection(){


        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(url, user, password);
        }catch(SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void scriptSQL(String ficheiro) {
        // Ler ficheiro aqui.
        String script = "";

        BufferedReader br = null;
        try {
            String line;
            //System.out.println("ficheiro" + ficheiro);
            //File file = new File(ficheiro);
            FileReader fis = new FileReader(new File(ficheiro));
            br = new BufferedReader(fis);
            while (true) {
                line = br.readLine();
                if (line == null)
                    break;
                script += line;
            }
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
        }
        try {
            statement = conn.createStatement();
            //statement.executeUpdate("CREATE DATABASE " +" root");
           // System.out.println("Created database with name " + "root" + ".");

            conn = DriverManager.getConnection(url, user, password);
            statement = conn.createStatement();

            for (String action : script.split(";")) {
                System.out.println("Executando:\n" + action);
                statement.executeUpdate(action + ";");
            }
            System.out.println("tabela " + ficheiro + "criada com sucesso");
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + e.getMessage());
        }

    }
}

