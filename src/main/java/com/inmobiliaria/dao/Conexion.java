/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class Conexion {

    private static final String url = "jdbc:postgresql://ep-silent-waterfall-a4zakeb5-pooler.us-east-1.aws.neon.tech/neondb?sslmode=require";
    private static final String user = "neondb_owner";
    private static final String password = "npg_tbvyf2JkHO4z";

    public Connection establecerConexion() {
        Connection conectar = null;

        try {
            conectar = DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null, "Se conecto correctamente a la base de datos");

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos" + e.toString());
        }
        return conectar;
    }

}
