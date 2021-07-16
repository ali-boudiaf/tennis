package com.mycompany.tennis.core;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class TestDeConnection {
    public static void main(String... args){
        Connection conn = null;
        try {

                BasicDataSource dataSource = new BasicDataSource();
                dataSource.setInitialSize(5);
                dataSource.setUrl("jdbc:mysql://localhost:8889/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

                dataSource.setUsername("root");
                dataSource.setPassword("root");

                conn = dataSource.getConnection();

                conn.setAutoCommit(false);

                //Oracle Driver officiel OJDBC Thin
                //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","COURSDB","COURSDB");
                //Postgres Driver officiel
                //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","COURSDB","COURSDB");

                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?,?,?)");
                String nom = "Capriati";
                String prenom = "Jennifer";
                String sexe = "F";
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, sexe);


                preparedStatement.executeUpdate();

                nom = "Johannson";
                prenom = "Thomas";
                sexe = "M";
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, sexe);


                preparedStatement.executeUpdate();

                conn.commit();

                System.out.println("success");


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn!=null) conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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

