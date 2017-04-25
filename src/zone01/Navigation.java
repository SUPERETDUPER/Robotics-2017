package zone01;

import java.util.ArrayList;

public class Navigation {

	// Starting location green square
	public static final Location START = new Location() {

		@Override
		protected void addToSequence(ArrayList<BaseSequence> sequence,
				Location currentLocation) {

		}
	};

	public static final Location CENTER_FACING_RIGHT = new Location() {

		@Override
		protected void addToSequence(ArrayList<BaseSequence> sequence,
				Location currentLocation) {

		}
	};

	public static final Location CENTER_FACING_LEFT = new Location() {

		@Override
		protected void addToSequence(ArrayList<BaseSequence> sequence,
				Location currentLocation) {

		}
	};

	public static final Location ARM_ONE_LEFT = new Location() {

		@Override
		protected void addToSequence(ArrayList<BaseSequence> sequence,
				Location currentLocation) {

		}
	};

	public static final Location ARM_ONE_RIGHT = new Location() {

		@Override
		protected void addToSequence(ArrayList<BaseSequence> sequence,
				Location currentLocation) {

		}
	};

	private static Location currentLocation = START;

	public static Location getLocation() {
		return currentLocation;
	}

	public static void updateLocation(Location newLocation) {
		currentLocation = newLocation;
	}

}
