package mcz.model;

public class Score {

	private int score;

	public Score() {
		super();
		this.score = 0;
	}

	public int getScore() {
		return this.score;
	}
	
	public void addPoints(int points) {
		this.score += points;
	}
	
	
}
