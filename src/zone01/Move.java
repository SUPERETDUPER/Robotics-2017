package zone01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Move {
	private final Node destination;

	public Move(Node destination) {
		this.destination = destination;
	}

	public ArrayList<Node> getPath() {
		// Visited nodes
		ArrayList<Node> visited = new ArrayList<>();

		// Queue containing path
		LinkedList<ArrayList<Node>> queue = new LinkedList<>();

		// Add current location to visited
		visited.add(Navigation.getLocation());

		// Add current path (just currentLocation) to queue
		queue.add(new ArrayList<>(Arrays.asList(Navigation.getLocation())));

		// current path in graph
		ArrayList<Node> current;

		// End node
		Node last;

		while (queue.size() != 0) {

			current = queue.remove();
			last = current.get(-1);

			if (last == destination) {
				return current;
			}

			// Nodes connected
			Node[] connectedNodes = last.getConnectedNodes();

			for (int i = 0; i < connectedNodes.length; i++) {
				if (!visited.contains(connectedNodes[i])) {
					// Add node to visited
					visited.add(connectedNodes[i]);

					// Create new path with node on it
					ArrayList<Node> newPath = current;
					newPath.add(connectedNodes[i]);
					// Add new path to queue
					queue.add(newPath);
				}
			}
		}
		return new ArrayList<Node>();
	}
}