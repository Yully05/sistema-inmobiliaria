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
public class InmueblesInmobiliaria extends Inmueble {
    
    private LocalDate fechaAdquisicion;
    private double costo;

    public InmueblesInmobiliaria() {
    }

    public InmueblesInmobiliaria(LocalDate fecha, double costo, int cod, String descripcion, double precio, String dir, String ciudad, String depto, 
                                  double tamaño, int cant_baños, int tipo, int estado, int modo) {
        
        super(cod, descripcion, precio, dir, ciudad, depto, tamaño, cant_baños, tipo, estado, modo);
        this.fechaAdquisicion = fecha;
        this.costo = costo;
    }

    public LocalDate getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(LocalDate fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
    
}
