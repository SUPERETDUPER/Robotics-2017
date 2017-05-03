package zone01;

import lejos.robotics.Color;

public class ActionFollowToIntersection extends ActionBase {

	private boolean intersectionOnRight;
	private int colorToWaitFor;

	/**
	 * Follow line till intersection
	 *
	 * @param intersectionOnRight side of line to follow
	 */
	public ActionFollowToIntersection(boolean intersectionOnRight) {
		this(intersectionOnRight, Color.BLACK);
	}

	/**
	 * Follow line till intersection
	 *
	 * @param intersectionOnRight if true follows left side
	 * @param colorToWaitFor color of intersection
	 */
	public ActionFollowToIntersection(boolean intersectionOnRight, int colorToWaitFor) {
		this.intersectionOnRight = intersectionOnRight;
		this.colorToWaitFor = colorToWaitFor;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see zone01.ActionBase#execute()
	 */
	@Override
	public void execute() {
		if (intersectionOnRight) {
			LineFollowerData.setToLeft();
		} else {
			LineFollowerData.setToRight();
		}
		LineFollowerData.start();
		ColorSensor.waitForLineInactive(colorToWaitFor);
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
		if (intersectionOnRight) {
			message += "left";
		} else {
			message += "right";
		}
		message += ", until intersection (color " + colorToWaitFor + ").";
		return message;
	}
}
