package com.inmobiliaria.dao;

import com.inmobiliaria.model.Propietario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Asus
 */
public class PropietarioDAO {

    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public PropietarioDAO() {
        connection = conexion.establecerConexion();
    }

    public boolean RegistrarPropietario(Propietario propietario) {

        String sql = "INSERT INTO propietario (cedula, nombres, apellidos, direccion, fecha_nacimiento)" // Mantener 'apellidos'
                + " VALUES (?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, propietario.getCedula());
            ps.setString(2, propietario.getNombres());
            ps.setString(3, propietario.getApellidos()); // Mantener 'apellidos'
            ps.setString(4, propietario.getDireccion());
            ps.setDate(5, Date.valueOf(propietario.getFechaNacimiento()));

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar Propietario: " + e.toString());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean ActualizarPropietario(Propietario propietario) {

        String sql = "UPDATE propietario SET nombres = ?, apellidos = ?, direccion = ?, fecha_nacimiento = ? WHERE cedula = ?"; // Mantener 'apellidos'
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, propietario.getNombres());
            ps.setString(2, propietario.getApellidos()); // Mantener 'apellidos'
            ps.setString(3, propietario.getDireccion());
            ps.setDate(4, Date.valueOf(propietario.getFechaNacimiento()));
            ps.setString(5, propietario.getCedula());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Propietario: " + e.toString());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean EliminarPropietario(String cedula) {
        String sql = "DELETE FROM propietario WHERE cedula = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cedula);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Propietario: " + e.toString());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public Propietario ConsultarPropietario(String cedula) {
        Propietario propietario = null;
        String sql = "SELECT * FROM propietario WHERE cedula = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if (rs.next()) {
                propietario = new Propietario();
                propietario.setCedula(rs.getString("cedula"));
                propietario.setNombres(rs.getString("nombres"));
                propietario.setApellidos(rs.getString("apellidos")); // Mantener 'apellidos'
                propietario.setDireccion(rs.getString("direccion"));
                propietario.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta de Propietario" + e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return propietario;
    }

    public List<Propietario> listarPropietario() { // Cambiado a public para ser accesible desde el controlador
        List<Propietario> listaPropietario = new ArrayList<>();
        String sql = "SELECT * FROM propietario";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Propietario propietario = new Propietario();
                propietario.setCedula(rs.getString("cedula"));
                propietario.setNombres(rs.getString("nombres"));
                propietario.setApellidos(rs.getString("apellidos")); // Mantener 'apellidos'
                propietario.setDireccion(rs.getString("direccion"));
                propietario.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                listaPropietario.add(propietario);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar Propietarios" + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaPropietario;
    }
}