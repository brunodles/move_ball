package mcz.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class ViewCreditos extends ViewBase {

	private ViewCreditosListener listener;
	
	public ViewCreditos(Context context) {
		super(context);

		transparent = true;
		
		Hole hole = new Hole(Color.WHITE);
		hole.setX(345);
		hole.setY(1100);
		hole.setRadius(150);
		hole.setText("Back");

		holes.add(hole);
	}
	
	public void setListener(ViewCreditosListener listener) {
		this.listener = listener;
	}

	@Override
	protected void onFirstDraw(Canvas canvas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMoveBall() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onBallOverHole(Hole hole) {
		listener.closeCreditos();
	}	
	
	public static interface ViewCreditosListener{
		public void closeCreditos();
	};

}
