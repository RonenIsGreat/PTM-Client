package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LocalFileManager {

	public LevelInfo getLevelInfo(File file) throws ClassNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		LevelInfo levelInfo = (LevelInfo) objectInputStream.readObject();
		return levelInfo;
	}
	
	public void saveLevelInfo(File file, LevelInfo levelInfo) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(levelInfo);
		objectOutputStream.close();
		fileOutputStream.close();
	}
}
