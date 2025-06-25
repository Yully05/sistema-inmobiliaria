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
            connection.setAutoCommit(false); // Iniciar transacción

            //insertar en la tabla general (contrato)
            psContrato = connection.prepareStatement(sqlContrato);
            psContrato.setInt(1, contratoCl.getCodigo());
            psContrato.setString(2, contratoCl.getDescripcion());
            psContrato.setInt(3, contratoCl.getModalidadComercializacion());
            psContrato.setDate(4, Date.valueOf(contratoCl.getFechaCreacion()));
            psContrato.setDate(5, Date.valueOf(contratoCl.getFechaExpiracion()));
            psContrato.setString(6, contratoCl.getCedulaAgente());
            psContrato.executeUpdate();

            //insertar en la tabla contrato_cliente
            PreparedStatement psContratoCliente = connection.prepareStatement(sqlContratoCliente);
            psContratoCliente.setInt(1, contratoCl.getCodigo());
            psContratoCliente.setDouble(2, contratoCl.getValor());
            psContratoCliente.setString(3, contratoCl.getNombreFiador());
            psContratoCliente.setString(4, contratoCl.getCelularFiador());
            psContratoCliente.setString(5, contratoCl.getCedulaCliente());
            psContratoCliente.executeUpdate();
            psContratoCliente.close();

            connection.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción en caso de error
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al registrar Contrato Cliente: " + e.toString());
            return false;
        } finally {
            try {
                if (psContrato != null) psContrato.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean ActualizarContratoCliente(ContratoCliente contratoCl) {

        String sqlContrato = "UPDATE contrato SET descripcion = ?, id_modalidad = ?, fecha_creacion = ?, fecha_expiracion = ?, cedula_agente = ? WHERE codigo = ?";
        String sqlContratoCliente = "UPDATE contrato_cliente SET valor = ?, nombre_fiador = ?, telefono_fiador = ?, cedula_cliente = ? WHERE codigo_contrato = ?";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            // Actualizar en la tabla general (contrato)
            psContrato = connection.prepareStatement(sqlContrato);
            psContrato.setString(1, contratoCl.getDescripcion());
            psContrato.setInt(2, contratoCl.getModalidadComercializacion());
            psContrato.setDate(3, Date.valueOf(contratoCl.getFechaCreacion()));
            psContrato.setDate(4, Date.valueOf(contratoCl.getFechaExpiracion()));
            psContrato.setString(5, contratoCl.getCedulaAgente());
            psContrato.setInt(6, contratoCl.getCodigo());
            psContrato.executeUpdate();

            // Actualizar en la tabla contrato_cliente
            PreparedStatement psContratoCliente = connection.prepareStatement(sqlContratoCliente);
            psContratoCliente.setDouble(1, contratoCl.getValor());
            psContratoCliente.setString(2, contratoCl.getNombreFiador());
            psContratoCliente.setString(3, contratoCl.getCelularFiador());
            psContratoCliente.setString(4, contratoCl.getCedulaCliente());
            psContratoCliente.setInt(5, contratoCl.getCodigo());
            psContratoCliente.executeUpdate();
            psContratoCliente.close();

            connection.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción en caso de error
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al actualizar Contrato Cliente: " + e.toString());
            return false;
        } finally {
            try {
                if (psContrato != null) psContrato.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean EliminarContratoCliente(int codigoContrato) {
        String sqlContratoCliente = "DELETE FROM contrato_cliente WHERE codigo_contrato = ?";
        String sqlContrato = "DELETE FROM contrato WHERE codigo = ?";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            PreparedStatement psContratoCliente = connection.prepareStatement(sqlContratoCliente);
            psContratoCliente.setInt(1, codigoContrato);
            psContratoCliente.executeUpdate();
            psContratoCliente.close();

            psContrato = connection.prepareStatement(sqlContrato);
            psContrato.setInt(1, codigoContrato);
            int filasAfectadas = psContrato.executeUpdate();

            connection.commit(); // Confirmar transacción
            return filasAfectadas > 0;
        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción en caso de error
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al eliminar Contrato Cliente: " + e.toString());
            return false;
        } finally {
            try {
                if (psContrato != null) psContrato.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public ContratoCliente ConsultarContratoCliente(int codigoContrato) {
        ContratoCliente contratoCl = null;
        String sql = "SELECT c.*, cc.valor, cc.nombre_fiador, cc.telefono_fiador, cc.cedula_cliente FROM contrato c JOIN contrato_cliente cc ON c.codigo = cc.codigo_contrato WHERE c.codigo = ?";

        try {
            psContrato = connection.prepareStatement(sql);
            psContrato.setInt(1, codigoContrato);
            resultado = psContrato.executeQuery();

            if (resultado.next()) {
                contratoCl = new ContratoCliente();
                contratoCl.setCodigo(resultado.getInt("codigo"));
                contratoCl.setDescripcion(resultado.getString("descripcion"));
                contratoCl.setModalidadComercializacion(resultado.getInt("id_modalidad"));
                contratoCl.setFechaCreacion(resultado.getDate("fecha_creacion").toLocalDate()); // Corregido
                contratoCl.setFechaExpiracion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setValor(resultado.getDouble("valor"));
                contratoCl.setNombreFiador(resultado.getString("nombre_fiador"));
                contratoCl.setCelularFiador(resultado.getString("telefono_fiador"));
                contratoCl.setCedulaCliente(resultado.getString("cedula_cliente"));
                contratoCl.setCedulaAgente(resultado.getString("cedula_agente"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar Contrato Cliente: " + e.toString());
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (psContrato != null) psContrato.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return contratoCl;
    }

    public List<ContratoCliente> ListarContratosCl() {
        List<ContratoCliente> listaContratos = new ArrayList<>();
        String sql = "SELECT c.*, cc.valor, cc.nombre_fiador, cc.telefono_fiador, cc.cedula_cliente FROM contrato c JOIN contrato_cliente cc ON c.codigo = cc.codigo_contrato";
        try {
            psContrato = connection.prepareStatement(sql);
            resultado = psContrato.executeQuery();

            while (resultado.next()) {
                ContratoCliente contratoCl = new ContratoCliente();
                contratoCl.setCodigo(resultado.getInt("codigo"));
                contratoCl.setDescripcion(resultado.getString("descripcion"));
                contratoCl.setModalidadComercializacion(resultado.getInt("id_modalidad"));
                contratoCl.setFechaCreacion(resultado.getDate("fecha_creacion").toLocalDate()); // Corregido
                contratoCl.setFechaExpiracion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoCl.setValor(resultado.getDouble("valor"));
                contratoCl.setNombreFiador(resultado.getString("nombre_fiador"));
                contratoCl.setCelularFiador(resultado.getString("telefono_fiador"));
                contratoCl.setCedulaCliente(resultado.getString("cedula_cliente"));
                contratoCl.setCedulaAgente(resultado.getString("cedula_agente"));
                listaContratos.add(contratoCl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar Contratos" + e.toString());

        } finally {
            try {
                if (resultado != null) resultado.close();
                if (psContrato != null) psContrato.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaContratos;
    }

}