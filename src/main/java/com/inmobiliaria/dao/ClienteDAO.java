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
    PreparedStatement sentencia;
    ResultSet resultado;

    public ClienteDAO() {
        
        connection = conexion.establecerConexion();
    }

    public boolean RegistrarCliente(Cliente cliente) {
        
        String sqlCliente = "INSERT INTO cliente (cedula, nombres, apellidos, direccion, correo, fecha_nacimiento, fecha_expedicion_doc)"
                + " VALUES (?,?,?,?,?,?,?)";
        String sqlTel = "INSERT INTO telefonos_cliente (cedula_cliente, telefono) VALUES (?, ?)";
        try {
            sentencia = connection.prepareStatement(sqlCliente);
            sentencia.setString(1, cliente.getCedula());
            sentencia.setString(2, cliente.getNombres());
            sentencia.setString(3, cliente.getApellidos());
            sentencia.setString(4, cliente.getDireccion());
            sentencia.setString(5, cliente.getCorreo());
            sentencia.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
            sentencia.setDate(7, Date.valueOf(cliente.getFechaExpDoc()));
            sentencia.executeUpdate();
            
            //insertar telfonos
            PreparedStatement sentenciaTel = connection.prepareStatement(sqlTel);
            for (String telefono : cliente.getTelefonos()) {
                sentenciaTel.setString(1, cliente.getCedula());
                sentenciaTel.setString(2, telefono);
                sentenciaTel.executeUpdate();
               
            }
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
        
        String sqlUpdateCliente = "UPDATE cliente SET nombres=?, apellidos=?, direccion=?, correo=?, fecha_nacimiento=?, fecha_expedicion_doc=? WHERE cedula=?";
        String sqlDeleteTel = "DELETE FROM telefonos_cliente WHERE cedula_cliente=?";
        String sqlInsertTel = "INSERT INTO telefonos_cliente (cedula_cliente, telefono) VALUES (?, ?)";
        
        try {
            sentencia = connection.prepareStatement(sqlUpdateCliente);
            sentencia.setString(1, cliente.getNombres());
            sentencia.setString(2, cliente.getApellidos());
            sentencia.setString(3, cliente.getDireccion());
            sentencia.setString(4, cliente.getCorreo());
            sentencia.setDate(5, Date.valueOf(cliente.getFechaNacimiento()));
            sentencia.setDate(6, Date.valueOf(cliente.getFechaExpDoc()));
            sentencia.setString(7, cliente.getCedula());
            sentencia.executeUpdate();
            
            PreparedStatement sentenciaDeleteTel = connection.prepareStatement(sqlDeleteTel);
            sentenciaDeleteTel.setString(1, cliente.getCedula());
            sentenciaDeleteTel.executeUpdate();
            
            PreparedStatement sentenciaInsertTel = connection.prepareStatement(sqlInsertTel);
            for (String telefono : cliente.getTelefonos()) {
                if (telefono != null && !telefono.isBlank()) {
                    sentenciaInsertTel.setString(1, cliente.getCedula());
                    sentenciaInsertTel.setString(2, telefono);
                    sentenciaInsertTel.addBatch();
                }
            }
            sentenciaInsertTel.executeBatch();
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
        
        String sqlCliente = "DELETE FROM cliente WHERE cedula = ?";
        String sqlTel = "DELETE FROM telefonos_cliente WHERE cedula_cliente=?";
        try {
            PreparedStatement sentenciaTel = connection.prepareStatement(sqlTel);
            sentenciaTel.setString(1, cedula);
            sentenciaTel.executeUpdate();
            
            sentencia = connection.prepareStatement(sqlCliente);
            sentencia.setString(1, cedula);
            sentencia.executeUpdate();
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
        String sqlTel = "SELECT telefono FROM telefonos_cliente WHERE cedula_cliente=?";
        try {
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, cedula);
            resultado = sentencia.executeQuery();
            
            if (resultado.next()){
                cliente = new Cliente();
                cliente.setCedula(resultado.getString("cedula"));
                cliente.setNombres(resultado.getString("nombres"));
                cliente.setApellidos(resultado.getString("apellidos"));
                cliente.setDireccion(resultado.getString("direccion"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setFechaNacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());
                cliente.setFechaExpDoc(resultado.getDate("fecha_expedicion_doc").toLocalDate());
                
                PreparedStatement sentenciaTel = connection.prepareStatement(sqlTel);
                sentenciaTel.setString(1, cedula);
                ResultSet resultadoTel = sentenciaTel.executeQuery();
                
                List<String> telefonos = new ArrayList<>();
                while (resultadoTel.next()) {
                    telefonos.add(resultadoTel.getString("telefono"));
            }
                cliente.setTelefonos(telefonos);
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
        String sql = "SELECT * FROM cliente";
        String sqlTel = "SELECT telefono FROM telefonos_cliente WHERE cedula_cliente=?";
        try {
            sentencia = connection.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()){
                Cliente cliente = new Cliente();
                String cedula = resultado.getString("cedula");
                
                cliente.setCedula(cedula);
                cliente.setNombres(resultado.getString("nombres"));
                cliente.setApellidos(resultado.getString("apellidos"));
                cliente.setDireccion(resultado.getString("direccion"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setFechaNacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());
                cliente.setFechaExpDoc(resultado.getDate("fecha_expedicion_doc").toLocalDate());
                
                List<String> telefonos = new ArrayList<>();
                PreparedStatement sentenciaTel = connection.prepareStatement(sqlTel);
                
                sentenciaTel.setString(1, cedula);
                ResultSet resultadoTel = sentenciaTel.executeQuery();
                    
               while (resultadoTel.next()) {
                   telefonos.add(resultadoTel.getString("telefono"));
                }
                cliente.setTelefonos(telefonos);
                listaCliente.add(cliente);
            }
        }catch (SQLException e){
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error al listar Clientes. " + e.toString());

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
