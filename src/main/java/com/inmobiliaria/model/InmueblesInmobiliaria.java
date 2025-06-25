package com.inmobiliaria.model;

import java.time.LocalDate;
import lombok.Data; // Añadir Lombok Data
import lombok.EqualsAndHashCode;

/**
 *
 * @author Asus
 */
@Data // Usar @Data para que Lombok genere el constructor con super() y getters/setters
@EqualsAndHashCode(callSuper = false)
public class InmueblesInmobiliaria extends Inmueble {

    private LocalDate fechaAdquisicion;
    private double costo;

    // Lombok generará un constructor con todos los campos (propios y heredados)
    // y los getters/setters. No es necesario escribirlos manualmente.

}