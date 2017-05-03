package zone01;

public class ActionTravel extends ActionBase {
	private final float distance;

	/**
	 * @param distance (in milimeters)
	 */
	public ActionTravel(float distance) {
		this.distance = distance;
	}

	@Override
	public void execute() {
		LineFollowerData.stop();
		MyChassis.get().travel(distance);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see zone01.ActionBase#getLogMessage()
	 * Ex: "Travel 500 millimeters."
	 */
	@Override
	public String getLogMessage() {
		return "Travel " + distance + "millimeters.";
	}
}
