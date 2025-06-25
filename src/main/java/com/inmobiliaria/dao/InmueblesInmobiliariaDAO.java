package com.inmobiliaria.dao;

import com.inmobiliaria.model.InmueblesInmobiliaria;

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
public class InmueblesInmobiliariaDAO {

    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public InmueblesInmobiliariaDAO() {
        connection = conexion.establecerConexion();
    }

    public boolean RegistrarInmuebleInmobiliaria(InmueblesInmobiliaria inmuebleInmobiliaria) {

        String sql = "INSERT INTO inmuebles_inmobiliaria (codigo, descripcion, precio, direccion, ciudad, departamento, tamaño, cant_baños, fecha_adquisicion, costo, tipo, modalidad, estado)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, inmuebleInmobiliaria.getCodigo());
            ps.setString(2, inmuebleInmobiliaria.getDescripcion());
            ps.setDouble(3, inmuebleInmobiliaria.getPrecio());
            ps.setString(4, inmuebleInmobiliaria.getDireccion());
            ps.setString(5, inmuebleInmobiliaria.getCiudad());
            ps.setString(6, inmuebleInmobiliaria.getDepartamento());
            ps.setDouble(7, inmuebleInmobiliaria.getTamaño());
            ps.setInt(8, inmuebleInmobiliaria.getCant_baños());
            ps.setDate(9, Date.valueOf(inmuebleInmobiliaria.getFechaAdquisicion()));
            ps.setDouble(10, inmuebleInmobiliaria.getCosto());
            ps.setInt(11, inmuebleInmobiliaria.getTipoInmueble());
            ps.setInt(12, inmuebleInmobiliaria.getModalidadComercializacion());
            ps.setInt(13, inmuebleInmobiliaria.getEstadoInmueble());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar Inmueble de la Inmobiliaria: " + e.toString());
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

    public boolean ActualizarInmuebleInmobiliaria(InmueblesInmobiliaria inmuebleInmobiliaria) {

        String sql = "UPDATE inmuebles_inmobiliaria SET descripcion = ?, precio = ?, direccion = ?, ciudad = ?, departamento = ?, tamaño = ?, cant_baños = ?, fecha_adquisicion = ?, costo = ?, tipo = ?, modalidad = ?, estado = ? WHERE codigo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, inmuebleInmobiliaria.getDescripcion());
            ps.setDouble(2, inmuebleInmobiliaria.getPrecio());
            ps.setString(3, inmuebleInmobiliaria.getDireccion());
            ps.setString(4, inmuebleInmobiliaria.getCiudad());
            ps.setString(5, inmuebleInmobiliaria.getDepartamento());
            ps.setDouble(6, inmuebleInmobiliaria.getTamaño());
            ps.setInt(7, inmuebleInmobiliaria.getCant_baños());
            ps.setDate(8, Date.valueOf(inmuebleInmobiliaria.getFechaAdquisicion()));
            ps.setDouble(9, inmuebleInmobiliaria.getCosto());
            ps.setInt(10, inmuebleInmobiliaria.getTipoInmueble());
            ps.setInt(11, inmuebleInmobiliaria.getModalidadComercializacion());
            ps.setInt(12, inmuebleInmobiliaria.getEstadoInmueble());
            ps.setInt(13, inmuebleInmobiliaria.getCodigo());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Inmueble de la Inmobiliaria: " + e.toString());
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

    public boolean EliminarInmuebleInmobiliaria(int codigo) {
        String sql = "DELETE FROM inmuebles_inmobiliaria WHERE codigo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, codigo);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Inmueble de la Inmobiliaria: " + e.toString());
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

    public InmueblesInmobiliaria ConsultarInmuebleInmobiliaria(int codigo) {
        InmueblesInmobiliaria inmuebleInmobiliaria = null;
        String sql = "SELECT * FROM inmuebles_inmobiliaria WHERE codigo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                inmuebleInmobiliaria = new InmueblesInmobiliaria();
                inmuebleInmobiliaria.setCodigo(rs.getInt("codigo"));
                inmuebleInmobiliaria.setDescripcion(rs.getString("descripcion"));
                inmuebleInmobiliaria.setPrecio(rs.getDouble("precio"));
                inmuebleInmobiliaria.setDireccion(rs.getString("direccion"));
                inmuebleInmobiliaria.setCiudad(rs.getString("ciudad"));
                inmuebleInmobiliaria.setDepartamento(rs.getString("departamento"));
                inmuebleInmobiliaria.setTamaño(rs.getDouble("tamaño"));
                inmuebleInmobiliaria.setCant_baños(rs.getInt("cant_baños"));
                inmuebleInmobiliaria.setFechaAdquisicion(rs.getDate("fecha_adquisicion").toLocalDate());
                inmuebleInmobiliaria.setCosto(rs.getDouble("costo"));
                inmuebleInmobiliaria.setTipoInmueble(rs.getInt("tipo"));
                inmuebleInmobiliaria.setModalidadComercializacion(rs.getInt("modalidad"));
                inmuebleInmobiliaria.setEstadoInmueble(rs.getInt("estado"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar Inmueble de la Inmobiliaria: " + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return inmuebleInmobiliaria;
    }

    public List<InmueblesInmobiliaria> listarInmueblesInmobiliaria() {
        List<InmueblesInmobiliaria> listaInmuebleInmobiliaria = new ArrayList<>();
        String sql = "SELECT * FROM inmuebles_inmobiliaria";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                InmueblesInmobiliaria inmuebleInmobiliaria = new InmueblesInmobiliaria();
                inmuebleInmobiliaria.setCodigo(rs.getInt("codigo"));
                inmuebleInmobiliaria.setDescripcion(rs.getString("descripcion"));
                inmuebleInmobiliaria.setPrecio(rs.getDouble("precio"));
                inmuebleInmobiliaria.setDireccion(rs.getString("direccion"));
                inmuebleInmobiliaria.setCiudad(rs.getString("ciudad"));
                inmuebleInmobiliaria.setDepartamento(rs.getString("departamento"));
                inmuebleInmobiliaria.setTamaño(rs.getDouble("tamaño"));
                inmuebleInmobiliaria.setCant_baños(rs.getInt("cant_baños"));
                inmuebleInmobiliaria.setFechaAdquisicion(rs.getDate("fecha_adquisicion").toLocalDate());
                inmuebleInmobiliaria.setCosto(rs.getDouble("costo"));
                inmuebleInmobiliaria.setTipoInmueble(rs.getInt("tipo"));
                inmuebleInmobiliaria.setModalidadComercializacion(rs.getInt("modalidad"));
                inmuebleInmobiliaria.setEstadoInmueble(rs.getInt("estado"));
                listaInmuebleInmobiliaria.add(inmuebleInmobiliaria);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar Inmuebles" + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaInmuebleInmobiliaria;
    }
}