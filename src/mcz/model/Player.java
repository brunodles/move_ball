package mcz.model;

public class Player {
	
	private Score score;
	private Level level;
	
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
	
	private void sendScoreToArduino() {
		// enviar score para o arduino
		
	}

	private void sendMsgNextLevelToArduino() {
		// enviar novo level para o arduino	
		
	}
	
	private void sendMsgGameOverToArduino() {
		// enviar msg de game over para o arduino
		
	}
	
	private void sendTimerToArduino(int time) {
		// enviar o tempo para o arduino
		
	}
}
