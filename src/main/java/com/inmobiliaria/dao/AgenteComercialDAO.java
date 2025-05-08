/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    PreparedStatement ps;
    ResultSet rs;

    public AgenteComercialDAO() {
        
        connection = conexion.establecerConexion();
    }
    
    public boolean RegistrarAgente(AgenteComercial agente) {
        
        String sql = "INSERT INTO agente_comercial (cedula, login, contraseña, nombres, apellidos, direccion, fecha_nacimiento, fecha_expedicion_doc, correo, celular)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, agente.getCedula());
            ps.setString(2, agente.getLogin());
            ps.setString(3, agente.getContraseña());
            ps.setString(4, agente.getNombres());
            ps.setString(5, agente.getApellidos());
            ps.setString(6, agente.getDireccion());
            ps.setDate(7, Date.valueOf(agente.getFechaNacimiento()));
            ps.setDate(8, Date.valueOf(agente.getFechaExpDoc()));
            ps.setString(9, agente.getCorreo());
            ps.setString(10, agente.getCelular());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar Agente Comercial" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public boolean ActualizarAgente(AgenteComercial agente) {
        
        String sql = "UPDATE agente_comercial SET contraseña=?, nombres=?, apellidos=?, direccion=?, fecha_nacimiento=?, fecha_expedicion_doc=?, correo=?, celular=? WHERE cedula=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, agente.getContraseña());
            ps.setString(2, agente.getNombres());
            ps.setString(3, agente.getApellidos());
            ps.setString(4, agente.getDireccion());
            ps.setDate(5, Date.valueOf(agente.getFechaNacimiento()));
            ps.setDate(6, Date.valueOf(agente.getFechaExpDoc()));
            ps.setString(7, agente.getCorreo());
            ps.setString(8, agente.getCelular());
            ps.setString(9, agente.getCedula());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar de Agente Comercial" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public boolean EliminarAgente(String cedula) {
        
        String sql = "DELETE FROM agente_comercial WHERE cedula = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar Agente Comercial" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public AgenteComercial ConsultarAgente(String cedula) throws SQLException{
        
        AgenteComercial agente = null;
        String sql = "SELECT * FROM agente_comercial WHERE cedula = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            
            if (rs.next()){
                agente = new AgenteComercial();
                agente.setCedula(rs.getString("cedula"));
                agente.setLogin(rs.getString("login"));
                agente.setContraseña(rs.getString("contraseña"));
                agente.setNombres(rs.getString("nombres"));
                agente.setApellidos(rs.getString("apellidos"));
                agente.setDireccion(rs.getString("direccion"));
                agente.setCorreo(rs.getString("correo"));
                agente.setCelular(rs.getString("celular"));
                agente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                agente.setFechaExpDoc(rs.getDate("fecha_expedicion_doc").toLocalDate());
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la busqueda de Agente Comercial" + e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return agente;
    }
    
    
    public List listarAgente() throws SQLException {
        
        List<AgenteComercial> listaAgente = new ArrayList<>();
        String sql = "SELECT * FROM agente_comercial";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                AgenteComercial agente = new AgenteComercial();
                agente.setCedula(rs.getString("cedula"));
                agente.setLogin(rs.getString("login"));
                agente.setContraseña(rs.getString("contraseña"));
                agente.setNombres(rs.getString("nombres"));
                agente.setApellidos(rs.getString("apellidos"));
                agente.setDireccion(rs.getString("direccion"));
                agente.setCorreo(rs.getString("correo"));
                agente.setCelular(rs.getString("celular"));
                agente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                agente.setFechaExpDoc(rs.getDate("fecha_expedicion_doc").toLocalDate());
                listaAgente.add(agente);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar Agentes Comerciales" + e.toString());

        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaAgente;
        
    }
}
    
    
    
    
