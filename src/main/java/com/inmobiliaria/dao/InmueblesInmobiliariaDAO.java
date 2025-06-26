package com.inmobiliaria.dao;

import com.inmobiliaria.model.InmueblesInmobiliaria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InmueblesInmobiliariaDAO {
    private final Conexion conexion = new Conexion();
    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean registrarInmueble(InmueblesInmobiliaria inmueble) {
        String sql = "INSERT INTO inmuebles_inmobiliaria (codigo_inmueble, fecha_adquisicion, costo) VALUES (?, ?, ?)";
        try {
            connection = conexion.establecerConexion();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, inmueble.getCodigo());
            ps.setDate(2, Date.valueOf(inmueble.getFechaAdquisicion()));
            ps.setDouble(3, inmueble.getCosto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar inmueble: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    public boolean actualizarInmueble(InmueblesInmobiliaria inmueble) {
        String sql = "UPDATE inmuebles_inmobiliaria SET fecha_adquisicion = ?, costo = ? WHERE codigo_inmueble = ?";
        try {
            connection = conexion.establecerConexion();
            ps = connection.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(inmueble.getFechaAdquisicion()));
            ps.setDouble(2, inmueble.getCosto());
            ps.setInt(3, inmueble.getCodigo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar inmueble: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    public boolean eliminarInmueble(int codigo) {
        String sql = "DELETE FROM inmuebles_inmobiliaria WHERE codigo_inmueble = ?";
        try {
            connection = conexion.establecerConexion();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar inmueble: " + e.getMessage());
            return false;
        } finally {
            cerrarRecursos();
        }
    }

    public InmueblesInmobiliaria consultarInmueble(int codigo) {
        String sql = "SELECT * FROM inmuebles_inmobiliaria WHERE codigo_inmueble = ?";
        InmueblesInmobiliaria inmueble = null;
        try {
            connection = conexion.establecerConexion();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                inmueble = new InmueblesInmobiliaria();
                inmueble.setCodigo(rs.getInt("codigo_inmueble"));
                inmueble.setFechaAdquisicion(rs.getDate("fecha_adquisicion").toLocalDate());
                inmueble.setCosto(rs.getDouble("costo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar inmueble: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return inmueble;
    }

    public List<InmueblesInmobiliaria> listarInmuebles() {
        String sql = "SELECT * FROM inmuebles_inmobiliaria";
        List<InmueblesInmobiliaria> lista = new ArrayList<>();
        try {
            connection = conexion.establecerConexion();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                InmueblesInmobiliaria inmueble = new InmueblesInmobiliaria();
                inmueble.setCodigo(rs.getInt("codigo_inmueble"));
                inmueble.setFechaAdquisicion(rs.getDate("fecha_adquisicion").toLocalDate());
                inmueble.setCosto(rs.getDouble("costo"));
                lista.add(inmueble);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar inmuebles: " + e.getMessage());
        } finally {
            cerrarRecursos();
        }
        return lista;
    }

    private void cerrarRecursos() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}