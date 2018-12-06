package Control;

import Fenetre.FenetreTest;
import Model.ModelTest;

import java.io.IOException;

public class ControlGroup {
	private ModelTest model;
	private FenetreTest fenetreTest;
	private ControlTuileCentre controlTuileCentre;
	private ControlCaseRoyaume controlCaseRoyaume;

	public ControlGroup(ModelTest model) throws IOException {
		this.model = model;
		this.fenetreTest = new FenetreTest(model);
		this.controlTuileCentre = new ControlTuileCentre(model,fenetreTest);
		this.controlCaseRoyaume = new ControlCaseRoyaume(model,fenetreTest);

	}
}
