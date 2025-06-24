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
public class ContratoCliente extends Contrato {
    
    private double valor;
    private String nombreFiador;
    private String celularFiador;
    private String cedulaCliente; //de la tabla cliente
 
}
