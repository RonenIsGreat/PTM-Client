package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.DataManager;
import Model.DataManagerListener;
import Model.LevelInfo;
import View.MazeDisplayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

public class MainWindowController implements Initializable, DataManagerListener{
	private DataManager dataManager;
	
	int[][] mazeData= {
			{1,1,1,1,1,1},	
			{0,0,0,0,0,1},	
			{1,1,1,0,0,1},	
			{1,0,1,0,0,1},	
			{1,0,1,0,0,1},	
			{1,1,1,1,0,1},
			{1,1,1,0,0,1},
			{1,1,1,1,1,1},
	};
	
	@FXML
	MazeDisplayer mazeDisplayer;
	
    @FXML
	Node borderPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mazeDisplayer.setMazeData(mazeData);
		dataManager = new DataManager();
		dataManager.addListener(this);
	}
		
	public void openLevel() {
		dataManager.loadLocalLevel(borderPane.getScene().getWindow());
	}
	
	public void solveLevel() {
		String host = "localhost";
		int port = 6400;
		char[][] pipe= {
				{'s','|','|','g'},	
				{' ',' ',' ',' '},	
				{' ',' ',' ',' '},	
				{' ',' ',' ',' '},
		};
		LevelInfo levelInfo = new LevelInfo(pipe, 0, 10);
		dataManager.solveLevel(host, port, levelInfo);
	}

	@Override
	public void levelLoaded(LevelInfo levelInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levelSaved() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void levelSolved(String[] solution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resultSaved() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void errorOccurred(String errorMessage) {
		// TODO Auto-generated method stub
		
	}
}
