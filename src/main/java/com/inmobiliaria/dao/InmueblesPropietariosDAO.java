/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.dao;

import com.inmobiliaria.model.InmueblesPropietario;
import java.sql.Connection;
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
public class InmueblesPropietariosDAO {
    
    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public InmueblesPropietariosDAO() {
        
        connection = conexion.establecerConexion();
    }
    
    
    public boolean RegistrarInmueblePropietario(InmueblesPropietario inmuebleProp) {
        
        String sql = "INSERT INTO inmuebles_propietarios (codigo, descripcion, precio, direccion, ciudad, departamento, tamaño, cant_baños, cedula_propietario, tipo, modalidad, estado)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, inmuebleProp.getCodigo());
            ps.setString(2, inmuebleProp.getDescripcion());
            ps.setDouble(3, inmuebleProp.getPrecio());
            ps.setString(4, inmuebleProp.getDireccion());
            ps.setString(5, inmuebleProp.getCiudad());
            ps.setString(6, inmuebleProp.getDepartamento());
            ps.setDouble(7, inmuebleProp.getTamaño());
            ps.setInt(8, inmuebleProp.getCant_baños());
            ps.setInt(9, inmuebleProp.getCedulaPropietario());
            ps.setInt(10, inmuebleProp.getTipoInmueble());
            ps.setInt(11, inmuebleProp.getModalidadComercializacion());
            ps.setInt(12, inmuebleProp.getEstadoInmueble());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar Inmueble" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
        public boolean ActualizarInmueblePropietario(InmueblesPropietario inmuebleProp) {
        
        String sql = "UPDATE inmuebles_propietarios SET descripcion=?, precio=?, direccion=?, ciudad=?, departamento=?, tamaño=?, cant_baños=?, cedula_propietario=?, tipo=?, modalidad=?, estado=? WHERE codigo=?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, inmuebleProp.getDescripcion());
            ps.setDouble(2, inmuebleProp.getPrecio());
            ps.setString(3, inmuebleProp.getDireccion());
            ps.setString(4, inmuebleProp.getCiudad());
            ps.setString(5, inmuebleProp.getDepartamento());
            ps.setDouble(6, inmuebleProp.getTamaño());
            ps.setInt(7, inmuebleProp.getCant_baños());
            ps.setInt(8, inmuebleProp.getCedulaPropietario());
            ps.setInt(9, inmuebleProp.getTipoInmueble());
            ps.setInt(10, inmuebleProp.getModalidadComercializacion());
            ps.setInt(11, inmuebleProp.getEstadoInmueble());
            ps.setInt(12, inmuebleProp.getCodigo());
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al modificar Inmueble" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
    
    public boolean EliminarInmueblePropietario(String codigo) {
        
        String sql = "DELETE FROM inmuebles_propietarios WHERE codigo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, codigo);
            ps.executeUpdate();
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al eliminar Inmueble" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }
        
    public InmueblesPropietario ConsultarInmueblePropietario(String codigo) throws SQLException{
            
        InmueblesPropietario inmuebleProp = null;
        String sql = "SELECT * FROM inmuebles_propietarios WHERE codigo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            if (rs.next()){
                inmuebleProp = new InmueblesPropietario();
                inmuebleProp.setCodigo(rs.getInt("codigo"));
                inmuebleProp.setDescripcion(rs.getString("descripcion"));
                inmuebleProp.setPrecio(rs.getDouble("precio"));
                inmuebleProp.setDireccion(rs.getString("direccion"));
                inmuebleProp.setCiudad(rs.getString("ciudad"));
                inmuebleProp.setDepartamento(rs.getString("departamento"));
                inmuebleProp.setTamaño(rs.getDouble("tamaño"));
                inmuebleProp.setCant_baños(rs.getInt("cant_baños"));
                inmuebleProp.setCedulaPropietario(rs.getInt("cedula_propietario"));
                inmuebleProp.setTipoInmueble(rs.getInt("tipo"));
                inmuebleProp.setModalidadComercializacion(rs.getInt("modalidad"));
                inmuebleProp.setEstadoInmueble(rs.getInt("estado"));
                
            }

        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la busqueda de Inmueble" + e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return inmuebleProp;
    }
    
    
    public List listarInmueblesProp() throws SQLException {
        
        List<InmueblesPropietario> listaInmueblesProp = new ArrayList<>();
        String sql = "SELECT * FROM contrato_cliente";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                InmueblesPropietario inmuebleProp = new InmueblesPropietario();
                inmuebleProp.setCodigo(rs.getInt("codigo"));
                inmuebleProp.setDescripcion(rs.getString("descripcion"));
                inmuebleProp.setPrecio(rs.getDouble("precio"));
                inmuebleProp.setDireccion(rs.getString("direccion"));
                inmuebleProp.setCiudad(rs.getString("ciudad"));
                inmuebleProp.setDepartamento(rs.getString("departamento"));
                inmuebleProp.setTamaño(rs.getDouble("tamaño"));
                inmuebleProp.setCant_baños(rs.getInt("cant_baños"));
                inmuebleProp.setCedulaPropietario(rs.getInt("cedula_propietario"));
                inmuebleProp.setTipoInmueble(rs.getInt("tipo"));
                inmuebleProp.setModalidadComercializacion(rs.getInt("modalidad"));
                inmuebleProp.setEstadoInmueble(rs.getInt("estado"));
                listaInmueblesProp.add(inmuebleProp);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar Inmuebles" + e.toString());

        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaInmueblesProp; 
    }
    
}
