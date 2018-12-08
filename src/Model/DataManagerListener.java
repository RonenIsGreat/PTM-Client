package Model;

public interface DataManagerListener {
	void levelLoaded(LevelInfo levelInfo);
	void levelSaved();
	void levelSolved(String[] solution);
	void resultSaved();
	void errorOccurred(String errorMessage);
}
