/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inmobiliaria.model;

import lombok.Data;

/**
 *
 * @author Asus
 */
@Data
public class ContratoPropietario extends Contrato {
    
    private double valor;
    private double porcentajeComision;
    private String cedulaPropietario; //fk propietario
    
}
