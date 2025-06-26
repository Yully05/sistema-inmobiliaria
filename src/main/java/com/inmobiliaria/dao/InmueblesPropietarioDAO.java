package com.inmobiliaria.dao;

import com.inmobiliaria.model.InmueblesPropietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InmueblesPropietarioDAO {
    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement sentenciaInmueble;
    ResultSet resultado;

    public InmueblesPropietarioDAO() {
        connection = conexion.establecerConexion();
    }

    public boolean registrarInmueble(InmueblesPropietario inmueble) {
        String sql = "INSERT INTO inmuebles_propietario (codigo, descripcion, precio, direccion, ciudad, departamento, tamano, cant_banos, tipoInmueble, estadoInmueble, modalidadComercializacion, cedulaPropietario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            sentenciaInmueble = connection.prepareStatement(sql);
            sentenciaInmueble.setInt(1, inmueble.getCodigo());
            sentenciaInmueble.setString(2, inmueble.getDescripcion());
            sentenciaInmueble.setDouble(3, inmueble.getPrecio());
            sentenciaInmueble.setString(4, inmueble.getDireccion());
            sentenciaInmueble.setString(5, inmueble.getCiudad());
            sentenciaInmueble.setString(6, inmueble.getDepartamento());
            sentenciaInmueble.setDouble(7, inmueble.getTamano());
            sentenciaInmueble.setInt(8, inmueble.getCant_banos());
            sentenciaInmueble.setInt(9, inmueble.getTipoInmueble());
            sentenciaInmueble.setInt(10, inmueble.getEstadoInmueble());
            sentenciaInmueble.setInt(11, inmueble.getModalidadComercializacion());
            sentenciaInmueble.setString(12, inmueble.getCedulaPropietario());
            return sentenciaInmueble.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar inmueble: " + e.getMessage());
            return false;
        } finally {
            try {
                if (sentenciaInmueble != null) sentenciaInmueble.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public boolean actualizarInmueble(InmueblesPropietario inmueble) {
        String sql = "UPDATE inmuebles_propietario SET descripcion = ?, precio = ?, direccion = ?, ciudad = ?, departamento = ?, tamano = ?, cant_banos = ?, tipoInmueble = ?, estadoInmueble = ?, modalidadComercializacion = ?, cedulaPropietario = ? WHERE codigo = ?";
        try {
            sentenciaInmueble = connection.prepareStatement(sql);
            sentenciaInmueble.setString(1, inmueble.getDescripcion());
            sentenciaInmueble.setDouble(2, inmueble.getPrecio());
            sentenciaInmueble.setString(3, inmueble.getDireccion());
            sentenciaInmueble.setString(4, inmueble.getCiudad());
            sentenciaInmueble.setString(5, inmueble.getDepartamento());
            sentenciaInmueble.setDouble(6, inmueble.getTamano());
            sentenciaInmueble.setInt(7, inmueble.getCant_banos());
            sentenciaInmueble.setInt(8, inmueble.getTipoInmueble());
            sentenciaInmueble.setInt(9, inmueble.getEstadoInmueble());
            sentenciaInmueble.setInt(10, inmueble.getModalidadComercializacion());
            sentenciaInmueble.setString(11, inmueble.getCedulaPropietario());
            sentenciaInmueble.setInt(12, inmueble.getCodigo());
            return sentenciaInmueble.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar inmueble: " + e.getMessage());
            return false;
        } finally {
            try {
                if (sentenciaInmueble != null) sentenciaInmueble.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public boolean eliminarInmueble(int codigo) {
        String sql = "DELETE FROM inmuebles_propietario WHERE codigo = ?";
        try {
            sentenciaInmueble = connection.prepareStatement(sql);
            sentenciaInmueble.setInt(1, codigo);
            return sentenciaInmueble.executeUpdate() > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar inmueble: " + e.getMessage());
            return false;
        } finally {
            try {
                if (sentenciaInmueble != null) sentenciaInmueble.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }

    public InmueblesPropietario consultarInmueble(int codigo) {
        InmueblesPropietario inmueble = null;
        String sql = "SELECT * FROM inmuebles_propietario WHERE codigo = ?";
        try {
            sentenciaInmueble = connection.prepareStatement(sql);
            sentenciaInmueble.setInt(1, codigo);
            ResultSet resultado = sentenciaInmueble.executeQuery();
            if (resultado.next()) {
                inmueble = new InmueblesPropietario();
                inmueble.setCodigo(resultado.getInt("codigo"));
                inmueble.setDescripcion(resultado.getString("descripcion"));
                inmueble.setPrecio(resultado.getDouble("precio"));
                inmueble.setDireccion(resultado.getString("direccion"));
                inmueble.setCiudad(resultado.getString("ciudad"));
                inmueble.setDepartamento(resultado.getString("departamento"));
                inmueble.setTamano(resultado.getDouble("tamano"));
                inmueble.setCant_banos(resultado.getInt("cant_banos"));
                inmueble.setTipoInmueble(resultado.getInt("tipoInmueble"));
                inmueble.setEstadoInmueble(resultado.getInt("estadoInmueble"));
                inmueble.setModalidadComercializacion(resultado.getInt("modalidadComercializacion"));
                inmueble.setCedulaPropietario(resultado.getString("cedulaPropietario"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar inmueble: " + e.getMessage());
        } finally {
            try {
                if (sentenciaInmueble != null) sentenciaInmueble.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return inmueble;
    }

    public List<InmueblesPropietario> listarInmuebles() {
        List<InmueblesPropietario> listaInmuebles = new ArrayList<>();
        String sql = "SELECT * FROM inmuebles_propietario";
        try {
            sentenciaInmueble = connection.prepareStatement(sql);
            ResultSet resultado = sentenciaInmueble.executeQuery();
            while (resultado.next()) {
                InmueblesPropietario inmueble = new InmueblesPropietario();
                inmueble.setCodigo(resultado.getInt("codigo"));
                inmueble.setDescripcion(resultado.getString("descripcion"));
                inmueble.setPrecio(resultado.getDouble("precio"));
                inmueble.setDireccion(resultado.getString("direccion"));
                inmueble.setCiudad(resultado.getString("ciudad"));
                inmueble.setDepartamento(resultado.getString("departamento"));
                inmueble.setTamano(resultado.getDouble("tamano"));
                inmueble.setCant_banos(resultado.getInt("cant_banos"));
                inmueble.setTipoInmueble(resultado.getInt("tipoInmueble"));
                inmueble.setEstadoInmueble(resultado.getInt("estadoInmueble"));
                inmueble.setModalidadComercializacion(resultado.getInt("modalidadComercializacion"));
                inmueble.setCedulaPropietario(resultado.getString("cedulaPropietario"));
                listaInmuebles.add(inmueble);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar inmuebles: " + e.getMessage());
        } finally {
            try {
                if (sentenciaInmueble != null) sentenciaInmueble.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        return listaInmuebles;
    }
}
