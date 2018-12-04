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
	private JPanel jPanelRoyaume;
	private JPanel jPanelSouth;
	private Bouton[][] jButtonRoyaumeJoueur;
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
		jPanelRoyaume = new JPanel();
		jPanelRoyaume.setLayout(new GridLayout(5,5));
		jPanelRoyaume.setMaximumSize(new Dimension(320,320));
		jPanelRoyaume.setMinimumSize(new Dimension(320,320));

		jPanelRoyaume.setPreferredSize(new Dimension(320,320));
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

	private void afficheRoyaume() throws IOException {
		royaume = model.getJoueurs()[0].getRoyaume();
		System.out.println(royaume.getTuile(2,2));
		jButtonRoyaumeJoueur = new Bouton[5][5];

		final BufferedImage depart = ImageIO.read(new File("img/depart.jpg"));
		final BufferedImage croix = ImageIO.read(new File("img/croix.png"));

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				jButtonRoyaumeJoueur[i][j] = new Bouton();

				jButtonRoyaumeJoueur[i][j].setIcon(new ImageIcon(croix));

				jPanelRoyaume.add(jButtonRoyaumeJoueur[i][j]);
			}
		}
		jPanelRoyaume.setSize(jPanelRoyaume.getPreferredSize());
		jPanelSouth.add(jPanelRoyaume);
		jButtonRoyaumeJoueur[2][2].setIcon(new ImageIcon(depart));
		jButtonRoyaumeJoueur[2][2].setDisabledIcon(new ImageIcon(depart));
		jFrame.add(jPanelSouth,BorderLayout.SOUTH);
		jFrame.pack();
	}
	


	public void setActionListenerCaseRoyaume(ControlCaseRoyaume controlCaseRoyaume) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				jButtonRoyaumeJoueur[i][j].setActionCommand("" + i + "+" + j);
				jButtonRoyaumeJoueur[i][j].addActionListener(controlCaseRoyaume);
			}
		}
	}
}
