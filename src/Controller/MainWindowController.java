package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import View.MazeDisplayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainWindowController implements Initializable{
	
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
		
	public void openLevel() {
		DataManager dm = new DataManager();
		dm.loadLocalLevel(borderPane.getScene().getWindow());
	}
}
