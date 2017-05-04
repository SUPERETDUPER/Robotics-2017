package zone01;

public abstract class Connection {
	private Node start;
	private Node end;

	public Connection(Node startNode, Node endNode) {
		start = startNode;
		end = endNode;
	}

	public Node getEndNode() {
		return end;
	}

	public abstract void moveOverConnection();
}
