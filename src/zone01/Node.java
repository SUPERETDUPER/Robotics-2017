package zone01;

import java.util.ArrayList;

public class Node {
	private ArrayList<Connection> connections;

	public Node() {

	}

	public void addConnection(Connection connection) {
		connections.add(connection);
	}

	public Node[] getConnectedNodes() {
		Node[] nodes = new Node[connections.size()];
		for (int i = 0; i < connections.size(); i++) {
			nodes[i] = connections.get(i).getEndNode();
		}
		return nodes;
	}

}
