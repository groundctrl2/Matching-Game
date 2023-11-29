package sportsMatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The Game/Matching Screen for the matching game
 * 
 * @author Tommy Collier, Wesley Elliott
 */
public class GameScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Timer timer;
	int elapsedTime = 60;// Time played in seconds.
	int score = 0;
	JLabel playerNameLabel;
	private Player player = new Player("", 0);
	int firstInput = -1;
	ImageIcon tileBackImg = new ImageIcon(GameScreen.class.getResource("/resources/tileBack.png"));

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

	// Integer values (Indexes matching tilesBtns array) representing the randomly
	// assigned tile faces. Values are shuffled in GameScreen constructor.
	static List<Integer> faceIndexes = new ArrayList<>(
			Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 6, 7, 8));

	// Boolean values (Indexes matching tilesBtns array) representing whether a tile
	// is flipped.
	private boolean[] tileFlipped = { false, false, false, false, false, false, false, false, false, false, false,
			false, false, false, false, false, false, false };

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
		Collections.shuffle(faceIndexes);// Shuffle tile face indexes (Matched with tileImgs indexes)
		JPanel tilesPnl = tilesPnl(tileBackImg, frame);
		add(tilesPnl, BorderLayout.SOUTH);

		// Timer JLabel
		JLabel timerLbl = timerLbl();
		add(timerLbl, BorderLayout.EAST);

		// players name JLabel
		playerNameLabel = playerLbl();
		add(playerNameLabel, BorderLayout.NORTH);

		// Timer updates timerLbl in stopwatch timer format every second.
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				elapsedTime--;
				score -= 27;
				int minutes = elapsedTime / 60;
				int seconds = elapsedTime % 60;
				timerLbl.setText(String.format("%02d:%02d ", minutes, seconds));
				if (elapsedTime == 0) {
					gameOver(frame);
				}
			}
		});
	}

	/**
	 * Title Panel, includes the title, logo, and player name.
	 * 
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
		JLabel titleTxt = new JLabel(" SPORTS MATCH " + player.getName());
		titleTxt.setFont(SportsMatch.font);
		titleTxt.setForeground(SportsMatch.gold);
		titleTxt.setBackground(SportsMatch.purple);
		titleTxt.setOpaque(true);
		titlePnl.add(titleTxt);

		return titlePnl;
	}

	/**
	 * Timer JLabel, used by the timer to count down.
	 * 
	 * @return timerLbl
	 */
	private JLabel timerLbl() {
		JLabel timerLbl = new JLabel("01:00 ");
		timerLbl.setFont(SportsMatch.font);
		timerLbl.setForeground(SportsMatch.gold);
		timerLbl.setBackground(SportsMatch.purple);
		timerLbl.setOpaque(true);
		return timerLbl;
	}

	/**
	 * Default Player JLabel, updated with players name in @method setPlayer
	 * 
	 * @return playernameLabel
	 */
	private JLabel playerLbl() {
		JLabel playerNameLabel = new JLabel("Player: " + player.getName());
		playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		playerNameLabel.setFont(SportsMatch.fontSmall);
		playerNameLabel.setForeground(SportsMatch.gold);
		playerNameLabel.setBackground(SportsMatch.purple);
		playerNameLabel.setOpaque(true);

		return playerNameLabel;
	}

	/**
	 * Tiles JPanel, adds all JButtons and formats them with default tile back icon.
	 * 
	 * @return tilesPnl
	 */
	private JPanel tilesPnl(ImageIcon icon, SportsMatch frame) {
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
		for (int i = 0; i < tileBtns.length; i++) {
			tileBtns[i].setPreferredSize(new Dimension(100, 155));
			tileBtns[i].setIcon(new ImageIcon(icon.getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
			tileBtns[i].setBackground(SportsMatch.purple);
			tileBtns[i].setOpaque(true);
			setActionListener(tileBtns[i], i, frame);// Needed because icon can't be changed by index otherwise.

			constraints.gridx = col;
			constraints.gridy = row;
			tilesPnl.add(tileBtns[i], constraints);

			col++;
			if (col == 6) { // 3x6 grid
				col = 0;
				row++;
			}
		}

		return tilesPnl;
	}

	/**
	 * Adds an ActionListener to the given JButton tile. When pressed, swaps to corresponding face tile icon, sets
	 * its flipped value (tileFlipped boolean array) to true, and calls Match method to either reset the incorrect
	 * match pairing or leave it as a correct match.
	 * 
	 * @param JButton btn
	 * @param tileFaceIndexes index i
	 * @return JButton btn with Action Listener
	 */
	private JButton setActionListener(JButton btn, int index, SportsMatch frame) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btn.setIcon(new ImageIcon(
						tileImgs[faceIndexes.get(index)].getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
				match(index, frame); // Check for match and respond if appropriate.
			}
		});
		return btn;
	}

	/**
	 * Used in JButton action listener, checks if current selection and previous selection are valid matches,
	 * responding appropriately.
	 * 
	 * @param Jbutton index selection input
	 */
	public void match(int input, SportsMatch frame) {
		// If this is the first tile in a pairing chosen, or the previously selected tile is the same as this one,
		// store the new input.
		if (firstInput == -1 || firstInput == input) {
			firstInput = input;
			// Else If both/either tiles have already been flipped,
		} else if (tileFlipped[input] || tileFlipped[firstInput]) {
			if (tileFlipped[firstInput]) {// If both are flipped, reset firstInput.
				firstInput = input;
			} else {
				// If the second selection is, flip the first back.
				tileBtns[firstInput]
						.setIcon(new ImageIcon(tileBackImg.getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
			}
		}
		// Else If the two chosen tiles are not the same, reset their icons.
		else if (faceIndexes.get(firstInput) != faceIndexes.get(input)) {
			// Set tileFlipped values back to false
			tileFlipped[firstInput] = false;
			tileFlipped[input] = false;

			// After a delay of 1 second (So we can see what tile is flipped),
			// set the button back to the tile back icon.
			ActionListener resetTileIcons = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					tileBtns[firstInput].setIcon(
							new ImageIcon(tileBackImg.getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
					tileBtns[input].setIcon(
							new ImageIcon(tileBackImg.getImage().getScaledInstance(125, 175, Image.SCALE_SMOOTH)));
					firstInput = -1; // Reset the first input so another pair can be found.
					score -= 42;
				}
			};

			Timer timer = new Timer(200, resetTileIcons); // 200 milliseconds / 1/5 second.
			timer.setRepeats(false); // Stops the timer from repeating itself.
			timer.start();
		}
		// Else they're a match and we can reset for the next match.
		else {
			score += 739;
			tileFlipped[firstInput] = true;
			tileFlipped[input] = true;
			firstInput = -1;

			// checks if all the tiles are flipped
			allMatched(frame);
		}
	}

	/**
	 * Loops through the tileFlipped index and if all are true calls the @method gameOver
	 * 
	 * @param frame
	 */
	private void allMatched(SportsMatch frame) {
		int matched = 0;
		for (int i = 0; i < tileFlipped.length; i++) {
			if (tileFlipped[i] == true) {
				matched++;
			}
			if (matched == 18) {
				gameOver(frame);
			}
		}
	}

	/*
	 * Sets player highscore, stops timer, and displays game over message, writes to a csv file
	 * 
	 * @param frame
	 */
	private void gameOver(SportsMatch frame) {
		player.setHighScore(score);
		timer.stop();
		int option = JOptionPane
				.showConfirmDialog(
						null, "            Game Over!\n" + player.getName() + ", your score was: "
								+ player.getHighScore() + "\nDo you want to play again?",
						null, JOptionPane.YES_NO_OPTION);

		String playerData = "\n" + player.getName() + "," + player.getHighScore();
		try {
			FileWriter playerScore = new FileWriter("HighScores.csv", true);
			playerScore.write(playerData);
			playerScore.close();
		} catch (Exception e) {
			e.getStackTrace();
		}

		if (option == JOptionPane.YES_OPTION) {
			restartApplication();
		} else {
			System.exit(0);
		}
	}

	/*
	 * Automatically exits and restarts application (to refresh the highscore list).
	 */
	private static void restartApplication() {
		try {
			String javaCommand = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = SportsMatch.class.getName();

			// Build the command to restart the program
			List<String> command = new ArrayList<>();
			command.add(javaCommand);
			command.add("-cp");
			command.add(classpath);
			command.add(className);

			// Start a new process for the restarted program
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			processBuilder.start();

			// Exit the current instance of the program
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Starts the timer. Used in @class SportsMatch when contentPane swaps to @class GameScreen.
	 */
	public void startTimer() {
		timer.start();// Start the timer when the program starts.
	}

	/**
	 * Grabs player from Main Screen input and updates player label text with user input name.
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
		playerNameLabel.setText("Player: " + player.getName());

	}

}
