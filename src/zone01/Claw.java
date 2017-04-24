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
	public static Claw get() {
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

	/**
	 * Instantiates a new claw object. Private so that only one instance can be
	 * created by getClaw
	 */
	private Claw() {
		super(GlobalConstants.PORT_MOTOR_CLAW);
	}

	/**
	 * Calibrate the claw.
	 */
	public void calibrateClaw() {
		if (status == Claw.STATUS_UNCALIBRATED) {
			// Turn until stall
			setSpeed(GlobalConstants.CLAW_SPEED);
			backward();
			while (!this.isStalled()) {
				Delay.msDelay(GlobalConstants.IDLE_LOOP_LONG_DELAY);
			}

			// Open claw
			rotate(GlobalConstants.CLAW_DEGREES);

			// Reset_tacho_count
			resetTachoCount();

			// Update status
			status = Claw.STATUS_OPENED;
		}
	}

	/**
	 * Close claw.
	 */
	public void closeClaw() {
		// Calibrate if needed
		if (status == Claw.STATUS_UNCALIBRATED) {
			calibrateClaw();
		}

		// If opened
		if (status == Claw.STATUS_OPENED) {
			// close claw
			setSpeed(GlobalConstants.CLAW_SPEED);
			backward();
			while (!this.isStalled()) {
				Delay.msDelay(GlobalConstants.IDLE_LOOP_LONG_DELAY);
			}
			stop(true);

			// Update status
			status = Claw.STATUS_CLOSED;
		}
	}
	/**
	 * Opens claw.
	 */
	public void openClaw() {
		// Calibrate if needed
		if (status == Claw.STATUS_UNCALIBRATED) {
			calibrateClaw();
		}

		// If closed
		if (status == Claw.STATUS_CLOSED) {
			// Open claw
			setSpeed(GlobalConstants.CLAW_SPEED);
			forward();
			while (this.getTachoCount() < 0) {
				Delay.msDelay(GlobalConstants.IDLE_LOOP_SHORT_DELAY);
			}
			stop(true);

			// Update status
			status = Claw.STATUS_OPENED;
		}
	}
}
