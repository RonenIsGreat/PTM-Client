
package Controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TimeStepsController {
	public static BorderPane mainWindow;

    @FXML
    private Label timeNumberLabel;

    @FXML
    private Label stepsNumberLabel;
    
	public void setMainWindow(BorderPane borderPane) {
		mainWindow = borderPane;
	} 

	public StringProperty getTimeNumberLabelProperty() {
		return timeNumberLabel.textProperty();
	}
	
	public StringProperty getStepsNumberLabelProperty() {
		return timeNumberLabel.textProperty();
	}
}
