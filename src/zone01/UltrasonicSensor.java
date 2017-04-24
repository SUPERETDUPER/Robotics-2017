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

public class UltrasonicSensor {
	public static UltrasonicSensor get() {
		if (mUltrasonicSensor == null) {
			synchronized (UltrasonicSensor.class) {
				if (mUltrasonicSensor == null) {
					mUltrasonicSensor = new UltrasonicSensor();
				}
			}
		}
		return mUltrasonicSensor;
	}
	private final EV3UltrasonicSensor sensor;
	private final SampleProvider mSampleProvider;
	private final float[] sample = new float[1];

	private static UltrasonicSensor mUltrasonicSensor;

	private UltrasonicSensor() {
		sensor = new EV3UltrasonicSensor(GlobalConstants.PORT_ULTRASONIC);
		mSampleProvider = sensor.getDistanceMode();
	}

	public void closePort() {
		sensor.close();
	}

	public float getDistance() {
		mSampleProvider.fetchSample(sample, 0);
		return sample[0];
	}
}
