package Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.DataManager;
import Model.DataManagerListener;
import Model.LevelInfo;
import View.PipeDisplayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainWindowController implements Initializable, DataManagerListener{
	private DataManager dataManager;
	private ExecutorService executor;
	private int numberOfMoves;

	// Example of pipe board
	char[][] pipe= {
			{'s','|','|','g'},	
			{' ',' ',' ',' '},	
			{'-',' ','7',' '},	
			{' ','j',' ',' '},
	};

    @FXML
    void OnMessage(ActionEvent event) {

    }

    @FXML
    void OnSettings(ActionEvent event) {

    }

    @FXML
    void OnTheme(ActionEvent event) {
	   	try {
    		FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/View/ThemeWindow.fxml"));
    		Parent root1= (Parent) fxmlLoader.load();
    		Stage stage=new Stage();
    		stage.setTitle("Theme Window");
    		stage.setScene(new Scene(root1));
    	//	ThemeWindowController theme= (ThemeWindowController)fxmlLoader.getController();
    		//theme.playAudio1();
    		
    		ThemeWindowController.mainWindow = borderPane;
    		stage.show();
    	}catch(Exception e){
    		System.out.println("cant load new window");
    	}
    }
   
    
    @FXML
    void OnTimeSteps(ActionEvent event) {

    }
	
	
	
	
	@FXML
	PipeDisplayer pipeDisplayer;
	
    @FXML
    BorderPane borderPane;
    
    @FXML
    Pane parentOfCanvas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		numberOfMoves = 0;
		pipeDisplayer.setPipeData(pipe, numberOfMoves);
		dataManager = new DataManager();
		dataManager.addListener(this);
		executor = Executors.newCachedThreadPool();

		// Mouse click even on the canvas
		pipeDisplayer.setOnMouseClicked(event -> {
			onMouseClick(event);
	    });
		
		// Bind canvas size to stage size
		pipeDisplayer.widthProperty().bind(parentOfCanvas.widthProperty());
		pipeDisplayer.heightProperty().bind(parentOfCanvas.heightProperty());
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
		char[][] pipe= pipeDisplayer.getPipeData();
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
		char[][] pipe= pipeDisplayer.getPipeData();
		LevelInfo levelInfo = new LevelInfo(pipe, 0, 10);
		// --------------------------------------------------
		
		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override 
		    public void run() {
				dataManager.solveLevel(host, port, levelInfo);
		    }
		});
	}
	
	private void onMouseClick(MouseEvent event) {
        double px = event.getX();
        double py = event.getY();
        pipeDisplayer.mouseClicked(px, py);
        numberOfMoves = pipeDisplayer.getNumberOfMoves();
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
	public void errorOccurred(String errorMessage) {
		// TODO Auto-generated method stub
		
	}
}
