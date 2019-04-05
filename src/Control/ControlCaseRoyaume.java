package Control;

import Model.Royaume;
import Vues.FenetreTest;
import Model.Domino;
import Model.ModelTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The type Control case royaume.
 */
public class ControlCaseRoyaume implements ActionListener {

    /**
     * Utilisation du modèle via la classe ModelTest
     *
     * @see ModelTest
     */
    private ModelTest model;

    /**
     * Utilisation de la fenetre via la classe FenetreTest
     *
     * @see FenetreTest
     */
    private FenetreTest fenetre;

    /**
     * Valeur booléenne pour savoir si on est en phase de test ou non
     */
    private boolean test;

    /**
     * Instantiates a new Control case royaume.
     *
     * @param modelTest the model test
     */
    public ControlCaseRoyaume(ModelTest modelTest){
        this.model = modelTest;
        fenetre = new FenetreTest();
        test = true;
    }

    /**
     * Instantiates a new Control case royaume.
     *
     * @param model   the model
     * @param fenetre the fenetre
     */
    public ControlCaseRoyaume(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
    }

    /**
     * Permet de controler la possibilité de placer ou non un domino sur le royaume
     * Si l'action est possible, le domino est ajouté au Royaume
     * Sinon, un message s'affiche informant le joueur que cette action lui est impossible
     *
     * @param e     L'action effectuée
     *
     * @see Royaume
     * @see Domino
     * @see ModelTest
     */
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
        System.out.println("x1: " +x+" y1: "+y+" x2: "+x2+" y2: "+y2);

        if(model.addDominoRoyaume(domino, model.getJoueurActuel().getId(), x, y, x2, y2)){
                if (test){
                    model.getJoueurActuel().getRoyaume().showRoyaume();
                    model.changementJoueur();
                } else {
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
        } else {
            System.out.println(model.getDominoSelect());
            JOptionPane.showMessageDialog(fenetre.getjFrame(),"Vous ne pouvez pas placer ce domino ici","Attention", JOptionPane.WARNING_MESSAGE);
            //model.changementJoueur();
        }
    }
}
