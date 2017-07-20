package zone01;

public class Navigation {
	private static Node currentLocation;

	public static Node getLocation() {
		return currentLocation;
	}

	public static void setLocation(Node location) {
		currentLocation = location;
	}
}
