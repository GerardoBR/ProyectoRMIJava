/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;

/**
 *
 * @author Gerardo
 */
public class ObjetoRemoto{
    String[][] materias = null;
    double promedio;
    
    public void obtenerMaterias(String matricula, String cuatri, String parcial,String orden) {
        try {
            
            Registry registro = LocateRegistry.getRegistry("127.0.0.1",7777);
            InterfazRemota miInterfaz = (InterfazRemota) registro.lookup("rmiRemoto");

            materias = miInterfaz.consultar(matricula, cuatri, parcial,orden);
            promedio = miInterfaz.obtenerPromedio(matricula, cuatri, parcial);

                System.out.println("Clave Materia          Materia                   Calificacion");
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        System.out.print(materias[i][j]+"               ");
                    }
                    System.out.println("");
                }
                
                System.out.println("");
                System.out.println(promedio);

            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public String[][] materiasI(String matricula, String cuatri, String parcial,String orden) {
        
        try {
            
            Registry registro = LocateRegistry.getRegistry("127.0.0.1",7777);
            InterfazRemota miInterfaz = (InterfazRemota) registro.lookup("rmiRemoto");

            materias = miInterfaz.consultar(matricula, cuatri, parcial,orden);
                
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return materias;
    }   
    
    
    
    public double promedioI(String matricula, String cuatri, String parcial,String orden) {
        try {
            
            Registry registro = LocateRegistry.getRegistry("127.0.0.1",7777);
            InterfazRemota miInterfaz = (InterfazRemota) registro.lookup("rmiRemoto");

            promedio = miInterfaz.obtenerPromedio(matricula, cuatri, parcial);

                
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return promedio;
    }
}
