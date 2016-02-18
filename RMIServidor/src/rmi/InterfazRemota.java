/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;
import java.rmi.Remote;
import java.sql.ResultSet;

/**
 *
 * @author Gerardo
 */
public interface InterfazRemota extends Remote{
    
    //public String[][] consultar(String matricula, String cuatri, String parcial) throws java.rmi.RemoteException;
    public String[][] consultar(String matricula, String cuatri, String parcial, String orden) throws java.rmi.RemoteException;
    
    public double obtenerPromedio(String matricula, String cuatri, String parcial) throws java.rmi.RemoteException;
    
}
