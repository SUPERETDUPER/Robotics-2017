package zone01;

import java.util.ArrayList;

/**
 * @author superetduper
 *         Call the destination to move to
 */
public class Location {

	private final String DESCRIPTION;

	public Location(String description) {
		DESCRIPTION = description;
	}

	protected void buildOnSequence(ArrayList<ActionBase> sequence) {
		if (Navigation.getLocation() == this) {
			return;
		}
		throwUniplementedException();
	}

	public String getDescription() {
		return DESCRIPTION;
	}

	public ArrayList<ActionBase> getSequence() {
		ArrayList<ActionBase> sequence = new ArrayList<>();

		if (Navigation.getLocation() != this) {
			buildOnSequence(sequence);
		}
		return sequence;
	}
	public void moveTo() {
		ArrayList<ActionBase> sequence = this.getSequence();
		ActionBase.executeSequence(sequence);
		Navigation.updateLocation(this);
	}
	public void throwUniplementedException() {
		throw new RuntimeException("Unimplemented : From "
				+ Navigation.getLocation().getDescription() + " to "
				+ this.getDescription());
	}
}
