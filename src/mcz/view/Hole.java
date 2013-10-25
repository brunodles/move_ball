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

	public boolean isOver(Hole hole) {
		double rX = Math.pow(hole.getX() - x, 2);
		double rY = Math.pow(hole.getY() - y, 2);
		double rR = Math.pow(radius + hole.radius, 2);
		ballOver = (rX + rY) <= rR;
		return ballOver;
	}

	public boolean isOver(Ball ball) {
		double rX = Math.pow(ball.getX() - x, 2);
		double rY = Math.pow(ball.getY() - y, 2);
		double rR = Math.pow(radius - ball.radius, 2);
		ballOver = (rX + rY) <= rR;
		return ballOver;
	}
}
