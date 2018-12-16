package Control;

import Model.Domino;
import Vues.FenetreTest;
import Model.ModelTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlRotationTuile implements ActionListener {
    private ModelTest model;
    private FenetreTest fenetre;

    public ControlRotationTuile(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        Domino dominoSelect = model.getDominoSelect();
        int rot = 0;
        switch (model.getRotDominoSelect()){
            case 0:
                rot=90;
                model.setRotDominoSelect(rot);
                dominoSelect.setTuileNord(dominoSelect.getTuileOuest());
                dominoSelect.setTuileSud(dominoSelect.getTuileEst());
            break;
            case 90:
                rot=180;
                model.setRotDominoSelect(rot);
                dominoSelect.setTuileEst(dominoSelect.getTuileNord());
                dominoSelect.setTuileOuest(dominoSelect.getTuileSud());
            break;
            case 180:
                rot=270;
                model.setRotDominoSelect(rot);
                dominoSelect.setTuileNord(dominoSelect.getTuileOuest());
                dominoSelect.setTuileSud(dominoSelect.getTuileEst());
            break;
            case 270:
                rot=0;
                dominoSelect.setTuileEst(dominoSelect.getTuileNord());
                dominoSelect.setTuileOuest(dominoSelect.getTuileSud());
                model.setRotDominoSelect(rot);
            break;
        }
        try {
            fenetre.tournerTuileSelect(rot, Integer.valueOf(e.getActionCommand()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
