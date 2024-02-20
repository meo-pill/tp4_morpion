package com.morpion.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.morpion.game.MrpDonnee.Etat;

public class Morpion extends UnicastRemoteObject implements MrpInterface{
    
    /** Etat de la partie */
    private MrpDonnee donnee;

    /** indentifiant du joueur X (-1 si aucun) */
    private int identifiant_joueur_X;

    /** indentifiant du joueur O (-1 si aucun) */
    private int identifiant_joueur_O;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean joueTour(int id_joueur, int id_case) throws RemoteException {
        // verification que la partie est en cours
        if (donnee.etat_actuel != Etat.EN_COURS) {
            return false;
        }
        // verification que l'index de la case est valide
        if (id_case < 0 || id_case >= 9 || donnee.grille[id_case] != ' ') {
            return false;
        }
        // verification que c'est le tour du joueur qui joue et joue le tour
        if (id_joueur == identifiant_joueur_X && donnee.tour == 'X') {
            donnee.grille[id_case] = 'X';
        } else if (id_joueur == identifiant_joueur_O && donnee.tour == 'O') {
            donnee.grille[id_case] = 'O';
        } else {
            return false; // ce n'est pas le tour du joueur
        }
        // annonce du coup joué
        System.out.println("joueur " + id_joueur + " joue en " + id_case);
        
        // verification de victoire
        int[] combinaison_gagnante = this.verifVictoire();

        // si la partie est terminé
        if (combinaison_gagnante != null) {
            this.deconnexion(identifiant_joueur_X);
            this.deconnexion(identifiant_joueur_O);
            if (combinaison_gagnante[0] == -1) {
                this.donnee.egalite();
            } else {
                this.donnee.victoire(combinaison_gagnante, donnee.tour);
            }
            return true;
        }

        this.donnee.changer_tour();

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MrpDonnee recupDonnee() throws RemoteException {
        return this.donnee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int connexion_X() throws RemoteException {
        if (this.identifiant_joueur_X != -1) {
            return -1;
        }
        this.identifiant_joueur_X = (int) (Math.random() * 1000);
        this.joueurConnecte();
        this.donnee.X_connecte = true;
        return this.identifiant_joueur_X;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int connexion_O() throws RemoteException {
        if (this.identifiant_joueur_O != -1) {
            return -1;
        }
        this.identifiant_joueur_O = (int) (Math.random() * 1000) + 1000;
        this.joueurConnecte();
        this.donnee.O_connecte = true;
        return this.identifiant_joueur_O;
    }

    /**
     * Vérifie si un autre joueur est connecté et lance la partie si c'est le cas
     */
    private void joueurConnecte() {
        if (this.donnee.etat_actuel != Etat.EN_COURS) {
            this.donnee.attente();
        }

        if (this.identifiant_joueur_X != -1 && this.identifiant_joueur_O != -1) {
            this.donnee.commencer();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deconnexion(int id_joueur) throws RemoteException {
        System.out.println("deconnexion joueur " + id_joueur);

        if (this.identifiant_joueur_X == id_joueur) {
            this.identifiant_joueur_X = -1;
            this.donnee.X_connecte = false;
            this.donnee.victoire(new int[]{-1}, 'O');
            this.donnee.etat_actuel = Etat.ATTENTE;
        } else if (this.identifiant_joueur_O == id_joueur) {
            this.identifiant_joueur_O = -1;
            this.donnee.O_connecte = false;
            this.donnee.victoire(new int[]{-1}, 'X');
            this.donnee.etat_actuel = Etat.ATTENTE;
        }
        return;
    }

    /** 
     * Renvoie la combinaison gagnante si il y en a une, -1 si égalité, null sinon
     */
    private int[] verifVictoire() {
        // verification des lignes
        for (int i = 0; i < 9; i += 3) {
            if (donnee.grille[i] != ' ' && donnee.grille[i] == donnee.grille[i + 1] && donnee.grille[i] == donnee.grille[i + 2]) {
                return new int[]{i, i + 1, i + 2};
            }
        }
        // verification des colonnes
        for (int i = 0; i < 3; i++) {
            if (donnee.grille[i] != ' ' && donnee.grille[i] == donnee.grille[i + 3] && donnee.grille[i] == donnee.grille[i + 6]) {
                return new int[]{i, i + 3, i + 6};
            }
        }
        // verification des diagonales
        if (donnee.grille[0] != ' ' && donnee.grille[0] == donnee.grille[4] && donnee.grille[0] == donnee.grille[8]) {
            return new int[]{0, 4, 8};
        }
        if (donnee.grille[2] != ' ' && donnee.grille[2] == donnee.grille[4] && donnee.grille[2] == donnee.grille[6]) {
            return new int[]{2, 4, 6};
        }
        // verification de l'égalité
        for (int i = 0; i < 9; i++) {
            if (donnee.grille[i] == ' ') {
                return null;
            }
        }
        // atteint si la grille est pleine et qu'il n'y a pas de combinaison gagnante
        return new int[]{-1};
    }

    /**
     * Constructeur
     * crée une nouvelle partie avec les données par défaut
     * 
     * @throws RemoteException -
     */
    public Morpion() throws RemoteException {
        this.donnee = new MrpDonnee();
        this.identifiant_joueur_X = -1;
        this.identifiant_joueur_O = -1;
    }
}
