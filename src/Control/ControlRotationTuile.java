package Control;

import Model.Domino;
import Vues.FenetreTest;
import Model.ModelTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The type Control rotation tuile.
 */
public class ControlRotationTuile implements ActionListener {

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
     * Instantiates a new Control rotation tuile.
     *
     * @param model   the model
     * @param fenetre the fenetre
     */
    public ControlRotationTuile(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
        test = false;
    }

    /**
     * Instantiates a new Control rotation tuile.
     *
     * @param model the model
     */
    public ControlRotationTuile(ModelTest model) {
        this.model = model;
        this.fenetre = new FenetreTest();
        test = true;
    }

    /**
     * Permet d'effectuer la rotation du domino sélectionné si le programme n'est pas en phase de test
     *
     * @param e     L'action effectuée
     *
     * @see Domino
     * @see ModelTest
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Domino dominoSelect = model.getDominoSelect();
	    System.out.println("la");
	    System.out.println(((dominoSelect.getRotation()+90)/90));
        model.rotateTo(((dominoSelect.getRotation()+90)/90));
        try {
            if (!test){
                fenetre.tournerTuileSelect(dominoSelect.getRotation(), Integer.valueOf(e.getActionCommand()));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
