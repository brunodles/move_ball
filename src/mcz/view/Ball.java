package mcz.view;

import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class Ball {

	float x;
	float y;
	float radius = 50;
	String text = null;
	Paint paint;

	public Ball(int color) {
		paint = new Paint();
		paint.setColor(color);
	}

	public void draw(Canvas canvas) {
		// Log.d("mcz", String.format("x = %s y = %s radius = %s", x, y,
		// radius));
		canvas.drawCircle(x, y, radius, paint);
		if (text == null) {
			return;
		}
		invertPaint(paint);
		paint.setTextSize(radius - (radius / 10));
		canvas.drawText(text, x - (radius / 3), y + (radius / 3), paint);
		paint.setColorFilter(null);
	}

	public void invertPaint(Paint p) {
		float mx[] = { -1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f,
				0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f,
				0.0f, 1.0f, 0.0f };
		ColorMatrix cm = new ColorMatrix(mx);

		p.setColorFilter(new ColorMatrixColorFilter(cm));
	}

	public void move(float x, float y) {
		this.x += x;
		this.y += y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
