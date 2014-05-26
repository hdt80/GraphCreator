import java.util.ArrayList;

public class Path {
	ArrayList<Node> path = new ArrayList<Node>();
	String Label;
	int distance = 0;

	public Path(ArrayList<Node> path) {
		this.path = path;

	}

	public Path(String nlabel) {
		this.Label = nlabel;
	}

	public ArrayList<Node> getPath() {
		return path;
	}

	public void addPath(Node c) {
		path.add(c);
	}

	public int getDistance() {
		return distance;
	}

	public void addDistance(int d) {
		distance = distance + d;
	}
}
