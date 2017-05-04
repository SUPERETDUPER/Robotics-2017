package zone01;

import lejos.robotics.Color;

public class ActionCrossLine extends ActionBase {

	private final int intersection;

	public ActionCrossLine(int intersection) {
		this.intersection = intersection;
	}

	@Override
	public void execute() {
		if (GlobalConstants.BOTH == intersection) {
			LineFollowerData.stop();
			MyChassis.get().setVelocity(GlobalConstants.LINEAR_SPEED_SLOW, 0);
			ColorSensor.waitForColor(GlobalConstants.BOTH);
			ColorSensor.waitForColor(GlobalConstants.BOTH, Color.WHITE);
		} else if (GlobalConstants.RIGHT == intersection) {
			LineFollowerData.setToLeft();
			LineFollowerData.start();
			ColorSensor.waitForColor(GlobalConstants.RIGHT);
			ColorSensor.waitForColor(GlobalConstants.RIGHT, Color.WHITE);
		} else if (GlobalConstants.LEFT == intersection) {
			LineFollowerData.setToRight();
			LineFollowerData.start();
			ColorSensor.waitForColor(GlobalConstants.LEFT);
			ColorSensor.waitForColor(GlobalConstants.LEFT, Color.WHITE);
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public String getLogMessage() {
		return "Cross Intersection";
	}

}
