package mcz.moveball;

import mcz.view.ViewGame;
import mcz.view.ViewGame.ViewGameListener;
import android.app.Activity;
import android.os.Bundle;

public class GameActivity extends Activity implements ViewGameListener {

	ViewGame canvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		canvas = new ViewGame(this);
		setContentView(canvas);
	}
	
	public void receive(){ 
//		canvas.moveBall(x, y);
	}
	
	

}
