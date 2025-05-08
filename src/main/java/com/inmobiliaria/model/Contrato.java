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
    protected int modalidadComercializacion; //venta-alquiler (de la tabla ModalidadComercializacion
    protected LocalDate fechaCreacion;
    protected LocalDate fechaExpiracion;
    protected double valor;
    protected int codigoInmueble; //tabla inmuebles
    protected String cedulaAgente; //tabla agente_comercial

    public Contrato() {
    }

    public Contrato(int cod, String descripcion, int codModalidad, LocalDate fechaCreacion, LocalDate fechaExpiracion, double valor, int codInmueble, String cedulaAgente) {
        
        this.codigo = cod;
        this.descripcion = descripcion;
        this.modalidadComercializacion = codModalidad;
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

    public void setModalidadComercializacion(int codModalidadComercializacion) {
        this.modalidadComercializacion = codModalidadComercializacion;
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

    public String getCedulaAgente() {
        return cedulaAgente;
    }

    public void setCedulaAgente(String cedulaAgente) {
        this.cedulaAgente = cedulaAgente;
    }
    
    
    
}
