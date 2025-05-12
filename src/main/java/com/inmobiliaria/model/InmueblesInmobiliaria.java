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
@Data
public class InmueblesInmobiliaria extends Inmueble {
    
    private LocalDate fechaAdquisicion;
    private double costo;
    
}
