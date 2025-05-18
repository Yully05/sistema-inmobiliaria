package com.inmobiliaria.main;

import com.inmobiliaria.dao.Conexion;
import com.inmobiliaria.view.Dashboard;
import com.inmobiliaria.view.Login;
import java.sql.Connection;

/**
 *
 * @author Asus
 */

public class Main {

    public static void main(String[] args) {
        
        //comprobar conexion
        //Conexion conectar = new Conexion();
        //Connection establecerConexion = conectar.establecerConexion();
        
        //mostrar interfaz  login
        java.awt.EventQueue.invokeLater(() -> {
            new Dashboard().setVisible(true);
            //new Login().setVisible(true);
        });
        
    }
}
