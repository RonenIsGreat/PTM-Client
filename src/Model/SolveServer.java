package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class SolveServer {
	private String doneStr;
	
	public SolveServer(){
		doneStr = "done";
	}
	
	public String[] getLevelSolutionMoves(String host, int port, LevelInfo levelInfo) throws UnknownHostException, IOException {
		Socket socket = new Socket(host, port);
		sendBoardToSolve(socket, levelInfo);
		return getSolution(socket);
	}
	
	private void sendBoardToSolve(Socket socket, LevelInfo levelInfo) throws IOException {
		PrintWriter output = new PrintWriter(socket.getOutputStream());
		
		for (String line : levelInfo.getBoardLines()) {
			output.println(line);
			output.flush();
		}
		
		output.println(doneStr);
		output.flush();
	}
	
	private String[] getSolution(Socket socket) throws IOException {
		List<String> Solution = new ArrayList<String>();
		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String line;
		
		while(!(line=input.readLine()).equals(doneStr)) {
			Solution.add(line);
		}
		
		return (String[]) Solution.toArray(new String[0]);
	}
}
