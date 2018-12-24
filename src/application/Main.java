package application;
	
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
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("../View/MainWindow.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("../View/application.css").toExternalForm());
			//root.setStyle("-fx-base: rgba(60, 60, 60, 255);");
			//root.setStyle("-fx-base: rgba(100, 0, 0, 255);");
			//playAudio();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			
		}
	}
	private void playAudio() {
		AudioClip note= new AudioClip(this.getClass().getResource("Off Limits.wav").toString());
		note.play();
	}
	

	
	public static void main(String[] args) {
		launch(args);
	}
}
