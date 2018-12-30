package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LevelInfo implements Serializable{	
	private static final long serialVersionUID = 1L;
	char[][] pipeGameBoard;
	int numberOfSteps;
	int timeInSeconds;
	
	public LevelInfo(char[][] pipeGameBoard, int numberOfSteps, int timeInSeconds) {
		this.pipeGameBoard = pipeGameBoard;
		this.numberOfSteps = numberOfSteps;
		this.timeInSeconds = timeInSeconds;
	}
	
	public char[][] getPipeGameBoard(){
		return pipeGameBoard;
	}
	
	public int getNumberOfSteps() {
		return numberOfSteps;
	}
	
	public int getTimeInSeconds() {
		return timeInSeconds;
	}
	
	public String[] getBoardLines(){
		List<String> boardLines = new ArrayList<String>();
		
		for (char[] boardLine : pipeGameBoard) {
			boardLines.add(new String(boardLine));
		}
		
		return (String[]) boardLines.toArray(new String[0]);
	}
}
