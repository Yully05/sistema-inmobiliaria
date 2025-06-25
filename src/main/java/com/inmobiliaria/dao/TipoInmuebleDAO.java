package com.inmobiliaria.dao;

import com.inmobiliaria.model.TipoInmueble;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * @author Asus
 */
public class TipoInmuebleDAO {

    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public TipoInmuebleDAO() {
        connection = conexion.establecerConexion();
    }

    public List<TipoInmueble> listarTiposInmueble() {

        List<TipoInmueble> tiposInmuebles = new ArrayList();
        String sql = "SELECT * FROM tipo_inmueble";

        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoInmueble tipoInm = new TipoInmueble();
                tipoInm.setId(rs.getInt("id"));
                tipoInm.setTipoInmueble(rs.getString("tipo"));
                tiposInmuebles.add(tipoInm);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar Tipos de Inmueble: " + e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return tiposInmuebles;
    }

    public TipoInmueble consultarTipoInmId(int id) { //consulta por ID
        TipoInmueble tipoInm = null;
        String sql = "SELECT * FROM tipo_inmueble WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                tipoInm = new TipoInmueble();
                tipoInm.setId(rs.getInt("id")); // Asignar el valor
                tipoInm.setTipoInmueble(rs.getString("tipo")); // Asignar el valor
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return tipoInm;

    }

    public TipoInmueble consultarTipoInm(String tipo) { //consulta por tipoInm
        TipoInmueble tipoInm = null;
        String sql = "SELECT * FROM tipo_inmueble WHERE tipo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            if (rs.next()) {
                tipoInm = new TipoInmueble();
                tipoInm.setId(rs.getInt("id")); // Asignar el valor
                tipoInm.setTipoInmueble(rs.getString("tipo")); // Asignar el valor
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la consulta: " + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return tipoInm;
    }
}