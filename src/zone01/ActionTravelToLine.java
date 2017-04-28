package zone01;

public class ActionTravelToLine implements ActionBase {
	private final boolean lineUp;

	public ActionTravelToLine(boolean lineUp) {
		this.lineUp = lineUp;
	}

	@Override
	public void execute() {
		MyChassis.get().setVelocity(GlobalConstants.LINEAR_SPEED_SLOW, 0);
		boolean isLeftSide = ColorSensor.waitForLineEither();
		if (lineUp) {
			if (isLeftSide) {
				MyChassis.get().setVelocity(0,
						GlobalConstants.ANGULAR_SPEED_SLOW);
				ColorSensor.waitForLineRight();
			} else {
				MyChassis.get().setVelocity(0,
						-GlobalConstants.ANGULAR_SPEED_SLOW);
				ColorSensor.waitForLineLeft();
			}
		}
		MyChassis.get().stop();
	}
}
