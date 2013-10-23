package mcz.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

public abstract class ViewBase extends android.view.View implements
		OnTouchListener {

	public static int backgroundColor = Color.WHITE;
	public static int ballColor = Color.BLACK;
	private boolean alreadyDrawed = false;
	//
	protected Ball ball = new Ball(ballColor);
	protected ArrayList<Hole> holes = new ArrayList<Hole>();

	public ViewBase(Context context) {
		super(context);
		Log.d("mcz", "ViewBase");
		ball.setRadius(50);
	}

	protected void firstDraw(Canvas canvas) {
		alreadyDrawed = true;
		ball.setX(getWidth() / 2);
		ball.setY(getHeight() / 2);
		onFirstDraw(canvas);
	}

	protected abstract void onFirstDraw(Canvas canvas);

	@Override
	protected void onDraw(Canvas canvas) {
		Log.d("mcz", "onDraw " + ball.getX() + " " + ball.getY());
		super.onDraw(canvas);
		if (!alreadyDrawed) {
			firstDraw(canvas);
		}

		for (Ball hole : holes) {
			hole.draw(canvas);
		}

		ball.draw(canvas);
	}

	public void moveBall(float x, float y) {
		Log.d("mcz", String.format("X = %s Y = %s", x, y));
		ball.setX(x);
		ball.setY(y);
		invalidate();
		checkHole();
	}

	public void checkHole() {
		for (Hole hole : holes) {
			hole.isBallOver(ball);
		}
	}

	@Override
	public boolean onTouch(android.view.View view, MotionEvent motionEvent) {
		float x = motionEvent.getX();
		float y = motionEvent.getY();
		moveBall(x, y);
		return true;
	}

	// private class ThreadupdateScreen

}
