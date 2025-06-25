/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

import lombok.Data; // Añadir Lombok Data
import lombok.EqualsAndHashCode;

/**
 *
 * @author Asus
 */
@Data // Usar @Data para que Lombok genere el constructor con super() y getters/setters
@EqualsAndHashCode(callSuper = false)
public class InmueblesPropietario extends Inmueble {

    private String cedulaPropietario;

    // Lombok generará un constructor con todos los campos (propios y heredados)
    // y los getters/setters. No es necesario escribirlos manualmente.
}