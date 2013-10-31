package mcz.moveball;

import mcz.view.ViewMenu;
import mcz.view.ViewMenu.ViewMenuListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MenuMoveBallActivity extends Activity implements ViewMenuListener {

	ViewMenu canvas;
	boolean starting = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		canvas = new ViewMenu(this.getApplicationContext());
		canvas.setMenuListener(this);
		setContentView(canvas);
	}

	public void iniciarJogo() {
		if (starting) {
			return;
		}
		starting = true;
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
		starting = true;
	}

	public void creditos() {
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
