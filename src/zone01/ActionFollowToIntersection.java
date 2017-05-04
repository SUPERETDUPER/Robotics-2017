package zone01;

import lejos.robotics.Color;

public class ActionFollowToIntersection extends ActionBase {

	private final int intersection;
	private final int colorToWaitFor;

	public ActionFollowToIntersection() {
		this(GlobalConstants.LEFT, Color.BLACK);
	}

	public ActionFollowToIntersection(int intersection) {
		this(intersection, Color.BLACK);
	}

	/**
	 * Follow line till intersection
	 *
	 * @param intersectionOnRight if true follows left side
	 * @param colorToWaitFor color of intersection
	 */
	public ActionFollowToIntersection(int intersection, int colorToWaitFor) {
		this.intersection = intersection;
		this.colorToWaitFor = colorToWaitFor;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see zone01.ActionBase#execute()
	 */
	@Override
	public void execute() {
		if (intersection == GlobalConstants.RIGHT) {
			LineFollowerData.setToLeft();
		} else if (intersection == GlobalConstants.LEFT) {
			LineFollowerData.setToRight();
		} else {
			throw new RuntimeException();
		}
		LineFollowerData.start();
		ColorSensor.waitForColor(intersection, colorToWaitFor);;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see zone01.ActionBase#getLogMessage()
	 *
	 * Template : "Follow line on left, until intersection (color 7)."
	 */
	@Override
	public String getLogMessage() {
		String message = "Follow line on ";
		if (intersection == GlobalConstants.RIGHT) {
			message += "left";
		} else {
			message += "right";
		}
		message += ", until intersection (color " + colorToWaitFor + ").";
		return message;
	}
}
