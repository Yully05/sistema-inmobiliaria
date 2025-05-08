/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

/**
 *
 * @author Asus
 */
public abstract class Inmueble {
    
    protected int codigo;
    protected String descripcion;
    protected double precio;
    protected String direccion;
    protected String ciudad;
    protected String departamento;
    protected double tamaño;
    protected int cant_baños;
    //foto
    protected int tipoInmueble;
    protected int estadoInmueble;
    protected int modalidadComercializacion;

    public Inmueble() {
    }

    public Inmueble(int cod, String descripcion, double precio, String direccion, String ciudad, String depto, double tamaño, int cant_baños, int tipo, int estado, int modo) {
        
        this.codigo = cod;
        this.descripcion = descripcion;
        this.precio = precio;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.departamento = depto;
        this.tamaño = tamaño;
        this.cant_baños = cant_baños;
        this.tipoInmueble = tipo; //casa, apartamento, finca, local, tabla tipo inmueble
        this.estadoInmueble = estado; //vendido-disponible, tabla estado inmueble
        this.modalidadComercializacion = modo; //venta-alquiler, tabla modalidad comercializacion
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getTamaño() {
        return tamaño;
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public int getCant_baños() {
        return cant_baños;
    }

    public void setCant_baños(int cant_baños) {
        this.cant_baños = cant_baños;
    }

    public int getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(int tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }

    public int getEstadoInmueble() {
        return estadoInmueble;
    }

    public void setEstadoInmueble(int estadoInmueble) {
        this.estadoInmueble = estadoInmueble;
    }

    public int getModalidadComercializacion() {
        return modalidadComercializacion;
    }

    public void setModalidadComercializacion(int modalidadComercializacion) {
        this.modalidadComercializacion = modalidadComercializacion;
    }
    
    
    
}
