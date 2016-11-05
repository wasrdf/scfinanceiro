/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scfinaceiro.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Was
 */
public class Conexao {

    Connection connection = null;

    public Connection conectar() {
        String url = "jdbc:mysql://localhost:3306/scfinanceiro";
        String username = "root";
        String password = "26583205";

        System.out.println("Connecting database...");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }

}
