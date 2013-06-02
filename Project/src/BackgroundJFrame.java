import java.awt.*;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class BackgroundJFrame extends JFrame {
	public BackgroundJFrame() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}
		
		//this.backgroundFile = backgroundFile;
		setTitle("Sudoku");
		setSize(400,400);
		setLocationRelativeTo(null);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("image4.jpg")));
		setLayout(new FlowLayout());
		
		setSize(620,600);
	}
	
	public void setBackgroundImage(String backgroundFile) {
		setContentPane(new JLabel(new ImageIcon(backgroundFile)));
	}

}
