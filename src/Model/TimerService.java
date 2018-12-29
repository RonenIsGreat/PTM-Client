package Model;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerService {

	private int timeInSeconds;
	private Timer timer;
	private List<DataManagerListener> listeners;
	
	public TimerService(List<DataManagerListener> listeners) {
		timeInSeconds = 0;
		this.listeners = listeners;
	}

	public void start() {
		timer = new Timer();
		
		TimerTask task = new TimerTask() {
			public void run() {
				timeInSeconds++;
				
				// Notify everybody that may be interested.
		        for (DataManagerListener dml : listeners)
		        	dml.timeUpdated(timeInSeconds);
			}
		};
		
		int delay = 1000;
		int period = 1000;
		timer.scheduleAtFixedRate(task, delay, period);
	}
	
	public void stop() {
		timeInSeconds = 0;
		timer.cancel();
		
		// Notify everybody that may be interested.
        for (DataManagerListener dml : listeners)
        	dml.timeUpdated(timeInSeconds);
	}

}
