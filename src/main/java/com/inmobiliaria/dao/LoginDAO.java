package com.inmobiliaria.dao;

import com.inmobiliaria.model.AgenteComercial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @author Asus
 */
public class LoginDAO {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;
    Conexion conexion = new Conexion();

    public AgenteComercial log(String login, String contrasena) throws SQLException {

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
                agente.setApellidos(rs.getString("apellidos")); // Mantener 'apellidos'
                agente.setRol(rs.getString("rol"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return agente;
    }
}