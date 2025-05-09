/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author Asus
 */
//Esta es una anotacion Lombok, se utiliza para simplificar los getter and setters, constructores y metodos como toString, sobreescribir datos, etc.....
// @Data = a combinar todo en uno, para no tener que escribirlos por aparte..... 
@Data 
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
    
}