/*
 * Ball class: Responsible for creating the ball and making the ball move
 */
import java.awt.*;

public class Ball extends Rectangle{

	private static final long serialVersionUID = 1L;

	public static int yVelocity = 3; 
	public static int xVelocity = 3;
	public static final int BALL_DIAMETER = 10; //size of ball

	public Ball(int x, int y){
		super(x, y, BALL_DIAMETER, BALL_DIAMETER);
	}

	//called frequently from GamePanel class
	//updates the current location of the ball
	public void move(){
		y = y + yVelocity;
		x = x + xVelocity;
	}

	//called frequently from the GamePanel class
	//draws the current location of the ball to the screen
	public void draw(Graphics g){
		g.setColor(Color.white);
		g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
		
	}

}