package Control;

import Model.ModelTest;
import Vues.FenetreTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * The type Control tuile a choisir.
 */
public class ControlTuileAChoisir implements ActionListener {

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
     * Instantiates a new Control tuile a choisir.
     *
     * @param model   the model
     * @param fenetre the fenetre
     */
    public ControlTuileAChoisir(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
    }

    /**
     * Instantiates a new Control tuile a choisir.
     *
     * @param modelTest the model test
     */
    public ControlTuileAChoisir(ModelTest modelTest){
        this.model = modelTest;
        fenetre = new FenetreTest();
        test = true;
    }

    /**
     * Permet de sélectionner une tuile si au moins un domino est encore disponible
     * Possible uniquement si ce domino n'est pas déjà réservé par un autre joueur
     *
     * @param e     L'action effectuée
     *
     * @see FenetreTest
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //model.showDomDejaChoisi();
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
                System.out.println(model.getJoueurActuel().getCouleur().toString());
                fenetre.changeLabelPlayer(posDom,model.getJoueurActuel().getCouleur().toString() );
                model.setDominoDejaChoisi(posDom,true);
                System.out.println(model.getDominoSelect());
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
        } else {
            //JOptionPane.showMessageDialog(fenetre.getjFrame(),"Ce domino est déjà résérvé par un autre joueur","Attention", JOptionPane.WARNING_MESSAGE);
        }
    }
}
