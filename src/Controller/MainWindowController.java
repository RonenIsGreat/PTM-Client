package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.DataManager;
import Model.LevelInfo;
import View.MazeDisplayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

public class MainWindowController implements Initializable{
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
}
