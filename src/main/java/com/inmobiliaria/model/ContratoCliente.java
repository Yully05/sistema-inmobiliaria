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
public class ContratoCliente extends Contrato {
    
    private int cedulaCliente;
    private String nombreFiador;
    private int celularFiador;

    public ContratoCliente() {
    }

    public ContratoCliente(int cedulaCliente, String nombreFiador, int celularFiador, int cod, String descripcion, int modo, 
                           LocalDate fechaCreacion, LocalDate fechaExpiracion, double valor, int codInmueble, int cedulaAgente) {
        
        super(cod, descripcion, modo, fechaCreacion, fechaExpiracion, valor, codInmueble, cedulaAgente);
        this.cedulaCliente = cedulaCliente;
        this.nombreFiador = nombreFiador;
        this.celularFiador = celularFiador;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNombreFiador() {
        return nombreFiador;
    }

    public void setNombreFiador(String nombreFiador) {
        this.nombreFiador = nombreFiador;
    }

    public int getCelularFiador() {
        return celularFiador;
    }

    public void setCelularFiador(int celularFiador) {
        this.celularFiador = celularFiador;
    }
    
    
}
