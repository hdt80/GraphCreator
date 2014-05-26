public class Node {
	int x;
	int y;
	int r;
	int id;
	String label;
	boolean hiLt;
	GraphPanel panel = GraphCreator.panel;

	public Node(int x, int y, String label) {
		this.x = x;
		this.y = y;
		this.r = 40;
		this.label = label;
		hiLt = false;
		id = panel.nodeList.size();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public boolean gethiLt() {
		return hiLt;
	}

	public void sethiLt(boolean b) {
		hiLt = b;
	}

	// public int getID() {
	// return id;
	// }

}
