package com.morpion.game;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MrpInterface extends Remote{

    /**
     * Permet a un joueur de placer son simbole sur une case, seulement si c'est son tour et que la case est libre
     * Si le mouvent est gagant, la partie est terminé
     * 
     * @param id_joueur l'identifiant du joueur qui joue
     * @param id_case l'index de la case sur laquelle le joueur joue
     * @return vrai si le mouvement a été effectué faux sinon
     * @throws RemoteException -
     */
    public boolean joueTour(int id_joueur, int id_case) throws RemoteException;

    /**
     * Permet de récupérer l'état de la partie
     * 
     * @return l'état de la partie
     * @throws RemoteException -
     */
    public MrpDonnee recupDonnee() throws RemoteException;

    /**
     * Permet a un joueur de se connecter a la partie en tant que X
     * Si les deux joueurs sont connecté, la partie commence
     * Retourne l'identifiant du joueur dans l'intervale [0, 999]
     * 
     * @return l'identifiant du joueur, -1 si la place est déjà prise
     * @throws RemoteException -
     */
    public int connexion_X() throws RemoteException;

    /**
     * Permet a un joueur de se connecter a la partie en tant que O
     * Si les deux joueurs sont connecté, la partie commence
     * Retourne l'identifiant du joueur dans l'intervale [1000, 1999]
     * 
     * @return l'identifiant du joueur, -1 si la place est déjà prise
     * @throws RemoteException -
     */
    public int connexion_O() throws RemoteException;

    /**
     * Permet a un joueur de se déconnecter de la partie
     * Si la partie est en cours, le joueur perd la partie et la partie est terminé
     * Si la partie est en attente, la place est libéré
     * 
     * @param id_joueur l'identifiant du joueur qui se déconnecte
     * @throws RemoteException -
     */
    public void deconnexion(int id_joueur) throws RemoteException;
    
}
