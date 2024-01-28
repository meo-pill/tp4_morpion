import java.rmi.*;

public class MorpionClient {
    public static void main(String[] args) {
        try {
            boolean stop = false;
            char forme = 'O'; // a choisir avec interface ?
            MorpionInterface rev = (MorpionInterface) Naming.lookup("rmi://localhost:1099/MyReverse");
            // Tant que pas gagné
            while (!stop) {
                stop = rev.gagne('O');
            }
            // si c'est ton tour
            // tu joue

        } catch (Exception e) {
            System.out.println("Erreur d'accès à l'objet distant.");
            System.out.println(e.toString());
        }
    }
}
