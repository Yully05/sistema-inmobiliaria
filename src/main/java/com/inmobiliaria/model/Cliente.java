/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Asus
 */
@Data
public class Cliente {
    
    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String correo;
    private LocalDate fechaNacimiento;
    private LocalDate fechaExpDoc;
    private List<String> telefonos;
   
}
