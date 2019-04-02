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
    private ModelTest modelTest;
    private int tailleRoyaume;
    private ControlCaseRoyaume actionListener;


    JPanelRoyaume(ModelTest modelTest, int idJoueur) throws IOException {
        super();
        tailleRoyaume = modelTest.getTailleRoyaume();
        Joueur joueur = modelTest.getJoueur(idJoueur);
        royaume = joueur.getRoyaume();
        initAtribut();
        setLayout(new BorderLayout());
        JLabel nomJoueur = new JLabel(joueur.getNom());
        if (joueur.getId()==0) {
            nomJoueur.setForeground(Color.blue);
        }else if (joueur.getId()==1){
            nomJoueur.setForeground(Color.yellow);
        }else if (joueur.getId()==2){
            nomJoueur.setForeground(Color.red);
        }else if (joueur.getId()==3){
            nomJoueur.setForeground(Color.green);
        }else{
            nomJoueur.setForeground(Color.white);
        }
        nomJoueur.setFont(new Font("Helvetica",Font.PLAIN,20));
        nomJoueur.setBorder(new EmptyBorder(0,130,5,0));
        this.add(nomJoueur,BorderLayout.NORTH);
        this.add(panelRoyaume);
        setOpaque(false);
        setBorder(new EmptyBorder(0,40,130,40));

    }

    private void initAtribut() throws IOException {
        boutons = new Bouton[tailleRoyaume][tailleRoyaume];
        panelRoyaume = new JPanel();
        panelRoyaume.setLayout(new GridLayout(tailleRoyaume,tailleRoyaume));
        panelRoyaume.setPreferredSize(new Dimension(64*tailleRoyaume,64*tailleRoyaume));
        panelRoyaume.setSize(panelRoyaume.getPreferredSize());
        panelRoyaume.setOpaque(false);
        initBoutonPanel();
    }

    private void initBoutonPanel() throws IOException {

        croix = ImageIO.read(new File("img/croix.png"));
        final BufferedImage depart = ImageIO.read(new File("img/Tuile16.pivoté0.jpg"));

        for (int i = 0; i < tailleRoyaume; i++) {
            for (int j = 0; j < tailleRoyaume; j++) {
                boutons[i][j] = new Bouton();
                panelRoyaume.add(boutons[i][j]);
            }
        }
        boutons[2][2].setIcon(new ImageIcon(depart));
        updateRoyaume();
    }

    public void updateRoyaume() throws IOException {
        for (int i = 0; i < tailleRoyaume; i++) {
            for (int j = 0; j < tailleRoyaume; j++) {
                if (royaume.getTuile(i,j).getTerrain()!=null){
                    afficherTuileRoyaume(new ImageIcon(
                            ImageIO.read(new File("img/Tuile" +
                                    royaume.getTuile(i,j).getId() + ".pivoté" + royaume.getRotation(i, j) + ".jpg"))),i,j);
                }else{

                    afficherTuileRoyaume(new ImageIcon(croix),i,j);
                }
            }
        }
        revalidate();
    }
    public void setActionListener(ControlCaseRoyaume listener){
        actionListener = listener;
        for (int i = 0; i < tailleRoyaume; i++) {
            for (int j = 0; j < tailleRoyaume; j++) {
                boutons[i][j].setActionCommand("" + i + "/"+ j);
                boutons[i][j].addActionListener(listener);
            }
        }
    }

    private void afficherTuileRoyaume(Icon icon, int x, int y){
        boutons[x][y].setIcon(icon);
    }

    public void bloquerRoyaume(boolean b){
        for (int i = 0; i < tailleRoyaume; i++) {
            for (int j = 0; j < tailleRoyaume; j++) {
                if (b) boutons[i][j].removeActionListener(actionListener);
                else boutons[i][j].addActionListener(actionListener);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(ImageIO.read(new File("img/fondSable.png")),0,0,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bouton[][] getBoutons() {
        return boutons;
    }

    public void setBoutons(Bouton[][] boutons) {
        this.boutons = boutons;
    }
}
