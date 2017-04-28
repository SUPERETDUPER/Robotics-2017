package zone01;

public class ActionTravel implements ActionBase {
	private final float distance;

	public ActionTravel(float distance) {
		this.distance = distance;
	}

	@Override
	public void execute() {
		MyChassis.get().travel(distance);
	}
}
