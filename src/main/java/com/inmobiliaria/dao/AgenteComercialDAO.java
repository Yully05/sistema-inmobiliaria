package com.inmobiliaria.dao;

import com.inmobiliaria.model.AgenteComercial;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class AgenteComercialDAO {

    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement sentencia;
    ResultSet resultado;

    public AgenteComercialDAO() {
        this.connection = conexion.establecerConexion();
    }

    public boolean RegistrarAgente(AgenteComercial agenteModel) {

        String sql = "INSERT INTO agente_comercial (cedula, login, contrasena, nombres, apellidos, direccion, fecha_nacimiento,"
                + " fecha_expedicion_doc, correo, celular, rol) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, agenteModel.getCedula());
            sentencia.setString(2, agenteModel.getLogin());
            sentencia.setString(3, agenteModel.getContrasena());
            sentencia.setString(4, agenteModel.getNombres());
            sentencia.setString(5, agenteModel.getApellidos()); // Mantener 'apellidos'
            sentencia.setString(6, agenteModel.getDireccion());
            sentencia.setDate(7, Date.valueOf(agenteModel.getFechaNacimiento()));
            sentencia.setDate(8, Date.valueOf(agenteModel.getFechaExpDoc()));
            sentencia.setString(9, agenteModel.getCorreo());
            sentencia.setString(10, agenteModel.getCelular());
            sentencia.setString(11, agenteModel.getRol());

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar Agente Comercial: " + e.toString());
            return false;
        } finally {
            try {
                if (sentencia != null) sentencia.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean ActualizarAgente(AgenteComercial agenteModel) {

        String sql = "UPDATE agente_comercial SET login = ?, contrasena = ?, nombres = ?, apellidos = ?, direccion = ?, fecha_nacimiento = ?,"
                + " fecha_expedicion_doc = ?, correo = ?, celular = ?, rol = ? WHERE cedula = ?";
        try {
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, agenteModel.getLogin());
            sentencia.setString(2, agenteModel.getContrasena());
            sentencia.setString(3, agenteModel.getNombres());
            sentencia.setString(4, agenteModel.getApellidos()); // Mantener 'apellidos'
            sentencia.setString(5, agenteModel.getDireccion());
            sentencia.setDate(6, Date.valueOf(agenteModel.getFechaNacimiento()));
            sentencia.setDate(7, Date.valueOf(agenteModel.getFechaExpDoc())); // Corregido
            sentencia.setString(8, agenteModel.getCorreo());
            sentencia.setString(9, agenteModel.getCelular());
            sentencia.setString(10, agenteModel.getRol());
            sentencia.setString(11, agenteModel.getCedula());

            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar Agente Comercial: " + e.toString());
            return false;
        } finally {
            try {
                if (sentencia != null) sentencia.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean EliminarAgente(String cedula) {
        String sql = "DELETE FROM agente_comercial WHERE cedula = ?";
        try {
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, cedula);
            int filasAfectadas = sentencia.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Agente Comercial: " + e.toString());
            return false;
        } finally {
            try {
                if (sentencia != null) sentencia.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public AgenteComercial ConsultarAgente(String cedula) {
        AgenteComercial agenteModelo = null;
        String sql = "SELECT * FROM agente_comercial WHERE cedula = ?";
        try {
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, cedula);
            resultado = sentencia.executeQuery();
            if (resultado.next()) {
                agenteModelo = new AgenteComercial();
                agenteModelo.setCedula(resultado.getString("cedula"));
                agenteModelo.setLogin(resultado.getString("login"));
                agenteModelo.setContrasena(resultado.getString("contrasena"));
                agenteModelo.setNombres(resultado.getString("nombres"));
                agenteModelo.setApellidos(resultado.getString("apellidos")); // Mantener 'apellidos'
                agenteModelo.setDireccion(resultado.getString("direccion"));
                agenteModelo.setCorreo(resultado.getString("correo"));
                agenteModelo.setCelular(resultado.getString("celular"));
                agenteModelo.setFechaNacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());
                agenteModelo.setFechaExpDoc(resultado.getDate("fecha_expedicion_doc").toLocalDate());
                agenteModelo.setRol(resultado.getString("rol"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar Agente Comercial: " + e.toString());
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (sentencia != null) sentencia.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return agenteModelo;
    }

    public List<AgenteComercial> listarAgente() {
        List<AgenteComercial> listaAgente = new ArrayList<>();
        String sql = "SELECT * FROM agente_comercial";
        try {
            sentencia = connection.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                AgenteComercial agenteModelo = new AgenteComercial();

                agenteModelo.setCedula(resultado.getString("cedula"));
                agenteModelo.setLogin(resultado.getString("login"));
                agenteModelo.setContrasena(resultado.getString("contrasena"));
                agenteModelo.setNombres(resultado.getString("nombres"));
                agenteModelo.setApellidos(resultado.getString("apellidos")); // Mantener 'apellidos'
                agenteModelo.setDireccion(resultado.getString("direccion"));
                agenteModelo.setCorreo(resultado.getString("correo"));
                agenteModelo.setCelular(resultado.getString("celular"));
                agenteModelo.setFechaNacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());
                agenteModelo.setFechaExpDoc(resultado.getDate("fecha_expedicion_doc").toLocalDate());
                agenteModelo.setRol(resultado.getString("rol"));

                listaAgente.add(agenteModelo);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en dao al listar Agentes Comerciales" + e.toString());

        } finally {
            try {
                if (resultado != null) resultado.close();
                if (sentencia != null) sentencia.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaAgente;
    }
}