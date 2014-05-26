import java.util.ArrayList;

public class Q {
	public static ArrayList<String> q = new ArrayList<String>();

	public void enqueue(String s) {
		q.add(s);
	}

	public String dequeue() {
		String s = q.get(0);
		q.remove(s);
		return s;
	}

	public boolean isEmpty() {
		return q.isEmpty();
	}
}
