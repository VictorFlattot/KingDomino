package Vues;


import Control.ControlCaseRoyaume;
import Control.ControlRotationTuile;
import Model.ModelTest;
import Model.Royaume;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class FenetreTest extends JFrame {

	private ModelTest model;
	private JFrame jFrame;
	private Bouton[] jButtonTuillesCentre;
	private JPanel jPanelCentre;
	private JPanel jPanelRoyaume;
	private Map<Integer,Bouton[][]> mapRoyaumeJoueur;
	private JPanel jPanelSouth;
	private Bouton[][] jButtonRoyaumeJoueur;

	private JPanelRoyaume[] jPanelsRoyaume;

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
		afficherToutLesRoyaumes();
	}

	private void initRoyaumeToutJoueur(){
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jPanelsRoyaume[i] = new JPanelRoyaume();
		}

    	mapRoyaumeJoueur = new HashMap<>();

		for (int i = 0; i < model.getJoueurs().length; i++) {
			Bouton[][] boutons = new Bouton[5][5];
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					boutons[j][k] = new Bouton();
					jPanelsRoyaume[i].add(boutons[j][k]);
				}
			}
			mapRoyaumeJoueur.put(model.getJoueurs()[i].getId(),boutons);

		}


	}

	private void initAtribut() {
		jFrame = new JFrame("KingDomino");
		jFrame.setLayout(new BorderLayout());
		jPanelCentre = new JPanel();
		jPanelCentre.setLayout(new GridLayout(2,1));
		jButtonTuillesCentre = new Bouton[4];
		jPanelsRoyaume = new JPanelRoyaume[model.getNbJoueur()];
		jPanelSouth = new JPanel();
		jPanelRoyaume = new JPanel();
		jPanelRoyaume.setLayout(new GridLayout(5,5));
		jPanelRoyaume.setPreferredSize(new Dimension(320,320));
		jPanelRoyaume.setSize(jPanelRoyaume.getPreferredSize());
        controlRotationTuile = new ControlRotationTuile(model, this);
		boutontuileSelect = new Bouton();
		boutontuileSelect.addActionListener(controlRotationTuile);
		jFrame.pack();

	}
	private void initBoutonCentre(){
		for (int i = 0; i < 4; i++) {
			jButtonTuillesCentre[i] = new Bouton();
		}
	}

	private void afficherTuilleAuCentre() throws IOException {
		JPanel jPanelTuileAuCentre = new JPanel();
		jPanelTuileAuCentre.setLayout(new GridLayout(4,1));
		jPanelTuileSelect = new JPanel();
		for (int i = 0; i < 4; i++) {
			int idDom = model.getTuilesAuCentre().getDominoTab()[i].getId();
			final BufferedImage bi = ImageIO.read(new File(donneCheminDomino(idDom,0)));
			final BufferedImage croix = ImageIO.read(new File("img/croix.png"));

			jButtonTuillesCentre[i].setIcon(new ImageIcon(bi));
			jButtonTuillesCentre[i].setActionCommand(""+idDom+"/"+i);
			jPanelTuileAuCentre.add(jButtonTuillesCentre[i]);
		}
		jPanelCentre.add(jPanelTuileAuCentre);
		jPanelCentre.add(jPanelTuileSelect);
		jFrame.add(jPanelCentre,BorderLayout.CENTER);
		//jFrame.pack();

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

	private void afficherToutLesRoyaumes() throws IOException {
		for (int i = 0; i < model.getNbJoueur(); i++) {
			afficheRoyaume(i);
			bloquerToutBoutonRoyaume(true);

			final BufferedImage depart = ImageIO.read(new File("img/depart.jpg"));

			jPanelSouth.add(jPanelRoyaume);
			afficherTuileRoyaume(i,new ImageIcon(depart),2,2);
			if (i == 1)
				jFrame.add(jPanelSouth,BorderLayout.WEST);
			if (i == 2)
				jFrame.add(jPanelSouth,BorderLayout.EAST);
			if (i == 3)
				jFrame.add(jPanelSouth,BorderLayout.NORTH);
			if (i == 4)
				jFrame.add(jPanelSouth,BorderLayout.SOUTH);

			jFrame.pack();
		}
	}


	private JPanel afficheRoyaume(int idJoueur) throws IOException {
		Royaume royaume = model.getJoueurActuel().getRoyaume();
		jButtonRoyaumeJoueur = mapRoyaumeJoueur.get(idJoueur);


		final BufferedImage croix = ImageIO.read(new File("img/croix.png"));

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (royaume.getTuile(i,j).getTerrain()!=null){
					afficherTuileRoyaume(idJoueur,new ImageIcon(
							ImageIO.read(new File("img/Tuile" +
									royaume.getTuile(i,j).getId() + ".jpg"))),i,j);
				}else{
					afficherTuileRoyaume(idJoueur,new ImageIcon(croix),i,j);
				}


				jPanelRoyaume.add(mapRoyaumeJoueur.get(idJoueur)[i][j]);
			}
		}
		return jPanelRoyaume;

	}

	public void afficherTuileRoyaume(int index,Icon icon,int x,int y){
		mapRoyaumeJoueur.get(index)[x][y].setIcon(icon);
	}

	public void setActionListenerCaseRoyaume(ControlCaseRoyaume controlCaseRoyaume) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i!=2 || j!=2){

					jButtonRoyaumeJoueur[i][j].setActionCommand("" + i + "/"+ j);
					jButtonRoyaumeJoueur[i][j].addActionListener(controlCaseRoyaume);
				}
			}
		}
	}

	public void bloquerToutBoutonCentre(boolean b){
		for (int i = 0; i < 4; i++) {
		    jButtonTuillesCentre[i].setEnabled(!b);
		}
	}

	public void bloquerToutBoutonRoyaume(boolean b){
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				jButtonRoyaumeJoueur[i][j].setEnabled(!b);
			}
		}
	}

	private void bloquerBoutonCentre(int index,boolean b){
		jButtonTuillesCentre[index].setEnabled(b);
	}

	public void nouvelleSelectionDomino() throws IOException {
    	jPanelTuileSelect.remove(boutontuileSelect);
    	bloquerToutBoutonRoyaume(true);
    	bloquerBoutonDominoDejaPlacé();
    	jPanelTuileSelect.revalidate();
		System.out.println(model.getJoueurActuel());
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
