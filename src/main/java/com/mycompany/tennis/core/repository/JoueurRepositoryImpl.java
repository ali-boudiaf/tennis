package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JoueurRepositoryImpl {

    public void create(Joueur joueur) {
        Connection conn = null;
        try {

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5);
            dataSource.setUrl("jdbc:mysql://localhost:8889/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            dataSource.setUsername("root");
            dataSource.setPassword("root");

            conn = dataSource.getConnection();


            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO JOUEUR (NOM, PRENOM, SEXE) VALUES (?,?,?)");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());


            preparedStatement.executeUpdate();


            conn.commit();

            System.out.println("Joueur créé");


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Joueur joueur) {
        Connection conn = null;
        try {

            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setInitialSize(5);
            dataSource.setUrl("jdbc:mysql://localhost:8889/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris");

            dataSource.setUsername("root");
            dataSource.setPassword("root");

            conn = dataSource.getConnection();


            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());


            preparedStatement.executeUpdate();


            conn.commit();

            System.out.println("Joueur créé");


        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
