package Controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Model.DataManager;
import Model.DataManagerListener;
import Model.LevelInfo;
import Model.TimerService;
import View.PipeDisplayer;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainWindowController implements Initializable, DataManagerListener{
	private DataManager dataManager;
	private ExecutorService executor;

	// Example of pipe board
	char[][] pipe= {
			{'s','|','J','g'},	
			{' ','L','|','|'},	
			{'-',' ','7','7'},	
			{' ','J',' ',' '},
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
    
	@FXML
	TextField serverIPTextField;
	
	@FXML
	TextField serverPortNumberTextField;
	
	@FXML
	Label timeNumberLabel;
	
	@FXML
	Label stepsNumberLabel;
	
	@FXML
	Button startButton;
	
	@FXML
	Button stopButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pipeDisplayer.setPipeData(pipe, 0);
		dataManager = new DataManager();
		dataManager.addListener(this);
		executor = Executors.newCachedThreadPool();
		
		// Bind canvas size to stage size
		pipeDisplayer.widthProperty().bind(parentOfCanvas.widthProperty());
		pipeDisplayer.heightProperty().bind(parentOfCanvas.heightProperty());
		
		// Bind the steps from the pipe board to the display label
		stepsNumberLabel.textProperty().bind(pipeDisplayer.getNumberOfMovesProperty());
		
		stopButton.setDisable(true);
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
	    
		char[][] pipe= pipeDisplayer.getPipeData();
		LevelInfo levelInfo = new LevelInfo(pipe, 
											Integer.parseInt(stepsNumberLabel.getText()),
											Integer.parseInt(timeNumberLabel.getText()));
		
	    executor.execute(new Runnable() {
		    @Override 
		    public void run() {
				dataManager.saveLocalLevel(file, levelInfo);
		    }
		});
	}
	
	public void solveLevel() {
		String host = serverIPTextField.getText();
		int port = Integer.parseInt(serverPortNumberTextField.getText());
		char[][] pipe= pipeDisplayer.getPipeData();
		LevelInfo levelInfo = new LevelInfo(pipe,  
											Integer.parseInt(stepsNumberLabel.getText()),
											Integer.parseInt(timeNumberLabel.getText()));
		
		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override 
		    public void run() {
				dataManager.solveLevel(host, port, levelInfo);
		    }
		});
	}
	
	public void start() {
		dataManager.start();
		
		// Mouse click event on the canvas
		pipeDisplayer.setOnMouseClicked(event -> {
			onMouseClick(event);
	    });
		
		startButton.setDisable(true);
		stopButton.setDisable(false);
	}
	
	public void stop() {
		dataManager.stop();
		
		// Stop the click event on the canvas
		pipeDisplayer.setOnMouseClicked(null);
		
		stopButton.setDisable(true);
		startButton.setDisable(false);
	}
	
	private void onMouseClick(MouseEvent event) {
        double px = event.getX();
        double py = event.getY();
        pipeDisplayer.mouseClicked(px, py);
	}

	@Override
	public void levelLoaded(LevelInfo levelInfo) {
		Platform.runLater(()->
		{
			pipeDisplayer.setPipeData(levelInfo.getPipeGameBoard(), levelInfo.getNumberOfSteps());
			
			Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
			errorAlert.setHeaderText("Level have been loaded.");
			errorAlert.setContentText("Click OK to continue");
			errorAlert.showAndWait();
		});
	}

	@Override
	public void levelSaved() {
		Platform.runLater(()->
		{
			Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
			errorAlert.setHeaderText("Level have been saved.");
			errorAlert.setContentText("Click OK to continue");
			errorAlert.showAndWait();
		});
	}

	@Override
	public void levelSolved(String[] solutionMoves) {
		for (String move : solutionMoves) {
			String[] splittedMove = move.split(",");
			int row = Integer.parseInt(splittedMove[0]);
			int column = Integer.parseInt(splittedMove[1]);
			int rotateNumber = Integer.parseInt(splittedMove[2]);
			
			for (int i = 0; i < rotateNumber; i++) {
				Platform.runLater(()->
				{
					pipeDisplayer.rotateCell(row, column);;
				});
				
				try {
					int delayForRotations = 750;
					Thread.sleep(delayForRotations);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void errorOccurred(String errorMessage) {
		Platform.runLater(()->
		{
			Alert errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setHeaderText("Error Occurred!");
			errorAlert.setContentText(errorMessage);
			errorAlert.showAndWait();
		});
	}

	@Override
	public void timeUpdated(int timeInSeconds) {
		Platform.runLater(()->
		{
			timeNumberLabel.setText(Integer.toString(timeInSeconds));
		});
	}
}
