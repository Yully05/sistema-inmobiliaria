package com.inmobiliaria.dao;

import com.inmobiliaria.model.Cliente;
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
public class ClienteDAO {

    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement sentenciaCliente;
    ResultSet resultado;

    public ClienteDAO() {
        connection = conexion.establecerConexion();
    }

    public boolean RegistrarCliente(Cliente cliente) {

        String sqlCliente = "INSERT INTO cliente (cedula, nombres, apellidos, direccion, correo, fecha_nacimiento, fecha_expedicion_doc) VALUES (?,?,?,?,?,?,?)";
        String sqlTel = "INSERT INTO telefonos_cliente (cedula_cliente, telefono) VALUES (?,?)";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            sentenciaCliente = connection.prepareStatement(sqlCliente);
            sentenciaCliente.setString(1, cliente.getCedula());
            sentenciaCliente.setString(2, cliente.getNombres());
            sentenciaCliente.setString(3, cliente.getApellidos()); // Mantener 'apellidos'
            sentenciaCliente.setString(4, cliente.getDireccion());
            sentenciaCliente.setString(5, cliente.getCorreo());
            sentenciaCliente.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
            sentenciaCliente.setDate(7, Date.valueOf(cliente.getFechaExpDoc()));
            sentenciaCliente.executeUpdate();

            if (cliente.getTelefonos() != null) {
                for (String telefono : cliente.getTelefonos()) {
                    PreparedStatement sentenciaTel = connection.prepareStatement(sqlTel);
                    sentenciaTel.setString(1, cliente.getCedula());
                    sentenciaTel.setString(2, telefono);
                    sentenciaTel.executeUpdate();
                    sentenciaTel.close();
                }
            }

            connection.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción en caso de error
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al registrar Cliente: " + e.toString());
            return false;
        } finally {
            try {
                if (sentenciaCliente != null) sentenciaCliente.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean ActualizarCliente(Cliente cliente) {

        String sqlCliente = "UPDATE cliente SET nombres = ?, apellidos = ?, direccion = ?, correo = ?, fecha_nacimiento = ?, fecha_expedicion_doc = ? WHERE cedula = ?";
        String sqlDeleteTel = "DELETE FROM telefonos_cliente WHERE cedula_cliente = ?";
        String sqlInsertTel = "INSERT INTO telefonos_cliente (cedula_cliente, telefono) VALUES (?,?)";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            sentenciaCliente = connection.prepareStatement(sqlCliente);
            sentenciaCliente.setString(1, cliente.getNombres());
            sentenciaCliente.setString(2, cliente.getApellidos()); // Mantener 'apellidos'
            sentenciaCliente.setString(3, cliente.getDireccion());
            sentenciaCliente.setString(4, cliente.getCorreo());
            sentenciaCliente.setDate(5, Date.valueOf(cliente.getFechaNacimiento()));
            sentenciaCliente.setDate(6, Date.valueOf(cliente.getFechaExpDoc())); // Corregido
            sentenciaCliente.setString(7, cliente.getCedula());
            sentenciaCliente.executeUpdate();

            // Eliminar teléfonos existentes y luego insertar los nuevos
            PreparedStatement psDeleteTel = connection.prepareStatement(sqlDeleteTel);
            psDeleteTel.setString(1, cliente.getCedula());
            psDeleteTel.executeUpdate();
            psDeleteTel.close();

            if (cliente.getTelefonos() != null) {
                for (String telefono : cliente.getTelefonos()) {
                    PreparedStatement psInsertTel = connection.prepareStatement(sqlInsertTel);
                    psInsertTel.setString(1, cliente.getCedula());
                    psInsertTel.setString(2, telefono);
                    psInsertTel.executeUpdate();
                    psInsertTel.close();
                }
            }

            connection.commit(); // Confirmar transacción
            return true;

        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción en caso de error
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al actualizar Cliente: " + e.toString());
            return false;
        } finally {
            try {
                if (sentenciaCliente != null) sentenciaCliente.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean EliminarCliente(String cedula) {
        String sqlTel = "DELETE FROM telefonos_cliente WHERE cedula_cliente = ?";
        String sqlCliente = "DELETE FROM cliente WHERE cedula = ?";
        try {
            connection.setAutoCommit(false); // Iniciar transacción

            PreparedStatement psTel = connection.prepareStatement(sqlTel);
            psTel.setString(1, cedula);
            psTel.executeUpdate();
            psTel.close();

            sentenciaCliente = connection.prepareStatement(sqlCliente);
            sentenciaCliente.setString(1, cedula);
            int filasAfectadas = sentenciaCliente.executeUpdate();

            connection.commit(); // Confirmar transacción
            return filasAfectadas > 0;
        } catch (SQLException e) {
            try {
                connection.rollback(); // Revertir transacción en caso de error
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al eliminar Cliente: " + e.toString());
            return false;
        } finally {
            try {
                if (sentenciaCliente != null) sentenciaCliente.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public Cliente ConsultarCliente(String cedula) {
        Cliente cliente = null;
        String sqlCliente = "SELECT * FROM cliente WHERE cedula = ?";
        String sqlTel = "SELECT telefono FROM telefonos_cliente WHERE cedula_cliente = ?";
        PreparedStatement sentenciaTel = null; // Declarar aquí

        try {
            sentenciaCliente = connection.prepareStatement(sqlCliente);
            sentenciaCliente.setString(1, cedula);
            resultado = sentenciaCliente.executeQuery();
            if (resultado.next()) {
                cliente = new Cliente();
                cliente.setCedula(resultado.getString("cedula"));
                cliente.setNombres(resultado.getString("nombres"));
                cliente.setApellidos(resultado.getString("apellidos")); // Mantener 'apellidos'
                cliente.setDireccion(resultado.getString("direccion"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setFechaNacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());
                cliente.setFechaExpDoc(resultado.getDate("fecha_expedicion_doc").toLocalDate());

                List<String> telefonos = new ArrayList<>();
                sentenciaTel = connection.prepareStatement(sqlTel); // Inicializar aquí
                sentenciaTel.setString(1, cedula);
                ResultSet resultadoTel = sentenciaTel.executeQuery();

                while (resultadoTel.next()) {
                    telefonos.add(resultadoTel.getString("telefono"));
                }
                resultadoTel.close();
                cliente.setTelefonos(telefonos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar Cliente: " + e.toString());
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (sentenciaCliente != null) sentenciaCliente.close();
                if (sentenciaTel != null) sentenciaTel.close(); // Cerrar
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return cliente;
    }

    public List<Cliente> listarCliente() {
        List<Cliente> listaCliente = new ArrayList<>();
        String sqlCliente = "SELECT * FROM cliente";
        String sqlTel = "SELECT telefono FROM telefonos_cliente WHERE cedula_cliente = ?";
        PreparedStatement sentenciaTel = null; // Declarar aquí

        try {
            sentenciaCliente = connection.prepareStatement(sqlCliente);
            resultado = sentenciaCliente.executeQuery();

            while (resultado.next()) {
                Cliente cliente = new Cliente();
                String cedula = resultado.getString("cedula"); // Obtener cédula antes de usarla
                cliente.setCedula(cedula);
                cliente.setNombres(resultado.getString("nombres"));
                cliente.setApellidos(resultado.getString("apellidos")); // Mantener 'apellidos'
                cliente.setDireccion(resultado.getString("direccion"));
                cliente.setCorreo(resultado.getString("correo"));
                cliente.setFechaNacimiento(resultado.getDate("fecha_nacimiento").toLocalDate());
                cliente.setFechaExpDoc(resultado.getDate("fecha_expedicion_doc").toLocalDate());

                List<String> telefonos = new ArrayList<>();
                sentenciaTel = connection.prepareStatement(sqlTel); // Inicializar aquí
                sentenciaTel.setString(1, cedula);
                ResultSet resultadoTel = sentenciaTel.executeQuery();

                while (resultadoTel.next()) {
                    telefonos.add(resultadoTel.getString("telefono"));
                }
                resultadoTel.close();
                sentenciaTel.close(); // Cerrar
                cliente.setTelefonos(telefonos);
                listaCliente.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error al listar Clientes. " + e.toString());

        } finally {
            try {
                if (resultado != null) resultado.close();
                if (sentenciaCliente != null) sentenciaCliente.close();
                if (sentenciaTel != null) sentenciaTel.close(); // Cerrar
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaCliente;
    }
}