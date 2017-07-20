package zone01;

public class ActionRotate extends ActionBase {
	public static int LEFT = 0;
	public static int RIGHT = 1;
	public static int CENTER = 2;

	private final int angle;
	private final int rotateAround;

	/**
	 * Rotate robot
	 *
	 * @param angle angle to rotate by
	 */
	public ActionRotate(int angle, int rotateAround) {
		this.angle = angle;
		this.rotateAround = rotateAround;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see zone01.ActionBase#execute()
	 */
	@Override
	public void execute() {
		LineFollowerData.stop();
		if (rotateAround == LEFT) {
			MyChassis.get().arc(GlobalConstants.DISTANCE_BETWEEN_SENSORS / 2,
					angle);
		} else if (rotateAround == RIGHT) {
			MyChassis.get().arc(-GlobalConstants.DISTANCE_BETWEEN_SENSORS / 2,
					angle);
		} else if (rotateAround == CENTER) {
			MyChassis.get().rotate(angle);
		} else {
			throw new RuntimeException("Illegal argument for rotateAround");
		}
		MyChassis.get().waitComplete();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see zone01.ActionBase#getLogMessage()
	 *
	 * Ex: Rotate 60 degrees.
	 */
	@Override
	public String getLogMessage() {
		return "Rotate " + angle + " degrees.";
	}
}
