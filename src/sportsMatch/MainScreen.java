package sportsMatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class MainScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MainScreen(SportsMatch frame, JPanel gameScreen) {

		setBorder(new EtchedBorder(EtchedBorder.LOWERED, SportsMatch.purple, SportsMatch.blue));
		this.setBackground(SportsMatch.blue);
		this.setOpaque(true);
		setLayout(null);

		// Title JPanel
		JPanel titlePnl = titlePnl();
		this.add(titlePnl);

		// Start JButton
		// when clicked creates player object, with name from text field and high score
		// set at zero
		JPanel playPnl = playPnl(frame, gameScreen);
		this.add(playPnl);

		// HighScore Display
		JPanel highScore = highScore();
		this.add(highScore);

	}

	/**
	 * @return titlePnl
	 */
	private JPanel titlePnl() {
		JPanel titlePnl = new JPanel();
		titlePnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SportsMatch.blue, SportsMatch.purple));
		titlePnl.setBackground(SportsMatch.purple);
		titlePnl.setBounds(10, 11, 415, 484);

		JLabel topTitle = new JLabel("SPORTS");
		topTitle.setHorizontalAlignment(SwingConstants.CENTER);
		topTitle.setFont(new Font("Arial Black", Font.PLAIN, 70));
		topTitle.setForeground(SportsMatch.gold);
		topTitle.setBackground(SportsMatch.purple);
		topTitle.setOpaque(true);
		ImageIcon trophyImg = new ImageIcon(GameScreen.class.getResource("/resources/trophy.png"));

		JLabel bottomTitle = new JLabel("MATCH");
		bottomTitle.setHorizontalAlignment(SwingConstants.CENTER);
		bottomTitle.setFont(new Font("Arial Black", Font.PLAIN, 70));
		bottomTitle.setForeground(SportsMatch.gold);
		bottomTitle.setBackground(SportsMatch.purple);
		bottomTitle.setOpaque(true);
		titlePnl.setLayout(new GridLayout(0, 1, 0, 0));
		titlePnl.add(topTitle);

		JLabel titleImg = new JLabel("");
		titleImg.setHorizontalAlignment(SwingConstants.CENTER);
		titleImg.setIcon(new ImageIcon(trophyImg.getImage().getScaledInstance(100, 125, Image.SCALE_SMOOTH)));
		titlePnl.add(titleImg);
		titlePnl.add(bottomTitle);
		return titlePnl;
	}

	/**
	 * @param frame
	 * @param gameScreen
	 * @return playPnl
	 */
	private JPanel playPnl(SportsMatch frame, JPanel gameScreen) {
		JPanel playPnl = new JPanel();
		playPnl.setBounds(10, 506, 415, 217);
		playPnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, SportsMatch.blue, SportsMatch.purple));
		playPnl.setBackground(SportsMatch.purple);
		playPnl.setLayout(null);

		JTextField nameFld = new JTextField(10);
		nameFld.setForeground(SportsMatch.purple);
		nameFld.setBackground(SportsMatch.blue);
		nameFld.setText("Enter Name Here...");
		nameFld.setFont(new Font("Arial Black", Font.PLAIN, 20));
		nameFld.setBounds(10, 11, 395, 62);
		playPnl.add(nameFld);

		nameFld.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (nameFld.getText().equals("Enter Name Here...")) {
					nameFld.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (nameFld.getText().isEmpty()) {
					nameFld.setText("Enter Name Here...");
				}
			}
		});

		JButton startBtn = new JButton("PLAY");
		startBtn.setForeground(SportsMatch.gold);
		startBtn.setBackground(SportsMatch.purple);
		startBtn.setFont(SportsMatch.fontSmall);
		startBtn.setBounds(10, 84, 395, 122);
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameFld.getText();
				if (name.isEmpty() || name.equals("Enter Name Here...")) {
					JOptionPane.showMessageDialog(playPnl, "Please enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					Player player = new Player(name, 0);
					frame.switchTo("game", player);
				}

			}
		});
		playPnl.add(startBtn);
		return playPnl;
	}

	/**
	 * @return highScore
	 */
	private JPanel highScore() {
		JPanel highScore = new JPanel();
		highScore.setBorder(new LineBorder(SportsMatch.purple, 5, true));
		highScore.setBackground(SportsMatch.blue);
		highScore.setBounds(435, 11, 395, 712);
		add(highScore);
		highScore.setLayout(null);

		JLabel highLbl = new JLabel("HIGHSCORES");
		highLbl.setBackground(SportsMatch.purple);
		highLbl.setFont(SportsMatch.fontSmall);
		highLbl.setForeground(SportsMatch.gold);
		highLbl.setHorizontalAlignment(SwingConstants.CENTER);
		highLbl.setBounds(10, 11, 375, 109);
		highLbl.setOpaque(true);
		highScore.add(highLbl);

		JList list = new JList();
		list.setValueIsAdjusting(true);
		list.setBorder(new LineBorder(SportsMatch.purple, 5));
		list.setBackground(SportsMatch.blue);
		list.setBounds(10, 131, 375, 570);
		highScore.add(list);
		return highScore;
	}
}
