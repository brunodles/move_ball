package mcz.view;

import java.util.Random;

import mcz.model.Player;
import mcz.model.Player.PlayerListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class ViewGame extends ViewBase {

	private static final int HOLE_RADIUS = 100;
	HolesThread holesThread;
	Random random;
	ViewGameListener viewGameListener;
	Player player;

	public ViewGame(Context context) {
		super(context);
		random = new Random();
		holesThread = new HolesThread();
		player = new Player();
	}

	public void setViewGameListener(ViewGameListener viewGameListener) {
		this.viewGameListener = viewGameListener;
		player.setListener(viewGameListener);
	}

	@Override
	protected void onFirstDraw(Canvas canvas) {
		newHolesThread();
	}

	public void newHolesThread() {
		new Thread(holesThread).start();
	}

	public void initHoles() {
		sleep(calculateWaitingTime());
		holes.clear();
		int width = getWidth();
		int height = getHeight();
		for (int i = 1; i <= player.numHoles(); i++) {
			newHole(width, height, i);
		}
		try {
			this.postInvalidate();
		} catch (Exception e) {
		}
		newHolesThread();
	}

	private long calculateWaitingTime() {
		long result = player.timer();
		return result;
	}

	private void newHole(int width, int height, int i) {
		// TODO modificar score.
		Hole hole = new Hole(Color.RED);
		hole.setRadius(HOLE_RADIUS);
		int halfRadius = HOLE_RADIUS / 2;
		do {
			hole.setX(halfRadius + random.nextInt(width - HOLE_RADIUS));
			hole.setY(halfRadius + random.nextInt(height - HOLE_RADIUS));
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
			initHoles();
		}
	}

	public static interface ViewGameListener extends PlayerListener {
	}

	@Override
	public void onMoveBall() {
	}

	@Override
	protected void onBallOverHole(Hole hole) {
		player.obtainHole(hole.getScore());
		holes.remove(hole);
		player.verifyGame(0);
	}
}
