/*
 * Block1 class (in otherwords "Paddle 1"): Creates a paddle on the screen that can ONLY move vertically using the keys
   "w" and "s" on the keyboard.
 */
import java.awt.*;
import java.awt.event.*;

public class Block1 extends Rectangle{

	private static final long serialVersionUID = 1L;

	public int yVelocity;
	public final int SPEED = 7; //movement speed of the paddle
	public static final int width = 5;
	public static final int length = 40;

	//constructor creates Paddle1 at given location with given dimensions
	public Block1(int x, int y){
		super(x, y, width, length);
	}

	//called from GamePanel when any keyboard input is detected
	//updates the direction of the paddle based on user input
	//if the keyboard input isn't any of the options (w, s), then nothing happens
	public void keyPressed(KeyEvent e){
		if(e.getKeyChar() == 'w'){
			setYDirection(SPEED*-1);
			move();
		}

		if(e.getKeyChar() == 's'){
			setYDirection(SPEED);
			move();
		}
	}

	//called from GamePanel when any key is released (no longer being pressed down)
	//Makes the paddle stop moving in that direction
	public void keyReleased(KeyEvent e){
		if(e.getKeyChar() == 'w'){
			setYDirection(0);
			move();
		}

		if(e.getKeyChar() == 's'){
			setYDirection(0);
			move();
		}
	}

	//called whenever the movement of the paddle changes in the y-direction (up/down)
	public void setYDirection(int yDirection){
		yVelocity = yDirection;
	}

	//updates the current location of the paddle
	public void move(){
		y = y + yVelocity;
	}

	//draws the current location of the paddle to the screen
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x, y, width, length);
	}

}