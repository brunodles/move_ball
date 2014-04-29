package mcz.moveball;

import mcz.receiver.ArduinoController;
import mcz.view.ViewMenu;
import mcz.view.ViewMenu.ViewMenuListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MenuMoveBallActivity extends Activity implements ViewMenuListener {

	ViewMenu canvas;
	boolean starting = false;
	ArduinoController arduino;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		canvas = new ViewMenu(this.getApplicationContext());
		canvas.setMenuListener(this);
		setContentView(canvas);
		arduino = new ArduinoController(canvas, this);
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

	public void iniciarJogo() {
		if (starting) {
			return;
		}
		starting();
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
	}

	public void starting() {
		starting = true;
		Handler handler = new Handler();
		canvas.moveBallToInitialPosition();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				starting = false;
				canvas.moveBallToInitialPosition();
			}
		}, 1000); // 3 s
	}

	public void creditos() {
		if (starting) {
			return;
		}
		starting();
		Intent creditoIntent = new Intent(this, CreditosActivity.class);
		startActivity(creditoIntent);
	}

	@Override
	public void onCreditsOver() {
		creditos();
	}

	@Override
	public void onStartOver() {
		iniciarJogo();
	}

}
