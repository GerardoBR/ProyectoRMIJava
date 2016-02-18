/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gerardo
 */
public class ObjetoRemoto  extends UnicastRemoteObject implements InterfazRemota{
    
    Conexion cc = new Conexion();
    Connection conectar = cc.conexion();
    ResultSet rs;
    int idAlumno = 0;
    
    public ObjetoRemoto()throws RemoteException{
        
    }

    
    public String[][] consultar(String matricula, String cuatri, String parcial, String orden) throws RemoteException {
        String[][] materias = new String[2][5];
        String ordenM = null;
        System.out.println("Se consulto materias");
        if(orden.equals("Nombre de materia")){
            ordenM = " nombreMateria ASC;";
        }
        if(orden.equals("Calificacion")){
            ordenM = "calificacion ASC;";;
        }
        
        try{
            String consulta1="SELECT idAlumno FROM alumno WHERE matricula = '"+matricula+"' AND tipoExamen = '"+parcial+"' AND cuatrimestre = '"+cuatri+"'";
            
            Statement sentencia=conectar.createStatement();
            rs=sentencia.executeQuery(consulta1);
            while(rs.next()){
                idAlumno = Integer.parseInt(rs.getString(1));
            }                
            
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        try{
            String consulta2="SELECT claveMateria, nombreMateria, calificacion FROM materias WHERE Alumno_idAlumno = '"+idAlumno+"'  ORDER BY "+ordenM+" ;";
            Statement sentencia=conectar.createStatement();
            rs=sentencia.executeQuery(consulta2);
                System.out.println("Se consulto promedio");
            int rowSize = 0;
            try {
                rs.last();
                rowSize = rs.getRow();
                rs.beforeFirst();
            }
            catch(Exception ex) {

            }
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnSize = rsmd.getColumnCount();
            
            materias = new String[rowSize][columnSize];
            int i =0;
            while(rs.next() && i < rowSize)
            {
                for(int j=0;j<columnSize;j++){
                    materias[i][j] = rs.getString(j+1);

                }
                i++;                    
            }
            
            return materias;
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        
        finally{return materias;}
    }
    

    
    
    
    
    
    @Override
    public double obtenerPromedio(String matricula, String cuatri, String parcial) throws RemoteException {
        double promedio = 0;
        try{
            String consulta="SELECT idAlumno FROM alumno WHERE matricula = '"+matricula+"'"
                    + "AND tipoExamen = '"+parcial+"' AND cuatrimestre = '"+cuatri+"';";
            Statement sentencia=conectar.createStatement();
            rs=sentencia.executeQuery(consulta);
            while(rs.next()){
                idAlumno = Integer.parseInt(rs.getString(1));
            }    
                                 
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            String consulta2="SELECT ROUND(SUM(calificacion)/6,2) FROM materias where Alumno_idAlumno = '"+idAlumno+"';";
            Statement sentencia=conectar.createStatement();
            rs=sentencia.executeQuery(consulta2);
            while(rs.next()){
                promedio = (double) Double.parseDouble(rs.getString(1));
            }
            return promedio;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{return promedio;}
    }
    
    
    
}
