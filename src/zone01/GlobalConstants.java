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

import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;

/**
 * The Class GlobalConstants.
 */
public final class GlobalConstants {

	// Ports
	static final Port PORT_MOTOR_LEFT = MotorPort.A;
	static final Port PORT_MOTOR_RIGHT = MotorPort.B;
	static final Port PORT_MOTOR_LIFT = MotorPort.C;
	static final Port PORT_MOTOR_CLAW = MotorPort.D;
	static final Port PORT_ULTRASONIC = SensorPort.S4;
	static final Port PORT_COLOR_LEFT = SensorPort.S2;
	static final Port PORT_COLOR_RIGHT = SensorPort.S3;
	static final Port PORT_COLOR_CLAW = SensorPort.S1;

	// TODO : Test values especially with line follower
	// Delays between loops to reduce CPU usage
	static final int IDLE_LOOP_SHORT_DELAY = 150;
	static final int IDLE_LOOP_LONG_DELAY = 300;

	// TODO : Set values
	// Values for Chassis
	static final double WHEEL_DIAMETER = 56; // Millimeters
	static final double AXIS_LENGTH = 168; // Millimeters

	// TODO : Set values
	// Values for Lift
	final static int LIFT_DEGREES_DOWN = 160;
	final static int LIFT_DEGREES_UP = 400;
	final static int LIFT_SPEED = 600;

	// TODO : Reduce for efficiency
	// Values for Claw
	// final static int CLAW_SPEED = 500; USING MAX SPEED
	final static int CLAW_DEGREES = 2000;

	// TODO : Set values
	// Values for line following
	final static int LINEAR_SPEED_LINE = 10;
	final static int KP = 100;
	final static int KI = 0;
	final static int KD = 0;
	final static float MIDPOINT = (float) 0.46;

	// TODO : Set Values
	// Actions speeds
	final static int ANGULAR_SPEED_SLOW = 30; // Degrees per second
	final static int ANGULAR_SPEED_FAST = 30; // Degrees per second
	final static int LINEAR_SPEED_SLOW = 100; // Millimeters per second
	final static int LINEAR_SPEED_FAST = 100; // Millimeters per second

	// Actions values
	final static int DISTANCE_BETWEEN_SENSORS = 0;
	final static int LINE_THICKNESS = 21;

	// Navigation constants
	final static float DISTANCE_TO_ENTER_GREEN_ZONE = 0;
	final static float DISTANCE_TO_LEAVE_GREEN_ZONE = 0;

	// Helper
	// Pickup object
	final static float DISTANCE_CLAW_TO_SENSOR = (float) 0.04;

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int BOTH = 2;
	public static final int EITHER = 3;
	public static final int INACTIVE = 4;
}
