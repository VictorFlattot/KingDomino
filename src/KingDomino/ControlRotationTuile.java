package KingDomino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ControlRotationTuile implements ActionListener {
    private ModelTest model;
    private FenetreTest fenetre;

            ;

    public ControlRotationTuile(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fenetre.tournerTuileSelect(0,0);
    }
}
