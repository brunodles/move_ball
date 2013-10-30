package mcz.view;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class ViewGame extends ViewBase {

	private static final int WAITING_TIME_LEVEL_DEDUCTION = 500;
	private static final int MIN_WAITING_TIME = 1000;
	private static final long START_WAITING_TIME = 10000;
	//
	private int balls = 5;
	private int level = 1;
	HolesThread holesThread;
	Random random;
	ViewGameListener viewGameListener;

	public ViewGame(Context context) {
		super(context);
		random = new Random();
		holesThread = new HolesThread();
	}

	public void setViewGameListener(ViewGameListener viewGameListener) {
		this.viewGameListener = viewGameListener;
	}

	@Override
	protected void onFirstDraw(Canvas canvas) {
		newHolesThread();
	}

	public void newHolesThread() {
		new Thread(holesThread).start();
	}

	public void changeHoles() {
		sleep(calculateWaitingTime());
		holes.clear();
		int width = getWidth();
		int height = getHeight();
		for (int i = 1; i <= balls; i++) {
			newHole(width, height, i);
		}
		try {
			this.postInvalidate();
		} catch (Exception e) {
		}
		newHolesThread();
	}

	private long calculateWaitingTime() {
		long result = START_WAITING_TIME
				- (WAITING_TIME_LEVEL_DEDUCTION * level);
		if (result < MIN_WAITING_TIME) {
			result = MIN_WAITING_TIME;
		}
		return result;
	}

	private void newHole(int width, int height, int i) {
		Hole hole = new Hole(Color.RED);
		hole.setRadius(100);
		do {
			hole.setX(random.nextInt(width));
			hole.setY(random.nextInt(height));
		} while (isHoleOver(hole));
		hole.setText("" + i);
		holes.add(hole);
	}

	private boolean isHoleOver(Hole newHole) {
		for (Hole hole : holes) {
			if (hole.isOver(newHole)) {
				return true;
			}
		}
		return newHole.isOver(ball);
	}

	private class HolesThread implements Runnable {

		@Override
		public void run() {
			changeHoles();
		}
	}

	public static interface ViewGameListener {
	}

	@Override
	public void onMoveBall() {
	}

	@Override
	protected void onBallOverHole(Hole hole) {
		holes.remove(hole);
		if (holes.isEmpty()) {
			level++;
		}
	}
}
