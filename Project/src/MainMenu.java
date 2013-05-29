import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/*TODO: Need to have working;
 * Read in difficulty from drop down menu when we click "Play Sudoku"
 * 
 */

public class MainMenu {
	//private Sudoku SudokuBoards;
  	private int difficulty;
  	private SudokuImporter importer;
  	int[] sudokuFile;
  	private Sudoku sudoku;
	
	public MainMenu() {
		//Need to choose a board based on selected difficulty, not becasue we're passing from main
		//this.SudokuBoards = boards;
		importer = new SudokuImporter();
		sudokuFile = new int[81];
		sudoku = new Sudoku();
	}
	
	public void startMainMenu(final BackgroundJFrame f) {	
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(gridbag);
		
		c.fill =GridBagConstraints.BOTH;
		      
		this.playButton(f, c);
		this.difficultyOptions(f, c);
		this.instructionButton(f, c);
		this.exitButton(f, c);
		
		f.setSize(620,600);
		f.setVisible(true);
	}
      
	private void playButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton playButton = new JButton("Play Sudoku");
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 10;
		f.add(playButton, c);
		playButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					//Read difficulty from drop down menu, set difficulty = {1.2.3}
					if (difficulty != 0) {
						sudokuFile = importer.readSudoku(difficulty);
						sudoku.initSudoku(sudokuFile, difficulty);
						f.getContentPane().removeAll();
						SwingUtilities.updateComponentTreeUI(f);
						PlayMenu p = new PlayMenu(sudoku);
						p.startPlayMenu(f);
					} else {
						JOptionPane.showMessageDialog
        				(null,"Please choose a difficulty", "No Difficulty Selected", JOptionPane.PLAIN_MESSAGE);
					}
				}
		});
	}

	private void difficultyOptions(final BackgroundJFrame f, GridBagConstraints c) {
		String[] options = {"Choose Difficulty", "Easy", "Normal", "Hard"};
		final JComboBox modeCombo = new JComboBox(options);
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 0;
		f.add(modeCombo, c);
		modeCombo.addActionListener(new
				ActionListener() {
					public void actionPerformed(ActionEvent event) {
						String s = (String)modeCombo.getSelectedItem();
						switch (s) {
						case "Easy":
							difficulty = 1;
							System.out.println("Easy Difficulty selected, difficulty: " + difficulty);
							break;
						case "Normal":
							difficulty = 2;
							System.out.println("Normal Difficulty selected, difficulty: " + difficulty);
							break;
						case "Hard":
							difficulty = 3;
							System.out.println("Hard Difficulty selected, difficulty: " + difficulty);
							break;
						default:
							difficulty = 0;
							System.out.println("No Difficulty selected, difficulty: " + difficulty);
							break;
						}
					}
			});
	}

	private void instructionButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton instructionButton = new JButton("Instruction");
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10,0,0,0);
		f.add(instructionButton, c);
		instructionButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
				}
		});
	}
      
	private void exitButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton exitButton = new JButton("Exit");
		c.gridx = 0;
		c.gridy = 2;
		f.add(exitButton, c);
		exitButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					System.exit(0);
				}
		});
	}
}
