/* 
 * Name: Sabagan Chandrakanthan
 * Date: May 29, 2022
 * 
 * Main class starts the game. It runs the constructor in GameFrame class. It also plays "gamer music" during the game so that the
   player doesn't just see objects moving around. The game first starts with a window containing the PING PONG main menu and the player
   must click anywhere on the screen for the current window to close and the game window to open.
   
   Challenge Features: 
   - Music
   - 2D image (on the main screen)
 */ 
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

public class Main {
	
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
		File file = new File("../music/Sound.wav");
		AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		
		JFrame frame = new JFrame();
		ImageIcon imageIcon = new ImageIcon("../image/Background.jpg"); // storing image as ImageIcon
		Image image = imageIcon.getImage(); // turning it into image
		Image newimg = image.getScaledInstance(750, 500, java.awt.Image.SCALE_SMOOTH); // Changing dimensions of image to fit into button  
		imageIcon = new ImageIcon(newimg); 
		
		JButton b = new JButton(imageIcon);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(b);
		
		b.addActionListener(e -> {
			frame.dispose(); // closes current window
			clip.start(); 
			new GameFrame(); // open window with game
			clip.loop(5);
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(750, 500));
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);//set window in middle of screen
		frame.setResizable(false);
	}
}
