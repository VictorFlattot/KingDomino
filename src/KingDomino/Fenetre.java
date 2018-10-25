package KingDomino;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    JPanel grandPano = new JPanel();
    JPanel pion = new JPanel();
    int idRoyaumeAPlacer = 0;
    public Fenetre(){
        ajouteWidget();
        pack();
        setTitle("KingDomino");
        setExtendedState(this.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void ajouteWidget(){


    }

    public void afficheDominoAuCentre(TuilesAuCentre centre){
        DominoFen domino1 = new DominoFen(centre.dominoTab[0].getId(),this);
        DominoFen domino2 = new DominoFen(centre.dominoTab[1].getId(),this);
        DominoFen domino3 = new DominoFen(centre.dominoTab[2].getId(),this);
        DominoFen domino4 = new DominoFen(centre.dominoTab[3].getId(),this);
        JPanel hautGauche = new JPanel(new GridLayout(1,2));
        JPanel hautDroit = new JPanel(new GridLayout(1,2));
        JPanel basGauche = new JPanel(new GridLayout(1,2));
        JPanel basDroit = new JPanel(new GridLayout(1,2));
        hautGauche.add(domino1.bouton1);
        hautDroit.add(domino2.bouton1);
        basGauche.add(domino3.bouton1);
        basDroit.add(domino4.bouton1);
        grandPano.add(hautGauche);
        grandPano.add(hautDroit);
        grandPano.add(basGauche);
        grandPano.add(basDroit);
        setContentPane(grandPano);
        revalidate();
    }

    public void afficheRoyaumeJoueur(){
        grandPano.removeAll();
        JPanel royaumeCentre = new JPanel();
        royaumeCentre.setBackground(Color.BLUE);
        JButton selection;
        JPanel grille = new JPanel(new GridLayout(3,3));
        JPanel blanc = new JPanel();
        blanc.setBackground(Color.white);

        for(int i=0;i<=8;i++){
            if(i==4){
                grille.add(royaumeCentre);
            }else if(i%2!=0){
                selection = new JButton();
                selection.addActionListener(new ControlRoyaume(this));
                selection.setActionCommand(""+i);
                grille.add(selection);
            }else if(i!=4){
                selection = new JButton();
                grille.add(selection);
            }
        }
        setContentPane(grille);
        revalidate();
    }

    public void ajouteDomino(int noDomino, int emplacement){
        grandPano.removeAll();
        JPanel royaumeCentre = new JPanel();
        royaumeCentre.setBackground(Color.BLUE);
        JButton selection;
        JPanel grille = new JPanel(new GridLayout(3,3));
        JPanel blanc = new JPanel();
        blanc.setBackground(Color.white);

        for(int i=0;i<=8;i++){
            if(i==4){
                grille.add(royaumeCentre);
            }else if(i==emplacement){
                selection = new JButton();
                Icon image = new ImageIcon("images/"+noDomino+".png");
                selection.setIcon(image);
                grille.add(selection);
            }else{
                selection = new JButton();
                grille.add(selection);
            }
        }
        setContentPane(grille);
        revalidate();
    }



}
