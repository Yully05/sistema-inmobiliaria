/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.controller;

import com.inmobiliaria.dao.ContratoClienteDAO;
import com.inmobiliaria.model.ContratoCliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ContratoClienteController {
    
    private final ContratoClienteDAO contratoDAO;

    public ContratoClienteController() {
        this.contratoDAO = new ContratoClienteDAO();
    }

    public boolean registrar(ContratoCliente contrato) throws SQLException {
        return contratoDAO.RegistrarContratoCliente(contrato);
    }

    public boolean actualizar(ContratoCliente contrato) throws SQLException {
        return contratoDAO.ActualizarContratoCliente(contrato);
    }

    public boolean eliminar(int codigoContrato) throws SQLException {
        return contratoDAO.EliminarContratoCliente(codigoContrato);
    }

    public ContratoCliente consultarCodigo(int codigoContrato) throws SQLException {
        return contratoDAO.ConsultarContratoCliente(codigoContrato);
    }

    public List<ContratoCliente> listarTodos() throws SQLException {
        return contratoDAO.ListarContratosCl();
    }
    
}
