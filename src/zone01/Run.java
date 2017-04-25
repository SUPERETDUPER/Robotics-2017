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

import lejos.hardware.Button;
import lejos.hardware.KeyListener;
import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * @author superetduper
 *
 */
public class Run {

	public static void addEscapeKeyListner() {
		Button.ESCAPE.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(lejos.hardware.Key k) {
			}

			@Override
			public void keyReleased(lejos.hardware.Key k) {
				end();
			}
		});
	}

	/**
	 * Ends the program. To be called before termination to avoid memory leaks.
	 */
	public static void end() {
		LineFollowerData.end();
		MyChassis.closePorts();
		Lift.closePort();
		Claw.closePort();
		ColorSensor.closePorts();
		MyUltrasonic.closePort();
		System.exit(0);
	}

	/**
	 * Main method. Runs on launch.
	 *
	 * @param args arguments
	 */
	public static void main(String[] args) {

		// Start line follower
		Thread lineThread = new Thread(new LineFollower());
		lineThread.start();

		addEscapeKeyListner();

		Lift.raiseLift();

		Delay.msDelay(5000);
		Sound.beep();
		Lift.lowerLift();
		Delay.msDelay(5000);
		Sound.beep();
		Lift.raiseLift();
		end();

	}
}
