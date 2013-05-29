import java.util.concurrent.TimeUnit;

public class Timer implements Runnable
{
	long startTime;
	long elapsedTime;
	long timeInSecond;
	public static String time;
	public Timer() {
		startTime = System.nanoTime();
	}
	public void run() {
		while(true) {
			try {
				long estimatedTime = System.nanoTime() - startTime;
				timeInSecond = TimeUnit.NANOSECONDS.toSeconds(estimatedTime);
				time = String.valueOf(timeInSecond/60 + ":" + timeInSecond%60);
				Thread.sleep(1000);
			} catch(Exception e) {
				
			}
		}
	}
}