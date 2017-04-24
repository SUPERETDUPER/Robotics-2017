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

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.utility.Delay;

/**
 * The Class Lift. Responsible for all lift operations. Calibrates on first use.
 * Singleton pattern
 * 
 * @author superetduper
 */
public class Lift extends EV3LargeRegulatedMotor {

	// Constants
	private final static int STATUS_DOWN = 0;
	private final static int STATUS_UP = 1;
	private final static int STATUS_UNCALIBRATED = 2;
	private final static int STATUS_ENDED = 3;

	private static int status = STATUS_UNCALIBRATED;

	/** Static object accessible from getLift method **/
	private static Lift lift;

	public synchronized static void closePort() {
		if (lift == null) {
		} else {
			get().close();
		}
		status = STATUS_ENDED;
	}

	/**
	 * Gets the claw object and creates it if necessary Lazy implementation.
	 * 
	 * @return the claw object
	 */
	public static Lift get() {
		if (lift == null && status != STATUS_ENDED) {
			synchronized (Lift.class) {
				if (lift == null && status != STATUS_ENDED) {
					lift = new Lift();
				}
			}
		}
		return lift;
	}

	/**
	 * Instantiates a new lift. Private so that only one instance can be created
	 */
	private Lift() {
		super(GlobalConstants.PORT_MOTOR_LIFT);
	}

	/**
	 * Calibrate the lift.
	 */
	public void calibrateLift() {
		if (status == Lift.STATUS_UNCALIBRATED) {
			// Turn until stall
			setSpeed(GlobalConstants.LIFT_SPEED);
			backward();
			while (!isStalled()) {
				Delay.msDelay(GlobalConstants.IDLE_LOOP_LONG_DELAY);
			}

			// Let float until stall
			flt(true);
			while (!isStalled()) {
				Delay.msDelay(GlobalConstants.IDLE_LOOP_LONG_DELAY);
			}

			// Reset_tacho_count
			resetTachoCount();

			// Update status
			status = Lift.STATUS_DOWN;
		}
	}

	/**
	 * Moves lift in down position.
	 */
	public void lowerLift() {
		// Calibrate if needed
		if (status == Lift.STATUS_UNCALIBRATED) {
			calibrateLift();
		}

		// If Up
		if (status == Lift.STATUS_UP) {
			// Lower till TachoCount < 0
			setSpeed(GlobalConstants.LIFT_SPEED);
			backward();
			while (getTachoCount() > 0) {
				Delay.msDelay(GlobalConstants.IDLE_LOOP_SHORT_DELAY);
			}
			flt(true);

			// Update status
			status = Lift.STATUS_DOWN;
		}
	}

	/**
	 * Moves lift in up position.
	 */
	public void raiseLift() {
		// Calibrate if needed
		if (status == Lift.STATUS_UNCALIBRATED) {
			calibrateLift();
		}

		// If down
		if (status == Lift.STATUS_DOWN) {

			// Lift to position
			setSpeed(GlobalConstants.LIFT_SPEED);
			rotate(GlobalConstants.LIFT_DEGREES);

			// Update status
			status = Lift.STATUS_UP;
		}
	}
}