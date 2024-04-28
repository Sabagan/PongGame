/*
 * GamePanel class acts as the main "game loop" - continuously runs the game and calls whatever needs to be called. Child of JPanel 
   because JPanel contains methods for drawing to the screen. Implements KeyListener interface to listen for keyboard input
   Implements Runnable interface to use "threading" - let the game do two things at once
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	//dimensions of window
	public static final int GAME_WIDTH = 500;
	public static final int GAME_HEIGHT = 250;

	public Thread gameThread;
	public Image image;
	public Graphics graphics;
	public Block1 player1;
	public Block2 player2;
	public Ball ball;
	public Score1 score1;
	public Blank clear;

	
	
	public GamePanel() {
		clear = new Blank(GAME_WIDTH, GAME_HEIGHT); //clear
		player1 = new Block1(GAME_WIDTH/100, GAME_HEIGHT/3);
		player2 = new Block2(GAME_WIDTH-10, GAME_HEIGHT/3);
		ball = new Ball(GAME_WIDTH/2, (int)(Math.random()*GAME_HEIGHT-Ball.BALL_DIAMETER));
		score1 = new Score1(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true); //make everything in this class appear on the screen
		this.addKeyListener(this); //start listening for keyboard input
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

		//make this class run at the same time as other classes (without this each class would "pause" while another class runs). By using threading we can remove lag, and also allows us to do features like display timers in real time!
		gameThread = new Thread(this);
		gameThread.start();
	}

	//paint is a method in java.awt library that we are overriding. It is a special method - it is called automatically in the background in order to update what appears in the window. You NEVER call paint() yourself
	public void paint(Graphics g){
		//we are using "double buffering here" - if we draw images directly onto the screen, it takes time and the human eye can actually notice flashes of lag as each pixel on the screen is drawn one at a time. Instead, we are going to draw images OFF the screen, then simply move the image on screen as needed. 
		image = createImage(GAME_WIDTH, GAME_HEIGHT); //draw off screen
		graphics = image.getGraphics();
		draw(graphics);//update the positions of everything on the screen 
		g.drawImage(image, 0, 0, this); //move the image on the screen
	}
	
	//call the draw methods in each class to update positions as things move
	public void draw(Graphics g){
		player1.draw(g);
		player2.draw(g);
		ball.draw(g);
		score1.draw(g);
		
		// Executes final messages at the end of game
		if (Score1.score1 + Score1.score2 == 9)
		{
			clear.draw(g);
			
			g.drawString(Score1.score1+"-"+Score1.score2, (int)(GAME_WIDTH*0.4), (int)(GAME_HEIGHT*0.3));
			g.setFont(new Font("Consolas", Font.PLAIN, 20));
			if (Score1.score1 > Score1.score2)
			{
				g.drawString("Player 1 is the Winner!", (int)(GAME_WIDTH*0.2), (int)(GAME_HEIGHT*0.5));
			}
			if (Score1.score2 > Score1.score1)
			{
				g.drawString("Player 2 is the Winner!", (int)(GAME_WIDTH*0.2), (int)(GAME_HEIGHT*0.5));
			}
		}
	}
	

	//call the move methods in other classes to update positions
	//this method is constantly called from run(). By doing this, movements appear fluid and natural. If we take this out the movements appear sluggish and laggy
	public void move(){
		player1.move();
		player2.move();
		ball.move();
	}

	
	//handles all collision detection and responds accordingly
	public void checkCollision() {

		//force player to remain on screen
		if(player1.y<= 0){
			player1.y = 0;
		}
		if(player1.y >= GAME_HEIGHT - Block1.length){
			player1.y = GAME_HEIGHT-Block1.length;
		}
		
		if(player2.y<= 0){
			player2.y = 0;
		}
		if(player2.y >= GAME_HEIGHT - Block2.length){
			player2.y = GAME_HEIGHT-Block2.length;
		}
		
		if(ball.x <= 0)
		{
			Score1.score2++;
			ball.x = (GAME_WIDTH/2) - Ball.BALL_DIAMETER;
			ball.y = (int)(Math.random()*GAME_HEIGHT-Ball.BALL_DIAMETER);
		}
		if(ball.x >= GAME_WIDTH - Ball.BALL_DIAMETER)
		{
			Score1.score1++;
			ball.x = (GAME_WIDTH/2) - Ball.BALL_DIAMETER;
			ball.y = (int)(Math.random()*GAME_HEIGHT-Ball.BALL_DIAMETER);
		}
		if(ball.y <= 0) 
		{
			ball.y = 0;
			Ball.yVelocity = -Ball.yVelocity;
		}
		if(ball.y >= GAME_HEIGHT - Ball.BALL_DIAMETER)
		{
			ball.y = GAME_HEIGHT - Ball.BALL_DIAMETER;
			Ball.yVelocity = -Ball.yVelocity;
		}
		
		if(ball.intersects(player1))
		{
			Ball.xVelocity = -Ball.xVelocity;
		}
		if(ball.intersects(player2))
		{
			Ball.xVelocity = -Ball.xVelocity;
		}
		
	}

	//run() method is what makes the game continue running without end. It calls other methods to move objects,  check for collision, and update the screen
	public void run() {
		//the CPU runs our game code too quickly - we need to slow it down! The following lines of code "force" the computer to get stuck in a loop for short intervals between calling other methods to update the screen. 
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long now;

		while(true){ //this is the infinite game loop
			now = System.nanoTime();
			delta = delta + (now-lastTime)/ns;
			lastTime = now;

			//only move objects around and update screen if enough time has passed
			if(delta >= 1){
				move();
				checkCollision();
				repaint();
				delta--;
			}
			
			// This will break from the infinite game loop
			if (Score1.score1 + Score1.score2 == 9)
			{
				break;
			}
		}
	}
	
	//if a key is pressed, we'll send it over to the PlayerBall class for processing
		public void keyPressed(KeyEvent e){
			player1.keyPressed(e);
			player2.keyPressed(e);
		}

		//if a key is released, we'll send it over to the PlayerBall class for processing
		public void keyReleased(KeyEvent e){
			player1.keyReleased(e);
			player2.keyReleased(e);
		}

		//left empty because we don't need it; must be here because it is required to be overridded by the KeyListener interface
		public void keyTyped(KeyEvent e){

		}
}