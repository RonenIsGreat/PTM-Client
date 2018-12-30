package Model;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService {

	private int timeInSeconds;
	private Timer timer;
	private List<DataManagerListener> listeners;
	private boolean isRunning;
	
	public TimerService(List<DataManagerListener> listeners) {
		timeInSeconds = 0;
		this.listeners = listeners;
		isRunning = false;
	}

	public void start() {
		if(!isRunning) {
			isRunning = true;
			timer = new Timer();
		
			TimerTask task = new TimerTask() {
				public void run() {				
					// Notify everybody that may be interested.
			        for (DataManagerListener dml : listeners)
			        	dml.timeUpdated(timeInSeconds);
			        
					timeInSeconds++;
				}
			};
			
			int delay = 0;
			int period = 1000;
			timer.scheduleAtFixedRate(task, delay, period);
		}
	}
	
	public void stop() {
		if(isRunning) {
			isRunning = false;
			timer.cancel();
		}
	}
	
	public void restartTimer(int startTime) {
		if(isRunning) {
			isRunning = false;
			timer.cancel();
		}
		
		timeInSeconds = startTime;
	}

}
