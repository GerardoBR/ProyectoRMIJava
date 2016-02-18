package rmi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Gerardo
 */
public class Conexion {
    Connection conectar = null;
    
    public Connection conexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/Calificaciones","root","pablo");
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return conectar;
    }
}