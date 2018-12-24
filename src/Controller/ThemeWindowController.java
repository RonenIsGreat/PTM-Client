package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import application.Main;
import application.Main;
public class ThemeWindowController {
	
	public static BorderPane mainWindow;
	AudioClip note1;
	AudioClip note2;
	
    @FXML
    void OnTheme1(ActionEvent event) {
	   	try {
	   		
	   		mainWindow.setStyle("-fx-base: rgba(100, 0, 0, 255);");
    		playAudio1();
			
    	}catch(Exception e){
    		System.out.println("cant load new window");
    	}
    }

    @FXML
    void OnTheme2(ActionEvent event) {
	   	try {
	   		
	   		mainWindow.setStyle("-fx-base: rgba(60, 60, 60, 255);");
    		playAudio2();

    	}catch(Exception e){
    		System.out.println("cant load new window");
    	}

    }
    

    
	public void playAudio1() {
		note1= new AudioClip(this.getClass().getResource("Lounge Game2.wav").toString());
			note1.play();
			note2.stop();
		}
	public void playAudio2() {
		note2= new AudioClip(this.getClass().getResource("Off Limits.wav").toString());
		note2.play();
		note1.stop();
	}

	public void setMainWindow(BorderPane borderPane) {
		mainWindow = borderPane;
	}

}