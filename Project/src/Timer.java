import java.awt.GridBagConstraints;
import java.io.Serializable;

import javax.swing.JLabel;

public class Timer implements Runnable {
	Thread thread;
	private boolean flag=true;
	public boolean pauseFlag=false;
	BackgroundJFrame f;
	GridBagConstraints c;
	JLabel timeLabel;
	long time;
	long startTime;
	long currentTime;
	long beginTime;
	
	public Timer(BackgroundJFrame f, GridBagConstraints c, JLabel timeLabel)
	{
		thread=new Thread(this);
		this.f = f;
		this.c = c;
		this.timeLabel = timeLabel;
		//this.startTime = startTime;
	}
 
	public void run(){
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
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		}
	}
 
	public void start(long startTime){
		this.startTime = startTime;
		thread.start();
	}
 
	public void pause(){
		pauseFlag = true;
	}

	public void resume(){
		pauseFlag = false;
	}

	public void stop(){
		flag=false;
	}
	
	public void setTime(long time){
		this.time = time;
	}
	
	public Thread getThread() {
		return thread;
	}
	
	public long getTime(){
		return time;
	}
	
}