/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

import java.time.LocalDate;

/**
 *
 * @author Asus
 */
public abstract class Contrato {
    
    protected int codigo;
    protected String descripcion;
    protected int modalidadComercializacion; //venta-alquiler
    protected LocalDate fechaCreacion;
    protected LocalDate fechaExpiracion;
    protected double valor;
    protected int codigoInmueble;
    protected int cedulaAgente;

    public Contrato() {
    }

    public Contrato(int cod, String descripcion, int modo, LocalDate fechaCreacion, LocalDate fechaExpiracion, double valor, int codInmueble, int cedulaAgente) {
        
        this.codigo = cod;
        this.descripcion = descripcion;
        this.modalidadComercializacion = modo;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.valor = valor;
        this.codigoInmueble = codInmueble;
        this.cedulaAgente = cedulaAgente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getModalidadComercializacion() {
        return modalidadComercializacion;
    }

    public void setModalidadComercializacion(int modalidadComercializacion) {
        this.modalidadComercializacion = modalidadComercializacion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCodigoInmueble() {
        return codigoInmueble;
    }

    public void setCodigoInmueble(int codigoInmueble) {
        this.codigoInmueble = codigoInmueble;
    }

    public int getCedulaAgente() {
        return cedulaAgente;
    }

    public void setCedulaAgente(int cedulaAgente) {
        this.cedulaAgente = cedulaAgente;
    }
    
    
    
}
