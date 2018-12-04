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
	private JPanel jPanelTuileSelect;
	private JButton boutontuileSelect;
    private ControlRotationTuile controlRotationTuile;

    public FenetreTest(ModelTest model ) throws IOException {
		this.model=model;
		initAtribut();
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		afficherTuilleAuCentre();
		afficheRoyaume();
	}

	private void initAtribut() {
		jFrame = new JFrame("KingDomino");
		jFrame.setLayout(new BorderLayout());
		jPanelCentre = new JPanel();
		jPanelCentre.setLayout(new GridLayout(2,1));
		jButtonTuillesCentre = new Bouton[4];
		jPanelSouth = new JPanel();
		jPanelRoyaume = new JPanel();
		jPanelRoyaume.setLayout(new GridLayout(5,5));
        controlRotationTuile = new ControlRotationTuile(model, this);
		jFrame.pack();

	}

	private void afficherTuilleAuCentre() throws IOException {
		JPanel jPanelTuileAuCentre = new JPanel();
		jPanelTuileSelect = new JPanel();
		for (int i = 0; i < 4; i++) {
			int idDom = model.getTuilesAuCentre().getDominoTab()[i].getId();
			final BufferedImage bi = ImageIO.read(new File(donneCheminDomino(idDom,90)));
			final BufferedImage croix = ImageIO.read(new File("img/croix.png"));

			jButtonTuillesCentre[i] = new Bouton();
			jButtonTuillesCentre[i].setIcon(new ImageIcon(bi));
			jButtonTuillesCentre[i].setActionCommand(""+idDom+"/"+i);
			jPanelTuileAuCentre.add(jButtonTuillesCentre[i]);
		}
		jPanelCentre.add(jPanelTuileAuCentre);
		jPanelCentre.add(jPanelTuileSelect);
		jFrame.add(jPanelCentre,BorderLayout.CENTER);
		//jFrame.pack();

	}

	void afficheTuileSelect(Icon icon,int id){
	    boutontuileSelect = new Bouton();
	    boutontuileSelect.setIcon(icon);
	    boutontuileSelect.setActionCommand(String.valueOf(id));

        boutontuileSelect.addActionListener(controlRotationTuile);
		jPanelTuileSelect.add(boutontuileSelect);
		jFrame.revalidate();
	}

	void tournerTuileSelect(int rot,int idDom){
        /*image = ImageIO.read(new File(donneCheminDomino(idDom,0)));
        jPanelTuileSelect.remove(boutontuileSelect);
        afficheTuileSelect();
		*/
    }

	String donneCheminDomino(int numeroDomino , int rot){
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

	public void bloquerBoutonCentre(int index){
		for (int i = 0; i < 4; i++) {
		    jButtonTuillesCentre[i].setEnabled(false);

		}
	}


}
