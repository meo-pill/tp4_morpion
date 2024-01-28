package com.morpion.game;

import java.io.Serializable;

public class MrpDonnee implements Serializable{
    
    public enum Etat{
        EN_COURS,
        EGALITE,
        VICTOIRE,
        ATTENTE
    }

    public boolean X_connecte;
    public boolean O_connecte;

    /** X ou O pour différence le jouer devant jouer */
    public char tour;

    /** état de la partie */
    public Etat etat_actuel;

    /** plateau de jeu (stocker sous le forma d'un tableur simple et mis en forme a l'affichage) */
    public char[] grille;

    /** identifiant des case aillant permis la victoire */
    public int[] combinaison_gagnante;

    /**
     * Message affiché sur l'écran du client :
     * - En attente des joueurs...
     * - Au joueur X (ou O) de jouer.
     * - Égalité.
     * - Le joueur X (ou O) a gagné !
     */
    public String message;

    /** passe au tour du joureur suivant */
    void changer_tour(){
        if (tour == 'X') {
            tour = 'O';
        } else {
            tour = 'X';
        }
        this.message = "Au joueur " + tour + " de jouer.";
    }

    /** 
     * met fin a la partie et affiche le message de victoire
     * 
     * @param combinaison_gagnante identifiant des case aillant permis la victoire
     * @param gagnant X ou O (le joueur gagnant)
     */
    void victoire(int[] combinaison_gagnante, char gagnant){
        this.etat_actuel = Etat.VICTOIRE;
        this.combinaison_gagnante = combinaison_gagnante;
        this.message = "Le joueur " + gagnant + " a gagné !";
    }

    /** met fin a la partie et affiche le message d'égalité */
    void egalite(){
        this.etat_actuel = Etat.EGALITE;
        this.message = "Égalité.";
        this.combinaison_gagnante = null;
    }

    /** Commence la partie */
    void commencer(){
        System.out.println("commencer");
        this.etat_actuel = Etat.EN_COURS;
        this.grille = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        this.message = "Au joueur " + tour + " de jouer.";
    }

    /** 
     * attente de la connexion des deux joueurs
     */
    void attente(){
        this.etat_actuel = Etat.ATTENTE;
        this.message = "En attente des joueurs...";
        this.combinaison_gagnante = null;
    }

    /** 
     * initialise les données de la partie et appel attente()
     */
    MrpDonnee(){
        this.X_connecte = false;
        this.O_connecte = false;
        this.tour = 'X';
        this.grille = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
        this.attente();
    }
}
