package KingDomino;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;

public class Test extends JFrame
{

	/**
	 *
	 */
	private static final long serialVersionUID = -2707712944901661771L;

	public Test()
	{
		super("Hello, World!");

		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		Object[] objects = new Object[4];

		graph.getModel().beginUpdate();
		try
		{
			for (int i = 0; i < 4; i++) {
				 objects[i]= graph.insertVertex(parent, null, "Hello" +i, 20, 20, 80,
						30);
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					graph.insertEdge(parent, null, "Edge", objects[i] , objects[j]);
				}

			}

		}
		finally
		{
			graph.getModel().endUpdate();
		}

		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
	}

	public static void main(String[] args)
	{
		Test frame = new Test();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}

}
