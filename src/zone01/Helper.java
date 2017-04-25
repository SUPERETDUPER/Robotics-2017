package zone01;

import java.util.ArrayList;

public class Helper {
	public static void pickupObject() {
		Claw.openClaw();
		MyChassis.moveToUltrasonicDistance(
				GlobalConstants.DISTANCE_CLAW_TO_SENSOR,
				GlobalConstants.LINEAR_SPEED_SLOW);

		Lift.lowerLift();
		Claw.closeClaw();
	}
	public static void dropObject() {
		Lift.raiseLift();

		MyChassis.moveToUltrasonicDistance(
				GlobalConstants.DISTANCE_CLAW_TO_SENSOR,
				GlobalConstants.LINEAR_SPEED_SLOW);

		Claw.openClaw();
	}
	public static void executeSequence(ArrayList<BaseSequence> sequence) {
		for (int i = 0; i < sequence.size(); i++) {
			sequence.get(i).execute();
		}
	}
}
