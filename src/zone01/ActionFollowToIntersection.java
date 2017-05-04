package zone01;

import lejos.robotics.Color;

public class ActionFollowToIntersection extends ActionBase {

	private final boolean intersectionOnRight;
	private final int colorToWaitFor;
	private final boolean passIntersection;

	public ActionFollowToIntersection() {
		this(true, false, Color.BLACK);
	}

	public ActionFollowToIntersection(boolean intersectionOnRight) {
		this(intersectionOnRight, false, Color.BLACK);
	}

	public ActionFollowToIntersection(boolean intersectionOnRight,
			boolean passIntersection) {
		this(intersectionOnRight, passIntersection, Color.BLACK);
	}

	/**
	 * Follow line till intersection
	 *
	 * @param intersectionOnRight if true follows left side
	 * @param colorToWaitFor color of intersection
	 */
	public ActionFollowToIntersection(boolean intersectionOnRight,
			boolean passIntersection, int colorToWaitFor) {
		this.intersectionOnRight = intersectionOnRight;
		this.colorToWaitFor = colorToWaitFor;
		this.passIntersection = passIntersection;
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
		if (passIntersection) {
			ColorSensor.waitForLineInactive(Color.WHITE);
		}
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
