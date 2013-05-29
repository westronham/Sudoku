import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuBoard extends JPanel {

        public SudokuBoard(Sudoku sudoku) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setLayout(new GridLayout(3, 3));
            BoardPart boxGrid[] = new BoardPart[9];
            for (int i = 0; i < 9; i++) {
                boxGrid[i] = new BoardPart(i);
                add(boxGrid[i]);
            }
        }

        public static class BoardPart extends JPanel {

            public BoardPart(int j) {
                setBorder(BorderFactory.createLineBorder(Color.GRAY));
                setLayout(new GridLayout(3, 3));
                JTextField boxCell[] = new JTextField[9];
                int boxCol = getBoxColCoordinate(j);
                int boxRow = getBoxRowCoordinate(j);
                for (int i = 0, count = 0; i < 9; i++, count++) {
                    if (count == 3 || count == 6) {
                    	boxCol = boxCol - 3;
                    	boxRow = boxRow + 1;
                    }
                	boxCell[i] = new JTextField();
                    boxCell[i].setPreferredSize(new Dimension(30, 30));
                    add(boxCell[i]);
                    subGrids[boxCol][boxRow] = boxCell[i];
                    boxCol++;
                }
            }
        }
        
        public JTextField[][] getSubGrids() {
        	return subGrids;
        }
        
        public static int getBoxRowCoordinate(int j) {
        	if (j == 0) {
        		return 0;
        	} else if (j == 1) {
        		return 0;
        	} else if (j == 2) {
        		return 0;
        	} else if (j == 3) {
        		return 3;
        	} else if (j == 4) {
        		return 3;
        	} else if (j == 5) {
        		return 3;
        	} else if (j == 6) {
        		return 6;
        	} else if (j == 7) {
        		return 6;
        	} else if (j == 8) {
        		return 6;
        	}
        	return -1;
        }
        
        public static int getBoxColCoordinate(int j) {
        	if (j == 0) {
        		return 0;
        	} else if (j == 1) {
        		return 3;
        	} else if (j == 2) {
        		return 6;
        	} else if (j == 3) {
        		return 0;
        	} else if (j == 4) {
        		return 3;
        	} else if (j == 5) {
        		return 6;
        	} else if (j == 6) {
        		return 0;
        	} else if (j == 7) {
        		return 3;
        	} else if (j == 8) {
        		return 6;
        	}
        	return -1;
        }
       
        private static JTextField[][] subGrids = new JTextField[9][9];
}