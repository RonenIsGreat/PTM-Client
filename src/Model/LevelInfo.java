package Model;

import java.util.ArrayList;
import java.util.List;

public class LevelInfo {	
	char[][] pipeGameBoard;
	int numberOfSteps;
	int timeInSeconds;
	
	public LevelInfo(char[][] pipeGameBoard, int numberOfSteps, int timeInSeconds) {
		this.pipeGameBoard = pipeGameBoard;
		this.numberOfSteps = numberOfSteps;
		this.timeInSeconds = timeInSeconds;
	}
	
	public String[] getBoardLines(){
		List<String> boardLines = new ArrayList<String>();
		
		for (char[] boardLine : pipeGameBoard) {
			boardLines.add(new String(boardLine));
		}
		
		return (String[]) boardLines.toArray(new String[0]);
	}
}
