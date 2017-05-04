package zone01;

public abstract class Connection {
	private Node end;

	public Connection(Node endNode) {
		end = endNode;
	}

	public void execute() {
		getOperation().execute();
	}

	public Node getEndNode() {
		return end;
	}

	protected abstract ActionBase getOperation();
}
