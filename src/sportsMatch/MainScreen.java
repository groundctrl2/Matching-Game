package sportsMatch;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.*;


public class MainScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	public MainScreen(SportsMatch frame, JPanel gameScreen) {
		
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(61, 65, 118), new Color(130, 134, 189)));
		this.setBackground(new Color(130, 134, 189));
		this.setOpaque(true);
		setLayout(null);
		
		//Title JPanel
		JPanel titlePnl = titlePnl();
		this.add(titlePnl);
		
		//Start JButton
		//when clicked creates player object, with name from text field and high score set at zero
		JPanel playPnl = playPnl(frame, gameScreen);
		this.add(playPnl);
		
		//HighScore Display
		JPanel highScore = highScore();
		this.add(highScore);
		
	}

	/**
	 * @return titlePnl
	 */
	private JPanel titlePnl() {
		JPanel titlePnl = new JPanel();
		titlePnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(130, 134, 189), new Color(61, 65, 118)));
		titlePnl.setBackground(SportsMatch.purple);
		titlePnl.setBounds(10, 11, 415, 484);
		
		JLabel topTitle = new JLabel("SPORTS");
		topTitle.setHorizontalAlignment(SwingConstants.CENTER);
		topTitle.setFont(new Font("Arial Black", Font.PLAIN, 70));
		topTitle.setForeground(new Color(250, 187, 76));
		topTitle.setBackground(new Color(61, 65, 118));
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
	
	private JPanel playPnl(SportsMatch frame, JPanel gameScreen) {
		JPanel playPnl = new JPanel();
		playPnl.setBounds(10, 506, 415, 217);
		playPnl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(130, 134, 189), new Color(61, 65, 118)));
		playPnl.setBackground(SportsMatch.purple);
		playPnl.setLayout(null);
		
		JTextField nameFld = new JTextField(10);
		nameFld.setForeground(new Color(61, 65, 118));
		nameFld.setBackground(new Color(130, 134, 189));
		nameFld.setText("Enter Name Here...");
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
		startBtn.setForeground(new Color(130, 134, 189));
		startBtn.setBackground(new Color(61, 65, 118));
		startBtn.setFont(new Font("Lucida Console", Font.BOLD, 20));
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
	
	private JPanel highScore()
	{
		JPanel highScore = new JPanel();
		highScore.setBorder(new LineBorder(new Color(61, 65, 118), 5, true));
		highScore.setBackground(new Color(130, 134, 189));
		highScore.setBounds(435, 11, 395, 712);
		add(highScore);
		highScore.setLayout(null);
		
		JLabel highLbl = new JLabel("HIGHSCORES");
		highLbl.setBackground(new Color(61, 65, 118));
		highLbl.setFont(new Font("Arial Black", Font.PLAIN, 45));
		highLbl.setForeground(new Color(130, 134, 189));
		highLbl.setHorizontalAlignment(SwingConstants.CENTER);
		highLbl.setBounds(10, 11, 375, 109);
		highLbl.setOpaque(true);
		highScore.add(highLbl);
		
		JList list = new JList();
		list.setValueIsAdjusting(true);
		list.setBorder(new LineBorder(new Color(61, 65, 118), 5));
		list.setBackground(new Color(130, 134, 189));
		list.setBounds(10, 131, 375, 570);
		highScore.add(list);
		return highScore;
	}
}
