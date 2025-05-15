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
public abstract class Contrato {
    
    protected int codigo;
    protected String descripcion;
    protected int modalidadComercializacion; //venta-alquiler (de la tabla ModalidadComercializacion
    protected LocalDate fechaCreacion;
    protected LocalDate fechaExpiracion;
    protected double valor;
    protected int codigoInmueble;
    protected String cedulaAgente;
  
}
