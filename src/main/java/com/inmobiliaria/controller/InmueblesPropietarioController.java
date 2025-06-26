package com.inmobiliaria.controller;

import com.inmobiliaria.dao.InmueblesPropietarioDAO;
import com.inmobiliaria.model.InmueblesPropietario;
import java.sql.SQLException;
import java.util.List;

public class InmueblesPropietarioController {
    private final InmueblesPropietarioDAO inmuebleDAO;

    public InmueblesPropietarioController() {
        inmuebleDAO = new InmueblesPropietarioDAO();
    }

    public boolean registrar(InmueblesPropietario inmueble) throws SQLException {
        return inmuebleDAO.registrarInmueble(inmueble);
    }

    public boolean actualizar(InmueblesPropietario inmueble) throws SQLException {
        return inmuebleDAO.actualizarInmueble(inmueble);
    }

    public boolean eliminar(int codigo) throws SQLException {
        return inmuebleDAO.eliminarInmueble(codigo);
    }

    public InmueblesPropietario consultar(int codigo) throws SQLException {
        return inmuebleDAO.consultarInmueble(codigo);
    }

    public List<InmueblesPropietario> listarTodos() throws SQLException {
        return inmuebleDAO.listarInmuebles();
    }
}
