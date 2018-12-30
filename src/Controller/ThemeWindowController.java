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
	static AudioClip note1;
	static AudioClip note2;
	
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
    

    
    static public void playAudio1() {
		
		note1= new AudioClip(new ThemeWindowController().getClass().getResource("Lounge Game2.wav").toString());
		if (note1 != null) {
			note1.stop();
		}
			note1.play();
			note2.stop();
			
		}
	static	public void playAudio2() {
		
		note2= new AudioClip(new ThemeWindowController().getClass().getResource("Off Limits.wav").toString());
		if (note2 != null) {
			note2.stop();
		}
		note2.play();
		note1.stop();
		
	}

	public void setMainWindow(BorderPane borderPane) {
		mainWindow = borderPane;
	}

}