package Control;

import Fenetre.FenetreTest;
import Model.Domino;
import Model.ModelTest;
import Model.Tuile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ControlCaseRoyaume implements ActionListener {
    private ModelTest model;
    private FenetreTest fenetre;
    private Domino domino;
    private int x;
    private int y;


    public ControlCaseRoyaume(ModelTest model, FenetreTest fenetre) {
        this.model = model;
        this.fenetre = fenetre;
        fenetre.setActionListenerCaseRoyaume(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        domino = model.getDominoSelect();
        x = Integer.valueOf(e.getActionCommand().split("/")[0]);
        y = Integer.valueOf(e.getActionCommand().split("/")[1]);

        model.addDominoRoyaume(domino, model.indexJoueur(),x,y);

        Tuile[] tuile = domino.getTuiles();
        BufferedImage imgTuile2 = null;
        BufferedImage imgTuile1 = null;
        try {
            imgTuile1 = ImageIO.read(
                    new File("img/Tuile"+ tuile[0].getId()  +".jpg"));
            imgTuile2 = ImageIO.read(
                    new File("img/Tuile"+ tuile[1].getId()  +".jpg"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        fenetre.afficherTuileRoyaume(new ImageIcon(imgTuile1),x,y);
    }
}
