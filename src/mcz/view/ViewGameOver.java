package mcz.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

public class ViewGameOver extends ViewBase{

	private ViewGameOverListener listener;
	
	public ViewGameOver(Context context) {
		super(context);
		
		transparent = true;
		
		Hole hole = new Hole(Color.WHITE);
		hole.setX(345);
		hole.setY(1100);
		hole.setRadius(150);
		hole.setText("Menu");

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

	@Override
	protected void onBallOverHole(Hole hole) {
		if (listener != null){
			listener.closeGameOver();
		}
		
	}

	public ViewGameOverListener getListener() {
		return listener;
	}

	public void setListener(ViewGameOverListener listener) {
		this.listener = listener;
	}
	
	public static interface ViewGameOverListener{
		public void closeGameOver();
	}

}
