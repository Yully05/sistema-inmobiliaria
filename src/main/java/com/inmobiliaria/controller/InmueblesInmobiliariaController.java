package com.inmobiliaria.controller;

import com.inmobiliaria.dao.InmueblesInmobiliariaDAO;
import com.inmobiliaria.model.InmueblesInmobiliaria;

import java.sql.SQLException;
import java.util.List;

public class InmueblesInmobiliariaController {
    private final InmueblesInmobiliariaDAO dao;

    public InmueblesInmobiliariaController() {
        this.dao = new InmueblesInmobiliariaDAO();
    }

    public boolean registrar(InmueblesInmobiliaria inmueble) throws SQLException {
        return dao.registrarInmueble(inmueble);
    }

    public boolean actualizar(InmueblesInmobiliaria inmueble) throws SQLException {
        return dao.actualizarInmueble(inmueble);
    }

    public boolean eliminar(int codigo) throws SQLException {
        return dao.eliminarInmueble(codigo);
    }

    public InmueblesInmobiliaria consultar(int codigo) throws SQLException {
        return dao.consultarInmueble(codigo);
    }

    public List<InmueblesInmobiliaria> listarTodos() throws SQLException {
        return dao.listarInmuebles();
    }
}
