package zone01;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Location {
	public static AtomicInteger idIncrement = new AtomicInteger(0);
	private ArrayList<ActionBase> sequence = new ArrayList<>();

	private final int id;

	public Location() {
		this.id = idIncrement.getAndIncrement();
	}

	public Location(Area[] area) {
		this.id = idIncrement.getAndIncrement();

		for (int i = 0; i < area.length; i++) {
			area[i].add(this);
		}
	}

	protected abstract void buildOnSequence(ArrayList<ActionBase> sequence);

	public int getId() {
		return id;
	}

	private ArrayList<ActionBase> getSequence() {
		sequence.clear();
		buildOnSequence(sequence);
		return sequence;
	}

	public void moveTo() {
		Location currentLocation = Navigation.getLocation();
		if (currentLocation.getId() == this.getId()) {
			return;
		}
		Helper.executeSequence(this.getSequence());
		Navigation.updateLocation(this);
	}
	public void throwUniplementedException(Location destination) {
		throw new RuntimeException("Unimplemented Destination ("
				+ destination.getId() + ") from origin (");
	}

}
