package mcz.moveball;

import mcz.receiver.ArduinoController;
import mcz.view.ViewGame;
import mcz.view.ViewGame.ViewGameListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

public class GameActivity extends Activity implements ViewGameListener {

	ViewGame canvas;
	ArduinoController arduino;
	TextView score;
	TextView time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		score = (TextView) findViewById(R.id.txtScore);
		time = (TextView) findViewById(R.id.txtTime);
		FrameLayout frameLayout = (FrameLayout) findViewById(R.id.game);

		canvas = new ViewGame(this);
		canvas.setViewGameListener(this);
		frameLayout.addView(canvas);

		arduino = new ArduinoController(canvas, this);
		arduino.registerReceiverConnect();

	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(arduino);
		super.onDestroy();
	}

	public void receive() {
		// canvas.moveBall(x, y);
	}

	private void changeScoreOnAndroid(final int scoreValue) {
		this.score.post(new Runnable() {

			@Override
			public void run() {
				score.setText("" + scoreValue);
			}
		});
	}

	@Override
	public void sendScore(int score) {
		arduino.sendData('S', score);
		Log.d("mcz.bruno", "sendScore " + score);
		changeScoreOnAndroid(score);
	}

	@Override
	public void sendNextLevel(int level) {
		arduino.sendData('G', "Level " + level + "!");
	}

	@Override
	public void sendGameOver() {
		Log.d("mcz", "sendGameOver GAME OVER ");
		arduino.sendData('G', "Game Over");
		Intent it = new Intent(this, GameOverActivity.class);
		startActivity(it);
		finish();
	}

	@Override
	public void sendTime(int time) {
		time /= 1000;
		Log.d("mcz.bruno", "sendTime " + time);
		changeTimeOnAndroid(time);
		arduino.sendData('T', time);
	}

	private void changeTimeOnAndroid(final int timeValue) {
		this.time.post(new Runnable() {

			@Override
			public void run() {
				time.setText("" + timeValue);
			}
		});
	}

}
