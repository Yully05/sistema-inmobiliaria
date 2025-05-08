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
public class AgenteComercial {
    
    private String cedula;
    private String login;
    private String contraseña;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String correo;
    private String celular;
    private LocalDate fechaNacimiento;
    private LocalDate fechaExpDoc;

    public AgenteComercial() {
    }

    public AgenteComercial(String cedula, String login, String contraseña, String nombres, String apellidos, String direccion, String correo, String celular, LocalDate fechaNacimiento, LocalDate fechaExpDoc) {
        this.cedula = cedula;
        this.login = login;
        this.contraseña = contraseña;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.correo = correo;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaExpDoc = fechaExpDoc;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
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
