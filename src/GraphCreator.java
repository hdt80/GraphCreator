import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GraphCreator implements ActionListener, MouseListener {
	int state;
	final int NODE_CREATE = 1;
	final int EDGE_CREATE = 2;
	final int EDGE_CREATE_SECOND = 3;
	JFrame frame = new JFrame();
	GraphPanel panel = new GraphPanel();
	JButton addNode = new JButton("Node");
	JButton addEdge = new JButton("Edge");
	JTextField labels = new JTextField();
	Container west = new Container();
	Node tempNodeOne;

	public static void main(String[] args) {
		new GraphCreator();
	}

	public GraphCreator() {
		// set up the frame
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		// set up the container west
		west.setLayout(new GridLayout(3, 1));
		west.add(addNode);
		west.add(addEdge);
		west.add(labels);
		// add the container
		frame.add(west, BorderLayout.WEST);
		// add listeners
		panel.addMouseListener(this);
		addNode.addActionListener(this);
		addEdge.addActionListener(this);
		// set the state
		state = NODE_CREATE;
		// set the button's backgrounds
		addNode.setBackground(Color.GREEN);
		addEdge.setBackground(Color.GRAY);
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
		}
		if (state == EDGE_CREATE_SECOND) {
			if (panel.checkForNode(e.getX(), e.getY()) != null) {
				if (!panel.checkForNode(e.getX(), e.getY()).equals(tempNodeOne)) {
					panel.addEdge(new Edge(tempNodeOne, panel.checkForNode(
							e.getX(), e.getY()), labels.getText()));
					tempNodeOne.sethiLt(false);
					state = EDGE_CREATE;
					tempNodeOne = null;
					System.out.println("Creating edge sir");
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
			state = NODE_CREATE;
		}
		if (e.getSource().equals(addEdge)) {
			addNode.setBackground(Color.LIGHT_GRAY);
			addEdge.setBackground(Color.GREEN);
			state = EDGE_CREATE;
		}
	}

}
