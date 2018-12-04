package old;

import javax.swing.*;

public class DominoFen {
    int numDomino;
    String chemin;
    JButton bouton1;
    Fenetre fen;
    public DominoFen(int noDomino, Fenetre fen){
        this.fen = fen;
        numDomino = noDomino;
        chemin = donneCheminDomino(noDomino);
        bouton1 = new JButton();
        Icon icon = new ImageIcon(donneCheminDomino(noDomino));
        ControlBouton listener = new ControlBouton(fen);
        bouton1.addActionListener(listener);
        bouton1.setActionCommand(""+noDomino);
        bouton1.setIcon(icon);
    }

    public String donneCheminDomino(int numeroDomino){
        String retour = "images/";
        if(numeroDomino<10){
            retour += "0"+numeroDomino+".png";
            System.out.println(retour);
        }else {
            retour += numeroDomino + ".png";
            System.out.println(retour);
        }
        return retour;
    }

}
