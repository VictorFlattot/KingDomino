package Vues;


import Control.ControlCaseRoyaume;
import Control.ControlRotationTuile;
import Model.ModelTest;
import Model.Royaume;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FenetreTest extends JFrame {

	private ModelTest model;
	private JFrame jFrame;
	private Bouton[] jButtonTuillesCentre;
	private JPanel jPanelCentre;
	private JPanel jPanelOuest;
	private JPanel jPanelEst;
	private Map<Integer,Bouton[][]> mapRoyaumeJoueur;
	private JPanel jPanelNord;
	private JPanel jButtonRoyaumeJoueur;

	private JPanelRoyaume[] jPanelRoyaumes;

	private JPanel jPanelTuileSelect;
	private JButton boutontuileSelect;
    private ControlRotationTuile controlRotationTuile;

    public FenetreTest(ModelTest model ) throws IOException {
		this.model=model;
		initAtribut();
		initRoyaumeToutJoueur();
		initBoutonCentre();
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		afficherTuilleAuCentre();
		afficherRoyaume();
	}

	private void initAtribut() {
		jFrame = new JFrame("KingDomino");
		jFrame.setLayout(new BorderLayout());
		jPanelCentre = new JPanel();
		jPanelCentre.setLayout(new BoxLayout(jPanelCentre,BoxLayout.Y_AXIS));
		jPanelEst = new JPanel();
		jPanelEst.setLayout(new GridLayout(2,1));
		jPanelOuest = new JPanel();
		jPanelOuest.setLayout(new GridLayout(2,1));
		jButtonTuillesCentre = new Bouton[4];
		jPanelRoyaumes = new JPanelRoyaume[model.getNbJoueur()];
		jPanelNord = new JPanel();
		controlRotationTuile = new ControlRotationTuile(model, this);
		boutontuileSelect = new Bouton();
		boutontuileSelect.addActionListener(controlRotationTuile);
		jFrame.add(jPanelNord);
		jFrame.pack();

	}

	private void initRoyaumeToutJoueur() throws IOException {
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jPanelRoyaumes[i] = new JPanelRoyaume(model,model.getJoueurs()[i].getId());
		}
	}

	private void initBoutonCentre(){
		for (int i = 0; i < 4; i++) {
			jButtonTuillesCentre[i] = new Bouton();
		}
	}

	private void afficherTuilleAuCentre() throws IOException {
		JPanel jPanelTuileAuCentre = new JPanel();
		jPanelTuileSelect = new JPanel();
		for (int i = 0; i < 4; i++) {
			int idDom = model.getTuilesAuCentre().getDominoTab()[i].getId();
			final BufferedImage bi = ImageIO.read(new File(donneCheminDomino(idDom,90)));
			final BufferedImage croix = ImageIO.read(new File("img/croix.png"));

			jButtonTuillesCentre[i].setIcon(new ImageIcon(bi));
			jButtonTuillesCentre[i].setActionCommand(""+idDom+"/"+i);
			jPanelTuileAuCentre.add(jButtonTuillesCentre[i]);
		}
		jPanelCentre.add(jPanelTuileAuCentre);
		jPanelCentre.add(jPanelTuileSelect);
		jFrame.add(jPanelCentre,BorderLayout.CENTER);

		jFrame.pack();

	}

	public void afficheTuileSelect(Icon icon,int id){

	    boutontuileSelect.setIcon(icon);
	    boutontuileSelect.setActionCommand(String.valueOf(id));
		jPanelTuileSelect.add(boutontuileSelect);
		jPanelTuileSelect.revalidate();
		jFrame.repaint();
	}

	public void tournerTuileSelect(int rot,int idDom) throws IOException {

        BufferedImage image = ImageIO.read(new File(donneCheminDomino(idDom,rot)));
        jPanelTuileSelect.remove(boutontuileSelect);
        afficheTuileSelect(new ImageIcon(image),idDom);
    }

	private void afficherRoyaume() {

		if (model.getNbJoueur() < 3){
			jFrame.add(jPanelRoyaumes[0], BorderLayout.EAST);
			jFrame.add(jPanelRoyaumes[1],BorderLayout.WEST);
		}
		if (model.getNbJoueur() == 3){
			jFrame.add(jPanelRoyaumes[2],BorderLayout.SOUTH);
		}
		if (model.getNbJoueur() == 4){
			jPanelEst.add(jPanelRoyaumes[1]);
			jPanelEst.add(jPanelRoyaumes[3]);
			jPanelOuest.add(jPanelRoyaumes[0]);
			jPanelOuest.add(jPanelRoyaumes[2]);
			jFrame.add(jPanelEst,BorderLayout.EAST);
			jFrame.add(jPanelOuest,BorderLayout.WEST);
		}

		bloquerToutRoyaumes(true);

		jFrame.pack();
	}

	public String donneCheminDomino(int numeroDomino , int rot){
	    String nomImg ="";
	    switch (rot){
            case 0:break;
            case 90:nomImg=".pivoté90";break;
            case 180:nomImg=".pivoté180";break;
            case 270:nomImg=".pivoté270";break;
        }
		if(numeroDomino<10) return "img/0"+ numeroDomino+ nomImg +".jpg";
		return "img/"+ numeroDomino + nomImg +".jpg";

	}

	public void setActionListenerTuileCentre(ActionListener actionListener){
		for (int i = 0; i < 4; i++) {
			jButtonTuillesCentre[i].addActionListener(actionListener);
		}
	}

	public void changementJoueur() throws IOException {
    	jPanelRoyaumes[model.getJoueurActuel().getId()].updateRoyaume();
	}

	public void setActionListenerCaseRoyaume(ControlCaseRoyaume controlCaseRoyaume) {
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jPanelRoyaumes[i].setActionListener(controlCaseRoyaume);
		}
	}

	public void bloquerToutBoutonCentre(boolean b){
		for (int i = 0; i < 4; i++) {
		    jButtonTuillesCentre[i].setEnabled(!b);
		}
	}

	public void bloquerToutBoutonRoyaume(boolean b,int joueur){
		jPanelRoyaumes[joueur].bloquerRoyaume(b);
	}

	public void bloquerToutRoyaumes(boolean b){
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jPanelRoyaumes[i].bloquerRoyaume(b);
		}
	}

	private void bloquerBoutonCentre(int index,boolean b){
		jButtonTuillesCentre[index].setEnabled(b);
	}

	public void nouvelleSelectionDomino() throws IOException {
    	jPanelTuileSelect.remove(boutontuileSelect);
    	bloquerToutBoutonRoyaume(true,model.getJoueurActuel().getId());
    	bloquerBoutonDominoDejaPlacé();
    	jPanelTuileSelect.revalidate();
		if (model.faireUnNouveauTour()) {
			jPanelCentre.removeAll();
			model.nouveauTour();
			bloquerToutBoutonCentre(false);
			afficherTuilleAuCentre();
		}
		jFrame.repaint();
	}

	private void bloquerBoutonDominoDejaPlacé(){
		for (int i = 0; i < 4; i++) {
				bloquerBoutonCentre(i,!model.getDominoDejaPlacé()[i]);
		}
	}

	public void afficheErrPlacement(){
		JOptionPane.showMessageDialog(jFrame,
				"Eggs are not supposed to be green.");
	}


}
