package sportsMatch;

/**
 * Implements a basic Player object.
 * 
 * @author Tommy Collier, Wesley Elliott
 */
public class Player {

	String name;
	int highScore;

	public Player(String name, int highScore) {
		this.name = name;
		this.highScore = highScore;
	}

	public String getName() {
		return name;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	@Override
	public String toString() {
		return name + ": " + highScore;
	}

}
