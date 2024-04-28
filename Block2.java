/*
 * Block2 class (in otherwords "Paddle 2"): Creates a paddle on the screen that can ONLY move vertically using the keys
   up arrow and down arrow on the keyboard.
 */
import java.awt.*;
import java.awt.event.*;

public class Block2 extends Rectangle {

	private static final long serialVersionUID = 1L;

	public int yVelocity;
	public final int SPEED = 7; //movement speed of the paddle
	public static final int width = 5;
	public static final int length = 40;

	//constructor creates Paddle2 at given location with given dimensions
	public Block2(int x, int y){
		super(x, y, width, length);
	}

	//called from GamePanel when any keyboard input is detected
	//updates the direction of the Paddle based on user input
	//if the keyboard input isn't any of the options (up arrow, down arrow), then nothing happens
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == e.VK_UP){
			setYDirection(SPEED*-1);
			move();
		}

		if(e.getKeyCode() == e.VK_DOWN){
			setYDirection(SPEED);
			move();
		}
	}

	//called from GamePanel when any key is released (no longer being pressed down)
	//Makes the Paddle stop moving in that direction
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode() == e.VK_UP){
			setYDirection(0);
			move();
		}

		if(e.getKeyCode() == e.VK_DOWN){
			setYDirection(0);
			move();
		}
	}

	//called whenever the movement of the Paddle changes in the y-direction (up/down)
	public void setYDirection(int yDirection){
		yVelocity = yDirection;
	}

	//updates the current location of the paddle
	public void move(){
		y = y + yVelocity;
	}

	//called frequently from the GamePanel class
	//draws the current location of the paddle to the screen
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x, y, width, length);
	}

}