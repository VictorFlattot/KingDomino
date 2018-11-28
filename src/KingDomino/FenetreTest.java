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
	private JLabel jLabel;
	private Bouton[] jButtonTuillesCentre;
	private JPanel jPanelCentre;

	public FenetreTest(ModelTest model ) throws IOException {
		this.model=model;
		initAtribut();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);

		new Royaume(5);
		afficherTuilleAuCentre();
	}

	private void initAtribut() {
		jFrame = new JFrame("KingDomino");
		jFrame.setLayout(new BorderLayout());
		jLabel = new JLabel("test");
		jPanelCentre = new JPanel();
		jButtonTuillesCentre = new Bouton[4];
		jFrame.add(jLabel,BorderLayout.NORTH);
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
		jFrame.pack();

	}

	public String donneCheminDomino(int numeroDomino){
		String retour = "img/";
		if(numeroDomino<10){
			retour += "0"+numeroDomino+".pivoté90.jpg";
			System.out.println(retour);
		}else {
			retour += numeroDomino + ".pivoté90.jpg";
			System.out.println(retour);
		}
		return retour;
	}

	public void setActionListenerTuileCentre(ActionListener actionListener){
		for (int i = 0; i < 4; i++) {
			jButtonTuillesCentre[i].setActionCommand(String.valueOf(model.getTuilesAuCentre().getDominoTab()[i].getId()));
			jButtonTuillesCentre[i].addActionListener(actionListener);
		}
	}
}
