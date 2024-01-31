package com.morpion.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;


import com.morpion.Client;


/**
 * Interface graphique du jeu Morpion
 * 
 */
public class GUI extends JFrame {
    /** Largeur de la grille */
    public static final int LARGEUR = 600;
    /** Hauteur de la grille */
    public static final int HAUTEUR = 400;

    /** Label affichant le message */
    private JLabel messagLabel;
    /** Label affichant le symbole du joueur */
    private JLabel symboleLabel;
    /** Grille du jeu */
    private PannoGrille grille;
    /** Bouton permettant de jouer en X */
    private JButton boutonX;
    /** Bouton permettant de jouer en O */
    private JButton boutonO;

    /**
     * Constructeur de l'interface graphique
     */
    public GUI() {
        super("Morpion");

        this.setResizable(false);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.LINE_AXIS));
        
        // Gestion de la fermeture de la fenêtre
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (QuiterJeu()) {
                    System.exit(0);
                }
            }
        });
        
        // Menu
        Box GaucheBox = new Box(BoxLayout.Y_AXIS);
        GaucheBox.setAlignmentY(CENTER_ALIGNMENT);
        GaucheBox.setPreferredSize(new Dimension(LARGEUR/3, HAUTEUR));

        JLabel titreLabel = new JLabel("<html><h1>Morpion</h1></html>", JLabel.CENTER);
        titreLabel.setAlignmentX(CENTER_ALIGNMENT);
        GaucheBox.add(titreLabel);

        GaucheBox.add(Box.createRigidArea(new Dimension(0, 80)));

        messagLabel = new JLabel("En attente des joueurs", JLabel.CENTER);
        messagLabel.setAlignmentX(CENTER_ALIGNMENT);
        GaucheBox.add(messagLabel);

        symboleLabel = new JLabel("", JLabel.CENTER);
        symboleLabel.setAlignmentX(CENTER_ALIGNMENT);
        GaucheBox.add(symboleLabel);

        GaucheBox.add(Box.createRigidArea(new Dimension(0, 120)));

        this.boutonX = new JButton("Jouer \"X\"");
        this.dispoX(true);
        this.boutonX.setAlignmentX(CENTER_ALIGNMENT);
        GaucheBox.add(this.boutonX);

        GaucheBox.add(Box.createRigidArea(new Dimension(10, 10)));

        this.boutonO = new JButton("Jouer \"O\"");
        this.dispoO(true);
        this.boutonO.setAlignmentX(CENTER_ALIGNMENT);
        GaucheBox.add(this.boutonO);

        // affichage du menu
        this.add(GaucheBox);

        // Grille
        this.grille = new PannoGrille();
        this.grille.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // affichage de la grille
        this.add(this.grille);

        this.pack();
        this.setVisible(true);
    }

    /**
     * Affiche une boite de dialogue d'erreur
     * @param titre Titre de la boite de dialogue
     * @param message Message de la boite de dialogue
     * @param codeDErreur Code d'erreur de la boite de dialogue
     */
    void afficherErreur(String titre, String message, int codeDErreur) {
        BoiteDialoge boite = new BoiteDialoge(this);
        boite.miseDialoge(titre, message, codeDErreur);
        boite.afficher(JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Affiche une boite de dialogue d'information
     * @param titre Titre de la boite de dialogue
     * @param message Message de la boite de dialogue
     */
    void afficher(String titre, String message) {
        BoiteDialoge boite = new BoiteDialoge(this);
        boite.miseDialoge(titre, message, 0);
        boite.afficher(JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Affiche une boite de dialogue de choix
     * @param titre Titre de la boite de dialogue
     * @param message Message de la boite de dialogue
     * @param option Option de la boite de dialogue
     * @return Choix de l'utilisateur
     */
    int choix(String titre, String message, int option) {
        BoiteDialoge boite = new BoiteDialoge(this);
        boite.miseDialoge(titre, message, 0);
        return boite.choix(option);
    }

    /**
     * permet de quitter le jeu
     * @return true si le jeu est quitter, false sinon
     */
    boolean QuiterJeu() {
        int reponse = choix("Morpion", "partie toujours en cour. Quiter ?", JOptionPane.YES_NO_OPTION);

        if (reponse == JOptionPane.YES_OPTION) {
            if (Client.deconnecter()){
                this.afficher("INFO", "Vous avez quitter la partie");
            } else {
                this.afficherErreur("ERREUR", "Impossible de quitter la partie", 0);
            }
        }
        return reponse == 0;
    }

    /**
     * Permet de désactiver la grille
     * il n'est plus possible de cliquer sur les cases
     */
    public void desactiverGrille() {
        this.grille.desactiverGrille();
    }

    /**
     * Permet d'activer la grille
     * il est possible de cliquer sur les cases
     * @param cases Liste des cases à activer
     */
    public void activerCases(Integer[] cases) {
        this.grille.activerCases(cases);
    }

    /**
     * Permet de mettre en valeur les cases
     * @param cases Liste des cases à mettre en valeur
     */
    public void miseEnValleur(int[] cases) {
        this.grille.miseEnValleur(cases);
    }

    /**
     * Permet de mettre à jour la grille
     * @param cases Liste des cases à mettre à jour
     */
    public void miseAJourGrille(char[] cases) {
        this.grille.miseAJourTexte(cases);
    }

    /**
     * Permet d'afficher un message
     * @param message Message à afficher
     */
    public void afficheMessage(String message) {
        this.messagLabel.setText(message);
    }

    public void afficheSymbole(String symbole) {
        this.symboleLabel.setText(symbole);
    }

    /**
     * permet de mettre a zero la grille du jeu et les desactiver les cases
     */
    public void resetGrille() {
        this.grille.resetGrille();
    }

    /**
     * active ou désactive le bouton permettant de jouer en X
     * @param b true pour activer, false pour désactiver
     */
    public void dispoX(boolean b) {
        this.boutonX.setEnabled(b);

        if (b = true) {
            if (this.boutonX.getActionListeners().length == 0) {
                this.boutonX.addActionListener((ActionEvent e) -> Client.connecter_en_X());
            }
            this.boutonX.setText("Jouer \"X\"");
        } else {
            this.boutonX.removeActionListener(null);
            this.boutonX.setText("\"X\" est connecté");
        }
    }

    /**
     * active ou désactive le bouton permettant de jouer en O
     * @param b true pour activer, false pour désactiver
     */
    public void dispoO(boolean b) {
        this.boutonO.setEnabled(b);

        if (b = true) {
            if (this.boutonO.getActionListeners().length == 0) {
                this.boutonO.addActionListener((ActionEvent e) -> Client.connecter_en_O());
            }
            this.boutonO.setText("Jouer \"O\"");
        } else {
            this.boutonO.removeActionListener(null);
            this.boutonO.setText("\"O\" est connecté");
        }
    }
    
}
