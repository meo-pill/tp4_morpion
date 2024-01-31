package com.morpion.gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Classe permettant de gérer les boites de dialogues
 * @author Mewen Puren
 */
public class BoiteDialoge extends JOptionPane{
    
    /** Fenêtre parent contenant la boite */
    private JFrame frameParent;
    /** Code de la boite de dialogue */
    private int code;
    /** Titre de la boite de dialogue */
    private String titre;
    /** Message de la boite de dialogue */
    private String message;

    /**
     * Constructeur de la boite de dialogue
     * @param parent Fenêtre parent contenant la boite
     */
    public BoiteDialoge(JFrame parent){
        this.frameParent = parent;
        this.code = 0;
        this.titre = null;
        this.message = null;
    }

    /**
     * Permet de mettre à jour les informations de la boite de dialogue
     * @param titre Titre de la boite de dialogue
     * @param message Message de la boite de dialogue
     * @param code Code de la boite de dialogue
     */
    public void miseDialoge(String titre, String message, int code){
        this.titre = titre;
        this.message = message;
        this.code = code;
    }

    /**
     * Permet d'afficher la boite de dialogue
     */
    public void afficher(int typeMessage){
        if (this.code != 0){
            this.message += "\nCode : " + this.code;
        }
        JOptionPane.showMessageDialog(this.frameParent, this.message, this.titre, typeMessage);
    }

    /**
     * Permet d'afficher la boite de dialogue et de récupérer le choix de l'utilisateur
     * @param option Option de la boite de dialogue
     * @return Choix de l'utilisateur
     */
    public int choix(int option){
        if (this.code != 0){
            this.message += "\nCode : " + this.code;
        }
        return JOptionPane.showConfirmDialog(this.frameParent, this.message, this.titre, option);
    }

    /** 
     * Test de la classe BoiteDialoge
     * @param args -
     */
    public static void main(final String[] args){
        final JFrame frame = new JFrame();

        BoiteDialoge boite = new BoiteDialoge(frame);

        boite.miseDialoge("lol", "Hello World", 0);
        System.out.println("Affichage de la boite de dialogue");
        switch (boite.choix(YES_NO_CANCEL_OPTION)){
            case JOptionPane.YES_OPTION:
                System.out.println("Oui");
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("Non");
                break;
            case JOptionPane.CLOSED_OPTION:
                System.out.println("Fermer");
                break;
        }
    }
}
