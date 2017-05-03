package zone01;

public class ActionRotate extends ActionBase {

	private int angle;

	/**
	 * Rotate robot
	 *
	 * @param angle angle to rotate by
	 */
	public ActionRotate(int angle) {
		this.angle = angle;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see zone01.ActionBase#execute()
	 */
	@Override
	public void execute() {
		LineFollowerData.stop();
		MyChassis.get().rotate(angle);
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
