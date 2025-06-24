/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.controller;

import com.inmobiliaria.dao.ContratoPropietarioDAO;
import com.inmobiliaria.model.ContratoPropietario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ContratoPropietarioController {
    
    private final ContratoPropietarioDAO contratoDAO;

    public ContratoPropietarioController() {
        this.contratoDAO = new ContratoPropietarioDAO();
    }

    public boolean registrar(ContratoPropietario contrato) throws SQLException {
        return contratoDAO.RegistrarContratoPropietario(contrato);
    }

    public boolean actualizar(ContratoPropietario contrato) throws SQLException {
        return contratoDAO.ActualizarContratoPropietario(contrato);
    }

    public boolean eliminar(int codigoContrato) throws SQLException {
        return contratoDAO.EliminarContratoPropietario(codigoContrato);
    }

    public ContratoPropietario consultarCodigo(int codigoContrato) throws SQLException {
        return contratoDAO.ConsultarContratoPropietario(codigoContrato);
    }

    public List<ContratoPropietario> listarTodos() throws SQLException {
        return contratoDAO.ListarContratosProp();
    }
    
}
