/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.dao;

import com.inmobiliaria.model.ModalidadComercializacionInmueble;
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
public class ModalidadComercializacionDAO {
    
    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public ModalidadComercializacionDAO() {
        
        connection = conexion.establecerConexion();
    }
    
    
    public List<ModalidadComercializacionInmueble> listarModalidadInmueble() {
        
        List<ModalidadComercializacionInmueble> modalidadComer = new ArrayList();
        String sql = "SELECT * FROM tipo_comercializacion";
        
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                ModalidadComercializacionInmueble modalidad = new ModalidadComercializacionInmueble();
                modalidad.setId(rs.getInt("id"));
                modalidad.setModalidadComercializacion(rs.getString("tipo_comercializacion"));
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
        return modalidadComer;    
        
    }
    
    public ModalidadComercializacionInmueble consultarIDModalidad(int id) { //consulta por ID
        ModalidadComercializacionInmueble IDcomer = null;
        String sql = "SELECT * FROM tipo_comercializacion WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                IDcomer = new ModalidadComercializacionInmueble();
                rs.getInt("id");
                rs.getString("tipo_comercializacion");
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
        return IDcomer;
    
    }
    
    public ModalidadComercializacionInmueble consultarEstado(String modalidad) { //consulta por nombre modalidadComer
        ModalidadComercializacionInmueble modalidadComer = null;
        String sql = "SELECT * FROM tipo_comercializacion WHERE tipo_comercializacion = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, modalidad);
            rs = ps.executeQuery();
            if (rs.next()) {
                modalidadComer = new ModalidadComercializacionInmueble();
                rs.getInt("id");
                rs.getString("tipo_comercializacion");
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
        return modalidadComer;
    }
    
}
