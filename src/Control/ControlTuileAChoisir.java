package Control;

import Model.ModelTest;
import Vues.FenetreTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ControlTuileAChoisir implements ActionListener {

    private ModelTest model;
    private FenetreTest fenetre;


    public ControlTuileAChoisir(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ok");
        String actionCommandSplit[] = e.getActionCommand().split("/");
        int idDom = Integer.valueOf(actionCommandSplit[0]);
        int posDom = Integer.valueOf(actionCommandSplit[1]);
        fenetre.changeLabelPlayer(posDom);
        try {
            fenetre.changementJoueur();
            model.changementJoueur();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
