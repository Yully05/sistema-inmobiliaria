/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.controller;

import com.inmobiliaria.dao.ClienteDAO;
import com.inmobiliaria.model.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ClienteController {
    
    private final ClienteDAO clienteDAO;

    public ClienteController() {
        clienteDAO = new ClienteDAO();
    }
    
    public boolean registrar(Cliente clienteModel) throws SQLException {
        return clienteDAO.RegistrarCliente(clienteModel);
    }

    public boolean actualizar(Cliente clienteModel) throws SQLException {
        return clienteDAO.ActualizarCliente(clienteModel);
    }

    public boolean eliminar(String cedula) throws SQLException {
        return clienteDAO.EliminarCliente(cedula);
    }

    public Cliente consultarCedula(String cedula) throws SQLException {
        return clienteDAO.ConsultarCliente(cedula);
    }

    public List<Cliente> listarTodos() throws SQLException {
        return clienteDAO.listarCliente();
    }
    
}
