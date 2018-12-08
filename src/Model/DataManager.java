package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class DataManager {
	private List<DataManagerListener> listeners = new ArrayList<DataManagerListener>();
	private LocalFileManager localFileManager;
	private SolveServer solveServer;
	
	public DataManager() {
		localFileManager = new LocalFileManager();
		solveServer = new SolveServer();
	}
	
	public void addListener(DataManagerListener toAdd) {
        listeners.add(toAdd);
    }
	
	public void loadLocalLevel(Window parentWindow) {
		try {
			FileChooser chooser = new FileChooser();
			chooser.setTitle("Open pipe game file");
			ExtensionFilter extFiler = new ExtensionFilter("Levels", "*.lvl");
			chooser.setSelectedExtensionFilter(extFiler);
			chooser.setInitialDirectory(new File("./Resources"));
			File selectedFile = chooser.showOpenDialog(parentWindow);
			
			if(selectedFile != null) {
				LevelInfo levelInfo = localFileManager.getLevelInfo(selectedFile);
				
				// Notify everybody that may be interested.
		        for (DataManagerListener dml : listeners)
		        	dml.levelLoaded(levelInfo);
			}
		} catch (Exception e) {
			// Notify about the error
			for (DataManagerListener dml : listeners)
	        	dml.errorOccurred(e.getMessage());
		}
	}
	
	public void saveLocalLevel(LevelInfo levelInfo) {
		try {
			localFileManager.saveLevelInfo(levelInfo);
			
			// Notify everybody that may be interested.
	        for (DataManagerListener dml : listeners)
	        	dml.levelSaved();
		} catch (Exception e) {
			// Notify about the error
			for (DataManagerListener dml : listeners)
	        	dml.errorOccurred(e.getMessage());
		}
	}
	
	public void solveLevel(String host, int port, LevelInfo levelInfo){
		try {
			String[] solutionMoves = solveServer.getLevelSolutionMoves(host, port, levelInfo);
			
			// Notify everybody that may be interested.
	        for (DataManagerListener dml : listeners)
	        	dml.levelSolved(solutionMoves);
		} catch (Exception e) {
			// Notify about the error
			for (DataManagerListener dml : listeners)
	        	dml.errorOccurred(e.getMessage());
		}
	}
	
	public void saveResult(LevelInfo levelInfo) {
		// TODO
		
		// Notify everybody that may be interested.
        for (DataManagerListener dml : listeners)
        	dml.resultSaved();
	}
}
