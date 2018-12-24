package application;
	
import Controller.ThemeWindowController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private static Stage pStage;


	
	public static Stage getPrimaryStage() {
		return pStage;
	}
	
	private void setPrimaryStage(Stage pStage) {
		Main.pStage = pStage;
	}
	@Override
	
	public void start(Stage primaryStage) {
		try {
			setPrimaryStage(primaryStage);
			pStage= primaryStage;
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../View/MainWindow.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("../View/application.css").toExternalForm());
			//ThemeWindowController theme= (ThemeWindowController)fxmlLoader.getController();
			//playAudio1();
			root.setStyle("-fx-base: rgba(60, 60, 60, 255);");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
