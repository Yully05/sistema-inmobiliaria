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
    
    private static final String url = "jdbc:postgresql://localhost:5432/db_inmobiliaria";
    private static final String user = "postgres";
    private static final String password = "toor123";
    
    
    public Connection establecerConexion(){
        
        Connection conectar = null;
        try {
            conectar = DriverManager.getConnection(url, user, password);
            JOptionPane.showMessageDialog(null, "Se conecto correctamente a la base de datos");
            
        } catch (HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos" + e.toString());
        }
        return conectar;
    }
    
    
}
