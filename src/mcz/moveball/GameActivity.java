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

	@Override
	public void sendScore(int score) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendNextLevel(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendGameOver() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendTime(int time) {
		// TODO Auto-generated method stub
		
	}
	
	

}
