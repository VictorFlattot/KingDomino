package Vues;

import Control.ControlCaseRoyaume;
import Model.Joueur;
import Model.ModelTest;
import Model.Royaume;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPanelRoyaume extends JPanel {
    private JPanel panelRoyaume;
    private Bouton[][] boutons;
    private Royaume royaume;
    private BufferedImage croix;


    JPanelRoyaume(ModelTest modelTest, int idJoueur) throws IOException {
        super();
        Joueur joueur = modelTest.getJoueur(idJoueur);
        royaume = joueur.getRoyaume();
        initAtribut();
        setLayout(new BorderLayout());
        this.add(new JLabel(joueur.getNom()),BorderLayout.NORTH);
        this.add(panelRoyaume);
        setBorder(new EmptyBorder(20,20,20,20));

    }

    private void initAtribut() throws IOException {
        boutons = new Bouton[5][5];
        panelRoyaume = new JPanel();
        panelRoyaume.setLayout(new GridLayout(5,5));
        panelRoyaume.setPreferredSize(new Dimension(320,320));
        panelRoyaume.setSize(getPreferredSize());
        initBoutonPanel();
    }

    private void initBoutonPanel() throws IOException {

        croix = ImageIO.read(new File("img/croix.png"));
        final BufferedImage depart = ImageIO.read(new File("img/Tuile16.jpg"));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boutons[i][j] = new Bouton();
                panelRoyaume.add(boutons[i][j]);
            }
        }
        boutons[2][2].setIcon(new ImageIcon(depart));
        updateRoyaume();
    }

    public void updateRoyaume() throws IOException {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (royaume.getTuile(i,j).getTerrain()!=null){
                    afficherTuileRoyaume(new ImageIcon(
                            ImageIO.read(new File("img/Tuile" +
                                    royaume.getTuile(i,j).getId() + ".jpg"))),i,j);
                }else{

                    afficherTuileRoyaume(new ImageIcon(croix),i,j);
                }
            }
        }
    }
    public void setActionListener(ControlCaseRoyaume listener){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boutons[i][j].setActionCommand("" + i + "/"+ j);
                boutons[i][j].addActionListener(listener);
            }
        }
    }

    private void afficherTuileRoyaume(Icon icon, int x, int y){
        boutons[x][y].setIcon(icon);
    }

    public void bloquerRoyaume(boolean b){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boutons[i][j].setEnabled(!b);
            }
        }
    }



}
