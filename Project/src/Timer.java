import javax.swing.JLabel;

/**
 * A Timer class with multithreading.
 * @param thread a new thread
 * @param flag timer refresh when it set to true
 * @param pauseFlag timer paused when it set to true
 * @param time time elapsed
 * @param startTime when the game start
 * @param currentTime
 * @param begainTime start time with a pause game period correction
 */
public class Timer implements Runnable {
	
	private Thread thread;
	private boolean flag=true;
	private boolean pauseFlag;
	private JLabel timeLabel;
	private long time;
	private long startTime;
	private long currentTime;
	private long beginTime;
	
	public Timer(JLabel timeLabel)
	{
		thread=new Thread(this);
		this.timeLabel = timeLabel;
		pauseFlag = false;
	}
	
	/**
	 * Run a thread, refresh the timer on playMenu every second by calculating the time elapsed from
	 * game start. And let thread sleep when pauseFlag become true.
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		currentTime=System.currentTimeMillis();
		beginTime=currentTime - startTime;
		time=0;
		while(flag){
			time=System.currentTimeMillis()-beginTime;
			timeLabel.setText(time/1000/60/60 + "h  " + time/1000/60%60 + "m  " + time/1000%60 + "s");
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e1){
				e1.printStackTrace();
			}
			if(pauseFlag) {
				beginTime+=1000;
				while(pauseFlag){
					try{
						Thread.sleep(100);
						beginTime+=100;
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
 
	/**
	 * Start the timer thread.
	 */
	public void start(long startTime) {
		this.startTime = startTime;
		thread.start();
	}
 
	/**
	 * Pause the timer thread by set pauseFlag to true.
	 */
	public void pause() {
		pauseFlag = true;
	}

	/**
	 * Resume the timer thread by set pauseFlag to false.
	 */
	public void resume() {
		pauseFlag = false;
	}

	/**
	 * Stop timer by set Flag to false.
	 */
	public void stop(){
		flag=false;
	}
	
	/**
	 * set time to a value we want.
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	public Thread getThread() {
		return thread;
	}
	
	/**
	 * Get the time now.
	 */
	public long getTime(){
		return time;
	}
	
}