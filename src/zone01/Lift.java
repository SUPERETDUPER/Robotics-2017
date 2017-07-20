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

	/**
	 * Calibrate the lift.
	 */
	public static void calibrateLift() {
		if (status == Lift.STATUS_UNCALIBRATED) {
			// Turn until stall
			get().setSpeed(GlobalConstants.LIFT_SPEED);
			Claw.openClaw();
			get().forward();
			while (!get().isStalled()) {
				Delay.msDelay(GlobalConstants.IDLE_LOOP_LONG_DELAY);
			}

			// Raise lift
			get().rotate(-GlobalConstants.LIFT_DEGREES_DOWN);

			// Reset_tacho_count
			get().resetTachoCount();

			// Update status
			status = Lift.STATUS_DOWN;
		}
	}

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
	private static Lift get() {
		if (lift == null && status != STATUS_ENDED) {
			synchronized (Lift.class) {
				if (lift == null && status != STATUS_ENDED) {
					lift = new Lift();
				}
			}
		}
		return lift;
	}
	public static void lowerLift() {
		lowerLift(false);
	}

	/**
	 * Moves lift in down position.
	 */
	public static void lowerLift(boolean immediateReturn) {
		// Calibrate if needed
		if (status == Lift.STATUS_UNCALIBRATED) {
			calibrateLift();
		}

		// If Up
		if (status == Lift.STATUS_UP) {
			// Lower till TachoCount < 0
			get().setSpeed(GlobalConstants.LIFT_SPEED);
			if (immediateReturn) {
				get().rotate(-get().getTachoCount(), true);
			} else {
				get().rotate(-get().getTachoCount());
			}

			// Update status
			status = Lift.STATUS_DOWN;
		}
	}

	public static void raiseLift() {
		raiseLift(false);
	}

	/**
	 * Moves lift in up position.
	 */
	public static void raiseLift(boolean immediateReturn) {
		// Calibrate if needed
		if (status == Lift.STATUS_UNCALIBRATED) {
			calibrateLift();
		}

		// If down
		if (status == Lift.STATUS_DOWN) {

			// Lift to position
			get().setSpeed(GlobalConstants.LIFT_SPEED);
			if (immediateReturn) {
				get().rotate(-GlobalConstants.LIFT_DEGREES_UP
						+ GlobalConstants.LIFT_DEGREES_DOWN, true);
			} else {
				get().rotate(-GlobalConstants.LIFT_DEGREES_UP
						+ GlobalConstants.LIFT_DEGREES_DOWN);
			}

			// Update status
			status = Lift.STATUS_UP;
		}
	}

	/**
	 * Instantiates a new lift. Private so that only one instance can be created
	 */
	private Lift() {
		super(GlobalConstants.PORT_MOTOR_LIFT);
	}

}