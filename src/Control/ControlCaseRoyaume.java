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
        System.out.println("JA");
        System.out.println(model.getJoueurActuel().getNom());
        System.out.println();
        if(model.addDominoRoyaume(domino, model.getJoueurActuel().getId(), x, y, x2, y2)){


            try {
                fenetre.nouvelleSelectionDomino();
                fenetre.bloquerToutRoyaumes(true);
                fenetre.setActionListenerTuileCentreAChoisir();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else{
                JOptionPane.showMessageDialog(fenetre.getjFrame(),"Vous ne pouvez pas placer ce domino ici","Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
