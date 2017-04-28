package zone01;

import java.util.ArrayList;

import lejos.robotics.Color;

public class Navigation {
	public static final Area A_ARM = new Area();
	public static final Area A_BASE = new Area();
	public static final Area A_CENTER = new Area();
	public static final Area A_START = new Area();

	// Starting location green square
	public static final Location L_START = new Location(new Area[]{A_START}) {

		@Override
		protected void buildOnSequence(ArrayList<ActionBase> sequence) {
			if (A_BASE.contains(getLocation())
					|| getLocation() == L_CENTER_FACING_LEFT) {
				Navigation.L_CENTER_FACING_RIGHT.buildOnSequence(sequence);
				sequence.add(new ActionFollowTillLine(true));
				sequence.add(new ActionRotate(90));
			}
			// TODO : Implement other methods
			sequence.add(new ActionFollowTillLine(true, Color.GREEN));
			sequence.add(new ActionTravel(
					GlobalConstants.DISTANCE_TO_ENTER_GREEN_ZONE));
		}
	};

	public static final Location L_CENTER_FACING_RIGHT = new Location(
			new Area[]{A_CENTER}) {

		@Override
		protected void buildOnSequence(ArrayList<ActionBase> sequence) {

			// TODO : Implement other methods
		}
	};

	public static final Location L_CENTER_FACING_LEFT = new Location(
			new Area[]{A_CENTER}) {

		@Override
		protected void buildOnSequence(ArrayList<ActionBase> sequence) {

			// TODO : Implement other methods
		}
	};

	public static final Location L_ARM_ONE_LEFT = new Location(
			new Area[]{A_ARM}) {

		@Override
		protected void buildOnSequence(ArrayList<ActionBase> sequence) {
			// TODO : Implement other methods

		}
	};

	public static final Location L_ARM_ONE_RIGHT = new Location(
			new Area[]{A_ARM}) {

		@Override
		protected void buildOnSequence(ArrayList<ActionBase> sequence) {
			// TODO : Implement other methods

		}
	};

	public static final Location L_CENTER_TURBINE_F_L = new Location(
			new Area[]{A_CENTER}) {

		@Override
		protected void buildOnSequence(ArrayList<ActionBase> sequence) {
			// TODO : Implement other methods
			// TODO Auto-generated method stub

		}
	};

	private static Location currentLocation = L_START;

	public static Location getLocation() {
		return currentLocation;
	}

	public static void updateLocation(Location newLocation) {
		currentLocation = newLocation;
	}

}