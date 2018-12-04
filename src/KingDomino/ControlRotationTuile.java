package KingDomino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ControlRotationTuile implements ActionListener {
    private ModelTest model;
    private FenetreTest fenetre;
    private Domino dominoSelect;
    private Tuile tuile1;
    private Tuile tuile2;

    public ControlRotationTuile(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        dominoSelect = model.getDominoSelect();
        int rot = 0;
        switch (model.getRotDominoSelect()){
            case 0:
                rot=90;
                dominoSelect.setTuileNord(dominoSelect.getTuileOuest());
                dominoSelect.setTuileSud(dominoSelect.getTuileEst());
            break;
            case 90:
                rot=180;
                dominoSelect.setTuileEst(dominoSelect.getTuileNord());
                dominoSelect.setTuileOuest(dominoSelect.getTuileSud());
            break;
            case 180:
                rot=270;
                dominoSelect.setTuileNord(dominoSelect.getTuileOuest());
                dominoSelect.setTuileSud(dominoSelect.getTuileEst());
            break;
            case 270:
                rot=0;
                dominoSelect.setTuileEst(dominoSelect.getTuileNord());
                dominoSelect.setTuileOuest(dominoSelect.getTuileSud());
            break;
        }






        try {
            fenetre.tournerTuileSelect(rot, Integer.valueOf(e.getActionCommand()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
