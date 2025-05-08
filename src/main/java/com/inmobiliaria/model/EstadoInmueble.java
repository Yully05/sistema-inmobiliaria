/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

/**
 *
 * @author Asus
 */
public class EstadoInmueble {
    
    private int id;
    private String estadoInmueble; //vendido,disponible

    public EstadoInmueble() {
    }

    public EstadoInmueble(int id, String estadoInmueble) {
        this.id = id;
        this.estadoInmueble = estadoInmueble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstadoInmueble() {
        return estadoInmueble;
    }

    public void setEstadoInmueble(String estadoInmueble) {
        this.estadoInmueble = estadoInmueble;
    }
    
    
    
}
