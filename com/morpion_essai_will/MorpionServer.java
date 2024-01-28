import java.rmi.*;
import java.rmi.registry.*;

public class MorpionServer {
    public static void main(String[] args) {
        try {
            System.out.println("Serveur : Construction de l'implementation");

            Morpion rev = new Morpion();

            System.out.println("Objet Reverse lie dans le RMIregistry");

            Registry registry = LocateRegistry.createRegistry(1099);

            Naming.rebind("rmi://localhost:1099/MyReverse", rev);

            System.out.println("Attente des invocations des clients ...");

        } catch (Exception e) {
            System.out.println("Erreur de liaison de l'objet Reverse");
            System.out.println(e.toString());
        }
    } // fin du main
} // fin de la classe