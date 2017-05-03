package zone01;

import java.util.ArrayList;

public abstract class ActionBase {

	public static void executeSequence(ArrayList<ActionBase> sequence) {
		for (int i = 0; i < sequence.size(); i++) {
			sequence.get(i).logMessage();
			sequence.get(i).execute();
		}
	}

	public abstract void execute();

	public abstract String getLogMessage();

	public void logMessage() {
		System.out.println(getLogMessage());
	}
}
