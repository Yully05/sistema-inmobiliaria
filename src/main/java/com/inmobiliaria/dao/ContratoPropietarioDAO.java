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
        String sqlContratoProp = "INSERT INTO contrato_propietario (codigo_contrato, valor, porcentaje_comision, cedula_propietario) VALUES (?,?,?,?)"; // Corregido: eliminado un '?' extra

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            //insertar en la tabla general (contrato)
            psContrato = connection.prepareStatement(sqlContrato);
            psContrato.setInt(1, contratoProp.getCodigo());
            psContrato.setString(2, contratoProp.getDescripcion());
            psContrato.setInt(3, contratoProp.getModalidadComercializacion());
            psContrato.setDate(4, Date.valueOf(contratoProp.getFechaCreacion()));
            psContrato.setDate(5, Date.valueOf(contratoProp.getFechaExpiracion()));
            psContrato.setString(6, contratoProp.getCedulaAgente());
            psContrato.executeUpdate();

            //insertar en la tabla contrato_propietario
            PreparedStatement psContratoPropietario = connection.prepareStatement(sqlContratoProp);
            psContratoPropietario.setInt(1, contratoProp.getCodigo());
            psContratoPropietario.setDouble(2, contratoProp.getValor());
            psContratoPropietario.setDouble(3, contratoProp.getPorcentajeComision());
            psContratoPropietario.setString(4, contratoProp.getCedulaPropietario());
            psContratoPropietario.executeUpdate();
            psContratoPropietario.close();

            connection.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción en caso de error
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al registrar Contrato Propietario: " + e.toString());
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

    public boolean ActualizarContratoPropietario(ContratoPropietario contratoProp) {

        String sqlContrato = "UPDATE contrato SET descripcion = ?, id_modalidad = ?, fecha_creacion = ?, fecha_expiracion = ?, cedula_agente = ? WHERE codigo = ?";
        String sqlContratoProp = "UPDATE contrato_propietario SET valor = ?, porcentaje_comision = ?, cedula_propietario = ? WHERE codigo_contrato = ?";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            // Actualizar en la tabla general (contrato)
            psContrato = connection.prepareStatement(sqlContrato);
            psContrato.setString(1, contratoProp.getDescripcion());
            psContrato.setInt(2, contratoProp.getModalidadComercializacion());
            psContrato.setDate(3, Date.valueOf(contratoProp.getFechaCreacion()));
            psContrato.setDate(4, Date.valueOf(contratoProp.getFechaExpiracion()));
            psContrato.setString(5, contratoProp.getCedulaAgente());
            psContrato.setInt(6, contratoProp.getCodigo());
            psContrato.executeUpdate();

            // Actualizar en la tabla contrato_propietario
            PreparedStatement psContratoPropietario = connection.prepareStatement(sqlContratoProp);
            psContratoPropietario.setDouble(1, contratoProp.getValor());
            psContratoPropietario.setDouble(2, contratoProp.getPorcentajeComision());
            psContratoPropietario.setString(3, contratoProp.getCedulaPropietario());
            psContratoPropietario.setInt(4, contratoProp.getCodigo());
            psContratoPropietario.executeUpdate();
            psContratoPropietario.close();

            connection.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción en caso de error
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al actualizar Contrato Propietario: " + e.toString());
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

    public boolean EliminarContratoPropietario(int codigoContrato) {
        String sqlContratoProp = "DELETE FROM contrato_propietario WHERE codigo_contrato = ?";
        String sqlContrato = "DELETE FROM contrato WHERE codigo = ?";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            PreparedStatement psContratoPropietario = connection.prepareStatement(sqlContratoProp);
            psContratoPropietario.setInt(1, codigoContrato);
            psContratoPropietario.executeUpdate();
            psContratoPropietario.close();

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
            JOptionPane.showMessageDialog(null, "Error al eliminar Contrato Propietario: " + e.toString());
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

    public ContratoPropietario ConsultarContratoPropietario(int codigoContrato) {
        ContratoPropietario contratoProp = null;
        String sql = "SELECT c.*, cp.valor, cp.porcentaje_comision, cp.cedula_propietario FROM contrato c JOIN contrato_propietario cp ON c.codigo = cp.codigo_contrato WHERE c.codigo = ?";

        try {
            psContrato = connection.prepareStatement(sql);
            psContrato.setInt(1, codigoContrato);
            resultado = psContrato.executeQuery();

            if (resultado.next()) {
                contratoProp = new ContratoPropietario();
                contratoProp.setCodigo(resultado.getInt("codigo"));
                contratoProp.setDescripcion(resultado.getString("descripcion"));
                contratoProp.setModalidadComercializacion(resultado.getInt("id_modalidad"));
                contratoProp.setFechaCreacion(resultado.getDate("fecha_creacion").toLocalDate()); // Corregido
                contratoProp.setFechaExpiracion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setValor(resultado.getDouble("valor"));
                contratoProp.setPorcentajeComision(resultado.getDouble("porcentaje_comision"));
                contratoProp.setCedulaPropietario(resultado.getString("cedula_propietario"));
                contratoProp.setCedulaAgente(resultado.getString("cedula_agente"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar Contrato Propietario: " + e.toString());
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (psContrato != null) psContrato.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return contratoProp;
    }

    public List<ContratoPropietario> ListarContratosProp() {
        List<ContratoPropietario> listaContratos = new ArrayList<>();
        String sql = "SELECT c.*, cp.valor, cp.porcentaje_comision, cp.cedula_propietario FROM contrato c JOIN contrato_propietario cp ON c.codigo = cp.codigo_contrato";
        try {
            psContrato = connection.prepareStatement(sql);
            resultado = psContrato.executeQuery();

            while (resultado.next()) {
                ContratoPropietario contratoProp = new ContratoPropietario();
                contratoProp.setCodigo(resultado.getInt("codigo"));
                contratoProp.setDescripcion(resultado.getString("descripcion"));
                contratoProp.setModalidadComercializacion(resultado.getInt("id_modalidad"));
                contratoProp.setFechaCreacion(resultado.getDate("fecha_creacion").toLocalDate()); // Corregido
                contratoProp.setFechaExpiracion(resultado.getDate("fecha_expiracion").toLocalDate());
                contratoProp.setValor(resultado.getDouble("valor"));
                contratoProp.setPorcentajeComision(resultado.getDouble("porcentaje_comision"));
                contratoProp.setCedulaPropietario(resultado.getString("cedula_propietario"));
                contratoProp.setCedulaAgente(resultado.getString("cedula_agente"));
                listaContratos.add(contratoProp);
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