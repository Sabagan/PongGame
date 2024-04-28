/*
 * Blank class: Used to clear the screen. The purpose is clear the game screen and move on to the final screen with the score 
   and winner's name (either "player 1" or "player 2")
 */
import java.awt.*;

public class Blank extends Rectangle
{

	private static final long serialVersionUID = 1L;
	
	public static int WINDOW_HEIGHT = 0;
	public static int WINDOW_WIDTH = 0;

	public Blank (int w, int h){
		WINDOW_HEIGHT = h;
		WINDOW_WIDTH = w;
	}


	//called frequently from the GamePanel class
	public void draw(Graphics g){
	    g.setColor(Color.black); 
	    g.fillRect(x, y, WINDOW_WIDTH, WINDOW_HEIGHT);
	    g.setColor(Color.white);
	    g.setFont(new Font("Consolas", Font.PLAIN, 50));
	}
}
