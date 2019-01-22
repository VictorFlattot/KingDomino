package Control;

import Model.ModelTest;
import Vues.FenetreTest;

import javax.swing.*;
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
        model.showDomDejaChoisi();
        boolean autorisation = true;
        String[] actionCommandSplit = e.getActionCommand().split("/");
        int idDom = Integer.valueOf(actionCommandSplit[0]);
        int posDom = Integer.valueOf(actionCommandSplit[1]);

        for (int i = 0; i < model.getNbDominoCentre(); i++) {
            if (model.isDejaChoisit(i)){
               fenetre.removeControlerBoutonAChoisrCentre(i);
                if (i == posDom) autorisation = false;
            }

        }
        if (model.getNbTour()>1){
            System.out.println("KAAAAAAAAAAAAAA");
            try {
                fenetre.unTruc();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (autorisation){
            fenetre.changeLabelPlayer(posDom);
                try {
                    model.setDominoDejaChoisi(posDom,true);
                    model.changementJoueur();
                fenetre.changementJoueur();
                fenetre.changementTour();
                if (model.getNbTour()!=1) {
                    fenetre.removeAllControlerAChoisirCentre();
                    //fenetre.setActionListenerTuileCentreAPlacer();
                }
                    if (model.getNbTour()>1){
                        System.out.println("KAAAAAAAAAAAAAA");
                        try {
                            fenetre.unTruc();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
            } catch (IOException e1) {}

        }else{
            JOptionPane.showMessageDialog(fenetre.getjFrame(),"Ce domino est déjà résérvé par un autre joueur","Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
