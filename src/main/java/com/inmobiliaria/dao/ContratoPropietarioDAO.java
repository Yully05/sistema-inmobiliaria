/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.dao;

import com.inmobiliaria.model.ContratoPropietario;
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
public class ContratoPropietarioDAO {
    
    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public ContratoPropietarioDAO() {
        connection = conexion.establecerConexion();
    }
    
    public boolean RegistrarContratoPropietario(ContratoPropietario contratoProp) {
        
        String sql = "INSERT INTO contrato_propietario (codigo, descripcion, modalidad, fecha_creacion, fecha_expiracion, valor, porcentaje_comision, codigo_inmueble, cedula_propietario, cedula_agente)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, contratoProp.getCodigo());
            ps.setString(2, contratoProp.getDescripcion());
            ps.setInt(3, contratoProp.getModalidadComercializacion());
            ps.setDate(4, Date.valueOf(contratoProp.getFechaCreacion()));
            ps.setDate(5, Date.valueOf(contratoProp.getFechaExpiracion()));
            ps.setDouble(6, contratoProp.getValor());
            ps.setDouble(7, contratoProp.getPorcentajeComision());
            ps.setInt(8, contratoProp.getCodigoInmueble());
            ps.setString(9, contratoProp.getCedulaPropietario());
            ps.setInt(11, contratoProp.getCedulaAgente());
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar Contrato con Propietario" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
        public boolean ActualizarContratoPropietario(ContratoPropietario contratoProp) {
        
        String sql = "UPDATE contrato_propietario SET descripcion=?, modalidad=?, fecha_creacion=?, fecha_expiracion=?, valor=?, porcentaje_comision=?, codigo_inmueble=?, cedula_propietario=?, cedula_agente=? WHERE codigo=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, contratoProp.getDescripcion());
            ps.setInt(2, contratoProp.getModalidadComercializacion());
            ps.setDate(3, Date.valueOf(contratoProp.getFechaCreacion()));
            ps.setDate(4, Date.valueOf(contratoProp.getFechaExpiracion()));
            ps.setDouble(5, contratoProp.getValor());
            ps.setDouble(7, contratoProp.getPorcentajeComision());
            ps.setInt(8, contratoProp.getCodigoInmueble());
            ps.setString(9, contratoProp.getCedulaPropietario());
            ps.setInt(10, contratoProp.getCedulaAgente());
            ps.setInt(11, contratoProp.getCodigo());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al modificar Contrato con propietario" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public boolean EliminarContratoPropietario(String codigo) {
        
        String sql = "DELETE FROM contrato_propietario WHERE codigo = ?";
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
        
    public ContratoPropietario ConsultarContratoPropietario(String codigo) throws SQLException{
            
        ContratoPropietario contratoProp = null;
        String sql = "SELECT * FROM contrato_propietario WHERE codigo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            if (rs.next()){
                contratoProp = new ContratoPropietario();
                
                contratoProp.setCodigo(rs.getInt("codigo"));
                contratoProp.setDescripcion(rs.getString("descripcion"));
                contratoProp.setModalidadComercializacion(rs.getInt("modalidad"));
                contratoProp.setFechaCreacion(rs.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setValor(rs.getDouble("valor"));
                contratoProp.setPorcentajeComision(rs.getDouble("porcentaje_comision"));
                contratoProp.setCodigoInmueble(rs.getInt("codigo_inmueble"));
                contratoProp.setCedulaPropietario(rs.getString("cedula_propietario"));
                contratoProp.setCedulaAgente(rs.getInt("cedula_agente"));
                
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
        return contratoProp;
    }
    
    
    public List listarContratosProp() throws SQLException {
        
        List<ContratoPropietario> listaContratos = new ArrayList<>();
        String sql = "SELECT * FROM contrato_cliente";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                ContratoPropietario contratoProp = new ContratoPropietario();
                contratoProp.setCodigo(rs.getInt("codigo"));
                contratoProp.setDescripcion(rs.getString("descripcion"));
                contratoProp.setModalidadComercializacion(rs.getInt("modalidad"));
                contratoProp.setFechaCreacion(rs.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setFechaExpiracion(rs.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setValor(rs.getDouble("valor"));
                contratoProp.setPorcentajeComision(rs.getDouble("porcentaje_comision"));
                contratoProp.setCodigoInmueble(rs.getInt("codigo_inmueble"));
                contratoProp.setCedulaPropietario(rs.getString("cedula_propietario"));
                contratoProp.setCedulaAgente(rs.getInt("cedula_agente"));
                listaContratos.add(contratoProp);
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
