package mcz.model;

public class Level {

	private int timer;
	private int chronometer;
	private int numHoles;
	private int actualLevel;
	
	public static int initialTimer = 10000; // mileseconds
	public static int initialballs = 5; 
	public static int initialLevel = 1;
	
	public Level() {
		super();
		timer 	    = initialTimer;
		chronometer = initialTimer;
		numHoles 	= initialballs;
		actualLevel = initialLevel;
	}
	
	
	public boolean nextLevel() {		
		if (passedLevel()) {
			newTimerToNewLevel();
			numHoles = initialballs;
			actualLevel++;					
			return true;
		}
		
		return false;
	}
	
	public int getActualLevel(){
		return actualLevel;
	}
	
	public int getTimer() {
		return timer;
	}
	
	public int getChronometer() {
		return chronometer;
	}

	public void obtainHole() {
		numHoles --;
	}
	
	public void decreaseTimer(int time) {
		chronometer -= time;
	}
	
	public boolean timeOver() {
		return chronometer <= 0;
	}
	
	private boolean passedLevel() {
		return (numHoles == 0 && chronometer > 0);
	}
	
	private void newTimerToNewLevel() {
		timer -= 500; // mileseconds
		chronometer = timer;
	}
	
}
