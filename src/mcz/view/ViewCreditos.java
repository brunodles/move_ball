package mcz.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class ViewCreditos extends ViewBase {

	public ViewCreditos(Context context) {
		super(context);

		Hole hole = new Hole(Color.WHITE);
		hole.setX(345);
		hole.setY(615);
		hole.setRadius(100);
		hole.setText("Voltar");

		holes.add(hole);
	}

	@Override
	protected void onFirstDraw(Canvas canvas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMoveBall() {
		// TODO Auto-generated method stub

	}

}
