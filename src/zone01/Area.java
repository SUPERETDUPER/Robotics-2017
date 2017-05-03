package zone01;

import java.util.ArrayList;

/**
 * @author superetduper
 *         Group of Location stored together for easy sorting
 *         Stores only location id
 */
public class Area {
	private final ArrayList<Location> locations = new ArrayList<>();

	public Area() {
		this(new Location[]{});
	}

	/**
	 * Create an Area
	 *
	 * @param locations
	 */
	public Area(Location[] locations) {
		for (int i = 0; i < locations.length; i++) {
			this.locations.add(locations[i]);
		}
	}

	/**
	 * Add location to group
	 *
	 * @param location
	 */
	public void add(Location location) {
		this.locations.add(location);
	}

	/**
	 * Check if location is in group
	 *
	 * @param location
	 * @return
	 */
	public boolean contains(Location location) {
		if (locations.contains(location)) {
			return true;
		}
		return false;
	}
}
