import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;


/*TODO: We need to restrict input to jTextArea to numbers only
 *"Finish and Check Answer" should ask sudoku if it is solved correctly, if not 
 *
 * When 
 * 
 */
public class PlayMenu {
	private Sudoku sudokuBoard;
	private JTextField[][] listOfJTextAreaEntries;
	private HintSystem hintSystem;
	AbstractDocument doc;
	
	public PlayMenu(Sudoku SudokuBoard) {
		this.sudokuBoard = SudokuBoard;
		this.listOfJTextAreaEntries = new JTextField[9][9];
		this.hintSystem = new HintSystem(SudokuBoard);
	}
   
	public void startPlayMenu(final BackgroundJFrame f) {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		  
		//start a new thread
	      Timer gameTimer = new Timer();
	      Thread timeThread = new Thread(gameTimer);
	      timeThread.start();
		
		f.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		  
		this.saveButton(f, c);
		this.restartButton(f, c);
		this.exitButton(f, c);
		this.checkButton(f, c);
		this.hintButton(f, c);
		this.sudokuBoard(f, c);
		//this.showTimer(f, c);
		  
		f.setSize(620,600);
		f.setVisible(true);
	}
      
	private void saveButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton saveButton = new JButton("Save");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 1;
		  
		f.add(saveButton, c);
		saveButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
		
				}
		});
	}
      
	private void restartButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton instructionButton = new JButton("Restart");
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 3;
		f.add(instructionButton, c);
		instructionButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					sudokuBoard.resetSudoku();
					f.getContentPane().removeAll();
					SwingUtilities.updateComponentTreeUI(f);
					PlayMenu p = new PlayMenu(sudokuBoard);
					p.startPlayMenu(f);
				}
		});
	}
      
	private void exitButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton exitButton = new JButton("Quit");
		c.gridx = 6;
		c.gridy = 0;
		c.gridwidth = 3;
		f.add(exitButton, c);
		exitButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					f.getContentPane().removeAll();
					SwingUtilities.updateComponentTreeUI(f);
					MainMenu mainMenu = new MainMenu();
					mainMenu.startMainMenu(f);
				}
		});
	}
  
	private void checkButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton checkButton = new JButton("Check Answer");
		c.gridx = 3;
		c.gridy = 10;
		c.gridwidth =1;
		f.add(checkButton, c);
		checkButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int[][] saveBoard = new int[9][9]; 
					for(int i = 0; i < 9; i++) {
						for(int j = 0; j < 9; j++) {
							saveBoard[i][j] = new Integer(listOfJTextAreaEntries[i][j].getText());//given that the user input is only one integer between 0 and 10
						}
					}
					sudokuBoard.setSudoku(saveBoard);
					if(sudokuBoard.checkBoard()) {
						System.out.println("Board is correct!"); //What happens if it is correct
					} else {
						System.out.println("Board is wrong!");
					}
				}
		});
	}
      
	private void hintButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton hintButton = new JButton("Hint");
		c.gridx = 1;
		c.gridy = 10;
		f.add(hintButton, c);
		hintButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					doc.setDocumentFilter(new DocumentFilter());
					int randomNum = (new Random ()).nextInt(8) + 1;
					while(hintSystem.doneNumber(randomNum)) {
						randomNum = (new Random ()).nextInt(8) + 1;
					}        	
					hintSystem.getHint(sudokuBoard, randomNum);
					for(int i = 0; i < 9; i++) {
						for(int j = 0; j < 9; j++) {
							if(sudokuBoard.getPlayerSudoku()[i][j] != 0)
								listOfJTextAreaEntries[i][j].setText(String.valueOf(sudokuBoard.getPlayerSudoku()[i][j]));
						}
					}
				}
		});
	}
       
	private void sudokuBoard(final BackgroundJFrame f, GridBagConstraints c) {
		SudokuBoard board = new SudokuBoard(sudokuBoard); 
		c.gridx = 0;
		c.gridwidth = 9;
		c.gridy = 1;
		c.gridheight = 9;
		c.weighty = 0.01;
		c.weightx = 0.01;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(25,55,25,55);
		c.fill = GridBagConstraints.BOTH;
		f.add(board, c);
		listOfJTextAreaEntries = board.getSubGrids();
		
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				final JTextField sudokuArea = board.getSubGrids()[i][j];
		
				Font font;
				if (sudokuBoard.getPlayerSudoku()[i][j] != 0) {
					sudokuArea.setText(Integer.toString(sudokuBoard.getPlayerSudoku()[i][j]));
					sudokuArea.setEditable(false);
					font = new Font("Verdana", Font.BOLD, 12);
				} else {
					sudokuArea.setEditable(true);
					font = new Font("Verdana", Font.PLAIN, 12);
				}
				Document styleDoc = (sudokuArea).getDocument();
				if (styleDoc instanceof AbstractDocument) {
					doc = (AbstractDocument)styleDoc;
					doc.setDocumentFilter(new InputLimiter());
				}
				sudokuArea.setFont(font);
				sudokuArea.setOpaque(false);
				sudokuArea.setHorizontalAlignment(JTextField.CENTER);
				sudokuArea.addKeyListener(new
						KeyListener() {

					@Override
					public void keyPressed(KeyEvent arg0) {
						if (!sudokuArea.getText().isEmpty() && sudokuArea.isEditable()) {
							sudokuArea.setText("");
						}
					}

					@Override
					public void keyReleased(KeyEvent arg0) {
						updateUserSudoku(f);
					}

					@Override
					public void keyTyped(KeyEvent arg0) {
						
					}
				});
			}
		}
	}
	
	private void showTimer(BackgroundJFrame f, GridBagConstraints c) {
		JLabel timeLabel = new JLabel();
		timeLabel.setText("Time:" + Timer.time);
		c.gridx = 9;
		c.gridwidth = 3;
		f.add(timeLabel, c);
	}
	
	private void updateUserSudoku(BackgroundJFrame f) {
		int count = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if (listOfJTextAreaEntries[i][j].getText().trim().isEmpty()) {
					sudokuBoard.getPlayerSudoku()[i][j] = 0;
					count++;
				} else {
					sudokuBoard.getPlayerSudoku()[i][j] = new Integer(listOfJTextAreaEntries[i][j].getText());
				}
			}
		}
		if (count == 0) {
			System.out.println(this.isCorrect());
			if (this.isCorrect() == 0) {
				VictoryGUI victory = new VictoryGUI();
				victory.startVictoryGUI(f);
			} else {
				JOptionPane.showMessageDialog
				(null,"Incorrect: There are " + this.isCorrect() + " mistakes", "Incorrect Solution", JOptionPane.PLAIN_MESSAGE);
			}
		}
		sudokuBoard.printSudoku();
		System.out.println();
	}
	
	private int isCorrect() {
		int mistakes = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudokuBoard.getPlayerSudoku()[i][j] != sudokuBoard.getSolvedSudoku()[i][j]) {
					mistakes++;
				}
			}
		}
		return mistakes;
	}
}
