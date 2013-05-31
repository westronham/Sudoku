import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
public class PlayMenu implements Serializable {
	private Sudoku sudokuBoard;
	private JTextField[][] listOfJTextAreaEntries;
	private HintSystem hintSystem;
	AbstractDocument doc;
	int difficulty;
	private SudokuImporter importer;
  	int[] sudokuFile;
  	boolean showConflicts;
	
	public PlayMenu(Sudoku SudokuBoard, int difficulty) {
		this.sudokuBoard = SudokuBoard;
		this.listOfJTextAreaEntries = new JTextField[9][9];
		this.hintSystem = new HintSystem(SudokuBoard);
		this.difficulty = difficulty;
		importer = new SudokuImporter();
		sudokuFile = new int[81];
	}
   
	public void startPlayMenu(final BackgroundJFrame f) {
		f.setBackgroundImage("image.jpg");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		  
		//start a new thread
	      Timer gameTimer = new Timer();
	      Thread timeThread = new Thread(gameTimer);
	      timeThread.start();
		
		f.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		  
		this.saveButton(f, c);
		this.pauseButton(f, c);
		this.restartButton(f, c);
		this.exitButton(f, c);
		this.checkButton(f, c);
		this.hintButton(f, c);
		this.sudokuBoard(f, c);
		this.checkBox(f, c);
		//this.showTimer(f, c);
		  
		f.setSize(720,700);
		f.setVisible(true);
	}
      
	private void saveButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton saveButton = new JButton("Save Current Game");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		  
		f.add(saveButton, c);
		saveButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						FileOutputStream fileStream = new FileOutputStream("MyGame.ser");
						ObjectOutputStream os = new ObjectOutputStream(fileStream);
						os.writeObject(sudokuBoard);
						//os.writeObject(this);
						os.close();
						f.getContentPane().removeAll();
						SwingUtilities.updateComponentTreeUI(f);
						MainMenu mainMenu = new MainMenu();
						mainMenu.startMainMenu(f);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
		});
	}
	
	private void pauseButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton saveButton = new JButton("Pause Current Game");
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		  
		f.add(saveButton, c);
		saveButton.addActionListener(new
			ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					f.getContentPane().removeAll();
					SwingUtilities.updateComponentTreeUI(f);
					PausePage paused = new PausePage(f);
					
				}

		});
	}
      
	private void restartButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton instructionButton = new JButton("Restart");
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 2;
		f.add(instructionButton, c);
		instructionButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					sudokuBoard.resetSudoku();
					f.getContentPane().removeAll();
					SwingUtilities.updateComponentTreeUI(f);
					PlayMenu p = new PlayMenu(sudokuBoard, difficulty);
					p.startPlayMenu(f);
				}
		});
	}
      
	private void exitButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton exitButton = new JButton("Quit");
		c.gridx = 6;
		c.gridy = 0;
		c.gridwidth = 2;
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
				@Override
				public void actionPerformed(ActionEvent e) {
					int mistakes = checkProgress();
					if (mistakes == 0) {
						JOptionPane.showMessageDialog
        				(null,"All correct so far", null, JOptionPane.PLAIN_MESSAGE);
					} else {
						if (mistakes == 1) {
							JOptionPane.showMessageDialog
							(null,"Incorrect: There is " + mistakes + " mistake so far", "Incorrect Solution", JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog
							(null,"Incorrect: There are " + mistakes + " mistakes so far", "Incorrect Solution", JOptionPane.PLAIN_MESSAGE);
						}
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
					hintSystem.getHint(sudokuBoard);
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
				final JTextField sudokuArea = listOfJTextAreaEntries[i][j];
		
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
						int keyCode = arg0.getKeyChar();
						if (!sudokuArea.getText().isEmpty() && sudokuArea.isEditable() && keyCode <= 57 && keyCode >= 49) {
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
	
	private void checkBox(BackgroundJFrame f, GridBagConstraints c) {
		JCheckBox checkbox = new JCheckBox("Show conflicts");
		c.gridy = 20;
		c.gridx = 10;
		checkbox.addItemListener(new
				ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent arg0) {
						if (arg0.getStateChange() == ItemEvent.DESELECTED) {
							showConflicts = false;
						} else {
							showConflicts = true;
						}
						
					}
			
		});
		f.getContentPane().add(checkbox);
		
	}
	
	private void updateUserSudoku(BackgroundJFrame f) {
		int count = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if (listOfJTextAreaEntries[i][j].getText().trim().isEmpty()) {
					sudokuBoard.getPlayerSudoku()[i][j] = 0;
					count++;
				} else {
					sudokuBoard.getPlayerSudoku ()[i][j] = new Integer(listOfJTextAreaEntries[i][j].getText());
					
					//Few things: so firstly we don't want to go through every box, just the one we're on so we need to fix that
					//Also, we need to set it back to false if we change a number
					int value = sudokuBoard.getPlayerSudoku()[i][j];
					if (sudokuBoard.checkUserValueConditions(i, j, value)) {
						//highlight the conflicting cells
						System.out.println("THERE'S A CONFLICT BIATCH");
					} else {
						sudokuBoard.setUserValueConditions(i, j, value, true);
						System.out.println("all good");
					}
				}
			}
		}
		if (count == 0) {
			int mistakes = this.isCorrect();
			if (mistakes == 0) {
				ImageIcon icon = new ImageIcon("icon.gif");
				int query = JOptionPane.showConfirmDialog (null, 
                        "<html><font size=\"20\" face" +
                        "=\"Papyrus\">Congratulations!</font><br><font size=\"10\" face" +
                        "=\"Papyrus\">Would you like to start a new game?</font></html>", 
                        "You Win", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        icon);
				 if (query == JOptionPane.YES_OPTION){
					 sudokuFile = importer.readSudoku(difficulty);
						sudokuBoard.initSudoku(sudokuFile, difficulty);
						f.getContentPane().removeAll();
						SwingUtilities.updateComponentTreeUI(f);
						PlayMenu p = new PlayMenu(sudokuBoard, difficulty);
						p.startPlayMenu(f);
				 } else if (query == JOptionPane.NO_OPTION) {
				     f.getContentPane().removeAll();
				     SwingUtilities.updateComponentTreeUI(f);
				     MainMenu mainMenu = new MainMenu();
				     mainMenu.startMainMenu(f);
				 }
			} else {
				if (mistakes == 1) {
					JOptionPane.showMessageDialog
					(null,"Incorrect: There is " + mistakes + " mistake", "Incorrect Solution", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog
					(null,"Incorrect: There are " + mistakes + " mistakes", "Incorrect Solution", JOptionPane.PLAIN_MESSAGE);
				}
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
	
	private int checkProgress() {
		int mistakes = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (sudokuBoard.getPlayerSudoku()[i][j] != 0) {
					if (sudokuBoard.getPlayerSudoku()[i][j] != sudokuBoard.getSolvedSudoku()[i][j]) {
						mistakes++;
					}
				}
			}
		}
		return mistakes;
	}
}
