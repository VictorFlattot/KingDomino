package Control;

import Vues.FenetreTest;
import Model.Domino;
import Model.ModelTest;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlCaseRoyaume implements ActionListener {
    private ModelTest model;
    private FenetreTest fenetre;
    private boolean test;

    public ControlCaseRoyaume(ModelTest modelTest){
        this.model = modelTest;
        fenetre = new FenetreTest();
        test = true;
    }


    public ControlCaseRoyaume(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Domino domino = model.getDominoSelect();
        int x = Integer.valueOf(e.getActionCommand().split("/")[0]);
        int y = Integer.valueOf(e.getActionCommand().split("/")[1]);
        int x2= x;
        int y2= y;

        int rotation = domino.getRotation();
        if (rotation==0 || rotation==180) y2+=1;
        if (rotation==90 || rotation==270) x2-=1;
        System.out.println(x+" "+y+" "+x2+" "+y2);
        if(model.addDominoRoyaume(domino, model.getJoueurActuel().getId(), x, y, x2, y2)){



                if (test){
                    model.changementJoueur();
                }else{

                    try {
                        fenetre.nouvelleSelectionDomino();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    fenetre.bloquerToutRoyaumes(true);
                    if (model.getNbTour()<model.getNbTourMax()){
                        fenetre.setActionListenerTuileCentreAChoisir();
                        fenetre.boutonTour.setEnabled(false);
                    }else{
                        model.changementJoueur();
                        try {
                            fenetre.changementJoueur();
                            fenetre.changementTour();
                            fenetre.unTruc();
                            System.out.println("ici");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                    }
                    try {
                        fenetre.updateAllRoyaume();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }



        }else{
            JOptionPane.showMessageDialog(fenetre.getjFrame(),"Vous ne pouvez pas placer ce domino ici","Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
