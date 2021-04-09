/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongnv.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author redra
 */
public class Server {
    public static void main(String[] args) throws Exception {
        try {
            Registry reg = LocateRegistry.createRegistry(25585);
            
            ImplementServer is = new ImplementServer() {};
            reg.rebind("fhc", is);
            
            System.out.println("Server is Online...");
            while(true){
                Thread.sleep(100);
            }
        } catch (RemoteException ex) {}
    }
}
