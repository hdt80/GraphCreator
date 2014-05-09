public class Edge {
	Node one;
	Node two;
	String label;

	public Edge(Node one, Node two, String label) {
		this.one = one;
		this.two = two;
		this.label = label;
	}

	public Node getOne() {
		return one;
	}

	public void setOne(Node one) {
		this.one = one;
	}

	public Node getTwo() {
		return two;
	}

	public void setTwo(Node two) {
		this.two = two;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
