/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.dao;

import com.inmobiliaria.model.EstadoInmueble;
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
public class EstadoInmuebleDAO {
    
    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public EstadoInmuebleDAO() {
        
        connection = conexion.establecerConexion();
    }
    
    public List<EstadoInmueble> listarEstadosInmueble() throws SQLException {
        
        List<EstadoInmueble> listaEstados = new ArrayList();
        String sql = "SELECT * FROM estado_inmueble";
        
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                EstadoInmueble estado = new EstadoInmueble();
                estado.setId(rs.getInt("id"));
                estado.setEstadoInmueble(rs.getString("estado"));
            }
            
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al listar estados" + e.toString());

        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaEstados;    
        
    }
    
    public EstadoInmueble consultarIDEstado(int id) { //consulta por ID
        EstadoInmueble IDestado = null;
        String sql = "SELECT * FROM estado_inmueble WHERE id = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                IDestado = new EstadoInmueble();
                rs.getInt("id");
                rs.getString("estado");
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
        return IDestado;
    
    }
    
    public EstadoInmueble consultarEstado(String estado) { //consulta por tipo estado
        EstadoInmueble estadoNombre = null;
        String sql = "SELECT * FROM estado_inmueble WHERE estado = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, estado);
            rs = ps.executeQuery();
            if (rs.next()) {
                estadoNombre = new EstadoInmueble();
                rs.getInt("id");
                rs.getString("estado");
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
        return estadoNombre;
    }
}
