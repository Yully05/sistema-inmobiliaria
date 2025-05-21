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
public class ContratoPropietario extends Contrato {
    
    private String cedulaPropietario;
    private double porcentajeComision;

    public ContratoPropietario() {
    }

    public ContratoPropietario(String cedula, double comision, int cod, String descripcion, int modo, LocalDate fechaCreacion, LocalDate fechaExpiracion,
                               double valor, int codInmueble, String cedulaAgente) {
        
        //super(cod, descripcion, modo, fechaCreacion, fechaExpiracion, valor, codInmueble, cedulaAgente);
        this.cedulaPropietario = cedula;
        this.porcentajeComision = comision;
    }

    public String getCedulaPropietario() {
        return cedulaPropietario;
    }

    public void setCedulaPropietario(String cedulaPropietario) {
        this.cedulaPropietario = cedulaPropietario;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }
    
    
}
