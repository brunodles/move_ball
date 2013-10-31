package mcz.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

public class ViewMenu extends ViewBase {

	int holeRadius;
	ViewMenuListener listener;

	public ViewMenu(Context context) {
		super(context);
		Log.d("mcz", "ViewMenu");
	}

	public void setMenuListener(ViewMenuListener listener) {
		this.listener = listener;
	}

	public int lessSize() {
		int width = getWidth();
		int height = getHeight();
		if (width < height) {
			return width;
		}
		return height;
	}

	@Override
	protected void onFirstDraw(Canvas canvas) {
		holeRadius = lessSize() / 3;

		Hole start = new Hole(0xff0000ff);
		start.setRadius(holeRadius);
		start.setX(getWidth() / 2);
		start.setY(holeRadius);
		start.setText("Start");
		holes.add(start);

		Hole credit = new Hole(0xffff0000);
		credit.setRadius(holeRadius);
		credit.setX(getWidth() / 2);
		credit.setY(getHeight() - holeRadius);
		credit.setText("Credit");
		holes.add(credit);
	}

	public static interface ViewMenuListener {
		public void onCreditsOver();

		public void onStartOver();
	}

	@Override
	public void onMoveBall() {
	}

	@Override
	protected void onBallOverHole(Hole hole) {
		if ("START".equalsIgnoreCase(hole.getText())) {
			listener.onStartOver();
		} else {
			listener.onCreditsOver();
		}
		
	}

}
