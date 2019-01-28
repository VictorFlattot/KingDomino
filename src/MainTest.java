import Control.ControlGroup;
import Model.ModelTest;

import java.io.IOException;

/**
 * The type Main test.
 */
public class MainTest {
	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
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
