package Vues;


import Control.ControlCaseRoyaume;
import Control.ControlRotationTuile;
import Control.ControlTuileAChoisir;
import Model.ModelTest;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * The type Fenetre test.
 */
public class
FenetreTest extends JFrame {

	private ModelTest model;
	private JFrame jFrame;
	private Bouton[] jButtonTuilleCentreAPlacer;
	private Bouton[] jButtonTuilleCentreAChoisir;
	private Bouton[] jButtonTuilleCentreVide;
	private JPanel jPanelCentre;
	private JPanel jPanelOuest;
	private JPanel jPanelEst;
	private JPanel jPanelNord;
	private JPanelPressStart jPanelPressStart;
	private JFrame JFrameInstruction ;
	private JPanel JPanelInsctruction ;
	private JLabel[] jLabelsDomSelectByPlayer;
	private JLabel[] jLabelsDomPreviousSelectByPlayer;


	private JPanel jpanelNomJoueur ;

	private JPanel jPanelNombreJoueur ;

	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	int height = (int)dimension.getHeight();

	int width  = (int)dimension.getWidth();

	private JPanelRoyaume[] jPanelRoyaumes;

	private JPanel panelMenuJouerQuiter;

	private JPanel jPanelTuileSelect;
	private JButton boutontuileSelect;
	private Bouton boutonJouer;
	private Bouton boutonQuitter;
	private Bouton boutonRetour ;
	private Bouton instruction ;
    private ControlRotationTuile controlRotationTuile;
    private ControlTuileAChoisir controlTuileAChoisir;

	static GraphicsDevice device ;
	private Image fondKing ;
	private Image pressStart ;

	private Image instruction_img ;
	private Image instruction_img2 ;
	private Image instruction_img3 ;
	private KeyListener keyListener;

	private Image[] instructionTab ;
	private ControlCaseRoyaume controlCaseRoyaume;
	private Bouton deuxJoueurs;
	private Bouton troisJoueurs;
	private Bouton quatreJoueurs ;
	private JLabel joueur ;

	private Bouton valider ;

	public Bouton boutonTour ;

	private JLabel[] labelNomJouerSelectionNom;


	private JPanel jPanelSouth ;

	private JLabel dominoLabel ;
	private JLabel nomJoueurActif;
	private JPanel[] jPanel2PartTuileAChoisir;
	private JPanel[] jPanel2PartTuileAPlacer;

	/**
	 * Génère une nouvelle fenêtre de test
	 *
	 * @param model le modèle de donnée voulu
	 *
	 */
	public FenetreTest(ModelTest model ) throws IOException {
		this.model=model;
		initAtribut();
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.setVisible(true);

	}

	/**
	 * Instantiates a new Fenetre test.
	 */
	public  FenetreTest(){

	}

	private void initAtributJeu() throws IOException {

		jpanelNomJoueur = new JPanel();
		jPanelNombreJoueur = new JPanel();
		jPanelCentre = new JPanel();
		jPanelCentre.setLayout(new BoxLayout(jPanelCentre,BoxLayout.Y_AXIS));
		jPanelCentre.setOpaque(false);
		jPanelEst = new JPanel();
		jPanelEst.setLayout(new GridLayout(2,1));
		jPanelEst.setOpaque(false);
		jPanelOuest = new JPanel();
		jPanelOuest.setLayout(new GridLayout(2,1));
		jPanelOuest.setOpaque(false);
		jButtonTuilleCentreAPlacer = new Bouton[4];
		jButtonTuilleCentreAChoisir = new Bouton[4];
		jButtonTuilleCentreVide = new Bouton[4];
		jLabelsDomSelectByPlayer = new JLabel[model.getNbJoueur()];
		jLabelsDomPreviousSelectByPlayer = new JLabel[model.getNbJoueur()];


		boutonTour = new Bouton();

		jPanelSouth = new JPanel(new FlowLayout());

		jPanelNord = new JPanel();
		jPanelNord.setOpaque(false);
		boutontuileSelect = new Bouton();
		boutontuileSelect.addActionListener(controlRotationTuile);

		initBoutonCentre();
		initJLabelCouronneAChoisir();
		initJLabelCouronneAPlacer();
		controlCaseRoyaume = new ControlCaseRoyaume(model,this);
		setActionListenerCaseRoyaume();
		controlTuileAChoisir = new ControlTuileAChoisir(model,this);
		setActionListenerTuileCentreAChoisir();

	}

	private void initAtribut() throws IOException {



		valider = new Bouton() ;
		valider.setText("Jouer");

		boutonRetour = new Bouton();
		boutonRetour.setText("Retour");

		JFrameInstruction = new JFrame("Instruction");
		JFrameInstruction.setSize(777,773);

		deuxJoueurs = new Bouton();
		deuxJoueurs.setText("Deux Rois");
		troisJoueurs = new Bouton();
		troisJoueurs.setText("Trois Rois");
		quatreJoueurs = new Bouton();
		quatreJoueurs.setText("Quatre Rois");
		joueur = new JLabel("Combien de roi sont présent !");
		device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
		jFrame = new JFrame("KingDomino");
		fondKing = ImageIO.read(new File("img/kingdomino_fond.jpg"));
		instruction_img = ImageIO.read(new File("img/instruction_1.png"));
		instruction_img2 = ImageIO.read(new File("img/instruction_2.png"));
		instruction_img3 = ImageIO.read(new File("img/instruction_3.png"));
		instructionTab = new Image[] {instruction_img, instruction_img2, instruction_img3};

		setFullscreen(jFrame);
		jFrame.setLayout(new BorderLayout());


		controlRotationTuile = new ControlRotationTuile(model, this);
		keyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
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

	private void setFullscreen(JFrame JFrame){
		device.setFullScreenWindow(JFrame);

	}

	private void initRoyaumeToutJoueur() throws IOException {
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jPanelRoyaumes[i] = new JPanelRoyaume(model,model.getJoueurs()[i].getId());

		}
	}

	private void initBoutonCentre(){
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jButtonTuilleCentreAPlacer[i] = new Bouton();
			jButtonTuilleCentreAChoisir[i] = new Bouton();

		}
	}
	private void initJLabelCouronneAChoisir(){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
			jLabelsDomSelectByPlayer[i] = new JLabel();
		}
	}

	private void initJLabelCouronneAPlacer(){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
			jLabelsDomPreviousSelectByPlayer[i] = new JLabel();
		}
	}



	private void afficherTuilleAuCentre() throws IOException {
		JPanel jPanelTuileAuCentre = new JPanel();
		jPanelTuileAuCentre.setLayout(new GridLayout(2,4));
		jPanel2PartTuileAChoisir = new JPanel[model.getNbDominoCentre()];
		jPanel2PartTuileAPlacer = new JPanel[model.getNbDominoCentre()];
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
			jPanel2PartTuileAChoisir[i] = new JPanel();
			jPanel2PartTuileAChoisir[i].setLayout(new GridLayout(2,1));
			jPanel2PartTuileAPlacer[i] = new JPanel();
			jPanel2PartTuileAPlacer[i].setLayout(new GridLayout(2,1));
		}

		jPanelTuileSelect = new JPanel();
		int idDom;
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
			if (model.getNbTour() <= model.getNbTourMax()-1){
				idDom = model.getTuilleCentreAChoisir().getDominoTab()[i].getId();
				final BufferedImage bi = ImageIO.read(new File(donneCheminDomino(idDom,90)));

				jButtonTuilleCentreAChoisir[i].setIcon(new ImageIcon(bi));
				jButtonTuilleCentreAChoisir[i].setActionCommand(""+ idDom +"/"+ i);
				jPanel2PartTuileAChoisir[i].add(jButtonTuilleCentreAChoisir[i]);
				jPanel2PartTuileAChoisir[i].add(jLabelsDomSelectByPlayer[i]);
				jPanelTuileAuCentre.add(jPanel2PartTuileAChoisir[i]);
			}else{
				jPanel2PartTuileAChoisir[i].add(jButtonTuilleCentreAPlacer[i]);
				jPanelTuileAuCentre.add(jPanel2PartTuileAChoisir[i]);
			}



		}


		for (int i = 0; i < model.getNbDominoCentre(); i++) {
			if (model.getNbTour() !=1){
				idDom = model.getTuilesCentreAPLacer().getDominoTab()[i].getId();
				final BufferedImage bi = ImageIO.read(new File(donneCheminDomino(idDom,90)));
				jButtonTuilleCentreAPlacer[i].setIcon(new ImageIcon(bi));
				jPanel2PartTuileAPlacer[i].add(jButtonTuilleCentreAPlacer[i]);
				jPanel2PartTuileAPlacer[i].add(jLabelsDomPreviousSelectByPlayer[i]);
				jPanelTuileAuCentre.add(jPanel2PartTuileAPlacer[i]);

			}else{
				jPanel2PartTuileAPlacer[i].add(jButtonTuilleCentreAPlacer[i]);
				jPanelTuileAuCentre.add(jPanel2PartTuileAPlacer[i]);
			}
		}

		jPanelCentre.add(jPanelTuileAuCentre);
		jPanelCentre.add(jPanelTuileSelect);
		jFrame.add(jPanelCentre,BorderLayout.CENTER);
		jFrame.revalidate();

	}


	/**
	 * Affiche sur le plateau les tuiles composants le dominos séléctionner
	 *
	 * @param icon image de la tuile à placer sur le bouton
	 * @param id   id de la tuile
	 */
	public void afficheTuileSelect(Icon icon,int id){

	    boutontuileSelect.setIcon(icon);
	    boutontuileSelect.setActionCommand(String.valueOf(id));
		jPanelTuileSelect.add(boutontuileSelect);
		jPanelTuileSelect.revalidate();
		jFrame.repaint();
	}

	/**
	 * Fonction qui permet de tourner le domino séléctionner par l'utilisateur
	 *
	 * @param rot   degrées qui permet de faire tourner le domino
	 * @param idDom ici on envoie l'id du domino
	 * @throws IOException the io exception
	 */
	public void tournerTuileSelect(int rot,int idDom) throws IOException {

        BufferedImage image = ImageIO.read(new File(donneCheminDomino(idDom,rot)));
        jPanelTuileSelect.remove(boutontuileSelect);
        afficheTuileSelect(new ImageIcon(image),idDom);
    }

	/**
	 * Update de tout les royaumes
	 *
	 * @throws IOException the io exception
	 */
	public void updateAllRoyaume() throws IOException {
	    for (int i = 0; i < model.getNbJoueur(); i++) {
		    jPanelRoyaumes[model.getJoueur(i).getId()].updateRoyaume();
	    }
    }

	private void afficherRoyaume() throws IOException {
		if (model.getNbJoueur() == 2){
			jPanelEst.add(new JPanel());
			jPanelOuest.add(new JPanel());
			jPanelEst.add(jPanelRoyaumes[1]);
			jPanelOuest.add(jPanelRoyaumes[0]);

		}
		if (model.getNbJoueur() == 3){
			jFrame.add(jPanelRoyaumes[2],BorderLayout.SOUTH);
		}
		if (model.getNbJoueur() == 4){
			jPanelEst.add(jPanelRoyaumes[1]);
			jPanelEst.add(jPanelRoyaumes[3]);
			jPanelOuest.add(jPanelRoyaumes[0]);
			jPanelOuest.add(jPanelRoyaumes[2]);

		}


		jFrame.add(jPanelEst,BorderLayout.EAST);
		jFrame.add(jPanelOuest,BorderLayout.WEST);
		boutonTour.setText("Passer mon tour");
		boutonQuitter.setFont(new Font("Helvetica", Font.BOLD, 20));
		boutonTour.setFont(new Font("Helvetica", Font.BOLD, 20));
		jPanelSouth.add(boutonQuitter);
		jPanelSouth.add(boutonTour);
		boutonTour.setEnabled(false);
		boutonTour.addActionListener(e->{
			passerTour();
		});

		jFrame.add(jPanelSouth, BorderLayout.SOUTH);


		bloquerToutRoyaumes(true);

	}

	/**
	 * Permet de donnée le chemin de l'image du domino.
	 *
	 * @param numeroDomino Numero du domino
	 * @param rot          le degrées de rotation
	 * @return the string
	 */
	public String donneCheminDomino(int numeroDomino , int rot){
	    String nomImg =".pivoté";
	    switch (rot){
            case 0:nomImg+="0";break;
            case 90:nomImg+="90";break;
            case 180:nomImg+="180";break;
            case 270:nomImg+="270";break;
        }
		if(numeroDomino<10) return "img/0"+ numeroDomino+ nomImg +".jpg";
		return "img/"+ numeroDomino + nomImg +".jpg";

	}


	/**
	 * Création et application du listener sur tout les boutons
	 */
	public void setActionListenerTuileCentreAChoisir(){
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jButtonTuilleCentreAChoisir[i].addActionListener(controlTuileAChoisir);
		}
	}

	/**
	 * AH BAH LA CA FAIT UN TRUC MDR .
	 *
	 * @throws IOException the io exception
	 */
	public void unTruc() throws IOException {
		model.setDominoSelect(model.getTuilesCentreAPLacer().getDominoTab()[model.getPosJoueurActuel()]);
		int id = model.getDominoSelect().getId();
		BufferedImage image = ImageIO.read(new File(donneCheminDomino(id,0)));
		boutonTour.setEnabled(true);
		afficheTuileSelect(new ImageIcon(image),id);
		bloquerToutRoyaumes(true);
		bloquerRoyaumeJoueur(false,model.getJoueurActuel().getId());
		if (model.getJoueurActuel().isIA()){
			tourIA();
		}

	}

	/**
	 * Liste des actions à effectuer quand viens le tour de l'ia.
	 */
	public void tourIA() {
		System.out.println("tourIA");
		int[] coord = model.ouPlacerDomino();

		if (coord[4] != -1 ){
			System.out.println("x1: " +coord[0]+" y1: "+coord[1]);
			model.setRotDominoSelect(coord[4]);
			model.getJoueurActuel().getRoyaume().showRoyaume();
			boutontuileSelect.setActionCommand(""+ coord[0] +"/"+ coord[1]);
			controlCaseRoyaume.actionPerformed(new ActionEvent(boutontuileSelect, ActionEvent.ACTION_PERFORMED, boutontuileSelect.getActionCommand()));

		}else{
			System.out.println("passe tour");
			model.getJoueurActuel().getRoyaume().showRoyaume();
			model.changementJoueur();
		}
		int posDom = model.whoIsBestDomino();
		System.out.println("Position domino: "+posDom);
		controlTuileAChoisir.actionPerformed(
				new ActionEvent(jButtonTuilleCentreAChoisir[posDom],
						ActionEvent.ACTION_PERFORMED, jButtonTuilleCentreAChoisir[posDom].getActionCommand()));


	}

	/**
	 * Passer son tour
	 */
	public void passerTour() {
		try {
			nouvelleSelectionDomino();
			bloquerToutRoyaumes(true);
			if (model.getNbTour()<model.getNbTourMax()){
				setActionListenerTuileCentreAChoisir();
			}else{
				model.changementJoueur();
				changementJoueur();
				changementTour();
				unTruc();
			}

			updateAllRoyaume();
			jFrame.revalidate();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Ici on change de joueur.
	 * Après un tour ou quand on passe son tour.
	 *
	 * @throws IOException the io exception
	 */
	public void changementJoueur() throws IOException {
		nomJoueurActif.setText("C'est au tour du joueur : " + model.getJoueurActuel().getNom());
		jPanelRoyaumes[model.getJoueurActuel().getId()].updateRoyaume();
		if (model.getJoueurActuel().isIA() && !model.getDominoDejaPlace()[model.getPosJoueurActuel()]){
			System.out.println("géchan");
			controlTuileAChoisir.actionPerformed(new ActionEvent(jButtonTuilleCentreAChoisir[model.getPosJoueurActuel()], ActionEvent.ACTION_PERFORMED, jButtonTuilleCentreAChoisir[model.getPosJoueurActuel()].getActionCommand()));
		}

	}

	/**
	 * Ici on place le listener sur les boutons du royaume.
	 */
	public void setActionListenerCaseRoyaume() {
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jPanelRoyaumes[i].setActionListener(controlCaseRoyaume);
		}
	}

	/**
	 * Fonction qui permet de bloquer l'action du joueur sur les tuiles au centre.
	 * Fonction
	 *
	 * @param b the b
	 */
	public void bloquerToutBoutonAPlacerCentre(boolean b){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
		    jButtonTuilleCentreAPlacer[i].setEnabled(!b);
		}
	}


	/**
	 * Remove all controler a choisir centre.
	 */
	public void removeAllControlerAChoisirCentre(){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
		jButtonTuilleCentreAChoisir[i].removeActionListener(controlTuileAChoisir);
		}
	}

	/**
	 * Bloquer royaume joueur.
	 *
	 * @param b      the b
	 * @param joueur the joueur
	 */
	public void bloquerRoyaumeJoueur(boolean b, int joueur){
		jPanelRoyaumes[joueur].bloquerRoyaume(b);
	}

	/**
	 * Bloquer tout royaumes.
	 *
	 * @param b the b
	 */
	public void bloquerToutRoyaumes(boolean b){
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jPanelRoyaumes[i].bloquerRoyaume(b);
		}
	}


	private void bloquerBoutonAPlacerCentre(int index, boolean b){
		jButtonTuilleCentreAPlacer[index].setEnabled(b);
	}

	/**
	 * Remove controler bouton a choisr centre.
	 *
	 * @param index the index
	 */
	public void removeControlerBoutonAChoisrCentre(int index){
		jButtonTuilleCentreAChoisir[index].removeActionListener(controlTuileAChoisir);
	}

	/**
	 * Add controler bouton a choisr centre.
	 *
	 * @param index the index
	 */
	public void addControlerBoutonAChoisrCentre(int index){
		jButtonTuilleCentreAChoisir[index].addActionListener(controlTuileAChoisir);
	}

	/**
	 * Nouvelle selection domino.
	 *
	 * @throws IOException the io exception
	 */
	public void nouvelleSelectionDomino() throws IOException {
		nomJoueurActif.setText("C'est au tour du joueur : " + model.getJoueurActuel().getNom());
    	jPanelTuileSelect.remove(boutontuileSelect);
    	bloquerRoyaumeJoueur(true,model.getJoueurActuel().getId());
    	bloquerBoutonDominoDejaPlacé();
    	jPanelTuileSelect.revalidate();
		jFrame.repaint();
		jFrame.revalidate();
	}

	/**
	 * Changement tour.
	 *
	 * @throws IOException the io exception
	 */
	public void changementTour() throws IOException {
		if (model.faireUnNouveauTour()) {
			if (model.isPartieFinie()){
				jFrame.remove(jPanelCentre);
				jFrame.repaint();
				JOptionPane.showMessageDialog(jFrame,
						"FIN DE PARTIE.");
				JOptionPane.showMessageDialog(jFrame,"Score: \n" +
                        "J1: "+model.calculScore(model.getJoueurs()[0])+"\n"+"J2: "+model.calculScore(model.getJoueurs()[1])+
                        "\n"+"J3: "+model.calculScore(model.getJoueurs()[2])+"\n"+"J4: "+model.calculScore(model.getJoueurs()[3]));
				fermer();
			}else{
				jPanelCentre.removeAll();
				model.nouveauTour();
				for (int i = 0; i <model.getNbDominoCentre(); i++) {
					jLabelsDomPreviousSelectByPlayer[i] = jLabelsDomSelectByPlayer[i];
				}
				jLabelsDomSelectByPlayer = new JLabel[4];
				initJLabelCouronneAChoisir();
				bloquerToutBoutonAPlacerCentre(false);
				removeAllControlerAChoisirCentre();
				afficherTuilleAuCentre();
			}

		}
	}

	private void bloquerBoutonDominoDejaPlacé(){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
				bloquerBoutonAPlacerCentre(i,!model.getDominoDejaPlace()[i]);
		}
	}


	/**
	 * Afficher jeu.
	 *
	 * @throws IOException the io exception
	 */
	public void afficherJeu() throws IOException {
		nomJoueurActif = new JLabel("C'est au tour du joueur d : " + model.getJoueurActuel().getNom());
		nomJoueurActif.setFont(new Font("Helvetica", Font.BOLD, 30));
		jFrame.add(nomJoueurActif,BorderLayout.NORTH);
		initAtributJeu();
		jFrame.remove(panelMenuJouerQuiter);
		afficherTuilleAuCentre();
		afficherRoyaume();
		if (model.getJoueurActuel().isIA()){
			controlTuileAChoisir.actionPerformed(new ActionEvent(jButtonTuilleCentreAChoisir[model.getPosJoueurActuel()], ActionEvent.ACTION_PERFORMED, jButtonTuilleCentreAChoisir[model.getPosJoueurActuel()].getActionCommand()));
		}
		jFrame.revalidate();

	}

	/**
	 * Afficher menu jouer quitter.
	 *
	 * @throws IOException the io exception
	 */
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
					afficherChoixNbJoueur();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

			instruction.addActionListener( e -> {
				try {
					instruction();
					jFrame.repaint();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});

	}


	private void afficherChoixNbJoueur() throws IOException {
		jFrame.remove(panelMenuJouerQuiter);

		deuxJoueurs.setFont(new Font("Helvetica",Font.BOLD,70));
		troisJoueurs.setFont(new Font("Helvetica",Font.BOLD,70));
		quatreJoueurs.setFont(new Font("Helvetica",Font.BOLD,70));
		joueur.setFont(new Font("Helvetica",Font.BOLD,70));


		JPanel joueurBox = new JPanel();
		joueurBox.setLayout(new BoxLayout(joueurBox,BoxLayout.Y_AXIS));
		joueurBox.setAlignmentX(Component.CENTER_ALIGNMENT);

		joueurBox.setBorder(new EmptyBorder(height/8,height/4,0,height/4));

		joueurBox.setOpaque(false);
		jPanelNombreJoueur = new JPanelPressStart(fondKing);
		joueurBox.add(joueur);
		joueurBox.add(deuxJoueurs);
		joueurBox.add(troisJoueurs);
		joueurBox.add(quatreJoueurs);
		jPanelNombreJoueur.add(joueurBox);
		jFrame.add(jPanelNombreJoueur);

		jFrame.revalidate();

		deuxJoueurs.addActionListener(e ->{
			model.setNbJoueur(2);
			try {
				choixNomJoueur(2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		troisJoueurs.addActionListener(e->{
			model.setNbJoueur(3);
			try {
				choixNomJoueur(3);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		quatreJoueurs.addActionListener(e->{
			model.setNbJoueur(4);
			try {
				choixNomJoueur(4);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}

	/**
	 * Instruction.
	 *
	 * @throws IOException the io exception
	 */
	public void instruction() throws IOException {
    	jFrame.removeKeyListener(keyListener);
		boutonRetour.setFont(new Font("Helvetica", Font.BOLD, 40));
		JPanelInsctruction = new JPanelPressStart(ImageIO.read(new File("img/instruction_2.png")));
		JPanelInsctruction.add(boutonRetour);
    	JFrameInstruction.add(JPanelInsctruction);
    	JFrameInstruction.setVisible(true);
    	setFullscreen(JFrameInstruction);

    	JFrameInstruction.revalidate();
		boutonRetour.addActionListener(e->{
			JFrameInstruction.dispose();
			setFullscreen(jFrame);
		});
	}

	/**
	 * Fermer.
	 */
	public void fermer() {
    	int res = JOptionPane.showConfirmDialog(jFrame,"Voulez-vous abandonner votre royaume ?","", JOptionPane.YES_NO_OPTION);
		switch (res){
				case JOptionPane.YES_OPTION:
						jFrame.dispose();
						System.exit(0);
						break;
				case JOptionPane.NO_OPTION:

					break;
		}
	}

	/**
	 * Choix nom joueur.
	 *
	 * @param nombreJoueur the nombre joueur
	 * @throws IOException the io exception
	 */
	public void choixNomJoueur(int nombreJoueur) throws IOException {
		labelNomJouerSelectionNom= new JLabel[model.getNbJoueur()];


		JTextField[] jTextField = new JTextField[nombreJoueur];
		for (int i = 0; i < nombreJoueur; i++) {
			labelNomJouerSelectionNom[i] = new JLabel();
			labelNomJouerSelectionNom[i].setText("Roi "+ model.getCouleursUtilisé()[i]);
			labelNomJouerSelectionNom[i].setFont(new Font("Helvetica",Font.BOLD,80));
			jTextField[i] = new JTextField(30);
			jTextField[i].setFont(new Font("Helvetica",Font.BOLD,40));
			jTextField[i].setText("Roi "+ model.getCouleursUtilisé()[i]);
		}

		jFrame.remove(jPanelNombreJoueur);

		jpanelNomJoueur = new JPanelPressStart(fondKing);
		jpanelNomJoueur.setLayout(new FlowLayout());


		valider.setFont(new Font("Helvetica",Font.BOLD,100));

		jpanelNomJoueur.setOpaque(false);


		if(nombreJoueur>=1){
			jpanelNomJoueur.add(labelNomJouerSelectionNom[0]);
			jpanelNomJoueur.add(jTextField[0]);
		}
		if(nombreJoueur>=2){
			jpanelNomJoueur.add(labelNomJouerSelectionNom[1]);
			jpanelNomJoueur.add(jTextField[1]);
		}
		if(nombreJoueur>=3) {
			jpanelNomJoueur.add(labelNomJouerSelectionNom[2]);
			jpanelNomJoueur.add(jTextField[2]);
		}
		if(nombreJoueur>=4) {
			jpanelNomJoueur.add(labelNomJouerSelectionNom[3]);
			jpanelNomJoueur.add(jTextField[3]);
		}

		valider.addActionListener(e->{

			for (int i = 0; i < model.getNbJoueur(); i++) {
				model.setNomJoueur(jTextField[i].getText(),i);
				model.setCouleurJoueur(model.getCouleursUtilisé()[i],i);
			}
			try {

				jPanelRoyaumes = new JPanelRoyaume[model.getNbJoueur()];
				initRoyaumeToutJoueur();
				jFrame.remove(jpanelNomJoueur);
				afficherJeu();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		jpanelNomJoueur.add(valider);
		jFrame.add(jpanelNomJoueur);
		jFrame.revalidate();

	}

	/**
	 * Gets frame.
	 *
	 * @return the frame
	 */
	public JFrame getjFrame() {
		return jFrame;
	}

	/**
	 * Change label player.
	 *
	 * @param posDom  the pos dom
	 * @param couleur the couleur
	 * @throws IOException the io exception
	 */
	public void changeLabelPlayer(int posDom, String couleur) throws IOException {
		final BufferedImage bi = ImageIO.read(new File("img/Roi"+couleur+".png"));
		jLabelsDomSelectByPlayer[posDom].setIcon(new ImageIcon(bi));
	}
}
