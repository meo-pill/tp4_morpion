package com.morpion;

import com.morpion.game.Morpion;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Instanciation du serveur
 * 
 * @author @meo-pill
 * @see com.morpion.game.Morpion
 */
public class Server {
    public Server() throws RemoteException {
        System.out.println("serveur crée");
        try {
            LocateRegistry.createRegistry(1099);
            Morpion morpion = new Morpion();
            Naming.rebind("rmi:///Morpion", morpion);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Serveur prêt");
        }
    }

    
    /** 
     * @param args
     * @throws RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        System.out.println("creation du serveur");
        new Server();
        System.out.println("Serveur arrêté");
    }
}
