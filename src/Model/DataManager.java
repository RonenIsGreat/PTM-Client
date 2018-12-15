package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
	
	public void loadLocalLevel(File file) {
		try {
			if(file != null) {
				LevelInfo levelInfo = localFileManager.getLevelInfo(file);
				
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
	
	public void saveLocalLevel(File file, LevelInfo levelInfo) {
		try {
		    if(file != null) {
		    	localFileManager.saveLevelInfo(file, levelInfo);
				
				// Notify everybody that may be interested.
		        for (DataManagerListener dml : listeners)
		        	dml.levelSaved();
		    }
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
