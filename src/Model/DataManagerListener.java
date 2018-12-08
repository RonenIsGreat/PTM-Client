package Model;

public interface DataManagerListener {
	void levelLoaded(LevelInfo levelInfo);
	void levelSaved();
	void levelSolved();
	void resultSaved();
}
