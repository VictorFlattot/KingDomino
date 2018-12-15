package Vues;

import Model.Royaume;

import javax.swing.*;
import java.awt.*;

public class JPanelRoyaume extends JPanel {
    private Bouton[][] boutons;


    public JPanelRoyaume(){
        super();
        boutons = new Bouton[5][5];
        setLayout(new GridLayout(5,5));
        setSize(new Dimension(320,320));
    }

    public Bouton[][] getBoutons() {
        return boutons;
    }

    public void setBoutons(Bouton[][] boutons) {
        this.boutons = boutons;
    }

    public void addIconBouton(int x,int y,Icon icon){
        boutons[x][y].setIcon(icon);
    }



}
