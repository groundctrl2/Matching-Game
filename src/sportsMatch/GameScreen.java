package sportsMatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class GameScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Timer timer;
	int elapsedTime = 0;// Time played in seconds.

	// Front tile faces, each face randomly assigned to two tiles
	private ImageIcon[] tileImgs = { new ImageIcon(GameScreen.class.getResource("/resources/tile1.png")),
			new ImageIcon(GameScreen.class.getResource("/resources/tile2.png")),
			new ImageIcon(GameScreen.class.getResource("/resources/tile3.png")),
			new ImageIcon(GameScreen.class.getResource("/resources/tile4.png")),
			new ImageIcon(GameScreen.class.getResource("/resources/tile5.png")),
			new ImageIcon(GameScreen.class.getResource("/resources/tile6.png")),
			new ImageIcon(GameScreen.class.getResource("/resources/tile7.png")),
			new ImageIcon(GameScreen.class.getResource("/resources/tile8.png")),
			new ImageIcon(GameScreen.class.getResource("/resources/tile9.png")), };

	// Boolean values (Indexes matching tilesBtns array) representing whether a tile
	// is flipped.
	private boolean[] tileFlipped = { false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false };

	// Integer values (Indexes matching tilesBtns array) representing the randomly
	// assigned tile faces.
	private List<Integer> tileFaceIndices = new ArrayList<>();

	// Tile JButtons
	private JButton[] tileBtns = { new JButton(), new JButton(), new JButton(), new JButton(), new JButton(),
			new JButton(), new JButton(), new JButton(), new JButton(), new JButton(), new JButton(), new JButton(),
			new JButton(), new JButton(), new JButton(), new JButton(), new JButton(), new JButton() };

	/**
	 * Create the panel.
	 */
	public GameScreen(SportsMatch frame) {
		setLayout(new BorderLayout(0, 0));
		this.setBackground(SportsMatch.purple);
		this.setOpaque(true);

		// Title JLabel
		JPanel titlePnl = titlePnl();
		add(titlePnl, BorderLayout.WEST);

		// Tiles JPanel setup
		ImageIcon tileBackImg = new ImageIcon(GameScreen.class.getResource("/resources/tileBack.png"));
		JPanel tilesPnl = tilesPnl(tileBackImg);
		add(tilesPnl, BorderLayout.SOUTH);

		// Timer JLabel
		JLabel timerLbl = timerLbl();
		add(timerLbl, BorderLayout.EAST);

		// Timer updates timerLbl in stopwatch timer format every second.
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elapsedTime++;
				int minutes = elapsedTime / 60;
				int seconds = elapsedTime % 60;
				timerLbl.setText(String.format("%02d:%02d ", minutes, seconds));
			}
		});

		// timer.stop();
		// TODO End timer when all tiles matched.

		// return new Player(playerName, timerLbl.getText());
		// TODO Create and return new Player object when all tiles matched.
	}

	/**
	 * @return titleLbl
	 */
	private JPanel titlePnl() {
		// JPanel
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(SportsMatch.purple);

		// Image JLabel
		JLabel titleImg = new JLabel("");
		ImageIcon trophyImg = new ImageIcon(GameScreen.class.getResource("/resources/trophy.png"));
		titleImg.setIcon(new ImageIcon(trophyImg.getImage().getScaledInstance(75, 100, Image.SCALE_SMOOTH)));
		titlePnl.add(titleImg);

		// Text JLabel
		JLabel titleTxt = new JLabel(" SPORTS MATCH       ");
		titleTxt.setFont(SportsMatch.font);
		titleTxt.setForeground(SportsMatch.gold);
		titleTxt.setBackground(SportsMatch.purple);
		titleTxt.setOpaque(true);
		titlePnl.add(titleTxt);

		return titlePnl;
	}

	/**
	 * @return tilesPnl
	 */
	private JPanel tilesPnl(ImageIcon icon) {
		JPanel tilesPnl = new JPanel();
		tilesPnl.setBackground(SportsMatch.purple);
		tilesPnl.setLayout(new GridBagLayout());

		// Configure GridBagConstraints.
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.ipadx = 30; // Additional width padding
		constraints.ipady = 20; // Additional height padding
		constraints.insets = new Insets(5, 5, 5, 5); // External padding

		// Add Tile JButtons with tile back image.
		int row = 0;
		int col = 0;
		for (JButton btn : tileBtns) {
			btn.setPreferredSize(new Dimension(100, 155));
			btn.setIcon(new ImageIcon(icon.getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
			btn.setBackground(SportsMatch.purple);
			btn.setOpaque(true);

			constraints.gridx = col;
			constraints.gridy = row;
			tilesPnl.add(btn, constraints);

			col++;
			if (col == 6) { // 3x6 grid
				col = 0;
				row++;
			}
		}

		return tilesPnl;
	}

	/**
	 * @return timerLbl
	 */
	private JLabel timerLbl() {
		JLabel timerLbl = new JLabel("00:00 ");
		timerLbl.setFont(SportsMatch.font);
		timerLbl.setForeground(SportsMatch.gold);
		timerLbl.setBackground(SportsMatch.purple);
		timerLbl.setOpaque(true);
		return timerLbl;
	}
	
	/**
	 * Starts the timer. 
	 * Used in MainScreen when contentPane swaps to GameScreen.
	 */
	public void startTimer() {
		timer.start();// Start the timer when the program starts.
	}
}
