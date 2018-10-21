package KingDomino;

import javax.swing.*;
import java.awt.*;

public class DominoFen {
    Tuile tuile1;
    Tuile tuile2;
    JButton bouton1;
    JButton bouton2;
    public DominoFen(Tuile tuile1, Tuile tuile2){
        this.tuile1 = tuile1;
        this.tuile2 = tuile2;
        bouton1 = new JButton();
        bouton1.setBackground(donneCouleurTerrain(tuile1));
        bouton2=new JButton();
        bouton2.setBackground(donneCouleurTerrain(tuile2));
    }

    public Color donneCouleurTerrain(Tuile tuile) {

        switch (tuile.getTerrain()) {
            case CHAMPS:
                return Color.green;
            case LAC:
                return Color.blue;
            case MONTAGNES:
                return Color.gray;
            case MARAIS:
                return Color.red;
            case PRAIRIE:
                return Color.orange;
            case FORET:
                return Color.pink;
            case DEPART:
                return Color.black;
        }
        return Color.white;
    }
}
