/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

/**
 *
 * @author Asus
 */
public class TipoInmueble {
    
    private int id;
    private String tipoInmueble; // casa-aparatmento-finca-local

    public TipoInmueble() {
    }

    public TipoInmueble(int id, String tipoInmueble) {
        this.id = id;
        this.tipoInmueble = tipoInmueble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoInmueble() {
        return tipoInmueble;
    }

    public void setTipoInmueble(String tipoInmueble) {
        this.tipoInmueble = tipoInmueble;
    }
    
    
}
