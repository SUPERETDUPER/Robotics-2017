package zone01;

import java.util.ArrayList;

import lejos.robotics.Color;

/**
 * @author MCS
 *
 */
public class Navigation {
	public static final Area A_ARM; // Arms for turbine bases
	public static final Area A_TURBINE; // 5 Bases
	public static final Area A_CENTER; // Center of mat
	public static final Area A_TURBINE_NO_BASE;

	// Starting location green square
	public static final Location L_START;

	public static final Location L_CENTER_FR;
	public static final Location L_CENTER_FL;

	public static Location L_CENTER_TURBINE_FL;
	public static Location L_CENTER_TURBING_FR;
	public static Location L_TURBINE_GREEN;
	public static Location L_TURBINE_BLACK;
	public static Location L_TURBINE_BLUE;
	public static Location L_TURBINE_RED;
	public static Location L_TURBINE_YELLOW;

	public static Location L_ARM_ONE_L;
	public static Location L_ARM_ONE_R;
	public static Location L_ARM_TWO_L;
	public static Location L_ARM_TWO_R;
	public static Location L_ARM_THREE_L;
	public static Location L_ARM_THREE_R;

	private static Location currentLocation;

	static {

		/*
		 * Implemented for
		 * A_ARM
		 * A_TURBINE
		 * L_CENTER_FR
		 * L_CENTER_FL
		 */
		L_START = new Location("Starting Area") {
			/*
			 * (non-Javadoc)
			 *
			 * @see zone01.Location#buildOnSequence(java.util.ArrayList)
			 */
			@Override
			protected void buildOnSequence(ArrayList<ActionBase> sequence) {

				if (A_ARM.contains(getLocation())
						|| getLocation() == L_CENTER_FL
						|| getLocation() == L_CENTER_FR) {// IF LEFT SIDE GOTO
															// L_CENTER_FR THEN
															// MOVE TO
															// INTERSECTION THEN
															// TURN LEFT 90
					sequence = L_CENTER_FR.getSequence();

					sequence.add(new ActionFollowToIntersection());
					sequence.add(new ActionRotate(90, ActionRotate.LEFT));

				} else if (A_TURBINE.contains(getLocation())) { // IF RIGHT SIDE
																// GOTO
																// L_CENTER_TURBINE_FL
																// THEN GOTO
																// INTERSECTION
																// THEN TURN
																// RIGHT 90
					sequence = L_CENTER_TURBINE_FL.getSequence();

					sequence.add(new ActionFollowToIntersection());
					sequence.add(new ActionRotate(-90, ActionRotate.RIGHT));
				} else {
					throwUniplementedException();
				}

				sequence.add(new ActionFollowToIntersection(true, false,
						Color.GREEN));
				sequence.add(new ActionTravel(
						GlobalConstants.DISTANCE_TO_ENTER_GREEN_ZONE));
			}
		};
		/*
		 * Implements
		 * L_CENTER_FR
		 * L_START
		 * A_ARM
		 * A_TURBINE
		 */
		L_CENTER_FL = new Location("Center facing left") {
			/*
			 * (non-Javadoc)
			 *
			 * @see zone01.Location#buildOnSequence(java.util.ArrayList)
			 */
			@Override
			protected void buildOnSequence(ArrayList<ActionBase> sequence) {

				if (getLocation() == L_CENTER_FR
						|| A_ARM.contains(getLocation())) { // IF LEFT SIDE GOTO
															// L_CENTER_FR THEN
															// TURN AROUND
					sequence = L_CENTER_FR.getSequence();
					sequence.add(new ActionRotate(180, ActionRotate.CENTER));
				} else if (getLocation() == L_START) { // IF START GO OF GREEN
														// THEN GO TILL
														// INTERSECTION THEN
														// TURN RIGHT THEN
														// FOLLOW TILL
														// INTERSECTION

					sequence.add(new ActionTravel(
							GlobalConstants.DISTANCE_TO_LEAVE_GREEN_ZONE));
					sequence.add(new ActionFollowToIntersection());
					sequence.add(new ActionRotate(-90, ActionRotate.RIGHT));
					sequence.add(new ActionFollowToIntersection(false));

				} else if (A_TURBINE.contains(getLocation())) { // IF LEFT RIGHT
																// SIDE GOTO
																// L_CENTER_TURBINE_FL
																// THEN GO PASS
																// INTERSECTION
																// THEN GOTO
																// NEXT
																// INTERSECTION
					sequence = L_CENTER_TURBINE_FL.getSequence();

					sequence.add(new ActionFollowToIntersection(false, true));
					sequence.add(new ActionFollowToIntersection(false));
				} else {
					throwUniplementedException();
				}
			}
		};

		/*
		 * Implements
		 * A_TURBINE
		 * L_START
		 * L_CENTER_TURBINE_FL
		 * A_ARM
		 */
		L_CENTER_FR = new Location("Center facing right") {

			@Override
			protected void buildOnSequence(ArrayList<ActionBase> sequence) {
				if (A_TURBINE.contains(getLocation())
						|| getLocation() == L_START
						|| getLocation() == L_CENTER_TURBINE_FL) { // IF RIGHT,
																	// START OR
																	// CENTER
																	// FACING
																	// LEFT GOTO
																	// CENTER
																	// FACING
																	// LEFT AND
																	// TURN
																	// AROUND
					sequence = L_CENTER_FL.getSequence();

					sequence.add(new ActionRotate(180, ActionRotate.CENTER));
				} else if (A_ARM.contains(getLocation())) { // IF ARM TURN
															// AROUND
					sequence.add(new ActionRotate(180, ActionRotate.CENTER));
					if (getLocation() == L_ARM_ONE_L
							|| getLocation() == L_ARM_TWO_L
							|| getLocation() == L_ARM_THREE_L) {
						sequence.add(new ActionFollowToIntersection(false));
						sequence.add(new ActionRotate(-45, ActionRotate.RIGHT));
					} else if (getLocation() == L_ARM_ONE_R
							|| getLocation() == L_ARM_TWO_R
							|| getLocation() == L_ARM_THREE_R) {
						sequence.add(new ActionFollowToIntersection(true));
						sequence.add(new ActionRotate(45, ActionRotate.LEFT));
					}

					if (getLocation() == L_ARM_ONE_L
							|| getLocation() == L_ARM_ONE_R
							|| getLocation() == L_ARM_THREE_L
							|| getLocation() == L_ARM_THREE_R) {
						sequence.add(new ActionFollowToIntersection(true));
						sequence.add(new ActionRotate(45, ActionRotate.LEFT));

					} else if (getLocation() == L_ARM_TWO_L
							|| getLocation() == L_ARM_TWO_R) {
						sequence.add(new ActionFollowToIntersection(false));
						sequence.add(
								new ActionRotate(-45, ActionRotate.CENTER));
					} else {
						throwUniplementedException();
					}

				} else {
					throwUniplementedException();
				}
			}
		};

		/*
		 * Implements
		 * A_ARM
		 * A_CENTER
		 * A_TURBINE
		 * L_START
		 */
		L_CENTER_TURBING_FR = new Location("Turbine base facing right") {

			@Override
			protected void buildOnSequence(ArrayList<ActionBase> sequence) {
				if (A_ARM.contains(getLocation())
						|| A_CENTER.contains(getLocation())) { // IF LEFT GOTO
																// L_CENTER_FR
																// THEN PASS
																// NEXT
																// INTERSECTION
					sequence = L_CENTER_FR.getSequence();

					sequence.add(new ActionFollowToIntersection(false, true));
				} else if (A_TURBINE.contains(getLocation())) { // IF RIGHT GO
																// TO
																// L_CENTER_TURBINE_FL
																// THEN TURN
																// AROUND
					sequence = L_CENTER_TURBINE_FL.getSequence();
					sequence.add(new ActionRotate(180, ActionRotate.CENTER));
				} else if (getLocation() == L_START) {
					sequence.add(new ActionTravel(
							GlobalConstants.DISTANCE_TO_LEAVE_GREEN_ZONE));
					sequence.add(new ActionFollowToIntersection());
					sequence.add(new ActionRotate(90, ActionRotate.LEFT));
				}
				/*
				 * } else if (getLocation() == L_TURBINE_BLACK) {
				 * sequence.add(new ActionRotate(180, ActionRotate.CENTER));
				 * sequence.add(new ActionFollowToIntersection(true));
				 * sequence.add(new ActionRotate(45, ActionRotate.LEFT));
				 *
				 * } else if (getLocation() == L_TURBINE_GREEN) {
				 * sequence.add(new ActionRotate(180, ActionRotate.CENTER));
				 * sequence.add(new ActionFollowToIntersection(false));
				 * sequence.add(new ActionRotate(-45, ActionRotate.RIGHT));
				 * }
				 */
				else {
					throwUniplementedException();
				}
			};
		};

		A_ARM = new Area(new Location[]{L_ARM_ONE_L, L_ARM_ONE_R, L_ARM_TWO_L,
				L_ARM_TWO_R, L_ARM_THREE_L, L_ARM_THREE_R}); // All
																// arms

		A_TURBINE = new Area(new Location[]{L_CENTER_TURBINE_FL,
				L_CENTER_TURBING_FR, L_TURBINE_RED, L_TURBINE_BLACK,
				L_TURBINE_GREEN, L_TURBINE_BLUE, L_TURBINE_YELLOW}); // All
																		// turbines
																		// and
																		// turbine
																		// base

		A_CENTER = new Area(new Location[]{L_CENTER_FL, L_CENTER_FR}); // Center

		A_TURBINE_NO_BASE = new Area(
				new Location[]{L_TURBINE_RED, L_TURBINE_BLACK, L_TURBINE_GREEN,
						L_TURBINE_BLUE, L_TURBINE_YELLOW});

		currentLocation = L_START;
	}

	public static Location getLocation() {
		return currentLocation;
	}

	public static void updateLocation(Location newLocation) {
		currentLocation = newLocation;
	}
}
