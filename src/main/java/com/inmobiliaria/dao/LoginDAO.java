/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.dao;

import com.inmobiliaria.model.AgenteComercial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class LoginDAO {
    
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    Conexion conexion = new Conexion();
        
    public AgenteComercial log(String login, String contrasena) throws SQLException{
        
        AgenteComercial agente = new AgenteComercial();
        String sql = "SELECT * FROM agente_comercial WHERE login = ? AND contrasena = ?";
        try {
            connection = conexion.establecerConexion();
            ps = connection.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            if (rs.next()) {
                agente.setCedula(rs.getString("cedula"));
                agente.setLogin(rs.getString("login"));
                agente.setContrasena(rs.getString("contrasena"));
                agente.setNombres(rs.getString("nombres"));
                agente.setRol(rs.getString("rol"));
                
            } 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en el inicio de sesion" + e.toString());
            }
        return agente;
        }   
    
}
