/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

/**
 *
 * @author Asus
 */
public class InmueblesPropietario extends Inmueble {
    
    private int cedulaPropietario;

    public InmueblesPropietario() {
    }

    public InmueblesPropietario(int cedula, int cod, String descripcion, double precio, String direccion, String ciudad, String depto,
                                double tamaño, int cant_baños, int tipo, int estado, int modo) {
        
        super(cod, descripcion, precio, direccion, ciudad, depto, tamaño, cant_baños, tipo, estado, modo);
        this.cedulaPropietario = cedula;
    }

    public int getCedulaPropietario() {
        return cedulaPropietario;
    }

    public void setCedulaPropietario(int cedulaPropietario) {
        this.cedulaPropietario = cedulaPropietario;
    }
    
    
}
