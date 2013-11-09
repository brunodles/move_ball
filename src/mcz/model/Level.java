package mcz.model;

import android.util.Log;

public class Level {

	private int timer;
	private int chronometer;
	private int numHoles;
	private int remainNumHoles;
	private int actualLevel;

	public static int initialTimer = 10000; // mileseconds
	public static int initialHales = 5;
	public static int initialLevel = 1;

	public Level() {
		super();
		timer = initialTimer;
		chronometer = initialTimer;
		numHoles = initialHales;
		remainNumHoles = initialHales;
		actualLevel = initialLevel;
	}

	public boolean nextLevel() {
		Log.d("mcz nextLevel", "remainNumHoles HOLE: " + remainNumHoles);
		Log.d("mcz nextLevel", "chronometer: " + chronometer);
		if (passedLevel()) {
			Log.d("mcz nextLevel", "TRUE PassedLevel");
			newTimerToNewLevel();
			numHoles = initialHales;
			remainNumHoles = initialHales;
			actualLevel++;
			return true;
		}

		return false;
	}

	public int pointHoleLevel(int valueHole) {
//		int timePoint = (remainTimer() * 150) / timer;
		return actualLevel * valueHole;
	}

	public int getNumHoles() {
		return numHoles;
	}

	public int getActualLevel() {
		return actualLevel;
	}

	public int getTimer() {
		return timer;
	}

	public int getChronometer() {
		return chronometer;
	}

	public void obtainHole() {
		remainNumHoles--;
	}

	public void decreaseTimer(int time) {
		chronometer -= time;
	}

	public boolean timeOver() {
		return chronometer < 0;
	}

	public int remainTimer() {
		return timer - chronometer;
	}

	private boolean passedLevel() {
		return (remainNumHoles == 0 && chronometer > 0);
	}

	private void newTimerToNewLevel() {
		timer -= 1000; // mileseconds
		chronometer = timer;
		if (timer < 1000) { // 1 segundo
			timer = 1000;
		}
	}
}
