package com.morpion;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.morpion.game.MrpDonnee;
import com.morpion.game.MrpInterface;
import com.morpion.game.MrpDonnee.Etat;
import com.morpion.gui.GUI;

/**
 * Classe permettant de gérer le client du jeu Morpion
 * 
 */
public class Client {
    
    private Client() {}

    private static MrpInterface jeu;
    private static MrpDonnee jeuDonnee;
    private static int identifiant_client = -1;

    private static GUI gui;

    /** 
     * action pour cliquer sur une case de la grille
     */
    public static boolean boutonGrilleCliqer(int index) {
        try {
            return jeu.joueTour(identifiant_client, index);
        } catch (RemoteException e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * action pour cliquer sur le bouton X
     */
    public static boolean connecter_en_X(){
        if (identifiant_client != -1) {
            System.out.println("Un identifiant est déjà attribué au joueur");
            return false;
        }

        try {
            identifiant_client = jeu.connexion_X();
            if (identifiant_client != -1) {
                System.out.println("Identifiant du joueur : " + identifiant_client);
                return true;
            }
        } catch (RemoteException e) {
            System.out.println(e);
        }

        System.out.println("Impossible de se connecter en X");
        return false;
    }

    /**
     * action pour cliquer sur le bouton O
     */
    public static boolean connecter_en_O(){
        if (identifiant_client != -1) {
            System.out.println("Un identifiant est déjà attribué au joueur");
            return false;
        }

        try {
            identifiant_client = jeu.connexion_O();
            if (identifiant_client != -1) {
                System.out.println("Identifiant du joueur : " + identifiant_client);
                return true;
            }
        } catch (RemoteException e) {
            System.out.println(e);
        }

        System.out.println("Impossible de se connecter en O");
        return false;
    }

    /**
     * action pour cliquer sur le bouton déconnexion
     */
    public static Boolean deconnecter(){
        try {
            System.out.println("Déconnexion du joueur " + identifiant_client);
            jeu.deconnexion(identifiant_client);
            identifiant_client = -1;
        } catch (RemoteException e) {
            System.out.println(e);
            return false;
        }

        System.out.println("Déconnexion réussie");
        return true;
    }

    /**
     * boucle principale du client
     * @param args
     */
    public static void main(String[] args){
        boolean deconnecter = true;
        boolean jeux_vient_de_commencer = false;

        gui = new GUI();

        try {
            jeu = (MrpInterface) Naming.lookup("rmi:///Morpion");
        } catch (Exception e) {
            System.out.println("Erreur : ");
            System.out.println(e);
            System.exit(-1);
        }

        while (true) {
            try {
                Thread.sleep(200);
            }catch (Exception e) {}

            try {
                jeuDonnee = jeu.recupDonnee();
            } catch (RemoteException e) {
                System.out.println(e);
                System.exit(-1);
            }

            gui.afficheMessage(jeuDonnee.message);
            gui.miseAJourGrille(jeuDonnee.grille);

            if (identifiant_client != -1)
                gui.afficheSymbole("Vous êtes le joueur" + (identifiant_client < 1000 ? 'X' : 'O') +".");
            else
                gui.afficheSymbole("");
            
            if (jeuDonnee.etat_actuel == Etat.EN_COURS) {
                if (jeux_vient_de_commencer){
                    gui.resetGrille();
                    jeux_vient_de_commencer = false;
                }

                ArrayList<Integer> activer = new ArrayList<Integer>();

                if (identifiant_client == -1 ||
                        !((jeuDonnee.tour == 'X' && identifiant_client < 1000) ||
                            (jeuDonnee.tour == 'O' && identifiant_client >= 1000))) {
                    gui.desactiverGrille();
                    continue;
                }

                deconnecter = false;

                for (int i = 0; i < 9; i++) {
                    if (jeuDonnee.grille[i] == ' ') {
                        activer.add(i);
                    }
                }

                if (activer.size() > 0) {
                    gui.activerCases(activer.toArray(new Integer[0]));
                }
            } else {
                jeux_vient_de_commencer = true;
                if (jeuDonnee.etat_actuel == Etat.VICTOIRE) {
                    gui.miseEnValleur(jeuDonnee.combinaison_gagnante);
                }
                if (!deconnecter){
                    identifiant_client = -1;
                    deconnecter = true;
                }
            }
            if (jeuDonnee.X_connecte){
                gui.dispoX(false);
            } else {
                gui.dispoX(true);
            }
            if (jeuDonnee.O_connecte){
                gui.dispoO(false);
            } else {
                gui.dispoO(true);
            }
        }
    }
}
