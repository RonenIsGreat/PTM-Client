package Controller;

import Model.DataManager;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class MainWindowController {
	@FXML
	Node borderPane;
	
	public void openLevel() {
		DataManager dm = new DataManager();
		dm.loadLocalLevel(borderPane.getScene().getWindow());
	}
}
