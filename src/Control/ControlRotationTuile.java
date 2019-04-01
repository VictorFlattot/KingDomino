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
    private boolean test;

    public ControlRotationTuile(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
        test = false;
    }

    public ControlRotationTuile(ModelTest model) {
        this.model = model;
        this.fenetre = new FenetreTest();
        test = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        Domino dominoSelect = model.getDominoSelect();
        model.rotateTo(((dominoSelect.getRotation()+90)/90)%4);
        try {
            if (!test){
                fenetre.tournerTuileSelect(dominoSelect.getRotation(), Integer.valueOf(e.getActionCommand()));
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
