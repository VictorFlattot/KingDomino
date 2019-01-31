import Control.ControlGroup;
import Model.ModelTest;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
			ModelTest model = new ModelTest();
			try {
				ControlGroup control = new ControlGroup(model);
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

}
