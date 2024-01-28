import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MorpionGUI extends JFrame {
    public MorpionGUI() {
        super("Morpion");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font f = new Font("Liberation Serif", Font.PLAIN, 18);
        UIManager.put("Button.font", f);
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
        }
        ;

        JPanel jp = new JPanel(new GridLayout(3, 3));

        JButton b1 = new JButton(" ");
        JButton b2 = new JButton(" ");
        JButton b3 = new JButton(" ");
        JButton b4 = new JButton(" ");
        JButton b5 = new JButton(" ");
        JButton b6 = new JButton(" ");
        JButton b7 = new JButton(" ");
        JButton b8 = new JButton(" ");
        JButton b9 = new JButton(" ");

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (b1.getText().equals(" ")) // si la case est bien vide
                    b1.setText("X");
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (b2.getText().equals(" ")) // si la case est bien vide
                    b2.setText("X");
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (b3.getText().equals(" ")) // si la case est bien vide
                    b3.setText("X");
            }
        });
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (b4.getText().equals(" ")) // si la case est bien vide
                    b4.setText("X");
            }
        });
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (b5.getText().equals(" ")) // si la case est bien vide
                    b5.setText("X");
            }
        });
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (b6.getText().equals(" ")) // si la case est bien vide
                    b6.setText("X");
            }
        });
        b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (b7.getText().equals(" ")) // si la case est bien vide
                    b7.setText("X");
            }
        });
        b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (b8.getText().equals(" ")) // si la case est bien vide
                    b8.setText("X");
            }
        });
        b9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (b9.getText().equals(" ")) // si la case est bien vide
                    b9.setText("X");
            }
        });

        jp.add(b1);
        jp.add(b2);
        jp.add(b3);
        jp.add(b4);
        jp.add(b5);
        jp.add(b6);
        jp.add(b7);
        jp.add(b8);
        jp.add(b9);

        this.add(jp);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MorpionGUI();
            }
        });
    }
}
