
package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class TimeStepsController {
	public static BorderPane mainWindow;

    @FXML
    private Pane timeNumberLabel;

    @FXML
    private Label stepsNumberLabel;
    
    void changeSteps() {
    	
    	
    }
	public void setMainWindow(BorderPane borderPane) {
		mainWindow = borderPane;
	} 

}
