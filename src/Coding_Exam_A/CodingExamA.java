package Coding_Exam_A;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	public static void main(String[] args) {
		/*
		 * Write a program that asks the user for three pieces of information.
		 * 1. How many robots
		 * 2. The color of the shapes
		 * 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the requested number of robots
		 * each draw the requested shape in the requested color. The robots should execute at the same time so 
		 * Threads will need to be used. Arrange the robots so that the shapes do not overlap.
		 * For full credit, define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product should look like.
		 */
		

		int robots = Integer.parseInt(JOptionPane.showInputDialog("how many robots?"));
		
		String colorStr = JOptionPane.showInputDialog("what color? (red, green, or blue)");
		Color color;
		if (colorStr.toLowerCase().trim().equals("red")) {
			color = Color.red;
		} else if (colorStr.toLowerCase().trim().equals("green")) {
			color = Color.green;
		} else if (colorStr.toLowerCase().trim().equals("blue")) {
			color = Color.blue;
		} else {
			JOptionPane.showMessageDialog(null, "No. It's gonna be black");
			color = Color.black;
		}
		
		int sides = Integer.parseInt(JOptionPane.showInputDialog("how many sides?"));
		
		Thread[] threads = new Thread[robots];
		
		int turn = 360/sides;
		double drive = 100 * Math.sin((turn/2)*(Math.PI/180));
		
		for (int i = 0; i < robots; i++) {
			int num = i;
			threads[i] = new Thread(() -> {
				Robot robot = new Robot((num + 1) * 150, 300);
				robot.setSpeed(100);
				robot.penDown();
				robot.setPenColor(color);
				for (int j = 0; j < sides; j++) {
					robot.move((int)drive);
					robot.turn(turn);
				}
				robot.hide();
			});
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

	}
}
