package mcz.model;

public class Level {

	private int timer;
	private int chronometer;
	private int numHoles;
	private int remainNumHoles;
	private int actualLevel;
	
	public static int initialTimer = 10000; // mileseconds
	public static int initialHals  = 5; 
	public static int initialLevel = 1;
	
	public Level() {
		super();
		timer 	    = initialTimer;
		chronometer = initialTimer;
		numHoles 	= initialHals;
		actualLevel = initialLevel;
	}
	
	
	public boolean nextLevel() {		
		if (passedLevel()) {
			newTimerToNewLevel();
			numHoles 	   = initialHals;
			remainNumHoles = initialHals;
			actualLevel++;					
			return true;
		}
		
		return false;
	}
	
	public int pointHoleLevel(int valueHole) {
		int timePoint = (remainTimer() * 150) / timer;
		
		return actualLevel * valueHole * timePoint;
	}
	
	public int getNumHoles() {
		return numHoles;
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
		remainNumHoles --;
	}
	
	public void decreaseTimer(int time) {
		chronometer -= time;
	}
	
	public boolean timeOver() {
		return chronometer <= 0;
	}
	
	public int remainTimer() {
		return timer - chronometer;
	}
	
	private boolean passedLevel() {
		return (remainNumHoles == 0 && chronometer > 0);
	}
	
	private void newTimerToNewLevel() {
		timer -= 500; // mileseconds
		chronometer = timer;
	}
	
}
