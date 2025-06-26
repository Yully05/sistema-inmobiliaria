package com.inmobiliaria.dao;

import com.inmobiliaria.model.Cliente;
import com.inmobiliaria.model.Propietario;

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
public class PropietarioDAO {

    Conexion conexion = new Conexion();
    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public PropietarioDAO() {
        connection = conexion.establecerConexion();
    }

    public boolean RegistrarPropietario(Propietario propietario) {

        String sqlPropietario = "INSERT INTO propietario (cedula, nombres, apellidos, direccion, correo, fecha_nacimiento, fecha_expedicion_doc) VALUES (?,?,?,?,?,?,?)";
        String sqlTel = "INSERT INTO telefonos_propietario (cedula_propietario, telefono) VALUES (?,?)";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            ps = connection.prepareStatement(sqlPropietario);
            ps.setString(1, propietario.getCedula());
            ps.setString(2, propietario.getNombres());
            ps.setString(3, propietario.getApellidos()); // Mantener 'apellidos'
            ps.setString(4, propietario.getDireccion());
            ps.setString(5, propietario.getCorreo());
            ps.setDate(6, Date.valueOf(propietario.getFechaNacimiento()));
            ps.setDate(7, Date.valueOf(propietario.getFechaExpDoc()));
            ps.executeUpdate();

            if (propietario.getTelefonos() != null) {
                for (String telefono : propietario.getTelefonos()) {
                    PreparedStatement sentenciaTel = connection.prepareStatement(sqlTel);
                    sentenciaTel.setString(1, propietario.getCedula());
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
            JOptionPane.showMessageDialog(null, "Error al registrar Propietario: " + e.toString());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean ActualizarPropietario(Propietario propietario) {
        String sqlPropietario = "UPDATE propietario SET nombres = ?, apellidos = ?, direccion = ?, correo = ?, fecha_nacimiento = ?, fecha_expedicion_doc = ? WHERE cedula = ?";
        String sqlDeleteTel = "DELETE FROM telefonos_propietario WHERE cedula_propietario = ?";
        String sqlInsertTel = "INSERT INTO telefonos_propietario (cedula_propietario, telefono) VALUES (?,?)";

        try {
            connection.setAutoCommit(false); // Iniciar transacción

            ps = connection.prepareStatement(sqlPropietario);
            ps.setString(1, propietario.getNombres());
            ps.setString(2, propietario.getApellidos()); // Mantener 'apellidos'
            ps.setString(3, propietario.getDireccion());
            ps.setString(4, propietario.getCorreo());
            ps.setDate(5, Date.valueOf(propietario.getFechaNacimiento()));
            ps.setDate(6, Date.valueOf(propietario.getFechaExpDoc())); // Corregido
            ps.setString(7, propietario.getCedula());
            ps.executeUpdate();

            // Eliminar teléfonos existentes y luego insertar los nuevos
            PreparedStatement psDeleteTel = connection.prepareStatement(sqlDeleteTel);
            psDeleteTel.setString(1, propietario.getCedula());
            psDeleteTel.executeUpdate();
            psDeleteTel.close();

            if (propietario.getTelefonos() != null) {
                for (String telefono : propietario.getTelefonos()) {
                    PreparedStatement psInsertTel = connection.prepareStatement(sqlInsertTel);
                    psInsertTel.setString(1, propietario.getCedula());
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
            JOptionPane.showMessageDialog(null, "Error al actualizar Propietario: " + e.toString());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean EliminarPropietario(String cedula) {
        String sql = "DELETE FROM propietario WHERE cedula = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, cedula);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar Propietario: " + e.toString());
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public Propietario ConsultarPropietario(String cedula) {
        Propietario propietario = null;
        String sqlCliente = "SELECT * FROM propietario WHERE cedula = ?";
        String sqlTel = "SELECT telefono FROM telefonos_propietario WHERE cedula_propietario = ?";
        PreparedStatement sentenciaTel = null; // Declarar aquí

        try {
            ps = connection.prepareStatement(sqlCliente);
            ps.setString(1, cedula);
            rs = ps.executeQuery();
            if (rs.next()) {
                propietario = new Propietario();
                propietario.setCedula(rs.getString("cedula"));
                propietario.setNombres(rs.getString("nombres"));
                propietario.setApellidos(rs.getString("apellidos")); // Mantener 'apellidos'
                propietario.setDireccion(rs.getString("direccion"));
                propietario.setCorreo(rs.getString("correo"));
                propietario.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                propietario.setFechaExpDoc(rs.getDate("fecha_expedicion_doc").toLocalDate());

                List<String> telefonos = new ArrayList<>();
                sentenciaTel = connection.prepareStatement(sqlTel); // Inicializar aquí
                sentenciaTel.setString(1, cedula);
                ResultSet resultadoTel = sentenciaTel.executeQuery();

                while (resultadoTel.next()) {
                    telefonos.add(resultadoTel.getString("telefono"));
                }
                resultadoTel.close();
                propietario.setTelefonos(telefonos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar Propietario: " + e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (sentenciaTel != null) sentenciaTel.close(); // Cerrar
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return propietario;
    }

    public List<Propietario> listarPropietario() { // Cambiado a public para ser accesible desde el controlador
        List<Propietario> listaPropietario = new ArrayList<>();
        String sqlCliente = "SELECT * FROM propietario";
        String sqlTel = "SELECT telefono FROM telefonos_propietario WHERE cedula_propietario = ?";
        PreparedStatement sentenciaTel = null; // Declarar aquí

        try {
            ps = connection.prepareStatement(sqlCliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                Propietario propietario = new Propietario();
                String cedula = rs.getString("cedula"); // Obtener cédula antes de usarla
                propietario.setCedula(cedula);
                propietario.setNombres(rs.getString("nombres"));
                propietario.setApellidos(rs.getString("apellidos")); // Mantener 'apellidos'
                propietario.setDireccion(rs.getString("direccion"));
                propietario.setCorreo(rs.getString("correo"));
                propietario.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                propietario.setFechaExpDoc(rs.getDate("fecha_expedicion_doc").toLocalDate());

                List<String> telefonos = new ArrayList<>();
                sentenciaTel = connection.prepareStatement(sqlTel); // Inicializar aquí
                sentenciaTel.setString(1, cedula);
                ResultSet resultadoTel = sentenciaTel.executeQuery();

                while (resultadoTel.next()) {
                    telefonos.add(resultadoTel.getString("telefono"));
                }
                resultadoTel.close();
                sentenciaTel.close(); // Cerrar
                propietario.setTelefonos(telefonos);
                listaPropietario.add(propietario);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "Error al listar Clientes. " + e.toString());

        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (sentenciaTel != null) sentenciaTel.close(); // Cerrar
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
        return listaPropietario;
    }
}