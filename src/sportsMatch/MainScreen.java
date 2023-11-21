package sportsMatch;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.SwingConstants;

public class MainScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public MainScreen(SportsMatch frame, JPanel gameScreen) {
		this.setBackground(SportsMatch.purple);
		this.setOpaque(true);
		setLayout(new BorderLayout());
		
		//Title JPanel
		JPanel titlePnl = titlePnl();
		this.add(titlePnl, BorderLayout.WEST);
		
		//Start JButton
		JButton startBtn = new JButton("START");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.switchTo("game");
				((GameScreen) gameScreen).startTimer();
			}
		});
		this.add(startBtn, BorderLayout.SOUTH);
		
		//TODO HighScore Panel (Add to EAST)
	}

	/**
	 * @return titlePnl
	 */
	private JPanel titlePnl() {
		JPanel titlePnl = new JPanel();
		titlePnl.setLayout(new BorderLayout());
		titlePnl.setBackground(SportsMatch.purple);
		//titlePnl.setPreferredSize(new Dimension(100, 100));
		
		JLabel topTitle = new JLabel("SPORTS");
		topTitle.setHorizontalAlignment(SwingConstants.CENTER);
		topTitle.setFont(SportsMatch.font);
		topTitle.setForeground(SportsMatch.gold);
		topTitle.setBackground(SportsMatch.purple);
		topTitle.setOpaque(true);
		
		JLabel titleImg = new JLabel("");
		titleImg.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon trophyImg = new ImageIcon(GameScreen.class.getResource("/resources/trophy.png"));
		titleImg.setIcon(new ImageIcon(trophyImg.getImage().getScaledInstance(75, 100, Image.SCALE_SMOOTH)));
		
		JLabel bottomTitle = new JLabel("MATCH");
		bottomTitle.setHorizontalAlignment(SwingConstants.CENTER);
		bottomTitle.setFont(SportsMatch.font);
		bottomTitle.setForeground(SportsMatch.gold);
		bottomTitle.setBackground(SportsMatch.purple);
		bottomTitle.setOpaque(true);
		
		titlePnl.add(topTitle, BorderLayout.NORTH);
		titlePnl.add(titleImg, BorderLayout.CENTER);
		titlePnl.add(bottomTitle, BorderLayout.SOUTH);
		return titlePnl;
	}

}
