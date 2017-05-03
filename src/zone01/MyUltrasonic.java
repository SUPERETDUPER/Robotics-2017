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

import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

/**
 * The Class MyUltrasonic.
 */
public class MyUltrasonic extends EV3UltrasonicSensor {
	private static MyUltrasonic mSensor;

	private static SampleProvider mSampleProvider;

	private static float[] sample = new float[1];

	private static boolean closed = false;

	/**
	 * Closes ultrasonicPort
	 */
	public synchronized static void closePort() {
		if (mSensor == null) {

		} else {
			get().close();
		}
		closed = true;
	}

	/**
	 *
	 * Gets the ultrasonic sensor
	 *
	 * @return the ultrasonic sensor
	 */
	private static MyUltrasonic get() {
		if (mSensor == null && !closed) {
			synchronized (MyUltrasonic.class) {
				if (mSensor == null && !closed) {
					try {
						mSensor = new MyUltrasonic();
					} catch (IllegalArgumentException e) {
						mSensor = new MyUltrasonic();
					}
				}
			}
		}
		return mSensor;
	}

	/**
	 * Gets the current distance in meters
	 *
	 * @return the distance in meters
	 */
	public static float getDistance() {
		if (mSampleProvider == null) {
			synchronized (get()) {
				if (mSampleProvider == null) {
					mSampleProvider = get().getDistanceMode();
				}
			}
		}
		mSampleProvider.fetchSample(sample, 0);
		return sample[0];
	}

	/**
	 *
	 * Instantiates a new my ultrasonic.
	 */
	private MyUltrasonic() {
		super(GlobalConstants.PORT_ULTRASONIC);
	}
}
