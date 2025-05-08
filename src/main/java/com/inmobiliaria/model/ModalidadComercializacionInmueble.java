/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

/**
 *
 * @author Asus
 */
public class ModalidadComercializacionInmueble {
    
    private int id;
    private String modalidadComercializacion; // en venta-alquiler

    public ModalidadComercializacionInmueble() {
    }

    public ModalidadComercializacionInmueble(int id, String modalidadComercializacion) {
        this.id = id;
        this.modalidadComercializacion = modalidadComercializacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModalidadComercializacion() {
        return modalidadComercializacion;
    }

    public void setModalidadComercializacion(String modalidadComercializacion) {
        this.modalidadComercializacion = modalidadComercializacion;
    }
    
    
    
}
