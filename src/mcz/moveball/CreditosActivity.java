package mcz.moveball;

import mcz.view.ViewCreditos;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;

public class CreditosActivity extends Activity {

	FrameLayout frameLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creditos);
		
		frameLayout = (FrameLayout) findViewById(R.id.teste);
		
		ViewCreditos viewCreditos = new ViewCreditos(this);
		
//		frameLayout.inflate(this, view, frameLayout);
		frameLayout.addView(viewCreditos);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.creditos, menu);
		return true;
	}

}
