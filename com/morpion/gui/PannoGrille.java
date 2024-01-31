package com.morpion.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import com.morpion.Client;

/**
 * Classe représentant les boutons de la grille de jeu
 * @see JButton
 */
class BoutonGrille extends JButton {
    private int index;

    public BoutonGrille(int index) {
        this.index = index;

        this.addActionListener((ActionEvent e) -> {
            this.setEnabled(false);
            if (Client.boutonGrilleCliqer(this.index)) {
                this.setEnabled(true);
            }
        });
    }
}

/**
 * Grille de morpion contenant les boutons
 * 
 * @see JPanel
 */
class PannoGrille extends JPanel {

    /** Tableau contenant les boutons de la grille */
    private BoutonGrille[] boutons;

    /**
     * Constructeur de la grille
     * Initialise les boutons et les ajoutes à la grille
     * les boutons sont indexés de 0 à 8
     */
    public PannoGrille() {
        boutons = new BoutonGrille[9];
        for (int i = 0; i < 9; i++) {
            boutons[i] = new BoutonGrille(i);
        }
        this.setPreferredSize(new Dimension(GUI.HAUTEUR, GUI.HAUTEUR));

        GridLayout layout = new GridLayout(3, 3);
        layout.setHgap(5);
        layout.setVgap(5);
        this.setLayout(layout);
        for (JButton bouton : boutons) {
            this.add(bouton);
        }
        this.resetGrille();
    }

    /**
     * Permet de réinitialiser la grille
     * Les boutons sont désactivés et leur bordure est réinitialisée
     */
    public void resetGrille() {
        for (BoutonGrille b : boutons) {
            b.setEnabled(false);
            b.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }

    /**
     * Permet de mettre à jour le texte des boutons
     * @param cases Tableau de caractères contenant les caractères à afficher
     */
    public void miseAJourTexte(char[] cases){
        for (int i = 0; i < 9; i++) {
            boutons[i].setText(Character.toString(cases[i]));
        }
    }

    /**
     * Permet de mettre en valeur les boutons
     * @param cases Tableau d'entiers contenant les indices des boutons à mettre en valeur
     */
    public void miseEnValleur(int[] cases){
        for (int index : cases) {
            boutons[index].setBorder(BorderFactory.createLineBorder(Color.RED));
        }
    }

    /**
     * Permet de désactiver les boutons
     */
    public void desactiverGrille(){
        for (BoutonGrille b : boutons) {
            b.setEnabled(false);
        }
    }

    /**
     * Permet d'activer les boutons
     * @param cases Tableau d'entiers contenant les indices des boutons à activer
     */
    public void activerCases(Integer[] cases){
        for (int index : cases) {
            boutons[index].setEnabled(true);
        }
    }
}