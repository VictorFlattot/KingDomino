package KingDomino;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class FenetreTest extends JFrame {

	private ModelTest model;
	private JFrame jFrame;
	private Bouton[] jButtonTuillesCentre;
	private JPanel jPanelCentre;
	private JPanel jPanelSouth;
	private JLabel[][] jButtonRoyaumeJoueur;
	private Royaume royaume;

	public FenetreTest(ModelTest model ) throws IOException {
		this.model=model;
		initAtribut();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		afficherTuilleAuCentre();
		afficheRoyaume();
	}

	private void initAtribut() {
		jFrame = new JFrame("KingDomino");
		jFrame.setLayout(new BorderLayout());
		jPanelCentre = new JPanel();
		jButtonTuillesCentre = new Bouton[4];
		jPanelSouth = new JPanel();
		jPanelSouth.setLayout(new GridLayout(5,5));
		jFrame.pack();

	}

	private void afficherTuilleAuCentre() throws IOException {

		for (int i = 0; i < 4; i++) {
			final BufferedImage bi = ImageIO.read(new File(donneCheminDomino(
					model.getTuilesAuCentre().getDominoTab()[i].getId())));

			jButtonTuillesCentre[i] = new Bouton();
			jButtonTuillesCentre[i].setIcon(new ImageIcon(bi));
			jPanelCentre.add(jButtonTuillesCentre[i]);
		}
		jFrame.add(jPanelCentre,BorderLayout.CENTER);
		//jFrame.pack();

	}

	private String donneCheminDomino(int numeroDomino){
		String retour = "img/";
		if(numeroDomino<10) return "img/0"+ numeroDomino+".pivoté90.jpg";
		return "img/"+ numeroDomino + ".pivoté90.jpg";

	}

	public void setActionListenerTuileCentre(ActionListener actionListener){
		for (int i = 0; i < 4; i++) {
			jButtonTuillesCentre[i].setActionCommand(
					String.valueOf(model.getTuilesAuCentre().getDominoTab()[i].getId())
			);
			jButtonTuillesCentre[i].addActionListener(actionListener);
		}
	}

	private void afficheRoyaume(){
		royaume = model.getJoueur().getRoyaume();
		System.out.println(royaume.getTuile(2,2));
		jButtonRoyaumeJoueur = new JLabel[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				jButtonRoyaumeJoueur[i][j] = new JLabel();
				jButtonRoyaumeJoueur[i][j].setText(textLabel(i,j));
				jPanelSouth.add(jButtonRoyaumeJoueur[i][j]);
			}
		}

		jFrame.add(jPanelSouth,BorderLayout.SOUTH);
		jFrame.pack();
	}
	/*
		A ENLEVER APRES
	 */
	private String textLabel(int x,int y){
		if (royaume.getTuile(x,y).getTerrain() == Terrain.DEPART) return "D";
		return "vide";
	}


}
