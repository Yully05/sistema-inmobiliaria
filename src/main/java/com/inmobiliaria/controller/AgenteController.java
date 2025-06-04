/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.controller;

import com.inmobiliaria.dao.AgenteComercialDAO;
import com.inmobiliaria.model.AgenteComercial;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public class AgenteController {
    
    private final AgenteComercialDAO agenteDAO;

    public AgenteController() {
        agenteDAO = new AgenteComercialDAO();
    }
    
    public boolean registrar(AgenteComercial agente) throws SQLException {
        return agenteDAO.RegistrarAgente(agente);
    }

    public boolean actualizar(AgenteComercial agente) throws SQLException {
        return agenteDAO.ActualizarAgente(agente);
    }

    public boolean eliminar(String cedula) throws SQLException {
        return agenteDAO.EliminarAgente(cedula);
    }

    public AgenteComercial consultarCedula(String cedula) throws SQLException {
        return agenteDAO.ConsultarAgente(cedula);
    }

    public List<AgenteComercial> listarTodos() throws SQLException {
        return agenteDAO.listarAgente();
    }
    
}
