package mcz.view;

import android.graphics.Canvas;

public class Hole extends Ball {

	boolean ballOver = false;

	public Hole(int color) {
		super(color);
	}

	@Override
	public void draw(Canvas canvas) {
		paint.setAlpha(ballOver ? 50 : 255);
		super.draw(canvas);
	}

	public boolean isBallOver(Ball ball) {
		float rX = ball.getX() - x;
		float rY = ball.getY() - y;
		if (rX < 0) {
			rX *= -1;
		}
		if (rY < 0) {
			rY *= -1;
		}
		ballOver = (rX < radius) && (rY < radius);
		return ballOver;
	}
}
