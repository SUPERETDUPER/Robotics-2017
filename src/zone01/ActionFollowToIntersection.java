package zone01;

import lejos.robotics.Color;

public class ActionFollowToIntersection extends ActionBase {

	private boolean followLeft;
	private int colorToWaitFor;

	/**
	 * Follow line till intersection
	 *
	 * @param followLeft side of line to follow
	 */
	public ActionFollowToIntersection(boolean followLeft) {
		this(followLeft, Color.BLACK);
	}

	/**
	 * Follow line till intersection
	 *
	 * @param followLeft if true follows left side
	 * @param colorToWaitFor color of intersection
	 */
	public ActionFollowToIntersection(boolean followLeft, int colorToWaitFor) {
		this.followLeft = followLeft;
		this.colorToWaitFor = colorToWaitFor;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see zone01.ActionBase#execute()
	 */
	@Override
	public void execute() {
		if (followLeft) {
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
		if (followLeft) {
			message += "left";
		} else {
			message += "right";
		}
		message += ", until intersection (color " + colorToWaitFor + ").";
		return message;
	}
}
