/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *
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
        
        String sql = "INSERT INTO propietario (cedula, nombres, apellidos, direccion, correo, fecha_nacimiento, fecha_expedicion_doc)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, propietario.getCedula());
            ps.setString(2, propietario.getNombres());
            ps.setString(3, propietario.getApellidos());
            ps.setString(4, propietario.getDireccion());
            ps.setString(5, propietario.getCorreo());
            ps.setDate(6, Date.valueOf(propietario.getFechaNacimiento()));
            ps.setDate(7, Date.valueOf(propietario.getFechaExpDoc()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar Propietario" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
        public boolean ActualizarPropietario(Propietario propietario) {
        
        String sql = "UPDATE cliente SET nombres=?, apellidos=?, direccion=?, correo=?, fecha_nacimiento=?, fecha_expedicion_doc=? WHERE cedula=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, propietario.getNombres());
            ps.setString(2, propietario.getApellidos());
            ps.setString(3, propietario.getDireccion());
            ps.setString(4, propietario.getCorreo());
            ps.setDate(5, Date.valueOf(propietario.getFechaNacimiento()));
            ps.setDate(6, Date.valueOf(propietario.getFechaExpDoc()));
            ps.setString(7, propietario.getCedula());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al modificar Propietario" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public boolean EliminarPropietario(String cedula) {
        
        String sql = "DELETE FROM propietario WHERE cedula = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar Propietario" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
        
    public Propietario ConsultarPropietario(String cedula) throws SQLException{
            
        Propietario propietario = null;
        String sql = "SELECT * FROM propietario WHERE cedula = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();

            if (rs.next()){
                propietario = new Propietario();
                propietario.setCedula(rs.getString("cedula"));
                propietario.setNombres(rs.getString("nombres"));
                propietario.setApellidos(rs.getString("apellidos"));
                propietario.setDireccion(rs.getString("direccion"));
                propietario.setCorreo(rs.getString("correo"));
                propietario.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                propietario.setFechaExpDoc(rs.getDate("fecha_expedicion_doc").toLocalDate());
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la busqueda de Propietario" + e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return propietario;
    }
    
    
    public List listarPropietario() throws SQLException {
        
        List<Propietario> listaPropietario = new ArrayList<>();
        String sql = "SELECT * FROM agente_comercial";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Propietario propietario = new Propietario();
                propietario.setCedula(rs.getString("cedula"));
                propietario.setNombres(rs.getString("nombres"));
                propietario.setApellidos(rs.getString("apellidos"));
                propietario.setDireccion(rs.getString("direccion"));
                propietario.setCorreo(rs.getString("correo"));
                propietario.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                propietario.setFechaExpDoc(rs.getDate("fecha_expedicion_doc").toLocalDate());
                listaPropietario.add(propietario);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar Propietarios" + e.toString());

        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaPropietario; 
    }
    
       
}
