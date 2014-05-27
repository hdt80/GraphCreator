import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GraphCreator implements ActionListener, MouseListener {
	// setting up all the states
	int state;
	final int NODE_CREATE = 1;
	final int EDGE_CREATE = 2;
	final int EDGE_CREATE_SECOND = 3;
	final int SALESMAN_RUNNING = 4;
	final int SALESMAN_RUNNING_TWO = 5;
	// set up the frame and containers
	JFrame frame = new JFrame();
	static GraphPanel panel = new GraphPanel(); // gotta be static for Node
	Container west = new Container();
	Container east = new Container();
	Container south = new Container();
	// adding node and edge's stuff
	JButton addNode = new JButton("Node");
	JButton addEdge = new JButton("Edge");
	JTextField labels = new JTextField("A");
	// test connection buttons
	JButton testConnection = new JButton("Test connection");
	JTextField testConnectionA = new JTextField("Node A");
	JTextField testConnectionB = new JTextField("Node B");
	// traveling salesman stuff
	JButton salesman = new JButton("Travelling salesman");
	JTextField salesmanTF = new JTextField();
	boolean running = false;
	// used for making edges
	Node tempNodeOne;
	ArrayList<Path> completed = new ArrayList<Path>();
	boolean start = false;

	public static void main(String[] args) {
		new GraphCreator();
	}

	public GraphCreator() {
		// set up the frame
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		// setting up the layouts
		frame.setLayout(new BorderLayout());
		frame.add(west, BorderLayout.WEST);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(east, BorderLayout.EAST);
		frame.add(south, BorderLayout.SOUTH);
		// set up the container west
		west.setLayout(new GridLayout(3, 1));
		west.add(addNode);
		west.add(addEdge);
		west.add(labels);
		// set up container east
		east.setLayout(new GridLayout(3, 1));
		east.add(testConnectionA);
		east.add(testConnectionB);
		east.add(testConnection);
		// set up container south
		south.setLayout(new GridLayout(1, 2));
		south.add(salesmanTF);
		south.add(salesman);
		// add listeners
		panel.addMouseListener(this);
		addNode.addActionListener(this);
		addEdge.addActionListener(this);
		testConnection.addActionListener(this);
		salesman.addActionListener(this);
		// set the state
		state = NODE_CREATE;
		// set the button's backgrounds
		addNode.setBackground(Color.GREEN);
		addEdge.setBackground(Color.LIGHT_GRAY);
		testConnection.setBackground(Color.GRAY);
		salesman.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (state == NODE_CREATE) {
			panel.addNode(new Node(e.getX(), e.getY(), labels.getText()));
			state = NODE_CREATE;
		}
		if (state == EDGE_CREATE) {
			if (isInt(salesmanTF.getText()) == true) {
				for (int i = 0; i < panel.nodeList.size(); i++) {
					panel.nodeList.get(i).sethiLt(false);
					frame.repaint();
				}
				if (panel.checkForNode(e.getX(), e.getY()) != null) {
					tempNodeOne = panel.checkForNode(e.getX(), e.getY());
					tempNodeOne.sethiLt(true);
					state = EDGE_CREATE_SECOND;
				} else {
					System.out.println("No node found!");
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Your edge isn't a int. Use an int.", "",
						JOptionPane.NO_OPTION);
			}
		}
		if (state == EDGE_CREATE_SECOND) {
			if (panel.checkForNode(e.getX(), e.getY()) != null) {
				if (!panel.checkForNode(e.getX(), e.getY()).equals(tempNodeOne)) {
					panel.addEdge(new Edge(tempNodeOne, panel.checkForNode(
							e.getX(), e.getY()), labels.getText()));
					tempNodeOne.sethiLt(false);
					state = EDGE_CREATE;
					tempNodeOne = null;
				}
			}
		}
		frame.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addNode)) {
			addNode.setBackground(Color.GREEN);
			addEdge.setBackground(Color.LIGHT_GRAY);
			salesman.setBackground(Color.LIGHT_GRAY);
			state = NODE_CREATE;
		}
		if (e.getSource().equals(addEdge)) {
			addNode.setBackground(Color.LIGHT_GRAY);
			addEdge.setBackground(Color.GREEN);
			salesman.setBackground(Color.LIGHT_GRAY);
			state = EDGE_CREATE;
		}
		if (e.getSource().equals(testConnection)) {
			// make sure they exist or else bad stuff happends
			if (panel.nodeExists(testConnectionA.getText()) == false) {
				JOptionPane.showMessageDialog(null, testConnection.getText()
						+ " doesn't exist.", "", JOptionPane.NO_OPTION);
			} else if (panel.nodeExists(testConnectionB.getText()) == false) {
				JOptionPane.showMessageDialog(null, testConnectionB.getText()
						+ " doesn't exist.", "", JOptionPane.NO_OPTION);
			} else {
				Q q = new Q();
				ArrayList<String> connectedList = new ArrayList<String>();
				connectedList.add(panel.getNode(testConnectionA.getText())
						.getLabel());
				ArrayList<String> edges = panel
						.getConnectedLabels(testConnectionA.getText());
				for (int a = 0; a < edges.size(); a++) {
					// walk through the edges and add them to the q
					q.enqueue(edges.get(a));
				}
				while (q.isEmpty() == false) {
					String currentNode = q.dequeue();
					if (!connectedList.contains(currentNode)) {
						connectedList.add(currentNode);
					}
					edges = panel.getConnectedLabels(currentNode);
					for (int a = 0; a < edges.size(); a++) {
						// check each edge for if its connected
						if (!connectedList.contains(edges.get(a))) {
							q.enqueue(edges.get(a));
						}
					}
				}
				if (connectedList.contains(testConnectionB.getText())) {
					JOptionPane.showMessageDialog(null, "They are connected",
							"", JOptionPane.NO_OPTION);
				} else {
					JOptionPane
							.showMessageDialog(null, "They are not connected",
									"", JOptionPane.NO_OPTION);
				}
			}
		}
		if (e.getSource().equals(salesman)) {
			completed.clear();
			start = true;
			// check if node exists
			boolean real = false;
			for (int i = 0; i < panel.nodeList.size(); i++) {
				if (panel.nodeList.get(i).getLabel()
						.equals(salesmanTF.getText())) {
					real = true;
				}
			}
			if (real == true) {
				traveling(panel.getNode(salesmanTF.getText()),
						new ArrayList<Node>(), 0);
				// if there are no paths
				if (completed.isEmpty() == true) {
					JOptionPane
							.showMessageDialog(panel,
									"no working paths seem to exist!\nPlease try a different start value.");
				} else {// find the shortest route
					Path tmp = completed.get(0);
					for (int i = 0; i < completed.size(); i++) {
						if (completed.get(i).getDistance() < tmp.getDistance()) {
							tmp = completed.get(i);
						}
					}
					String output = "";
					for (int j = 0; j < tmp.path.size(); j++) {
						output = output + tmp.path.get(j).getLabel() + "  ";
					}
					JOptionPane.showMessageDialog(null, output,
							"Most Efficient Path:", JOptionPane.NO_OPTION);
					JOptionPane.showMessageDialog(null, tmp.getDistance(),
							"Distance:", JOptionPane.NO_OPTION);
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Not a valid starting path!", "ERROR!",
						JOptionPane.NO_OPTION);
			}
		}
		printState();
	}

	public void printState() {
		System.out.println(state);
	}

	public boolean isInt(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public void traveling(Node n, ArrayList<Node> path, int total) {
		if (start == true) {
			path.add(n);
			start = false;
		}
		if (path.size() == panel.nodeList.size()) {
			// add path to the list
			ArrayList<Node> path1 = new ArrayList<Node>();
			for (int i = 0; i < path.size(); i++) {
				path1.add(path.get(i));
			}
			completed.add(new Path(path1));
			completed.get(completed.size() - 1).addDistance(total);
			// remove last node
			path.remove(path.size() - 1);
			return;
			// loop through each of the edges
		}
		Edge edge = null;
		for (int i = 0; i < panel.edgeList.size(); i++) {
			edge = panel.edgeList.get(i);
			if (edge.getConnectedEdge(n) != null) {
				boolean isin = false; // checking if it's in the path
				for (int j = 0; j < path.size(); j++) {
					if (path.get(j).equals(edge.getConnectedEdge(n)) == true) {
						isin = true;
					}
				}
				if (isin == false) { // gotta put it in
					path.add(edge.getConnectedEdge(n));
					int edgecost = Integer.parseInt(edge.getLabel());
					int temp = total + edgecost;
					traveling(edge.getConnectedEdge(n), path, temp);
				}
			}

		}
		// remove node
		path.remove(n);
	}

}
