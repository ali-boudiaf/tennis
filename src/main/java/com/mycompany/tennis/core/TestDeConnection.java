package com.mycompany.tennis.core;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {
            //Seulement avant Java 7/JDBC 4
            //Class.forName(DRIVER_CLASS_NAME);

            //MySQL driver MySQL Connector
            conn = DriverManager.getConnection("jdbc:mysql://localhost:8889/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","root","root");
            //Oracle Driver officiel OJDBC Thin
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","COURSDB","COURSDB");
            //Postgres Driver officiel
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","COURSDB","COURSDB");

            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=? WHERE ID=?");
            Long identifiant=24L;
            String nom = "Errani";
            String prenom = "Sara";
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setLong(3, identifiant);


            int nbEnregistrementModifies = preparedStatement.executeUpdate();


            System.out.println("nbEnregistrementModifie "+nbEnregistrementModifies);
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

