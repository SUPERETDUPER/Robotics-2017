package zone01;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Location {
	public static AtomicInteger idIncrement = new AtomicInteger(0);

	private final int id;

	public Location() {
		this.id = idIncrement.getAndIncrement();
	}

	protected abstract void addToSequence(ArrayList<BaseSequence> sequence,
			Location currentLocation);

	private ArrayList<BaseSequence> buildSequence(Location currentLocation) {
		ArrayList<BaseSequence> sequence = new ArrayList<>();
		addToSequence(sequence, currentLocation);
		return sequence;
	}

	public int getId() {
		return id;
	}

	public void moveTo() {
		Location currentLocation = Navigation.getLocation();
		if (currentLocation.getId() == this.getId()) {
			return;
		}
		Helper.executeSequence(this.buildSequence(currentLocation));
		Navigation.updateLocation(this);
	}

	public void throwUniplementedException(Location destination) {
		throw new RuntimeException("Unimplemented Destination ("
				+ destination.getId() + ") from origin (");
	}
}
