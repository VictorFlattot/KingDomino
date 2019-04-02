package Control;

import Vues.FenetreTest;
import Model.ModelTest;

import java.io.IOException;

/**
 * The type Control group.
 */
public class ControlGroup {

    /**
     * Utilisation du modèle via la classe ModelTest
     *
     * @see ModelTest
     */
	private ModelTest model;

    /**
     * Utilisation de la fenetre via la classe FenetreTest
     *
     * @see FenetreTest
     */
	private FenetreTest fenetreTest;

	/**
	 * Instantiates a new Control group.
	 *
	 * @param model the model
	 * @throws IOException the io exception
	 */
	public ControlGroup(ModelTest model) throws IOException {
		this.model = model;
		this.fenetreTest = new FenetreTest(model);
	}
}
