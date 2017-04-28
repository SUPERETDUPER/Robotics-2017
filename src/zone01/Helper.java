package zone01;

import java.util.ArrayList;

public class Helper {
	public static void dropObject() {
		Lift.raiseLift();

		MyChassis.moveToUltrasonicDistance(
				GlobalConstants.DISTANCE_CLAW_TO_SENSOR,
				GlobalConstants.LINEAR_SPEED_SLOW, false);

		Claw.openClaw();
	}
	public static void executeSequence(ArrayList<ActionBase> sequence) {
		for (int i = 0; i < sequence.size(); i++) {
			sequence.get(i).execute();
		}
	}
	public static void pickupObject() {
		Claw.openClaw(true);
		Lift.raiseLift();
		MyChassis.moveToUltrasonicDistance(
				GlobalConstants.DISTANCE_CLAW_TO_SENSOR,
				GlobalConstants.LINEAR_SPEED_SLOW, true);

		Lift.lowerLift();
		Claw.closeClaw();
	}
}
