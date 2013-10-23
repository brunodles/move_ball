package mcz.moveball;

import mcz.view.ViewMenu;
import android.app.Activity;
import android.os.Bundle;

public class MenuMoveBallActivity extends Activity {

	ViewMenu canvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		canvas = new ViewMenu(this.getApplicationContext());
		setContentView(canvas);
		canvas.setOnTouchListener(canvas);
	}

	public void iniciarJogo() {
		
	}

}
