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
public class Propietario {
    
    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String correo;
    private LocalDate fechaNacimiento;
    private LocalDate fechaExpDoc;

    public Propietario() {
    }

    public Propietario(String cedula, String nombres, String apellidos, String direccion, String correo, LocalDate fechaNacimiento, LocalDate fechaExpDoc) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaExpDoc = fechaExpDoc;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaExpDoc() {
        return fechaExpDoc;
    }

    public void setFechaExpDoc(LocalDate fechaExpDoc) {
        this.fechaExpDoc = fechaExpDoc;
    }
    
    
}
