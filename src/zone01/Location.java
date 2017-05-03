package zone01;

import java.util.ArrayList;

/**
 * @author superetduper
 *         Call the destination to move to
 */
public abstract class Location {

	private final String DESCRIPTION;

	public Location(String description) {
		DESCRIPTION = description;
	}

	public Location(String description, Area[] area) {
		DESCRIPTION = description;
		for (int i = 0; i < area.length; i++) {
			area[i].add(this);
		}
	}

	protected abstract void buildOnSequence(ArrayList<ActionBase> sequence);

	public String getDescription() {
		return DESCRIPTION;
	}

	private ArrayList<ActionBase> getSequence() {
		ArrayList<ActionBase> sequence = new ArrayList<>();
		buildOnSequence(sequence);
		return sequence;
	}
	public void moveTo() {
		Location currentLocation = Navigation.getLocation();
		if (currentLocation == this) {
			return;
		}
		ArrayList<ActionBase> sequence = this.getSequence();
		ActionBase.executeSequence(sequence);
		Navigation.updateLocation(this);
	}
	public void throwUniplementedException() {
		throw new RuntimeException(
				"Unimplemented : " + Navigation.getLocation().getDescription()
						+ " to " + this.getDescription());
	}

}
