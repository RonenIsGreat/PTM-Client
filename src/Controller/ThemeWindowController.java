package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import application.Main;

public class ThemeWindowController {

    @FXML
    void OnTheme1(ActionEvent event) {
	   	try {
	   		
    		FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/View/MainWindow.fxml"));
    		Parent root1= (Parent) fxmlLoader.load();
    		Stage stage=new Stage();
    		stage.setTitle("Main Window");
    		stage.setScene(new Scene(root1));
    		root1.setStyle("-fx-base: rgba(100, 0, 0, 255);");
    		playAudio1();
    		stage.show();
    		
    	}catch(Exception e){
    		System.out.println("cant load new window");
    	}
    }

    @FXML
    void OnTheme2(ActionEvent event) {
	   	try {
	   		

    		FXMLLoader fxmlLoader =new FXMLLoader(getClass().getResource("/View/MainWindow.fxml"));
    		Parent root1= (Parent) fxmlLoader.load();
    		Stage stage=new Stage();
    		stage.setTitle("Main Window");
    		stage.setScene(new Scene(root1));
    		root1.setStyle("-fx-base: rgba(60, 60, 60, 255);");
    		playAudio2();
    		stage.show();
    	}catch(Exception e){
    		System.out.println("cant load new window");
    	}

    }
	private void playAudio1() {
		AudioClip note= new AudioClip(this.getClass().getResource("Lounge Game2.wav").toString());
		note.play();
	}
	private void playAudio2() {
		AudioClip note= new AudioClip(this.getClass().getResource("Lounge Game2.wav").toString());
		note.play();
	}

}