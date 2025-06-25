package com.inmobiliaria.controller;

import com.inmobiliaria.dao.PropietarioDAO;
import com.inmobiliaria.model.Propietario;

import java.sql.SQLException;
import java.util.List;

public class PropietarioController {
    private final PropietarioDAO propietarioDAO;

    public PropietarioController() {
        propietarioDAO = new PropietarioDAO();
    }

    public boolean registrar(Propietario propietarioModel) throws SQLException {
        return propietarioDAO.RegistrarPropietario(propietarioModel);
    }

    public boolean actualizar(Propietario propietarioModel) throws SQLException {
        return propietarioDAO.ActualizarPropietario(propietarioModel);
    }

        public boolean eliminar(String cedula) throws SQLException {
        return propietarioDAO.EliminarPropietario(cedula);
    }

    public Propietario consultarCedula(String cedula) throws SQLException {
        return propietarioDAO.ConsultarPropietario(cedula);
    }

    public List<Propietario> listarTodos() throws SQLException {
        return propietarioDAO.listarPropietario();
    }
}
