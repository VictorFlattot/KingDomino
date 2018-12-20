package Vues;


import Control.ControlCaseRoyaume;
import Control.ControlRotationTuile;
import Model.ModelTest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;


public class
FenetreTest extends JFrame {

	private ModelTest model;
	private JFrame jFrame;
	private JPanel jPanelInstruction ;
	private Bouton[] jButtonTuillesCentre;
	private JPanel jPanelCentre;
	private JPanel jPanelOuest;
	private JPanel jPanelEst;
	private JPanel jPanelNord;
	private JPanelPressStart jPanelPressStart;

	private JPanelRoyaume[] jPanelRoyaumes;

	private JPanel panelMenuJouerQuiter;

	private JScrollPane panelInstruction ;

	private JPanel jPanelTuileSelect;
	private JButton boutontuileSelect;
	private Bouton boutonJouer;
	private Bouton boutonQuitter;
	private Bouton boutonRetour ;
	private Bouton instruction ;
    private ControlRotationTuile controlRotationTuile;
    static GraphicsDevice device ;
	private Image fondKing ;
	private Image pressStart ;

	private Image instruction_img ;
	private Image instruction_img2 ;
	private Image instruction_img3 ;
	private KeyListener keyListener;

	private Image[] instructionTab ;


    public FenetreTest(ModelTest model ) throws IOException {
		this.model=model;
		initAtribut();
		initRoyaumeToutJoueur();
		initBoutonCentre();
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setVisible(true);

	}

	private void initAtribut() throws IOException {
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		jFrame = new JFrame("KingDomino");
		fondKing = ImageIO.read(new File("img/kingdomino_fond.jpg"));
		instruction_img = ImageIO.read(new File("img/instruction_1.png"));
		instruction_img2 = ImageIO.read(new File("img/instruction_2.png"));
		instruction_img3 = ImageIO.read(new File("img/instruction_3.png"));
		instructionTab = new Image[] {instruction_img, instruction_img2, instruction_img3};
		setFullscreen();
		jFrame.setLayout(new BorderLayout());
		jPanelCentre = new JPanel();
		jPanelCentre.setLayout(new BoxLayout(jPanelCentre,BoxLayout.Y_AXIS));
		jPanelCentre.setOpaque(false);
		jPanelEst = new JPanel();
		jPanelEst.setLayout(new GridLayout(2,1));
		jPanelEst.setOpaque(false);
		jPanelOuest = new JPanel();
		jPanelOuest.setLayout(new GridLayout(2,1));
		jPanelOuest.setOpaque(false);
		jButtonTuillesCentre = new Bouton[4];
		jPanelRoyaumes = new JPanelRoyaume[model.getNbJoueur()];
		jPanelNord = new JPanel();
		jPanelNord.setOpaque(false);
		controlRotationTuile = new ControlRotationTuile(model, this);
		boutontuileSelect = new Bouton();
		boutontuileSelect.addActionListener(controlRotationTuile);
		keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				if (e.getKeyCode()==10) {
					try {
						afficherMenuJouerQuitter();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (e.getKeyCode()==27) fermer();
			}

			@Override
			public void keyReleased(KeyEvent e) {}
		};
		jFrame.addKeyListener(keyListener);
		pressStart = ImageIO.read(new File("img/kingdomino_menu_press_start.png"));
		jPanelPressStart = new JPanelPressStart(pressStart);
		panelMenuJouerQuiter = new JPanel();
		boutonJouer = new Bouton();
		instruction = new Bouton();
		boutonQuitter = new Bouton();
		boutonQuitter.setText("Quitter");
		boutonQuitter.addActionListener(e -> fermer());
		jFrame.add(jPanelPressStart);


	}

	private void setFullscreen(){
		device.setFullScreenWindow(jFrame);

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
		jFrame.revalidate();

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

	private void afficherRoyaume() throws IOException {


		if (model.getNbJoueur() == 2){
			jFrame.add(jPanelRoyaumes[1], BorderLayout.EAST);
			jFrame.add(jPanelRoyaumes[0],BorderLayout.WEST);
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
			if (model.isPartieFinie()){
				jFrame.remove(jPanelCentre);
				jFrame.repaint();

				JOptionPane.showMessageDialog(jFrame,
						"FIN DE PARTIE.");

			}else{
				jPanelCentre.removeAll();
				model.nouveauTour();
				bloquerToutBoutonCentre(false);
				afficherTuilleAuCentre();
			}

		}
		jFrame.repaint();
	}

	private void bloquerBoutonDominoDejaPlacé(){
		for (int i = 0; i < 4; i++) {
				bloquerBoutonCentre(i,!model.getDominoDejaPlace()[i]);
		}
	}

	public void afficheErrPlacement(){
		JOptionPane.showMessageDialog(jFrame,
				"Eggs are not supposed to be green.");
	}


	public void afficherJeu() throws IOException {
		jFrame.remove(panelMenuJouerQuiter);

		afficherTuilleAuCentre();

		afficherRoyaume();
		jFrame.revalidate();

	}

	public void afficherMenuJouerQuitter() throws IOException {

			jFrame.remove(jPanelPressStart);
			panelMenuJouerQuiter = new JPanelPressStart(fondKing);
			panelMenuJouerQuiter.setOpaque(true);
			panelMenuJouerQuiter.add(boutonJouer);
			panelMenuJouerQuiter.add(instruction);
			panelMenuJouerQuiter.add(boutonQuitter);
			jFrame.repaint();
			jFrame.add(panelMenuJouerQuiter);
			jFrame.removeKeyListener(keyListener);


			instruction.setText("Instruction");
			boutonJouer.setText("Jouer");
			instruction.setFont(new Font("Helvetica",Font.BOLD,50));
			boutonJouer.setFont(new Font("Helvetica",Font.BOLD,50));
			boutonQuitter.setFont(new Font("Helvetica", Font.BOLD, 50));

			boutonJouer.addActionListener(e -> {
				try {
					afficherJeu();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

			instruction.addActionListener( e -> {
				try {
					instruction();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

	}


	private void afficherChoixNbJoueur() {
		jFrame.remove(panelMenuJouerQuiter);
		panelMenuJouerQuiter.setLayout(new BoxLayout(panelMenuJouerQuiter,BoxLayout.PAGE_AXIS));
		panelMenuJouerQuiter.add(boutonJouer);
		panelMenuJouerQuiter.add(boutonQuitter);
		jFrame.add(panelMenuJouerQuiter);
	}

	public void instruction() throws IOException {
    	jFrame.removeKeyListener(keyListener);
    	jFrame.remove(panelMenuJouerQuiter);

    	jPanelInstruction = new JPanelPressStart(instructionTab[1]);
    	jPanelInstruction.add(boutonQuitter);
    	jFrame.add(jPanelInstruction);
    	jFrame.revalidate();
	}

	public void fermer() {
    	int res = JOptionPane.showConfirmDialog(null,"Vous ne voulez pas devenir roi ?","", JOptionPane.YES_NO_OPTION);
		switch (res){
				case JOptionPane.YES_OPTION:
						jFrame.dispose();
					break;
				case JOptionPane.NO_OPTION:

					break;
		}
	}


}
