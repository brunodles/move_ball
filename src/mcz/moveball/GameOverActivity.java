package mcz.moveball;

import mcz.receiver.ArduinoController;
import mcz.view.ViewGameOver;
import mcz.view.ViewGameOver.ViewGameOverListener;
import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class GameOverActivity extends Activity implements ViewGameOverListener {

	FrameLayout frameLayout;
	ArduinoController arduino;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_over);
		frameLayout = (FrameLayout) findViewById(R.id.gameOver);
		ViewGameOver viewGameOver = new ViewGameOver(this);
		viewGameOver.setListener(this);
		frameLayout.addView(viewGameOver);

		arduino = new ArduinoController(viewGameOver, this);
	}

	@Override
	protected void onResume() {
		arduino.registerReceiverConnect();
		super.onResume();
	}

	@Override
	protected void onPause() {
		arduino.unregisterReceiver();
		super.onPause();
	}

	@Override
	public void closeGameOver() {
		// Intent it = new Intent(this, MenuMoveBallActivity.class);
		// startActivity(it);
		finish();

	}

}
