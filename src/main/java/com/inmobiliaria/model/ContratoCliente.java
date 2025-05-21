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
    
    private String cedulaCliente; //de la tabla cliente
    private String nombreFiador;
    private String celularFiador;

    public ContratoCliente() {
    }

    public ContratoCliente(String cedulaCliente, String nombreFiador, String celularFiador, int cod, String descripcion, int modo, 
                           LocalDate fechaCreacion, LocalDate fechaExpiracion, double valor, int codInmueble, String cedulaAgente) {
        
        //super(cod, descripcion, modo, fechaCreacion, fechaExpiracion, valor, codInmueble, cedulaAgente);
        this.cedulaCliente = cedulaCliente;
        this.nombreFiador = nombreFiador;
        this.celularFiador = celularFiador;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNombreFiador() {
        return nombreFiador;
    }

    public void setNombreFiador(String nombreFiador) {
        this.nombreFiador = nombreFiador;
    }

    public String getCelularFiador() {
        return celularFiador;
    }

    public void setCelularFiador(String celularFiador) {
        this.celularFiador = celularFiador;
    }
    
    
}
