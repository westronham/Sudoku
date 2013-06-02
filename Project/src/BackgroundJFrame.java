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
		
		setTitle("Sudoku");
		//setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(820,800);
	}
	
	public void setBackgroundImage(String backgroundFile) {
		setContentPane(new JLabel(new ImageIcon(backgroundFile)));
	}

}
