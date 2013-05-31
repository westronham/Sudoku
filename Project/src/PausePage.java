import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;


public class PausePage {
	public PausePage(BackgroundJFrame f) {
		JLabel pause = new JLabel("Game Paused");
		f.getContentPane().add(pause);
		JButton returnB = new JButton("Resume Game");
		returnB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Go back to game screen
				//Resume timer
				
			}
		});
		f.getContentPane().add(returnB);
	}
}
