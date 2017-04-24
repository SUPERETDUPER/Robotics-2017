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
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;

/**
 * The Class ChassisHelper Responsible for control of robot movement Lazy
 * implementation Static class
 */
public class MyChassis extends WheeledChassis {
	// TODO : make close port thread safe (don't allow creation after closing)

	private static WheeledChassis mChassis;
	private static EV3LargeRegulatedMotor leftMotor;
	private static EV3LargeRegulatedMotor rightMotor;

	public static void closePorts() {
		leftMotor.close();
		rightMotor.close();
	}

	/**
	 * Creates the chassis.
	 * 
	 * @return the wheeled chassis
	 */
	private static Wheel[] createWheels() {
		leftMotor = new EV3LargeRegulatedMotor(GlobalConstants.PORT_MOTOR_LEFT);
		rightMotor = new EV3LargeRegulatedMotor(
				GlobalConstants.PORT_MOTOR_RIGHT);

		Wheel wheelLeft = WheeledChassis.modelWheel(leftMotor,
				GlobalConstants.WHEEL_DIAMETER).offset(
				-GlobalConstants.AXIS_LENGTH / 2);

		Wheel wheelRight = WheeledChassis.modelWheel(rightMotor,
				GlobalConstants.WHEEL_DIAMETER).offset(
				GlobalConstants.AXIS_LENGTH / 2);

		return new Wheel[]{wheelLeft, wheelRight};
	}

	/**
	 * Gets the chassis object Time consuming ofn first call because requires
	 * implementation of two motors.
	 * 
	 * @return the wheeled chassis
	 */
	public static WheeledChassis get() {
		if (mChassis == null) {
			synchronized (MyChassis.class) {
				if (mChassis == null) {
					mChassis = new MyChassis();
				}
			}
		}
		return mChassis;
	}

	public MyChassis() {
		super(createWheels(), WheeledChassis.TYPE_DIFFERENTIAL);
	}
	// TODO : Examine location of method
	/**
	 * Turns chassis around
	 */
	// Turn robot around
	public void turnAround() {
		stop();
		setAngularSpeed(GlobalConstants.ANGULAR_SPEED);
		rotate(180);
		waitComplete();
	}
}
