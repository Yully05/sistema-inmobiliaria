/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.dao;

import com.inmobiliaria.model.TipoInmueble;
import java.sql.Connection;
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
public class TipoInmuebleDAO {
    
    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public TipoInmuebleDAO() {
        connection = conexion.establecerConexion();
    }
    
    
    public List<TipoInmueble> listarTiposInmueble() {
        
        List<TipoInmueble> tiposInmuebles = new ArrayList();
        String sql = "SELECT * FROM tipo_inmueble";
        
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                TipoInmueble tipoInm = new TipoInmueble();
                tipoInm.setId(rs.getInt("id"));
                tipoInm.setTipoInmueble(rs.getString("tipo"));
            }
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar tipos de comercializacion" + e.toString());

        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return tiposInmuebles;    
        
    }
    
    public TipoInmueble consultarIDTipo(int id) { //consulta por ID
        
        TipoInmueble tipoInm = null;
        String sql = "SELECT * FROM tipo_inmueble WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                tipoInm = new TipoInmueble();
                rs.getInt("id");
                rs.getString("tipo");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la consulta" + e.toString());

        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return tipoInm;
    
    }
    
    public TipoInmueble consultarTipoInm(String tipo) { //consulta por tipoInm
        TipoInmueble tipoInm = null;
        String sql = "SELECT * FROM tipo_inmueble WHERE tipo = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            if (rs.next()) {
                tipoInm = new TipoInmueble();
                rs.getInt("id");
                rs.getString("tipo");
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la consulta" + e.toString());

        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return tipoInm;
    }
    
}
