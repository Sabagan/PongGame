/*
 * Score1 class: Creates the scores for player 1 and player 2 on the screen. It also draws the lines in the middle of screen.
 */
import java.awt.*;

public class Score1 extends Rectangle{

	private static final long serialVersionUID = 1L;

	public static int WINDOW_HEIGHT = 0;
	public static int WINDOW_WIDTH = 0;
	public static int score1 = 0;
	public static int score2 = 0;

	//constructor creates ball at given location with given dimensions
	public Score1 (int h, int w){
		WINDOW_HEIGHT = h;
		WINDOW_WIDTH = w;
	}


	//called frequently from the GamePanel class
	//draws the current location of the ball to the screen
	public void draw(Graphics g){
	    g.setColor(Color.white);
	    g.setFont(new Font("Consolas", Font.PLAIN, 50));
	    g.drawString(String.valueOf(score1), (int)(WINDOW_WIDTH*0.43), (int)(WINDOW_HEIGHT*0.1));  
	    g.drawString(String.valueOf(score2), (int)(WINDOW_WIDTH*1.4), (int)(WINDOW_HEIGHT*0.1)); 
	    g.drawLine(WINDOW_HEIGHT/2, 0, WINDOW_HEIGHT/2, WINDOW_WIDTH); // draws vertical line
	}
}