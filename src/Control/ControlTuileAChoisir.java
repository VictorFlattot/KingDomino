package Control;

import Model.ModelTest;
import Vues.FenetreTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The type Control tuile a choisir.
 */
public class ControlTuileAChoisir implements ActionListener {

    private ModelTest model;
    private FenetreTest fenetre;


    /**
     * Instantiates a new Control tuile a choisir.
     *
     * @param model   the model
     * @param fenetre the fenetre
     */
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


        if (autorisation){
            try {
                fenetre.changeLabelPlayer(posDom,model.getJoueurActuel().getCouleur().toString() );
                model.setDominoDejaChoisi(posDom,true);
                model.changementJoueur();
                fenetre.changementJoueur();
                fenetre.changementTour();
                if (model.getNbTour()!=1) {
                    fenetre.removeAllControlerAChoisirCentre();
                }
                if (model.getNbTour()>1){
                    try {
                        fenetre.unTruc();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (IOException e1) {
                System.out.println(e1);
            }

        }else{
            JOptionPane.showMessageDialog(fenetre.getjFrame(),"Ce domino est déjà résérvé par un autre joueur","Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
