package zone01;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Area {
	private static AtomicInteger idIncrement = new AtomicInteger(0);
	private final ArrayList<Integer> locationIds = new ArrayList<>();
	private final int id;

	public Area() {
		id = idIncrement.getAndIncrement();
	}

	public void add(Location location) {
		locationIds.add(location.getId());
	}

	public boolean contains(Location location) {
		if (locationIds.contains(location.getId())) {
			return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}
}
