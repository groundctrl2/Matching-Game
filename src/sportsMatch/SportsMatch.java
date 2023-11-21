package sportsMatch;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Sports-themed matching game.
 * 
 * @author Tommy Collier, Jack Bluth, Wesley Elliot
 */
public class SportsMatch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	CardLayout cardLayout = new CardLayout();
	JPanel mainScreen = new MainScreen();
	JPanel gameScreen = new GameScreen();
	
	//Colors and Fonts
	static Color purple = new Color(61, 65, 118);
	static Color gold = new Color(250, 187, 76);
	static Color blue = new Color(57, 109, 178);
	static Color yellow = new Color(255, 247, 102);
	static Font font = new Font("Arial Black", Font.PLAIN, 50);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SportsMatch frame = new SportsMatch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SportsMatch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(purple);
		contentPane.setOpaque(true);
		setContentPane(contentPane);
		
		contentPane.add(gameScreen);
		this.pack();
	}
	
	/**
	 * Used to swap scenes.
	 * 
	 * @param panelName
	 */
	public void switchTo(String panelName) {
		cardLayout.show(getContentPane(), panelName);
	}

}

