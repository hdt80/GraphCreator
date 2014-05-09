import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	public static ArrayList<Node> nodeList = new ArrayList<Node>();
	public static ArrayList<Edge> edgeList = new ArrayList<Edge>();
	public static boolean[][] adjMatrix = new boolean[nodeList.size()][nodeList
			.size()];

	public GraphPanel() {
		super();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		// drawing all the nodes
		for (int i = 0; i < nodeList.size(); i++) {
			Node n = nodeList.get(i);
			g.setColor(Color.BLACK);
			if (n.gethiLt() == true) {
				g.setColor(Color.BLUE);
				g.drawOval(n.getX() - n.getR() / 2, n.getY() - n.getR() / 2,
						n.getR(), n.getR());
			} else {
				g.setColor(Color.BLACK);
				g.drawOval(n.getX() - n.getR() / 2, n.getY() - n.getR() / 2,
						n.getR(), n.getR());
			}
			g.setColor(Color.BLACK);
			g.drawString(n.getLabel(), n.getX(), n.getY());
		}
		// drawing all the edges
		for (int i = 0; i < edgeList.size(); i++) {
			g.setColor(Color.BLACK);
			Edge e = edgeList.get(i);
			g.drawLine(e.getOne().getX(), e.getOne().getY(), e.getTwo().getX(),
					e.getTwo().getY());
		}
	}

	public void addNode(Node node) {
		nodeList.add(node);
	}

	public void addEdge(Edge edge) {
		edgeList.add(edge);
	}

	public Node checkForNode(int x, int y) {
		for (int i = 0; i < nodeList.size(); i++) {
			Node n = nodeList.get(i);
			// using this a^2 + b^2 = kog getting wrecked
			double radius = Math.sqrt(Math.pow(x - n.getX(), 2)
					+ Math.pow(y - n.getY(), 2));
			if (radius < n.getR()) {
				return n;
			}
		}
		return null;
	}
}
