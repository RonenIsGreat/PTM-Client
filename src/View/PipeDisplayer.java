package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

public class PipeDisplayer extends Canvas{
	
	private char[][] pipeBoardData;
	private StringProperty leftToRightPipe;
	private StringProperty leftToUpPipe;
	
	public PipeDisplayer() {
		leftToRightPipe = new SimpleStringProperty();
		leftToUpPipe = new SimpleStringProperty();
	}
	
	public String getLeftToRightPipe() {
		return leftToRightPipe.get();
	}

	public void setLeftToRightPipe(String leftToRightPipe) {
		this.leftToRightPipe.set(leftToRightPipe);
	}

	public String getLeftToUpPipe() {
		return leftToUpPipe.get();
	}

	public void setLeftToUpPipe(String leftToUpPipe) {
		this.leftToUpPipe.set(leftToUpPipe);;
	}
	
	public void setPipeData(char[][] pipeData) {
		this.pipeBoardData = pipeData;
		redrew();
	}
	
	public char[][] getPipeData(){
		return pipeBoardData;
	}
	
	public void redrew(){
		if(pipeBoardData !=null) {
			double W=getWidth();
			double H=getHeight();
			double w =W / pipeBoardData[0].length;
			double h =H / pipeBoardData.length;
	
			GraphicsContext gc = getGraphicsContext2D();
			gc.setTextAlign(TextAlignment.CENTER);
	        gc.setTextBaseline(VPos.CENTER);
			
			Image leftToRightPipe = null;
			Image leftToUpPipe = null;
			
			try {
				String leftToRightPipeImagePath = getLeftToRightPipe();
				String leftToUpPipeImagePath = getLeftToUpPipe();
				leftToRightPipe = new Image(new FileInputStream(leftToRightPipeImagePath));
				leftToUpPipe = new Image(new FileInputStream(leftToUpPipeImagePath));
			
				// BackGround of canvas
				gc.setFill(javafx.scene.paint.Color.GRAY);
				gc.fillRect(0, 0, W, H);
				
				for(int i=0;i<pipeBoardData.length;i++)
					for(int j=0;j<pipeBoardData[i].length;j++) {
						if(pipeBoardData[i][j]=='-'){
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
							gc.setFill(javafx.scene.paint.Color.BLACK);
							gc.fillText("Start", j*w + w/2, i*h + h/2);
						}else if(pipeBoardData[i][j]=='g'){
							gc.setFill(javafx.scene.paint.Color.RED);
							gc.fillOval(j*w, i*h, w, h);
							gc.setFill(javafx.scene.paint.Color.BLACK);
							gc.fillText("End", j*w + w/2, i*h + h/2);
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
        rotate(gc, angle, x + w / 2, y + h / 2);
        gc.drawImage(image, x, y, w, h);
        gc.restore(); // back to original state (before rotation)
    }
	
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
