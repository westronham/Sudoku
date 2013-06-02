import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 * Custom JFrame that allows for a background image
 */
public class BackgroundJFrame extends JFrame {
	
	/**
	 * Initialises the JFrame
	 */
	public BackgroundJFrame() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		
		setTitle("Sudoku");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(820,800);
		setResizable(false);
	}
	
	/**
	 * Changes the background image
	 * @param backgroundFile The image file that the background will be changed to
	 */
	public void setBackgroundImage(String backgroundFile) {
		setContentPane(new JLabel(new ImageIcon(backgroundFile)));
	}

}
