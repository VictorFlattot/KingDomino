package Vues;

import Control.ControlCaseRoyaume;
import Control.ControlRotationTuile;
import Control.ControlTuileAChoisir;
import Model.Couleur;
import Model.Domino;
import Model.Joueur;
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
 * Classe définissant les fonctionnalités de la fenêtre
 * Permet d'initialiser les différentes fenêtres de jeu
 * Permet d'effectuer les actions de chaque tour pour chaque joueur
 * Intègre une partie pour l'IA
 * Gère le changement de tour; Possibilité de passer son tour
 * Gère les différentes interfaces, les boutons ont des fonctionnalités implémentées
 */
public class FenetreTest extends JFrame {

    /**
     * Utilisation du modèle via la classe ModelTest
     *
     * @see ModelTest
     */
	private ModelTest model;

	/**
     * L'interface du projet
     *
     * @see JFrame
     */
	private JFrame jFrame;

	/**
     * Un tableau de bouton servant au placement des tuiles
     *
     * @see Bouton
     */
	private Bouton[] jButtonTuilleCentreAPlacer;

	/**
     * Un tableau de bouton servant au choix des tuiles
     *
     * @see Bouton
     */
	private Bouton[] jButtonTuilleCentreAChoisir;

	/**
     * Un tableau de bouton représentant les tuilles au centre qui sont vides
     *
     * @see Bouton
     */
	private Bouton[] jButtonTuilleCentreVide;

	/**
     * Perment de diviser l'intreface en 4 zones
     * <i><b>jPanelCentre</b></i> représente la zone centrale
     *
     * @see JPanel
     */
	private JPanel jPanelCentre;

    /**
     * Perment de diviser l'intreface en 4 zones
     * <i><b>jPanelOuest</b></i> représente la zone gauche
     *
     * @see JPanel
     */
	private JPanel jPanelOuest;

    /**
     * Perment de diviser l'intreface en 4 zones
     * <i><b>jPanelEst</b></i> représente la zone droite
     *
     * @see JPanel
     */
	private JPanel jPanelEst;

    /**
     * Perment de diviser l'intreface en 4 zones
     * <i><b>jPanelNord</b></i> représente la zone supérieur
     *
     * @see JPanel
     */
	private JPanel jPanelNord;

	/**
     * Permet de mettre un affichage pour les boutons Start
     *
     * @see JPanelPressStart
     */
	private JPanelPressStart jPanelPressStart;

	/**
     * Permet d'avoir une frame spécial pour les instructions
     *
     * @see JFrame
     */
	private JFrame JFrameInstruction;

	/**
     * Le panel permetant aux instructions d'avoir lieu
     *
     * @see JPanel
     */
	private JPanel JPanelInsctruction;

	/**
     * Un tableau de <i><b>JLabel</b></i> represantant les dominos sélectionnés par les joueurs
     *
     * @see JLabel
     */
	private JLabel[] jLabelsDomSelectByPlayer;

	/**
     * Un tableau de <i><b>JLabel</b></i> represantant les précédents dominos sélectionnés par les joueurs
     *
     * @see JLabel
     */
    private JLabel[] jLabelsDomPreviousSelectByPlayer;

    /**
     * Permet d'avoir un affichage du nom du joueur
     *
     * @see JPanel
     */
	private JPanel jpanelNomJoueur;

	/**
     * Permet d'avoir un affichage pour le nombre de joueur
     *
     * @see JPanel
     */
	private JPanel jPanelNombreJoueur;

	/**
     * Permet de récupérer les dimensions de l'écran
     *
     * @see Dimension
     */
	Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

	/**
     * Contient la hauteur de l'écran utilisé
     *
     * @see Dimension
     */
	int height = (int)dimension.getHeight();

	/**
     * Contient la largeur de l'écran utilisé
     *
     * @see Dimension
     */
	int width  = (int)dimension.getWidth();

	/**
     * Un tableau de <i><b>JPanelRoyaume</b></i> représantant les différents royaumes des joueurs
     *
     * @see JPanelRoyaume
     */
	private JPanelRoyaume[] jPanelRoyaumes;

	/**
     * L'affichage des différents meunus <i>(Jouer, Quitter, ...)</i>
     *
     * @see JPanel
     */
	private JPanel panelMenuJouerQuiter;

	/**
     * L'affichage de la tuile sélectionnée
     *
     * @see JPanel
     */
	private JPanel jPanelTuileSelect;

	/**
     * Le bouton pour le contrôle de la tuile sélectionnée
     *
     * @see JButton
     */
	private JButton boutontuileSelect;

    /**
     * Le bouton permettant l'action de <b>jouer</b>
     *
     * @see Bouton
     */
	private Bouton boutonJouer;

    /**
     * Le bouton permettant l'action de <b>quitter</b>
     *
     * @see Bouton
     */
	private Bouton boutonQuitter;

    /**
     * Le bouton permettant l'action du <b>retour</b>
     *
     * @see Bouton
     */
	private Bouton boutonRetour;

    /**
     * Le bouton permettant d'accéder aux <b>instructions</b>
     *
     * @see Bouton
     */
	private Bouton instruction;

	/**
     * Controlleur de la rotation des tuiles
     *
     * @see ControlRotationTuile
     */
    private ControlRotationTuile controlRotationTuile;

    /**
     * Controlleur de la tuile à choisir
     *
     * @see ControlTuileAChoisir
     */
    private ControlTuileAChoisir controlTuileAChoisir;

    /**
     * L'endroit où faire apparaître le résultat graphique
     *
     * @see GraphicsDevice
     */
	static GraphicsDevice device;

	/**
     * L'image de fond pour le jeu
     *
     * @see Image
     */
	private Image fondKing;

	/**
     * Une image particulière pour l'interface au commencement qu jeu
     *
     * @see Image
     */
	private Image pressStart;

    /**
     * La page <b>1</b> des instructions
     *
     * @see Image
     */
	private Image instruction_img;

    /**
     * La page <b>2</b> des instructions
     *
     * @see Image
     */
	private Image instruction_img2;

    /**
     * La page <b>3</b> des instructions
     *
     * @see Image
     */
	private Image instruction_img3;

	/**
     * Permet la lecture des touches claviers appuyées
     *
     * @see KeyListener
     */
	private KeyListener keyListener;

	/**
     * Un tableau d'<b>Images</b> pour les instructions
     *
     * @see Image
     */
	private Image[] instructionTab;

	/**
     * Le controlleur pour les cases des différents royaumes
     *
     * @see ControlCaseRoyaume
     */
	private ControlCaseRoyaume controlCaseRoyaume;

	/**
     * Bouton pour sélectionner le mode <b>deux Joueurs</b>
     *
     * @see Bouton
     */
	private Bouton deuxJoueurs;

	private Bouton unJoueur;

    /**
     * Bouton pour sélectionner le mode <b>trois Joueurs</b>
     *
     * @see Bouton
     */
	private Bouton troisJoueurs;

    /**
     * Bouton pour sélectionner le mode <b>quatre Joueurs</b>
     *
     * @see Bouton
     */
	private Bouton quatreJoueurs;

	/**
     * Permet l'affichage des joueurs
     *
     * @see JLabel
     */
	private JLabel joueur;

    /**
     * Le bouton pour <b>valider</b> le choix
     *
     * @see Bouton
     */
	private Bouton valider;

	/**
     * Le bouton pour <b>passer le tour</b>
     *
     * @see Bouton
     */
	public Bouton boutonTour;

	/**
     * Tableau pour l'affichage des différents nom des joueurs
     *
     * @see JLabel
     */
	private JLabel[] labelNomJouerSelectionNom;

    /**
     * Perment de diviser l'intreface en 4 zones
     * <i><b>jPanelSouth</b></i> représente la zone inférieur
     *
     * @see JPanel
     */
	private JPanel jPanelSouth;

	/**
     * Permet d'afficher le domino
     *
     * @see JLabel
     */
	private JLabel dominoLabel;

	/**
     * Affiche le nom du joueur actif
     *
     * @see JLabel
     */
	private JLabel nomJoueurActif;

	/**
     * Un tableau représentant les tuiles à choisir
     *
     * @see JPanel
     */
	private JPanel[] jPanel2PartTuileAChoisir;

	/**
     * Un tableau repésentant les tuiles à placer
     *
     * @see JPanel
     */
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

	/**
     * Initialise les attributs pour le jeu
     * Affichage graphique des différents éléments, affichage des noms, royaumes...
     *
     * @see Bouton
     * @see ControlCaseRoyaume
     * @see ControlTuileAChoisir
     *
     * @throws IOException
     */
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

	/**
     * Initialise les attributs suivants les choix effectués par l'utilisateurs
     * Possiblilité de plusieurs joueurs <i>(2, 3, 4)</i>
     * Réglage de l'affichage des instructions
     * Gère le fait que la touche <b>Entrée</b> du clavier permet d'acéder à la fenêtre suivante
     *
     * @throws IOException
     *
     * @see Bouton
     * @see JFrame
     * @see ControlRotationTuile
     * @see KeyListener
     */
	private void initAtribut() throws IOException {
	    valider = new Bouton() ;
		valider.setText("Jouer");

		boutonRetour = new Bouton();
		boutonRetour.setText("Retour");

		JFrameInstruction = new JFrame("Instruction");
		JFrameInstruction.setSize(777,773);
        unJoueur = new Bouton();
        unJoueur.setText("Un rois");
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

	/**
     * Permet de mettre le jeu en plein écran
     *
     * @see JFrame
     * @see GraphicsDevice
     */
	private void setFullscreen(JFrame JFrame){
		device.setFullScreenWindow(JFrame);
	}

	/**
     * Initialise le royaume de chaque joueur un par un
     *
     * @throws IOException
     *
     * @see JPanelRoyaume
     * @see ModelTest
     * @see Joueur
     */
	private void initRoyaumeToutJoueur() throws IOException {
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jPanelRoyaumes[i] = new JPanelRoyaume(model,model.getJoueurs()[i].getId());
		}
	}

	/**
     * Initialise les boutons au centre
     * Initialise donc les <i>tuiles à placer</i> et les <i>tuiles à choisir</i>
     *
     * @see Bouton
     * @see ModelTest
     * @see Joueur
     */
	private void initBoutonCentre(){
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jButtonTuilleCentreAPlacer[i] = new Bouton();
			jButtonTuilleCentreAChoisir[i] = new Bouton();
		}
	}

	/**
     * Initialise les jetons des joueurs
     * Les joueurs seront représentés par des couronnes de couleurs
     *
     * @see Couleur
     * @see ModelTest
     * @see Domino
     * @see JLabel
     */
	private void initJLabelCouronneAChoisir(){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
			jLabelsDomSelectByPlayer[i] = new JLabel();
		}
	}

	/**
     * Initialise les couronnes des joueurs à placer
     *
     * @see ModelTest
     * @see JLabel
     */
	private void initJLabelCouronneAPlacer(){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
			jLabelsDomPreviousSelectByPlayer[i] = new JLabel();
		}
	}

	/**
     * Affiche les deux <i>types</i> tuiles au centre
     * Les tuiles à choisir et les tuiles à placer
     *
     * @throws IOException
     *
     * @see JPanel
     * @see ModelTest
     * @see JFrame
     */
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
			} else {
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
			} else {
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
     *
     * @see Bouton
     * @see JPanel
     * @see JFrame
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
	 *
     * @throws IOException the io exception
     *
     * @see BufferedImage
	 */
	public void tournerTuileSelect(int rot,int idDom) throws IOException {
        BufferedImage image = ImageIO.read(new File(donneCheminDomino(idDom,rot)));
        jPanelTuileSelect.remove(boutontuileSelect);
        afficheTuileSelect(new ImageIcon(image),idDom);
    }

	/**
	 * Update les royaumes de chaque joueurs
	 *
	 * @throws IOException the io exception
     *
     * @see ModelTest
     * @see JPanelRoyaume
	 */
	public void updateAllRoyaume() throws IOException {
	    for (int i = 0; i < model.getNbJoueur(); i++) {
		    jPanelRoyaumes[model.getJoueur(i).getId()].updateRoyaume();
	    }
    }

    /**
     * Affiche les royaumes suivant le nombre de joueurs <i>(2,3 ou 4)</i>
     * Le bouton <i><b>boutonTour</b></i> se nomme <i>Passer mon tour</i> et effectue l'action du même nom
     *
     * @see FenetreTest#boutonTour
     * @see FenetreTest#passerTour()
     * @see FenetreTest#bloquerToutRoyaumes(boolean)
     *
     * @throws IOException
     */
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
	 * @param rot          le degrée de rotation du domino
     *
     * @see Domino
     *
	 * @return le chemin de l'image désirée
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
	 * Création et application du listener sur tous les boutons
	 */
	public void setActionListenerTuileCentreAChoisir(){
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jButtonTuilleCentreAChoisir[i].addActionListener(controlTuileAChoisir);
		}
	}

	/**
	 * Permet qu'après la sélection d'un domino tous les royaumes soient bloqués sauf celui du joueur actuel
	 *
     * @see FenetreTest#tourIA()
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
     * L'IA peut prendre un domino parmi ceux disponible sinon l'IA passe son tour
     * Si l'IA a un domino, l'IA le place dans son royaume
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
		} else {
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
	 * Passer au joueur suivant
     * Vérifie si ce n'est pas le dernier tour, si c'est bien le dernier alors la partie se termine
     * Sinon, on commence un nouveau tour avec de nouveaux dominos
	 */
	public void passerTour() {
		try {
			nouvelleSelectionDomino();
			bloquerToutRoyaumes(true);
			if (model.getNbTour()<model.getNbTourMax()){
				setActionListenerTuileCentreAChoisir();
			} else {
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
	 * Elle est appeller quand le joueur à choisis sa tuile.
	 *
	 * @param b Permet de savoir si on bloque ou débloque les tuiles.
	 */
	public void bloquerToutBoutonAPlacerCentre(boolean b){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
		    jButtonTuilleCentreAPlacer[i].setEnabled(!b);
		}
	}

	/**
	 * Enlève les controller des boutons au centres une fois que le joueur à choisis.
	 */
	public void removeAllControlerAChoisirCentre(){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
		jButtonTuilleCentreAChoisir[i].removeActionListener(controlTuileAChoisir);
		}
	}

	/**
	 * Bloque le royaume du joueur désigné.
	 *
	 * @param b      Booléen permettant de bloquer ou débloquer le joueur
	 * @param joueur Le joueur désigné
	 */
	public void bloquerRoyaumeJoueur(boolean b, int joueur){
		jPanelRoyaumes[joueur].bloquerRoyaume(b);
	}

	/**
	 * On bloque ou débloque tout les royaume en fonction du paramètre.
	 *
	 * @param b Permet de bloquer ou débloquer le royaume de tout les joueurs.
	 */
	public void bloquerToutRoyaumes(boolean b){
		for (int i = 0; i < model.getNbJoueur(); i++) {
			jPanelRoyaumes[i].bloquerRoyaume(b);
		}
	}

	/**
     * Permet de bloquer les dominos au centre
     * Impossible pour un joueur de sélectionner plusieurs dominos ou de prendre un domino déjà réservé
     *
     * @param index     le domino à bloquer
     * @param b         Booléen bloquant ou non ledit domino
     */
	private void bloquerBoutonAPlacerCentre(int index, boolean b){
		jButtonTuilleCentreAPlacer[index].setEnabled(b);
	}

	/**
	 * Permet d'enlever le controller d'un bouton spécifique au centre.
	 *
	 * @param index indice du bouton dans le tableau de domino au centre.
	 */
	public void removeControlerBoutonAChoisrCentre(int index){
		jButtonTuilleCentreAChoisir[index].removeActionListener(controlTuileAChoisir);
	}

	/**
	 * On ajoute un controlleur sur un bouton spécifique au centre.
	 *
	 * @param index indice du bouton dans le tableau de domino au centre.
	 */
	public void addControlerBoutonAChoisrCentre(int index){
		jButtonTuilleCentreAChoisir[index].addActionListener(controlTuileAChoisir);
	}

	/**
	 * Une fois que tout les premiers domino on été séléctionner on les passes en temps que domino choisi
	 * et on affiche un nouveau tirage de domino à choisir.
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
	 * Ici on va changer de tour.
	 * On regarde d'abord si la partie est fini:
	 * - si oui on enlève les dominos au centre et on affiche une popup avec "Fin de partie" et on affiche les scores ensuites
	 * - si non on enlève les dominos du centre, on réaffiche les dominos séléctionner au tour précédent par les joueurs et on affiche une nouvelle séléction de domino.
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

	/**
     * Permet de bloquer un domino déja placé
     */
	private void bloquerBoutonDominoDejaPlacé(){
		for (int i = 0; i < model.getNbDominoCentre(); i++) {
				bloquerBoutonAPlacerCentre(i,!model.getDominoDejaPlace()[i]);
		}
	}


	/**
	 * Fonction qui permet d'afficher la fenêtre du jeu.
	 * On initialise les attributs du jeu et on enlève le menu à l'écran du joueur.
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
	 * Affichage de la première page de menu.
	 * Boutons présents : Jouer, instructions, Quitter
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

	/**
     * Système d'affichage suivant le nombre de joueurs sélectionné
     *
     * @throws IOException
     */
	private void afficherChoixNbJoueur() throws IOException {
		jFrame.remove(panelMenuJouerQuiter);
        unJoueur.setFont(new Font("Helvetica",Font.BOLD,70));
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
		joueurBox.add(unJoueur);
		joueurBox.add(deuxJoueurs);
		joueurBox.add(troisJoueurs);
		joueurBox.add(quatreJoueurs);
		jPanelNombreJoueur.add(joueurBox);
		jFrame.add(jPanelNombreJoueur);

		jFrame.revalidate();

		unJoueur.addActionListener(e ->{
		    model.setNbJoueur(1);
		    try{
		        choixNomJoueur(1);
            } catch (IOException e1){
		        e1.printStackTrace();
            }
        });

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
	 * Permet d'afficher les instructions de jeu pour savoir comment jouer.
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
	 * Permet de fermer le jeu.
	 * Une popup demande confirmation.
	 *
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
	 * Affichage des différents composants pour que le joueur puisse saisir son nom.
	 * Affichage des composants en fonction du nombre de joueur.
	 *
	 * @param nombreJoueur Paramètre concernant le nombre de joueur.
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
		    switch (model.getNbJoueurReel()){
                case 1:
                    model.setNomJoueur("Roi Jaune",1);
                    model.setNomJoueur("Roi rouge",2);
                    model.setNomJoueur("Roi vert",3);
                    break;
                case 2:
                    model.setNomJoueur("Roi Jaune",1);
                    model.setNomJoueur("Roi rouge",2);
                    break;
                case 3:
                    model.setNomJoueur("Roi Jaune",1);

            }
			for (int i = 0; i < model.getNbJoueurReel(); i++) {
				model.setNomJoueur(jTextField[i].getText(),i);

			}
            for (int i = 0; i < 4; i++) {
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
	 * Retourne la frame actuelle.
	 *
	 * @return the frame
	 */
	public JFrame getjFrame() {
		return jFrame;
	}

	/**
	 * Change le nom du label du joueur actuelle.
	 *
	 * @param posDom  Position du domino.
	 * @param couleur Couleur du joueur.
	 * @throws IOException the io exception
	 */
	public void changeLabelPlayer(int posDom, String couleur) throws IOException {
		final BufferedImage bi = ImageIO.read(new File("img/Roi"+couleur+".png"));
		jLabelsDomSelectByPlayer[posDom].setIcon(new ImageIcon(bi));
	}
}