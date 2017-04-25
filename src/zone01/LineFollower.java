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

import lejos.utility.Delay;

/**
 * The Class LineFollower. Thread (implements runnable).
 * Interacts with LineFollowerData and ColorSensor
 */
public final class LineFollower implements Runnable {

	/**
	 * Instantiates a new line follower.
	 */
	public LineFollower() {
	}

	/*
	 * Core of line following thread
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		float integral = 0;
		float lasterror = 0;
		float derivative;
		float error;

		while (!LineFollowerData.isEnded()) {
			if (LineFollowerData.isMoving()) {
				synchronized (MyChassis.get()) {
					// Nested while loop to execute code when stopped
					while (LineFollowerData.isMoving()) {
						// Calculate error
						error = GlobalConstants.MIDPOINT
								- ColorSensor.getSampleReadingActive();

						// Calculate correction
						integral += error;
						derivative = error - lasterror;

						float correction = GlobalConstants.KP * error
								+ GlobalConstants.KI * integral
								+ GlobalConstants.KD * derivative;

						// Switch correction if side switches
						if (LineFollowerData.isSetToRight()) {
							correction *= -1;
						}

						// Set velocity
						MyChassis.get().setVelocity(
								GlobalConstants.LINEAR_SPEED_LINE, correction);

						// Wait
						Delay.msDelay(GlobalConstants.IDLE_LOOP_SHORT_DELAY);

					}
					// Executed when no longer moving
					// Stop chassis
					MyChassis.get().stop();
				}
			}
			Delay.msDelay(GlobalConstants.IDLE_LOOP_LONG_DELAY);
		}
	}
}