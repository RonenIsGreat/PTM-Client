package Model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SolveServer {
	private Socket socket;
	private String host;
	private int port;
	
	public SolveServer(){
	}
	
	public String[] getLevelSolution(String host, int port, LevelInfo levelInfo) throws UnknownHostException, IOException {
		Socket socket = new Socket(host, port);
		return null;
	}
}
