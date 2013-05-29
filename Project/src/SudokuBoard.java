import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuBoard extends JPanel {

        public SudokuBoard(Sudoku sudoku) {
            setBorder(BorderFactory.createLineBorder(Color.GRAY));
            setLayout(new GridLayout(3, 3));
            BoardPart input[] = new BoardPart[9];
            for (int i = 0; i < 9; i++) {
                input[i] = new BoardPart();
                add(input[i]);
            }
        }

        public static class BoardPart extends JPanel {

            public BoardPart() {
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
                setLayout(new GridLayout(3, 3));
                JTextField input[] = new JTextField[9];
                for (int i = 0; i < 9; i++) {
                    input[i] = new JTextField();
                    input[i].setPreferredSize(new Dimension(30, 30));
                    add(input[i]);
                    subGrids[i] = input[i];
                }
            }
        }
        
        public JTextField[] getSubGrids(int i) {
        	
        	return subGrids;
        }
       
        private static JTextField[] subGrids = new JTextField[9];
}