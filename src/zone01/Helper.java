package zone01;

class Helper {
	public static void dropObject() {
		Lift.raiseLift();

		MyChassis.moveToUltrasonicDistance(
				GlobalConstants.DISTANCE_CLAW_TO_SENSOR,
				GlobalConstants.LINEAR_SPEED_SLOW, false);

		Claw.openClaw();
	}

	public static void pickupObject() {
		Claw.openClaw(true);
		MyChassis.moveToUltrasonicDistance(
				GlobalConstants.DISTANCE_CLAW_TO_SENSOR,
				GlobalConstants.LINEAR_SPEED_SLOW, true);

		Lift.lowerLift();
		Claw.closeClaw();
	}
}
