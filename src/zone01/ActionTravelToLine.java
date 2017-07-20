package zone01;

/**
 * @author superetduper
 *         Travel straight to the first line and the line up
 */
public class ActionTravelToLine extends ActionBase {
	private final boolean lineUp;

	public ActionTravelToLine(boolean lineUp) {
		this.lineUp = lineUp;
	}

	@Override
	public void execute() {
		// TODO : Method is not precise. Only corrects once.
		LineFollowerData.stop();
		MyChassis.get().setVelocity(GlobalConstants.LINEAR_SPEED_SLOW, 0);
		int side = ColorSensor.waitForColor(GlobalConstants.EITHER);
		if (lineUp) {
			if (side == GlobalConstants.LEFT) {
				MyChassis.get().arc(
						GlobalConstants.DISTANCE_BETWEEN_SENSORS / 2,
						Double.POSITIVE_INFINITY);
				ColorSensor.waitForColor(GlobalConstants.RIGHT);
			} else {
				MyChassis.get().arc(
						-GlobalConstants.DISTANCE_BETWEEN_SENSORS / 2,
						Double.POSITIVE_INFINITY);
				ColorSensor.waitForColor(GlobalConstants.LEFT);
			}
		}
		MyChassis.get().stop();
	}

	@Override
	public String getLogMessage() {
		String message = "Travel straight to line";
		if (lineUp) {
			message += " and line up";
		}
		message += ".";
		return message;
	}
}
