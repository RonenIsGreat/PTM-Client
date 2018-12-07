package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class DataManager {
	private List<DataManagerListener> listeners = new ArrayList<DataManagerListener>();
	
	public void addListener(DataManagerListener toAdd) {
        listeners.add(toAdd);
    }
	
	public void loadLocalLevel(Window parentWindow) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open pipe game file");
		ExtensionFilter extFiler = new ExtensionFilter("Levels", "*.lvl");
		chooser.setSelectedExtensionFilter(extFiler);
		chooser.setInitialDirectory(new File("./Resources"));
		File selectedFile = chooser.showOpenDialog(parentWindow);
		
		if(selectedFile != null) {
			//TODO
			
			// Notify everybody that may be interested.
	        for (DataManagerListener dml : listeners)
	        	dml.levelLoaded();
		}
	}
	
	public void saveLocalLevel() {
		// TODO
		
		// Notify everybody that may be interested.
        for (DataManagerListener dml : listeners)
        	dml.levelSaved();
	}
	
	public void solveLevel(){
		// TODO
		
		// Notify everybody that may be interested.
        for (DataManagerListener dml : listeners)
        	dml.levelSolved();
	}
	
	public void saveResult() {
		// TODO
		
		// Notify everybody that may be interested.
        for (DataManagerListener dml : listeners)
        	dml.resultSaved();
	}
}