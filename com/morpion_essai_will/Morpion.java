import java.rmi.*;
import java.rmi.server.*;

public class Morpion extends UnicastRemoteObject implements MorpionInterface {
    char[] jeu = new char[9];

    public Morpion() throws RemoteException {
        super();
        for (int i = 0; i < 9; i++) {
            jeu[i] = ' ';
        }
    }

    public void jouer(int i, char c) {
        jeu[i] = c;
    }

    // Retourne vrai si le joueur à gagné
    public boolean gagne(char c) {
        // horizontal
        if (jeu[0] == c && jeu[1] == c && jeu[2] == c) {
            return true;
        }
        if (jeu[3] == c && jeu[4] == c && jeu[5] == c) {
            return true;
        }
        if (jeu[6] == c && jeu[7] == c && jeu[8] == c) {
            return true;
        }
        // diagonal
        if (jeu[0] == c && jeu[4] == c && jeu[8] == c) {
            return true;
        }
        if (jeu[2] == c && jeu[4] == c && jeu[6] == c) {
            return true;
        }
        // vertical
        if (jeu[0] == c && jeu[3] == c && jeu[6] == c) {
            return true;
        }
        if (jeu[1] == c && jeu[4] == c && jeu[7] == c) {
            return true;
        }
        if (jeu[2] == c && jeu[5] == c && jeu[8] == c) {
            return true;
        }
        return false;
    }
}
