/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

import java.time.LocalDate;
import java.util.List; // Importar List

import lombok.Data;

/**
 * @author Asus
 */
@Data
public class Propietario {

    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private LocalDate fechaNacimiento;

//    private String correo;
//    private LocalDate fechaExpDoc;
//    private List<String> telefonos;

}