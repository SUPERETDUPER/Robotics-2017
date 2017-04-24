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

/**
 * The Class LineFollowerData.
 * 
 * Has three states
 * 1. Ended/Not Ended : When Ended thread ends and Line Following can no longer
 * start.
 * 2. Moving/Not Moving : Determines whether the robot is following the line or
 * not
 * 3. Left/Right : Determines which side of the line to follow when moving
 * 
 * Static class
 */
public class LineFollowerData {
	private static volatile boolean moving = false;
	private static volatile boolean ended = false;
	private static volatile boolean left = true;

	/**
	 * End line following and movement
	 */
	public static void end() {
		ended = true;
		moving = false;
	}

	/**
	 * Checks if line following is ended.
	 * 
	 * @return true, if is ended
	 */
	public static boolean isEnded() {
		return ended;
	}

	/**
	 * Checks if line following is moving.
	 * 
	 * @return true, if is moving
	 */
	public static boolean isMoving() {
		return moving;
	}

	/**
	 * Checks if line following is set to the left.
	 * 
	 * @return true, if it is set to the left
	 */
	public static boolean isSetToLeft() {
		return left;
	}

	/**
	 * Checks if line following is set to the right.
	 * 
	 * @return true, if it is set to the right
	 */
	public static boolean isSetToRight() {
		return !left;
	}

	/**
	 * Set the robot to the left Does not start the robot
	 */
	public static void setToLeft() {
		left = true;
	}

	/**
	 * Set the robot to the right Does not start the robot
	 */
	public static void setToRight() {
		left = false;
	}

	/**
	 * Starts the robot Thread must already be running
	 */
	public static void start() {
		moving = true;
	}

	/**
	 * Stops the robot Can be restarted with {@link start}
	 */
	public static void stop() {
		moving = false;
	}
}
