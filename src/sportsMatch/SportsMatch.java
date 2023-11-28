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
 * @author Tommy Collier, Jack Bluth, Wesley Elliott
 */
public class SportsMatch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	CardLayout cardLayout = new CardLayout();
	JPanel gameScreen = new GameScreen(this);
	JPanel mainScreen = new MainScreen(this, gameScreen);

	// Colors and Fonts
	static Color purple = new Color(61, 65, 118);
	static Color gold = new Color(250, 187, 76);
	static Color blue = new Color(57, 109, 178);
	static Color yellow = new Color(255, 247, 102);
	static Font font = new Font("Arial Black", Font.PLAIN, 50);
	static Font fontSmall = new Font("Arial Black", Font.PLAIN, 40);

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

		// Card Layout configuration
		setLayout(cardLayout);
		contentPane.add(mainScreen, "main");
		contentPane.add(gameScreen, "game");
		cardLayout.show(contentPane, "main");// Start with main page.

		// Reload and Pack
		mainScreen.revalidate();
		gameScreen.revalidate();
		this.pack();
	}

	/**
	 * Used to swap scenes, and set name from Main Screen
	 * 
	 * @param panelName
	 */
	public void switchTo(String panelName, Player player) {
		cardLayout.show(getContentPane(), panelName);
		((GameScreen) gameScreen).startTimer();
		((GameScreen) gameScreen).setPlayer(player);
	}

}
