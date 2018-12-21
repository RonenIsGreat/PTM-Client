package View;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;

public class MazeDisplayer extends Canvas{
	
	int[][] mazeData;
	char[][] pipeBoardData;
	
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
			
			Image leftToRightPipe = null;
			Image leftToUpPipe = null;
			
			try {
				leftToRightPipe = new Image(new FileInputStream("./resources/-.jpg"));
				leftToUpPipe = new Image(new FileInputStream("./resources/j.jpg"));
				
				for(int i=0;i<mazeData.length;i++)
					for(int j=0;j<mazeData[i].length;j++) {
						if(pipeBoardData[i][j]==' ') {
							gc.fillRect(j*w, i*h, w, h);
						}else if(pipeBoardData[i][j]=='-'){
							double angle = 0;
							drawRotatedImage(gc, leftToRightPipe, j*w, i*h, w, h, angle);
						}else if(pipeBoardData[i][j]=='|'){
							double angle = 90;
							drawRotatedImage(gc, leftToRightPipe, j*w, i*h, w, h, angle);
						}else if(pipeBoardData[i][j]=='J'){
							double angle = 0;
							drawRotatedImage(gc, leftToUpPipe, j*w, i*h, w, h, angle);
						}else if(pipeBoardData[i][j]=='L'){
							double angle = 90;
							drawRotatedImage(gc, leftToUpPipe, j*w, i*h, w, h, angle);
						}else if(pipeBoardData[i][j]=='F'){
							double angle = 180;
							drawRotatedImage(gc, leftToUpPipe, j*w, i*h, w, h, angle);
						}else if(pipeBoardData[i][j]=='7'){
							double angle = 270;
							drawRotatedImage(gc, leftToUpPipe, j*w, i*h, w, h, angle);
						}else if(pipeBoardData[i][j]=='s'){
							gc.setFill(javafx.scene.paint.Color.GREEN);
							gc.fillOval(j*w, i*h, w, h);
						}else if(pipeBoardData[i][j]=='g'){
							gc.setFill(javafx.scene.paint.Color.RED);
							gc.fillOval(j*w, i*h, w, h);
						}
					}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void drawRotatedImage(GraphicsContext gc, Image image, double x, double y, double w, double h, double angle) {
        gc.save(); // saves the current state on stack, including the current transform
        rotate(gc, angle, x + w / 2, x + h / 2);
        gc.drawImage(image, x, y, w, h);
        gc.restore(); // back to original state (before rotation)
    }
	
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
