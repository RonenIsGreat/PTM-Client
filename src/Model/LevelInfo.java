package Model;

public class LevelInfo {	
	char[][] pipeGameBoard;
	int numberOfSteps;
	int timeInSeconds;
	
	public LevelInfo(char[][] pipeGameBoard, int numberOfSteps, int timeInSeconds) {
		this.pipeGameBoard = pipeGameBoard;
		this.numberOfSteps = numberOfSteps;
		this.timeInSeconds = timeInSeconds;
	}
}
