/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.dao;

import com.inmobiliaria.model.Cliente;
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
public class ClienteDAO {
    
    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public ClienteDAO() {
        
        connection = conexion.establecerConexion();
    }

    public boolean RegistrarCliente(Cliente cliente) {
        
        String sql = "INSERT INTO cliente (cedula, nombres, apellidos, direccion, correo, fecha_nacimiento, fecha_expedicion_doc)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getDireccion());
            ps.setString(5, cliente.getCorreo());
            ps.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
            ps.setDate(7, Date.valueOf(cliente.getFechaExpDoc()));
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar Cliente" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
        public boolean ActualizarCliente(Cliente cliente) {
        
        String sql = "UPDATE cliente SET nombres=?, apellidos=?, direccion=?, correo=?, fecha_nacimiento=?, fecha_expedicion_doc=? WHERE cedula=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getCorreo());
            ps.setDate(5, Date.valueOf(cliente.getFechaNacimiento()));
            ps.setDate(6, Date.valueOf(cliente.getFechaExpDoc()));
            ps.setString(7, cliente.getCedula());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al modificar Cliente" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public boolean EliminarCliente(String cedula) {
        
        String sql = "DELETE FROM cliente WHERE cedula = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cedula);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar Cliente" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public Cliente ConsultarCliente(String cedula) throws SQLException{
        
        Cliente cliente = null;
        String sql = "SELECT * FROM cliente WHERE cedula = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            
            if (rs.next()){
                cliente = new Cliente();
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                cliente.setFechaExpDoc(rs.getDate("fecha_expedicion_doc").toLocalDate());
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la busqueda de Cliente" + e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return cliente;
    }
    
    
    public List listarCliente() throws SQLException {
        
        List<Cliente> listaCliente = new ArrayList<>();
        String sql = "SELECT * FROM agente_comercial";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                cliente.setFechaExpDoc(rs.getDate("fecha_expedicion_doc").toLocalDate());
                listaCliente.add(cliente);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar Clientes" + e.toString());

        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaCliente; 
    }
    
}
