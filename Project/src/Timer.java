import java.awt.GridBagConstraints;
import javax.swing.JLabel;

public class Timer implements Runnable {
	Thread thread;
	private boolean flag=true;
	BackgroundJFrame f;
	GridBagConstraints c;
	public Timer(BackgroundJFrame f, GridBagConstraints c)
	{
		thread=new Thread(this);
		this.f = f;
		this.c = c;
	}
 
	public void run(){
		long beginTime=System.currentTimeMillis();
		long time=0;
		JLabel timeLabel = new JLabel();
		c.gridx = 8;
		c.gridy = 10;
		c.gridwidth = 1;
		f.add(timeLabel, c);
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
 
	public void Pause(){
		try{
			thread.wait();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public void Resume(){
		thread.notifyAll();
	}

	public void stop(){
		flag=false;
	}
}