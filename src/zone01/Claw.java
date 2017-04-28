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

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.utility.Delay;

/**
 * The Class Claw. Responsible for all claw operations. Singleton Pattern. Auto
 * calibrates on first operation
 *
 * Results unpredictable if you use super methods.
 *
 * @author superetduper
 */
public class Claw extends EV3MediumRegulatedMotor {

	// Constants
	private final static int STATUS_OPENED = 0;
	private final static int STATUS_CLOSED = 1;
	private final static int STATUS_UNCALIBRATED = 2;
	private final static int STATUS_ENDED = 3;

	private static int status = Claw.STATUS_UNCALIBRATED;

	/** Static object accessible from getClaw method **/
	private static Claw claw;

	/**
	 * Calibrate the claw.
	 */
	public static void calibrateClaw() {
		if (status == Claw.STATUS_UNCALIBRATED) {
			// Turn until stall
			get().setSpeed(get().getMaxSpeed());
			get().backward();
			while (!get().isStalled()) {
				Delay.msDelay(GlobalConstants.IDLE_LOOP_LONG_DELAY);
			}

			// Open claw
			get().rotate(GlobalConstants.CLAW_DEGREES);

			// Reset_tacho_count
			get().resetTachoCount();

			// Update status
			status = Claw.STATUS_OPENED;
		}
	}

	/**
	 * Close claw.
	 */
	public static void closeClaw() {
		// Calibrate if needed
		if (status == Claw.STATUS_UNCALIBRATED) {
			calibrateClaw();
		}

		// If opened
		if (status == Claw.STATUS_OPENED) {
			// close claw
			get().setSpeed(get().getMaxSpeed());
			get().backward();
			while (!get().isStalled()) {
				Delay.msDelay(GlobalConstants.IDLE_LOOP_LONG_DELAY);
			}
			get().stop(true);

			// Update status
			status = Claw.STATUS_CLOSED;
		}
	}

	/**
	 * Closes port if open and stops new claw creation
	 */
	public synchronized static void closePort() {
		if (claw == null) {
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
	private static Claw get() {
		// If claw never created and not ended
		if (claw == null && status != STATUS_ENDED) {
			synchronized (Claw.class) {
				if (claw == null && status != STATUS_ENDED) {
					claw = new Claw();
				}
			}
		}
		return claw;
	}

	public static void markAsOpen() {
		status = STATUS_OPENED;
		get().resetTachoCount();
	}
	/**
	 * Opens claw.
	 */
	public static void openClaw() {
		openClaw(false);
	}
	public static void openClaw(boolean immediateReturn) {
		// Calibrate if needed
		if (status == Claw.STATUS_UNCALIBRATED) {
			calibrateClaw();
		}

		// If closed
		if (status == Claw.STATUS_CLOSED) {
			// Open claw
			get().setSpeed(get().getMaxSpeed());
			if (immediateReturn) {
				get().rotate(-get().getTachoCount(), true);
			} else {
				get().rotate(-get().getTachoCount());
			}

			// Update status
			status = Claw.STATUS_OPENED;
		}
	}

	/**
	 * Instantiates a new claw object. Private so that only one instance can be
	 * created by getClaw
	 */
	private Claw() {
		super(GlobalConstants.PORT_MOTOR_CLAW);
	}
}
