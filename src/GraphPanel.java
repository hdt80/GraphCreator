import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	private static final long serialVersionUID = 3248722979483760432L;
	public ArrayList<Node> nodeList = new ArrayList<Node>();
	public ArrayList<Edge> edgeList = new ArrayList<Edge>();

	ArrayList<ArrayList<Boolean>> adjMatrix = new ArrayList<ArrayList<Boolean>>();
	public boolean[][] adjcMatrix = new boolean[nodeList.size()][nodeList
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
			g.drawString(e.getLabel(),
					(e.getOne().getX() + e.getTwo().getX()) / 2, (e.getOne()
							.getY() + e.getTwo().getY()) / 2);
		}
	}

	// returns all of the connected Nodes
	public ArrayList<String> getConnectedLabels(String label) {
		ArrayList<String> toReturn = new ArrayList<String>();
		int b = getIndex(label);
		for (int i = 0; i < adjMatrix.size(); i++) {
			if (adjMatrix.get(b).get(i)
					&& (nodeList.get(i).getLabel().equals(label) == false)) {
				toReturn.add(nodeList.get(i).getLabel());
			}
		}
		return toReturn;
	}

	// returns a Node's index from a string
	public int getIndex(String s) {
		for (int i = 0; i < nodeList.size(); i++) {
			Node c = nodeList.get(i);
			if (s.equals(c.getLabel())) {
				return i;
			}
		}
		return -1;
	}

	// adds a node
	public void addNode(Node node) {
		nodeList.add(node);
		adjMatrix.add(new ArrayList<Boolean>());
		for (int i = 0; i < adjMatrix.size() - 1; i++) {
			adjMatrix.get(i).add(false);
		}
		for (int i = 0; i < adjMatrix.size(); i++) {
			adjMatrix.get(adjMatrix.size() - 1).add(false);
		}
		// legacy code when I was using a boolean[][]
		/*
		 * boolean[][] tempMatrix = new
		 * boolean[adjcMatrix.length][adjcMatrix.length]; for (int i = 0; i <
		 * tempMatrix.length; i++) { for (int j = 0; j < tempMatrix.length; j++)
		 * { tempMatrix[i][j] = adjcMatrix[i][j]; } } // print temp matrix for
		 * (int i = 0; i < tempMatrix.length; i++) { for (int j = 0; j <
		 * tempMatrix.length; j++) { System.out.println(i + ", " + j + ": " +
		 * tempMatrix[i][j] + ""); } } System.out.println("============");
		 * adjcMatrix = new boolean[tempMatrix.length + 1][tempMatrix.length +
		 * 1]; // System.out.println("tempMatrix lenght: " + adjcMatrix.length);
		 * for (int i = 1; i < adjcMatrix.length - 1; i++) { for (int j = 1; j <
		 * adjcMatrix.length - 1; j++) { // System.out.println(i + ", " + j);
		 * adjcMatrix[i][j] = tempMatrix[i][j]; } }
		 */
		printAdjMatrix();
		// printAdjcMatrix();
	}

	// add a line connecting two nodes
	public void addEdge(Edge edge) {
		edgeList.add(edge);
		int firstIndex = 0;
		int secondIndex = 0;
		for (int i = 0; i < nodeList.size(); i++) {
			if (edge.getOne().equals(nodeList.get(i))) {
				firstIndex = i;
			}
			if (edge.getTwo().equals(nodeList.get(i))) {
				secondIndex = i;
			}
		}
		// adjcMatrix[firstIndex][secondIndex] = true;
		// adjcMatrix[secondIndex][firstIndex] = true;
		adjMatrix.get(firstIndex).set(secondIndex, true);
		adjMatrix.get(secondIndex).set(firstIndex, true);
		printAdjMatrix();
		// printAdjcMatrix();
	}

	// check for a node in a x and y
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

	// prints the adjMatrix
	public void printAdjMatrix() {
		for (int i = 0; i < adjMatrix.size(); i++) {
			for (int j = 0; j < adjMatrix.size(); j++) {
				System.out.print(adjMatrix.get(i).get(j) + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	@Deprecated
	public void printAdjcMatrix() {
		System.out.println("adjcMatrix.lenght: " + adjcMatrix.length);
		for (int i = 0; i < adjcMatrix.length; i++) {
			for (int j = 0; j < adjcMatrix.length; j++) {
				System.out.println(adjcMatrix[i][j] + "\t");
			}
			System.out.println("");
		}
		System.out.println("");
	}

	// check a string for a node's name
	public boolean nodeExists(String s) {
		for (int i = 0; i < nodeList.size(); i++) {
			if (s.equals(nodeList.get(i).getLabel())) {
				return true;
			}
		}
		return false;
	}

	// gets a node from a name
	public Node getNode(String s) {
		for (int i = 0; i < nodeList.size(); i++) {
			Node n = nodeList.get(i);
			if (s.equals(n.getLabel())) {
				return n;
			}
		}
		return null;
	}

}
