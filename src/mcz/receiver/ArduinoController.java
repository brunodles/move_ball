package mcz.receiver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mcz.view.ViewBase;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import at.abraxas.amarino.Amarino;
import at.abraxas.amarino.AmarinoIntent;

public class ArduinoController extends BroadcastReceiver {

	private final String BLUETOOTH_ADDRESS = "20:13:10:30:05:98";
	ViewBase canvas;
	Context context;

	private static final String patternStr = "(\\d+?),(\\d+)";
	Pattern pattern;

	public ArduinoController(ViewBase canvas, Context context) {
		pattern = Pattern.compile(patternStr);
		this.canvas = canvas;
		this.context = context;
	}

	public void unregisterReceiver() {
		context.unregisterReceiver(this);
	}

	public void connect() {
		Amarino.connect(context, BLUETOOTH_ADDRESS);
	}

	public void registerReceiverConnect() {
		registerReceiver();
		connect();
	}

	private void registerReceiver() {
		IntentFilter intentFilter;
		intentFilter = new IntentFilter();
		intentFilter.addAction(AmarinoIntent.ACTION_CONNECT);
		intentFilter.addAction(AmarinoIntent.ACTION_CONNECTED);
		intentFilter.addAction(AmarinoIntent.ACTION_RECEIVED);
		context.registerReceiver(this, intentFilter);
	}

	public void sendData(char tag, int value) {
		Amarino.sendDataToArduino(context, BLUETOOTH_ADDRESS, tag, value);
	}

	public void sendData(char tag, String value) {
		Amarino.sendDataToArduino(context, BLUETOOTH_ADDRESS, tag, value);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d("mcz_receive", "onReceive");

		if (intent.getAction() == AmarinoIntent.ACTION_RECEIVED) {

			int dataType = intent
					.getIntExtra(AmarinoIntent.EXTRA_DATA_TYPE, -1);
			if (dataType == AmarinoIntent.STRING_EXTRA) {
				String pos = intent.getStringExtra(AmarinoIntent.EXTRA_DATA);
				// Log.d("mcz pos", "posicao      : " + pos);
				Matcher matcher = pattern.matcher(pos);
				if (matcher.find()) {
					try {
						int x = Integer.parseInt(matcher.group(2));
						int y = Integer.parseInt(matcher.group(1));
						// Log.d("mcz pos",
						// String.format("posicao array: %s,%s", x, y));
						x = ((535 - x) / 25);
						y = ((535 - y) / 25) * (-1);
						canvas.moveBall(x, y);
					} catch (Exception e) {
					}
				}
			}

		}

		if (intent.getAction() == AmarinoIntent.ACTION_CONNECTED) {
			Log.d("mcz connected", "ACTION_CONNECTED ");
		}

	}

}
