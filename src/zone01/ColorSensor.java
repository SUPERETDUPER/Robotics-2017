/* MIT License

Copyright (c) 2017 Martin C. Staadecker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE. */
package zone01;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * The Class ColorSensor. Responsible for access to all color sensors. Provides
 * easy access to line following color sensor red mode and color id of none line
 * following
 *
 * Active : Currently used by line follower Inactive : Sensor not used by line
 * follower
 *
 */
public class ColorSensor extends EV3ColorSensor {

	private static ColorSensor sensorLeft;
	private static ColorSensor sensorRight;
	private static ColorSensor sensorClaw;

	private static boolean closed = false;

	// Samples providers for line following sensor
	private static SampleProvider providerLeft;
	private static SampleProvider providerRight;

	private static final Object LEFT_LOCK = new Object();
	private static final Object RIGHT_LOCK = new Object();
	private static final Object CLAW_LOCK = new Object();

	private static final float[] sampleLeft = new float[1];
	private static final float[] sampleRight = new float[1];

	public synchronized static void closePorts() {
		if (sensorLeft != null) {
			getLeft().close();
		}
		if (sensorRight != null) {
			getRight().close();
		}
		if (sensorClaw != null) {
			getClaw().close();
		}
		closed = true;
	}

	/**
	 * Gets the sensor on the claw.
	 *
	 * @return the sensor on the claw
	 */
	public static ColorSensor getClaw() {
		if (sensorClaw == null && !closed) {
			synchronized (CLAW_LOCK) {
				if (sensorClaw == null && !closed) {
					sensorClaw = new ColorSensor(
							GlobalConstants.PORT_COLOR_CLAW);
				}
			}
		}
		return sensorClaw;
	}

	/**
	 * Gets the left sensor.
	 *
	 * @return the left sensor
	 */
	public static ColorSensor getLeft() {
		if (sensorLeft == null && !closed) {
			synchronized (LEFT_LOCK) {
				if (sensorLeft == null && !closed) {
					sensorLeft = new ColorSensor(
							GlobalConstants.PORT_COLOR_LEFT);
					providerLeft = sensorLeft.getRedMode();
				}
			}
		}
		return sensorLeft;
	}

	/**
	 * Gets the right sensor.
	 *
	 * @return the right sensor
	 */
	public static ColorSensor getRight() {
		if (sensorRight == null && !closed) {
			synchronized (RIGHT_LOCK) {
				if (sensorRight == null && !closed) {
					sensorRight = new ColorSensor(
							GlobalConstants.PORT_COLOR_RIGHT);
					providerRight = sensorRight.getRedMode();
				}
			}
		}
		return sensorRight;
	}

	/**
	 * ONLY FOR LINE FOLLOWER THREAD. Gets a reading from the active color
	 * sensor (only if moving).
	 *
	 * @return the sample reading active. Returns -1 if not moving
	 */
	public static float getSampleReadingActive() {
		if (!LineFollowerData.isMoving()) {
			throw new RuntimeException(
					"Called for active color sensor reading but line follower not moving");
		} else if (LineFollowerData.isSetToLeft()) {
			getLeft();
			providerLeft.fetchSample(sampleLeft, 0);
			return sampleLeft[0];
		} else {
			getRight();
			providerRight.fetchSample(sampleRight, 0);
			return sampleRight[0];
		}
	}
	public static int waitForColor(int sensor) {
		return waitForColor(sensor, Color.BLACK);
	}
	public static int waitForColor(int sensor, int color) {
		boolean doneLeft = false;
		boolean doneRight = false;
		while (true) {
			if (color == getLeft().getColorID()) {
				doneLeft = true;
			}
			if (color == getRight().getColorID()) {
				doneRight = true;
			}

			switch (sensor) {
				case GlobalConstants.LEFT :
					if (doneLeft) {
						return GlobalConstants.LEFT;
					}
					break;
				case GlobalConstants.RIGHT :
					if (doneRight) {
						return GlobalConstants.RIGHT;
					}
					break;
				case GlobalConstants.EITHER :
					if (doneLeft) {
						return GlobalConstants.LEFT;
					}
					if (doneRight) {
						return GlobalConstants.RIGHT;
					}
					break;
				case GlobalConstants.BOTH :
					if (doneLeft && doneRight) {
						return GlobalConstants.BOTH;
					}
					break;
				case GlobalConstants.INACTIVE :
					if (!LineFollowerData.isMoving()) {
						throw new RuntimeException(
								"Called for inactive color ID but line follower not active");
					}
					if (LineFollowerData.isSetToLeft() && doneRight) {
						return GlobalConstants.RIGHT;
					}
					if (LineFollowerData.isSetToRight() && doneLeft) {
						return GlobalConstants.LEFT;
					}
				default :
					throw new RuntimeException(
							"Illegal argument for sensor in color sensor wait for line");
			}
			Delay.msDelay(GlobalConstants.IDLE_LOOP_SHORT_DELAY);
		}
	}
	/**
	 * }
	 * Instantiates a new color sensor. Used only at start. Do not make the
	 * method time consuming or else review early implementation
	 */
	private ColorSensor(Port port) {
		super(port);
	}
}
