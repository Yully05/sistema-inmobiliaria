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
    PreparedStatement psContrato;
    ResultSet resultado;

    public ContratoPropietarioDAO() {
        connection = conexion.establecerConexion();
    }
    
    public boolean RegistrarContratoPropietario(ContratoPropietario contratoProp) {
        
        String sqlContrato = "INSERT INTO contrato (codigo, descripcion, id_modalidad, fecha_creacion, fecha_expiracion, cedula_agente) VALUES (?,?,?,?,?,?)";
        String sqlContratoProp = "INSERT INTO contrato_propietario (codigo_contrato, valor, porcentaje_comision, cedula_propietario) VALUES (?,?,?,?,?)";
        
        try {
            //insertar en la tabla general (contrato)
            psContrato = connection.prepareStatement(sqlContrato);
            psContrato.setInt(1, contratoProp.getCodigo());
            psContrato.setString(2, contratoProp.getDescripcion());
            psContrato.setInt(3, contratoProp.getModalidadComercializacion());
            psContrato.setDate(4, Date.valueOf(contratoProp.getFechaCreacion()));
            psContrato.setDate(5, Date.valueOf(contratoProp.getFechaExpiracion()));
            psContrato.setString(6, contratoProp.getCedulaAgente());
            psContrato.executeUpdate();
            
            //insertar en contrato prop
            PreparedStatement psContratoProp = connection.prepareStatement(sqlContratoProp);
            psContratoProp.setInt(1, contratoProp.getCodigo()); //fk
            psContratoProp.setDouble(2, contratoProp.getValor());
            psContratoProp.setDouble(3, contratoProp.getPorcentajeComision());
            psContratoProp.setString(4, contratoProp.getCedulaPropietario());
            psContratoProp.executeUpdate();
            
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

        String sqlContrato = "UPDATE contrato SET descripcion=?, id_modalidad=?, fecha_creacion=?, fecha_expiracion=?, cedula_agente=? WHERE codigo=?";
        String sqlContratoProp = "UPDATE contrato_propietario SET valor=?, porcentaje_comision=?, cedula_propietario=? WHERE codigo_contrato=?";

        try {
            //actualizar tabla contrato
            psContrato = connection.prepareStatement(sqlContrato);
            psContrato.setString(1, contratoProp.getDescripcion());
            psContrato.setInt(2, contratoProp.getModalidadComercializacion());
            psContrato.setDate(3, Date.valueOf(contratoProp.getFechaCreacion()));
            psContrato.setDate(4, Date.valueOf(contratoProp.getFechaExpiracion()));
            psContrato.setString(5, contratoProp.getCedulaAgente());
            psContrato.setInt(6, contratoProp.getCodigo());
            psContrato.executeUpdate();

            //actualizar contrato prop
            PreparedStatement psContratoProp = connection.prepareStatement(sqlContratoProp);
            psContratoProp.setDouble(1, contratoProp.getValor());
            psContratoProp.setDouble(2, contratoProp.getPorcentajeComision());
            psContratoProp.setString(3, contratoProp.getCedulaPropietario());
            psContratoProp.setInt(5, contratoProp.getCodigo());
            psContratoProp.executeUpdate();

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

    public boolean EliminarContratoPropietario(int codigo) {

        String sqlContratoProp = "DELETE FROM contrato_propietario WHERE codigo_contrato=?";
        String sqlContrato = "DELETE FROM contrato WHERE codigo=?";
        try {
            //primero en contrato_propietario
            PreparedStatement psContratoProp = connection.prepareStatement(sqlContratoProp);
            psContratoProp.setInt(1, codigo);
            psContratoProp.executeUpdate();

            //eliminar el contrato
            psContrato = connection.prepareStatement(sqlContrato);
            psContrato.setInt(1, codigo);
            psContrato.executeUpdate();

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

    public ContratoPropietario ConsultarContratoPropietario(int codigo) throws SQLException{

        ContratoPropietario contratoProp = null;

        String sql = "SELECT c.codigo, c.descripcion, c.id_modalidad, c.fecha_creacion, c.fecha_expiracion, c.cedula_agente, " +
                     "cc.valor, cc.nombre_fiador, cc.telefono_fiador, cc.cedula_cliente " +
                     "FROM contrato c JOIN contrato_propietario cc ON c.codigo = cc.codigo_contrato " +
                     "WHERE c.codigo = ?";

        try {
            psContrato = connection.prepareStatement(sql);
            psContrato.setInt(1, codigo);
            resultado = psContrato.executeQuery();

            if (resultado.next()){

                contratoProp = new ContratoPropietario();

                contratoProp.setCodigo(resultado.getInt("codigo"));
                contratoProp.setDescripcion(resultado.getString("descripcion"));
                contratoProp.setModalidadComercializacion(resultado.getInt("id_modalidad"));
                contratoProp.setFechaCreacion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setFechaExpiracion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setValor(resultado.getDouble("valor"));
                contratoProp.setPorcentajeComision(resultado.getDouble("porcentaje_comision"));
                contratoProp.setCedulaPropietario(resultado.getString("cedula_propietario"));
                contratoProp.setCedulaAgente(resultado.getString("cedula_agente"));
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


    public List ListarContratosProp() throws SQLException {

        List<ContratoPropietario> listaContratos = new ArrayList<>();
        String sql = "SELECT c.codigo, c.descripcion, c.id_modalidad, c.fecha_creacion, c.fecha_expiracion, c.cedula_agente, " +
                     "cc.valor, cc.porcentaje_comision, cc.cedula_propietario " +
                     "FROM contrato c JOIN contrato_propietario cc ON c.codigo = cc.codigo_contrato";
        try {
            psContrato = connection.prepareStatement(sql);
            resultado = psContrato.executeQuery();

            while (resultado.next()){
                ContratoPropietario contratoProp = new ContratoPropietario();
                contratoProp.setCodigo(resultado.getInt("codigo"));
                contratoProp.setDescripcion(resultado.getString("descripcion"));
                contratoProp.setModalidadComercializacion(resultado.getInt("id_modalidad"));
                contratoProp.setFechaCreacion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setFechaExpiracion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setValor(resultado.getDouble("valor"));
                contratoProp.setPorcentajeComision(resultado.getDouble("porcentaje_comision"));
                contratoProp.setCedulaPropietario(resultado.getString("cedula_propietario"));
                contratoProp.setCedulaAgente(resultado.getString("cedula_agente"));
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
