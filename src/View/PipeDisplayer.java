package View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;

public class PipeDisplayer extends Canvas{
	
	private char[][] pipeBoardData;
	private StringProperty leftToRightPipe;
	private StringProperty leftToUpPipe;
	private int numberOfMoves;
	
	public PipeDisplayer() {
		leftToRightPipe = new SimpleStringProperty();
		leftToUpPipe = new SimpleStringProperty();
		widthProperty().addListener(observable -> redraw());
		heightProperty().addListener(observable -> redraw());
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
	
	public void setPipeData(char[][] pipeData, int numberOfMoves) {
		this.pipeBoardData = pipeData;
		this.numberOfMoves = numberOfMoves;
		redraw();
	}
	
	public char[][] getPipeData(){
		return pipeBoardData;
	}
	
	public int getNumberOfMoves() {
		return numberOfMoves;
	}
	
	public void redraw(){
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
						switch (pipeBoardData[i][j]) {
						case '-':
						{
							double angle = 0;
							drawRotatedImage(gc, leftToRightPipe, j*w, i*h, w, h, angle);
							break;
						}
						case '|':
						{
							double angle = 90;
							drawRotatedImage(gc, leftToRightPipe, j*w, i*h, w, h, angle);
							break;
						}
						case 'J':
						{
							double angle = 0;
							drawRotatedImage(gc, leftToUpPipe, j*w, i*h, w, h, angle);
							break;
						}	
						case 'L':
						{
							double angle = 90;
							drawRotatedImage(gc, leftToUpPipe, j*w, i*h, w, h, angle);
							break;
						}
						case 'F':
						{
							double angle = 180;
							drawRotatedImage(gc, leftToUpPipe, j*w, i*h, w, h, angle);
							break;
						}	
						case '7':
						{
							double angle = 270;
							drawRotatedImage(gc, leftToUpPipe, j*w, i*h, w, h, angle);
							break;
						}	
						case 's':
						{
							gc.setFill(javafx.scene.paint.Color.GREEN);
							gc.fillOval(j*w, i*h, w, h);
							gc.setFill(javafx.scene.paint.Color.BLACK);
							gc.fillText("Start", j*w + w/2, i*h + h/2);
							break;
						}	
						case 'g':
						{
							gc.setFill(javafx.scene.paint.Color.RED);
							gc.fillOval(j*w, i*h, w, h);
							gc.setFill(javafx.scene.paint.Color.BLACK);
							gc.fillText("End", j*w + w/2, i*h + h/2);
							break;
						}	
						default:
							break;
						}
					}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void mouseClicked(double px, double py) {
		double W=getWidth();
		double H=getHeight();
		double w =W / pipeBoardData[0].length;
		double h =H / pipeBoardData.length;
		int col = (int)(px/w);
		int row = (int)(py/h);
		rotateCell(row, col);
	}
	
	private void drawRotatedImage(GraphicsContext gc, Image image, double x, double y, double w, double h, double angle) {
		ImageView iv = new ImageView(image);
		iv.setRotate(angle);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		Image rotatedImage = iv.snapshot(params, null);
    	gc.drawImage(rotatedImage, x, y, w, h);       
    }
	
	private void rotateCell(int row, int column) {
		Boolean isPipeRotated = true;
		
		switch (pipeBoardData[row][column]) {
		case '-':
		{
			pipeBoardData[row][column] = '|';
			break;
		}
		case '|':
		{
			pipeBoardData[row][column] = '-';
			break;
		}
		case 'J':
		{
			pipeBoardData[row][column] = 'L';
			break;
		}	
		case 'L':
		{
			pipeBoardData[row][column] = 'F';
			break;
		}
		case 'F':
		{
			pipeBoardData[row][column] = '7';
			break;
		}	
		case '7':
		{
			pipeBoardData[row][column] = 'J';
			break;
		}	
		default:
			isPipeRotated = false;
			break;
		}
		
		if(isPipeRotated) {
			numberOfMoves++;
			redraw();
		}
	}
	
	@Override
    public boolean isResizable() {
        return true;
    }
}
