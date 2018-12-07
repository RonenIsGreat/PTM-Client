package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class MazeDisplayer extends Canvas{
	
	int[][] mazeData;
	
	public void setMazeData(int[][] mazeData) {
	this.mazeData = mazeData;
	redrew();
	}
	
	public void redrew(){
		if(mazeData !=null) {
			double W=getWidth();
			double H=getHeight();
			double w =W / mazeData[0].length;
			double h =H / mazeData.length;
	
		
		GraphicsContext gc = getGraphicsContext2D();
		
		Image wall=null;
		try {
			wall = new Image(new FileInputStream("./resources/wall.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0;i<mazeData.length;i++)
			for(int j=0;j<mazeData[i].length;j++) {
				if(mazeData[i][j]!=0) {
					if(wall==null)
						gc.fillRect(j*w, i*w, w, h);
					else
						gc.drawImage(wall, j*w, i*h, w, h);
				}
			}
		}
	}
}
