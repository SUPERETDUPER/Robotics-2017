package zone01;

public class SequenceAngle implements BaseSequence {

	private int angle;

	public SequenceAngle(int angle) {
		this.angle = angle;
	}

	@Override
	public void execute() {
		LineFollowerData.stop();
		MyChassis.get().rotate(angle);
	}
}
