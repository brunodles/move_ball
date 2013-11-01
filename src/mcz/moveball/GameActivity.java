package mcz.moveball;

import mcz.view.ViewGame;
import mcz.view.ViewGame.ViewGameListener;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import at.abraxas.amarino.Amarino;
import at.abraxas.amarino.AmarinoIntent;

public class GameActivity extends Activity implements ViewGameListener {

	ViewGame canvas;
	private final String BLUETOOTH_ADDRESS = "";
	ArduinoReceiver receiver;
	IntentFilter intentFilter;
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

		Amarino.connect(this, BLUETOOTH_ADDRESS);

		intentFilter = new IntentFilter();
		intentFilter.addAction(AmarinoIntent.ACTION_RECEIVED);
		intentFilter.addAction(AmarinoIntent.ACTION_CONNECTED);

		this.registerReceiver(receiver, intentFilter);
	}

	public void receive() {
		// canvas.moveBall(x, y);
	}

	@Override
	public void sendScore(int score) {
		// Amarino.sendDataToArduino(this, BLUETOOTH_ADDRESS, 'S', score);
		Log.d("mcz.bruno", "sendScore " + score);
		changeScoreOnAndroid(score);
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
	public void sendNextLevel(int level) {
		// Amarino.sendDataToArduino(this, BLUETOOTH_ADDRESS, 'L', level);
	}

	@Override
	public void sendGameOver() {
		// Amarino.sendDataToArduino(this, BLUETOOTH_ADDRESS, 'G', "");
	}

	@Override
	public void sendTime(int time) {
		Log.d("mcz.bruno", "sendTime " + time);
		changeTimeOnAndroid(time);
		Amarino.sendDataToArduino(this, BLUETOOTH_ADDRESS, 'T', time);
	}

	private void changeTimeOnAndroid(final int timeValue) {
		this.time.post(new Runnable() {

			@Override
			public void run() {
				time.setText("" + timeValue);
			}
		});
	}

	private class ArduinoReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			if (intent.getAction() == AmarinoIntent.ACTION_RECEIVED) {
				int posicao[] = intent.getIntArrayExtra("");
				Log.d("posicao array: ", posicao.toString());
			}

		}

	}

}
