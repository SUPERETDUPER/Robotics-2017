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

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

/**
 * The Class ColorSensor. Responsible for access to all color sensors. Provides
 * easy access to line following color sensor red mode and color id of none line
 * following
 * 
 * Active : Currently used by line follower Inactive : Sensor not used by line
 * follower
 * 
 */
public class ColorSensor {

	private static ColorSensor mColorSensor = new ColorSensor();

	/**
	 * Singleton Pattern - Early Implementation Return an instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static ColorSensor get() {
		return mColorSensor;
	}
	private EV3ColorSensor sensorLeft;
	private EV3ColorSensor sensorRight;
	private EV3ColorSensor sensorClaw;

	// Sample providers for line following sensor
	private SampleProvider providerLeft;
	private SampleProvider providerRight;

	private final float[] sample = new float[1];

	private final Object LEFT_LOCK = new Object();
	private final Object RIGHT_LOCK = new Object();
	private final Object CLAW_LOCK = new Object();

	/**
	 * Instantiates a new color sensor. Used only at start. Do not make the
	 * method time consuming or else review early implementation
	 */
	private ColorSensor() {
	}

	public void closePorts() {
		sensorLeft.close();
		sensorRight.close();
		sensorClaw.close();
	}

	/**
	 * Gets the color ID of what the line follower is not using. Should not be
	 * used if line follower is not moving
	 * 
	 * @return the color ID of the inactive sensor. Returns -1 if lineFollower
	 *         is not moving
	 */
	public int getColorIDInactive() {
		if (!LineFollowerData.get().isMoving()) {
			return -1;
		} else if (LineFollowerData.get().isSetToLeft()) {
			return getSensorRight().getColorID();
		} else {
			return getSensorLeft().getColorID();
		}
	}

	/**
	 * ONLY FOR LINE FOLLOWER THREAD. Gets a reading from the active color
	 * sensor (only if moving).
	 * 
	 * @return the sample reading active. Returns -1 if not moving
	 */
	public float getSampleReadingActive() {
		if (!LineFollowerData.get().isMoving()) {
			return -1;
		} else if (LineFollowerData.get().isSetToLeft()) {
			providerLeft.fetchSample(sample, 0);
			return sample[0];
		} else {
			providerRight.fetchSample(sample, 0);
			return sample[0];
		}
	}

	/**
	 * Gets the sensor on the claw.
	 * 
	 * @return the sensor on the claw
	 */
	public EV3ColorSensor getSensorClaw() {
		if (sensorClaw == null) {
			synchronized (CLAW_LOCK) {
				if (sensorClaw == null) {
					sensorClaw = new EV3ColorSensor(
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
	public EV3ColorSensor getSensorLeft() {
		if (sensorLeft == null) {
			synchronized (LEFT_LOCK) {
				if (sensorLeft == null) {
					sensorLeft = new EV3ColorSensor(
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
	public EV3ColorSensor getSensorRight() {
		if (sensorRight == null) {
			synchronized (RIGHT_LOCK) {
				if (sensorRight == null) {
					sensorRight = new EV3ColorSensor(
							GlobalConstants.PORT_COLOR_RIGHT);
					providerRight = sensorRight.getRedMode();
				}
			}
		}
		return sensorRight;
	}
}
