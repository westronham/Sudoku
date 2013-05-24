import java.awt.*;
import javax.swing.*;

public class BackgroundJFrame extends JFrame {
	public BackgroundJFrame() {
		setTitle("Sudoku");
		setSize(400,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\Ron\\My Documents\\GitHub\\Project\\Project\\image.jpg")));
		setLayout(new FlowLayout());
		
		setSize(400,400);
	}
}
