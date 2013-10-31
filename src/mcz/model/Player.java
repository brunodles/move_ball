package mcz.model;

public class Player {

	private Score score;
	private Level level;
	private PlayerListener listener;

	public Player() {
		super();
		score = new Score();
		level = new Level();
	}

	public void obtainHole(int pointHole) {
		level.obtainHole();
		score.addPoints(pointHole);
		sendScoreToArduino();
	}

	public void verifyGame(int time) {
		level.decreaseTimer(time);

		boolean result = level.nextLevel();

		if (result) {
			sendMsgNextLevelToArduino();
			sendTimerToArduino(level.getTimer());
		} else if (level.timeOver()) {
			sendMsgGameOverToArduino();
			sendScoreToArduino();
		} else {
			sendTimerToArduino(level.getChronometer());

		}

	}

	public int pointHoleLevel(int valueHole) {
		return level.pointHoleLevel(valueHole);
	}
	
	public int timer(){
		return level.getTimer();
	}

	public int remainTimer() {
		return level.remainTimer();
	}

	public int numHoles() {
		return level.getNumHoles();
	}

	public void setListener(PlayerListener listener) {
		this.listener = listener;
	}

	private void sendScoreToArduino() {
		// enviar score para o arduino
		if (listener != null) {
			listener.sendScore(score.getScore());
		}

	}

	private void sendMsgNextLevelToArduino() {
		// enviar novo level para o arduino
		if (listener != null) {
			listener.sendNextLevel(level.getActualLevel());
		}
	}

	private void sendMsgGameOverToArduino() {
		// enviar msg de game over para o arduino
		if (listener != null) {
			listener.sendGameOver();
		}
	}

	private void sendTimerToArduino(int time) {
		// enviar o tempo para o arduino
		if (listener != null) {
			listener.sendTime(time);
		}
	}

	// TODO vê isso aqui.
	// public int getHoles(){
	// // return level.
	// }

	public static interface PlayerListener {
		public void sendScore(int score);

		public void sendNextLevel(int level);

		public void sendGameOver();

		public void sendTime(int time);
	}
}
