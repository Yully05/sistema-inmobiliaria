/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

import lombok.Data;

/**
 *
 * @author Asus
 */
@Data
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

}
