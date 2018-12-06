package Control;

import Fenetre.FenetreTest;
import Model.ModelTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlCaseRoyaume implements ActionListener {
    private ModelTest model;
    private FenetreTest fenetre;

    public ControlCaseRoyaume(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
        fenetre.setActionListenerCaseRoyaume(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
