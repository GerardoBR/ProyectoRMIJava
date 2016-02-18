/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Gerardo
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(7777);
            registro.rebind("rmiRemoto", new ObjetoRemoto());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
