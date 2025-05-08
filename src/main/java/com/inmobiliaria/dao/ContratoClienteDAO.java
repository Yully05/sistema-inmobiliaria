/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.dao;

import com.inmobiliaria.model.ContratoCliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class ContratoClienteDAO {
    
    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public ContratoClienteDAO() {
        
        connection = conexion.establecerConexion();
        
    }
    
    
    public boolean RegistrarContratoCliente(ContratoCliente contratoCl) {
        
        String sql = "INSERT INTO contrato_cliente (codigo, descripcion, modalidad, fecha_creacion, fecha_expiracion, valor, nombre_fiador, celular_fiador, codigo_inmueble, cedula_cliente, cedula_agente)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, contratoCl.getCodigo());
            ps.setString(2, contratoCl.getDescripcion());
            ps.setInt(3, contratoCl.getModalidadComercializacion());
            ps.setDate(4, Date.valueOf(contratoCl.getFechaCreacion()));
            ps.setDate(5, Date.valueOf(contratoCl.getFechaExpiracion()));
            ps.setDouble(6, contratoCl.getValor());
            ps.setString(7, contratoCl.getNombreFiador());
            ps.setString(8, contratoCl.getCelularFiador());
            ps.setInt(9, contratoCl.getCodigoInmueble());
            ps.setString(10, contratoCl.getCedulaCliente());
            ps.setString(11, contratoCl.getCedulaAgente());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar Contrato con Cliente" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
        public boolean ActualizarContratoCliente(ContratoCliente contratoCl) {
        
        String sql = "UPDATE contrato_cliente SET descripcion=?, modalidad=?, fecha_creacion=?, fecha_expiracion=?, valor=?, nombre_fiador=?, celular_fiador=?, codigo_inmueble=?, cedula_cliente=?, cedula_agente=? WHERE codigo=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, contratoCl.getDescripcion());
            ps.setInt(2, contratoCl.getModalidadComercializacion());
            ps.setDate(3, Date.valueOf(contratoCl.getFechaCreacion()));
            ps.setDate(4, Date.valueOf(contratoCl.getFechaExpiracion()));
            ps.setDouble(5, contratoCl.getValor());
            ps.setString(6, contratoCl.getNombreFiador());
            ps.setString(7, contratoCl.getCelularFiador());
            ps.setInt(8, contratoCl.getCodigoInmueble());
            ps.setString(9, contratoCl.getCedulaCliente());
            ps.setString(10, contratoCl.getCedulaAgente());
            ps.setInt(11, contratoCl.getCodigo());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al modificar Contrato con cliente" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public boolean EliminarContratoCliente(String codigo) {
        
        String sql = "DELETE FROM contrato_cliente WHERE codigo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar Contrato" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
        
    public ContratoCliente ConsultarContratoCliente(String codigo) throws SQLException{
            
        ContratoCliente contratoCl = null;
        String sql = "SELECT * FROM contrato_cliente WHERE codigo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            if (rs.next()){
                contratoCl = new ContratoCliente();
                
                contratoCl.setCodigo(rs.getInt("codigo"));
                contratoCl.setDescripcion(rs.getString("descripcion"));
                contratoCl.setModalidadComercializacion(rs.getInt("modalidad"));
                contratoCl.setFechaCreacion(rs.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setValor(rs.getDouble("valor"));
                contratoCl.setNombreFiador(rs.getString("nombre_fiador"));
                contratoCl.setCelularFiador(rs.getString("celular_fiador"));
                contratoCl.setCodigoInmueble(rs.getInt("codigo_inmueble"));
                contratoCl.setCedulaCliente(rs.getString("cedula_cliente"));
                contratoCl.setCedulaAgente(rs.getString("cedula_agente"));
                
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la busqueda de Contrato" + e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return contratoCl;
    }
    
    
    public List listarContratosCl() throws SQLException {
        
        List<ContratoCliente> listaContratos = new ArrayList<>();
        String sql = "SELECT * FROM contrato_cliente";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                ContratoCliente contratoCl = new ContratoCliente();
                contratoCl.setCodigo(rs.getInt("codigo"));
                contratoCl.setDescripcion(rs.getString("descripcion"));
                contratoCl.setModalidadComercializacion(rs.getInt("modalidad"));
                contratoCl.setFechaCreacion(rs.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setValor(rs.getDouble("valor"));
                contratoCl.setNombreFiador(rs.getString("nombre_fiador"));
                contratoCl.setCelularFiador(rs.getString("celular_fiador"));
                contratoCl.setCodigoInmueble(rs.getInt("codigo_inmueble"));
                contratoCl.setCedulaCliente(rs.getString("cedula_cliente"));
                contratoCl.setCedulaAgente(rs.getString("cedula_agente"));
                listaContratos.add(contratoCl);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar Contratos" + e.toString());

        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaContratos; 
    }
    
    
    
    
}
