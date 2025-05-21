package com.inmobiliaria.dao;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public class Conexion {

    private static final String url = "jdbc:postgresql://ep-silent-waterfall-a4zakeb5-pooler.us-east-1.aws.neon.tech/neondb?sslmode=require";
    private static final String user = "neondb_owner";
    private static final String password = "npg_tbvyf2JkHO4z";
    private static boolean mensaje = false;

    public Connection establecerConexion() {
        Connection conectar = null;

        try {
            conectar = DriverManager.getConnection(url, user, password);
            if (!mensaje) {
                System.out.println("Se conecto correctamente a la base de datos");
                mensaje = true; // se marca como mostrado
            }
        } catch (HeadlessException | SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.toString());
        }
        return conectar;
    }
}
