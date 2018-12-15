package Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.DataManager;
import Model.DataManagerListener;
import Model.LevelInfo;
import View.MazeDisplayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainWindowController implements Initializable, DataManagerListener{
	private DataManager dataManager;
	private ExecutorService executor;
	
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
    void newPage(ActionEvent event) {
	   	try {
    		FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/View/SecondWindow.fxml"));
    		Parent root1= (Parent) fxmlLoader.load();
    		Stage stage=new Stage();
    		stage.setTitle("Second Window");
    		stage.setScene(new Scene(root1));
    		stage.show();
    	}catch(Exception e){
    		System.out.println("cant load new window");
    	}
    }
	
	@FXML
	MazeDisplayer mazeDisplayer;
	
    @FXML
	Node borderPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mazeDisplayer.setMazeData(mazeData);
		dataManager = new DataManager();
		dataManager.addListener(this);
		executor = Executors.newCachedThreadPool();
	}
		
	public void openLevel() {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open pipe game file");
		ExtensionFilter extFiler = new ExtensionFilter("Levels", "*.lvl");
		chooser.getExtensionFilters().add(extFiler);
		chooser.setInitialDirectory(new File("./Resources"));
		File selectedFile = chooser.showOpenDialog(borderPane.getScene().getWindow());
		
		executor.execute(new Runnable() {
		    @Override 
		    public void run() {
				dataManager.loadLocalLevel(selectedFile);
		    }
		});
	}
	
	public void saveLevel() {
		FileChooser chooser = new FileChooser();
	    chooser.setTitle("Save pipe game level");
	    FileChooser.ExtensionFilter extFiler = new ExtensionFilter("Levels", "*.lvl");
	    chooser.getExtensionFilters().add(extFiler);
	    chooser.setInitialDirectory(new File("./Resources"));
	    File file = chooser.showSaveDialog(borderPane.getScene().getWindow());
	    
	    // Example of level info to save
		char[][] pipe= {
				{'s','|','|','g'},	
				{' ',' ',' ',' '},	
				{' ',' ',' ',' '},	
				{' ',' ',' ',' '},
		};
		LevelInfo levelInfo = new LevelInfo(pipe, 0, 10);
	    // --------------------------------------------------
		
	    executor.execute(new Runnable() {
		    @Override 
		    public void run() {
				dataManager.saveLocalLevel(file, levelInfo);
		    }
		});
	}
	
	public void solveLevel() {
		// Example of level info to solve
		String host = "localhost";
		int port = 6400;
		char[][] pipe= {
				{'s','|','|','g'},	
				{' ',' ',' ',' '},	
				{' ',' ',' ',' '},	
				{' ',' ',' ',' '},
		};
		LevelInfo levelInfo = new LevelInfo(pipe, 0, 10);
		// --------------------------------------------------
		
		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override 
		    public void run() {
				dataManager.solveLevel(host, port, levelInfo);
		    }
		});
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
