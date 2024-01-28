import java.rmi.Remote;

public interface MorpionInterface extends Remote {
    void jouer(int i, char c);

    boolean gagne(char c);
}