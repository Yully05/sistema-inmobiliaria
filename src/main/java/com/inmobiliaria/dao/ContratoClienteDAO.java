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
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class ContratoClienteDAO {
    
    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement psContrato;
    ResultSet resultado;

    public ContratoClienteDAO() {
        
        connection = conexion.establecerConexion();
    }
    
    public boolean RegistrarContratoCliente(ContratoCliente contratoCl) {
        
        String sqlContrato = "INSERT INTO contrato (codigo, descripcion, id_modalidad, fecha_creacion, fecha_expiracion, cedula_agente) VALUES (?,?,?,?,?,?)";
        String sqlContratoCliente = "INSERT INTO contrato_cliente (codigo_contrato, valor, nombre_fiador, telefono_fiador, cedula_cliente) VALUES (?,?,?,?,?)";
        
        try {
            //insertar en la tabla general (contrato)
            psContrato = connection.prepareStatement(sqlContrato);
            psContrato.setInt(1, contratoCl.getCodigo());
            psContrato.setString(2, contratoCl.getDescripcion());
            psContrato.setInt(3, contratoCl.getModalidadComercializacion());
            psContrato.setDate(4, Date.valueOf(contratoCl.getFechaCreacion()));
            psContrato.setDate(5, Date.valueOf(contratoCl.getFechaExpiracion()));
            psContrato.setString(6, contratoCl.getCedulaAgente());
            psContrato.executeUpdate();
            
            //insertar en contrato_cliente
            PreparedStatement psContratoCliente = connection.prepareStatement(sqlContratoCliente);
            psContratoCliente.setInt(1, contratoCl.getCodigo()); //fk
            psContratoCliente.setDouble(2, contratoCl.getValor());
            psContratoCliente.setString(3, contratoCl.getNombreFiador());
            psContratoCliente.setString(4, contratoCl.getCelularFiador());
            psContratoCliente.setString(5, contratoCl.getCedulaCliente());
            psContratoCliente.executeUpdate();
            
            return true;
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al registrar Contrato con Cliente" + e.toString());
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión" + e.toString());
            }
        }
    }
    
    public boolean ActualizarContratoCliente(ContratoCliente contratoCl) {

    String sqlContrato = "UPDATE contrato SET descripcion=?, id_modalidad=?, fecha_creacion=?, fecha_expiracion=?, cedula_agente=? WHERE codigo=?";
    String sqlContratoCliente = "UPDATE contrato_cliente SET valor=?, nombre_fiador=?, telefono_fiador=?, cedula_cliente=? WHERE codigo_contrato=?";
    
    try {
        //actualizar tabla contrato
        psContrato = connection.prepareStatement(sqlContrato);
        psContrato.setString(1, contratoCl.getDescripcion());
        psContrato.setInt(2, contratoCl.getModalidadComercializacion());
        psContrato.setDate(3, Date.valueOf(contratoCl.getFechaCreacion()));
        psContrato.setDate(4, Date.valueOf(contratoCl.getFechaExpiracion()));
        psContrato.setString(5, contratoCl.getCedulaAgente());
        psContrato.setInt(6, contratoCl.getCodigo());
        psContrato.executeUpdate();
        
        //actualizar contrato cliente
        PreparedStatement psContratoCliente = connection.prepareStatement(sqlContratoCliente);
        psContratoCliente.setDouble(1, contratoCl.getValor());
        psContratoCliente.setString(2, contratoCl.getNombreFiador());
        psContratoCliente.setString(3, contratoCl.getCelularFiador());
        psContratoCliente.setString(4, contratoCl.getCedulaCliente());
        psContratoCliente.setInt(5, contratoCl.getCodigo());
        psContratoCliente.executeUpdate();
        
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

    public boolean EliminarContratoCliente(int codigo) {

        String sqlContratoCliente = "DELETE FROM contrato_cliente WHERE codigo_contrato=?";
        String sqlContrato = "DELETE FROM contrato WHERE codigo=?";
        try {
            //primero en contrato_cliente
            PreparedStatement psContratoCliente = connection.prepareStatement(sqlContratoCliente);
            psContratoCliente.setInt(1, codigo);
            psContratoCliente.executeUpdate();

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

    public ContratoCliente ConsultarContratoCliente(int codigo) throws SQLException{

        ContratoCliente contratoCl = null;

        String sql = "SELECT c.codigo, c.descripcion, c.id_modalidad, c.fecha_creacion, c.fecha_expiracion, c.cedula_agente, " +
                     "cc.valor, cc.nombre_fiador, cc.telefono_fiador, cc.cedula_cliente " +
                     "FROM contrato c JOIN contrato_cliente cc ON c.codigo = cc.codigo_contrato " +
                     "WHERE c.codigo = ?";

        try {
            psContrato = connection.prepareStatement(sql);
            psContrato.setInt(1, codigo);
            resultado = psContrato.executeQuery();

            if (resultado.next()){

                contratoCl = new ContratoCliente();

                contratoCl.setCodigo(resultado.getInt("codigo"));
                contratoCl.setDescripcion(resultado.getString("descripcion"));
                contratoCl.setModalidadComercializacion(resultado.getInt("id_modalidad"));
                contratoCl.setFechaCreacion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setFechaExpiracion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setValor(resultado.getDouble("valor"));
                contratoCl.setNombreFiador(resultado.getString("nombre_fiador"));
                contratoCl.setCelularFiador(resultado.getString("telefono_fiador"));
                contratoCl.setCedulaCliente(resultado.getString("cedula_cliente"));
                contratoCl.setCedulaAgente(resultado.getString("cedula_agente"));
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


    public List ListarContratosCl() throws SQLException {

        List<ContratoCliente> listaContratos = new ArrayList<>();
        String sql = "SELECT c.codigo, c.descripcion, c.id_modalidad, c.fecha_creacion, c.fecha_expiracion, c.cedula_agente, " +
                     "cc.valor, cc.nombre_fiador, cc.telefono_fiador, cc.cedula_cliente " +
                     "FROM contrato c JOIN contrato_cliente cc ON c.codigo = cc.codigo_contrato";
        try {
            psContrato = connection.prepareStatement(sql);
            resultado = psContrato.executeQuery();

            while (resultado.next()){
                ContratoCliente contratoCl = new ContratoCliente();
                contratoCl.setCodigo(resultado.getInt("codigo"));
                contratoCl.setDescripcion(resultado.getString("descripcion"));
                contratoCl.setModalidadComercializacion(resultado.getInt("id_modalidad"));
                contratoCl.setFechaCreacion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setFechaExpiracion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setValor(resultado.getDouble("valor"));
                contratoCl.setNombreFiador(resultado.getString("nombre_fiador"));
                contratoCl.setCelularFiador(resultado.getString("telefono_fiador"));
                contratoCl.setCedulaCliente(resultado.getString("cedula_cliente"));
                contratoCl.setCedulaAgente(resultado.getString("cedula_agente"));
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
