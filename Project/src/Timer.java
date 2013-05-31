import java.awt.GridBagConstraints;
import java.io.Serializable;

import javax.swing.JLabel;

public class Timer implements Runnable {
	Thread thread;
	private boolean flag=true;
	BackgroundJFrame f;
	GridBagConstraints c;
	JLabel timeLabel;
	public Timer(BackgroundJFrame f, GridBagConstraints c, JLabel timeLabel)
	{
		thread=new Thread(this);
		this.f = f;
		this.c = c;
		this.timeLabel = timeLabel;
	}
 
	public void run(){
		long beginTime=System.currentTimeMillis();
		long time=0;
		while(flag){
			time=System.currentTimeMillis()-beginTime;
			timeLabel.setText("Time: " +time/1000/60/60 + ": " + time/1000/60%60 + ": " + time/1000%60);
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e1){
				e1.printStackTrace();
			}
		}
	}
 
	public void start(){
		thread.start();
	}
 
	public void pause(){
		synchronized(thread) {
			try{
				thread.wait();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public void resume(){
		thread.notifyAll();
	}

	public void stop(){
		flag=false;
	}
	
	public Thread getThread() {
		return thread;
	}
}