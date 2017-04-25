package zone01;

public class SequenceFollowSide implements BaseSequence {

	private boolean isLeft;

	public SequenceFollowSide(boolean isLeft) {
		this.isLeft = isLeft;
	}

	@Override
	public void execute() {
		if (isLeft) {
			LineFollowerData.setToLeft();
		} else {
			LineFollowerData.setToRight();
		}
		LineFollowerData.start();
		ColorSensor.waitForBlackLine();
		LineFollowerData.stop();
	}
}
