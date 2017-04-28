package zone01;

public class ActionRotate implements ActionBase {

	private int angle;

	public ActionRotate(int angle) {
		this.angle = angle;
	}

	@Override
	public void execute() {
		LineFollowerData.stop();
		MyChassis.get().rotate(angle);
	}
}
