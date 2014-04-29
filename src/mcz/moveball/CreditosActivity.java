package mcz.moveball;

import mcz.receiver.ArduinoController;
import mcz.view.ViewCreditos;
import mcz.view.ViewCreditos.ViewCreditosListener;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;

public class CreditosActivity extends Activity implements ViewCreditosListener {

	FrameLayout frameLayout;
	ArduinoController arduino;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creditos);

		frameLayout = (FrameLayout) findViewById(R.id.teste);

		ViewCreditos viewCreditos = new ViewCreditos(this);
		viewCreditos.setListener(this);
		// frameLayout.inflate(this, view, frameLayout);
		frameLayout.addView(viewCreditos);

		arduino = new ArduinoController(viewCreditos, this);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creditos, menu);
		return true;
	}

	@Override
	public void closeCreditos() {
		finish();

	}

}
