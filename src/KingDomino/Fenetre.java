package KingDomino;

import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame {
    JPanel grandPano = new JPanel(new GridLayout(2,2,10,10));
    JPanel mainFrame = new JPanel(new GridLayout(2,1,10,0));
    JPanel pion = new JPanel();
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
        Domino domino1 = centre.dominoTab[0];
        Domino domino2 = centre.dominoTab[1];
        Domino domino3 = centre.dominoTab[2];
        Domino domino4 = centre.dominoTab[3];
        JPanel domino1Vue = new JPanel(new GridLayout(1,2));
        JPanel domino2Vue = new JPanel(new GridLayout(1,2));
        JPanel domino3Vue = new JPanel(new GridLayout(1,2));
        JPanel domino4Vue = new JPanel(new GridLayout(1,2));
        DominoFen domino1Fen = new DominoFen(domino1.getTuile1(),domino1.getTuile2());
        DominoFen domino2Fen = new DominoFen(domino2.getTuile1(),domino2.getTuile2());
        DominoFen domino3Fen = new DominoFen(domino3.getTuile1(),domino3.getTuile2());
        DominoFen domino4Fen = new DominoFen(domino4.getTuile1(),domino4.getTuile2());

        domino1Vue.add(domino1Fen.bouton1);
        domino1Vue.add(domino1Fen.bouton2);
        domino2Vue.add(domino2Fen.bouton1);
        domino2Vue.add(domino2Fen.bouton2);
        domino3Vue.add(domino3Fen.bouton1);
        domino3Vue.add(domino3Fen.bouton2);
        domino4Vue.setLocation(10,10);
        domino4Vue.add(domino4Fen.bouton1);
        domino4Vue.add(domino4Fen.bouton2);
        domino1Fen.bouton1.setSize(40,40);
        domino1Fen.bouton2.setSize(40,40);
        grandPano.add(domino3Vue);
        grandPano.add(domino2Vue);
        grandPano.add(domino1Vue);
        grandPano.add(domino4Vue);
        mainFrame.add(pion);
        mainFrame.add(grandPano);
        setContentPane(mainFrame);
        mainFrame.revalidate();
    }



}
