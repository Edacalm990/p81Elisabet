package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private static final String SERVIDOR = "jdbc:mysql://192.168.56.101:3306/";
    private static  String NOMBRE_BASE_DATOS = "mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "elisabet";
    private static final String PASS = "5989fecha";
//    
//    private static final String SERVIDOR = "jdbc:mysql://localhost:3306/";
//    private static  String NOMBRE_BASE_DATOS = "mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
//    private static final String USER = "root";
//    private static final String PASS = "5989fecha!";

    private static Connection instancia = null;
    
    // Constructor privado no accesible desde otras clases
    private Conexion() {

    }

    public static void setNOMBRE_BASE_DATOS(String NOMBRE_BASE_DATOS) {
        Conexion.NOMBRE_BASE_DATOS = NOMBRE_BASE_DATOS;
    }
    
    

    // Método de clase para acceder a la instancia del objeto Connection
    public static Connection getInstance() {
        // Si el objeto Connection no está creado, se crea
        if (instancia == null) {
            try {

                // Se crea el objeto Connection	
                instancia = DriverManager.getConnection(SERVIDOR + NOMBRE_BASE_DATOS, USER, PASS);

                System.out.println("Conexión realizada con éxito.");

            } catch (SQLException e) {

                // Error en la conexión
                System.out.println("Conexión fallida: " + e.getMessage());
            }
        }
        return instancia;
    }

}