package zone01;

import lejos.robotics.Color;

public class ActionFollowTillLine implements ActionBase {

	private boolean followLeft;
	private int colorToWaitFor;

	public ActionFollowTillLine(boolean followLeft) {
		this.followLeft = followLeft;
		colorToWaitFor = Color.BLACK;
	}
	public ActionFollowTillLine(boolean followLeft, int colorToWaitFor) {
		// TODO Auto-generated constructor stub
		this.followLeft = followLeft;
		this.colorToWaitFor = colorToWaitFor;

	}

	@Override
	public void execute() {
		if (followLeft) {
			LineFollowerData.setToLeft();
		} else {
			LineFollowerData.setToRight();
		}
		LineFollowerData.start();
		ColorSensor.waitForLineInactive(colorToWaitFor);
		LineFollowerData.stop();
	}
}
