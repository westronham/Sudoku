import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
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
  	private JLabel error;
	
	public MainMenu() {
		//Need to choose a board based on selected difficulty, not becasue we're passing from main
		//this.SudokuBoards = boards;
		importer = new SudokuImporter();
		sudokuFile = new int[81];
		sudoku = new Sudoku();
	}
	
	public void startMainMenu(final BackgroundJFrame f) {
		f.setBackgroundImage("image4.jpg");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		 
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(gridbag);
		
		c.fill = GridBagConstraints.BOTH;
		
		this.errorMessage(f, c);
		this.playButton(f, c);
		this.difficultyOptions(f, c);
		this.loadButton(f, c);
		this.instructionButton(f, c);
		this.exitButton(f, c);
		
		f.setSize(720,700);
		//f.pack();
		f.setVisible(true);
	}
      
	private void playButton(final BackgroundJFrame f, final GridBagConstraints c) {
		JButton playButton = new JButton("Play Sudoku");
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 10;
		c.gridwidth = 1;
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
						PlayMenu p = new PlayMenu(sudoku, difficulty);
						p.startPlayMenu(f);
					} else {
						error.setVisible(true);
							
						//JOptionPane.showMessageDialog
        				//(null,"Please choose a difficulty", "No Difficulty Selected", JOptionPane.PLAIN_MESSAGE);
					}
				}
		});
	}
	
	private void errorMessage(BackgroundJFrame f, GridBagConstraints c) {
		Font font = new Font("Papyrus", Font.BOLD, 14);
		error = new JLabel("Please select a difficulty from the drop down menu");
		error.setFont(font);
		error.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		f.getContentPane().add(error, c);
		error.setVisible(false);
		SwingUtilities.updateComponentTreeUI(f);
	}

	private void difficultyOptions(final BackgroundJFrame f, GridBagConstraints c) {
		String[] options = {"Choose Difficulty", "Easy", "Normal", "Hard"};
		final JComboBox modeCombo = new JComboBox(options);
		c.gridx = 1;
		c.gridy = 1;
		c.ipady = 0;
		f.add(modeCombo, c);
		modeCombo.addActionListener(new
				ActionListener() {
					public void actionPerformed(ActionEvent event) {
						String s = (String)modeCombo.getSelectedItem();
						switch (s) {
						case "Easy":
							difficulty = 1;
							error.setVisible(false);
							System.out.println("Easy Difficulty selected, difficulty: " + difficulty);
							break;
						case "Normal":
							difficulty = 2;
							error.setVisible(false);
							System.out.println("Normal Difficulty selected, difficulty: " + difficulty);
							break;
						case "Hard":
							difficulty = 3;
							error.setVisible(false);
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
	
	private void loadButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton instructionButton = new JButton("Resume Previous Game");
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(10,0,0,0);
		f.add(instructionButton, c);
		instructionButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						FileInputStream fileStream = new FileInputStream("MyGame.ser");
						ObjectInputStream os = new ObjectInputStream(fileStream);
						Object one = os.readObject();
						Object two = os.readObject();
						//Object three = os.readObject();
						sudoku = (Sudoku) one;
						//Timer gameTimer = (Timer) two;
						PlayMenu p = (PlayMenu) two;
						os.close();
						f.getContentPane().removeAll();
						SwingUtilities.updateComponentTreeUI(f);
						p.startPlayMenu(f);
					} catch (ClassNotFoundException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
		});
	}

	private void instructionButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton instructionButton = new JButton("Instruction");
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(10,0,0,0);
		f.add(instructionButton, c);
		instructionButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					f.getContentPane().removeAll();
					SwingUtilities.updateComponentTreeUI(f);
					InstructionsPage instructions = new InstructionsPage();
					instructions.startInstructionsPage(f);
				}
		});
	}
      
	private void exitButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton exitButton = new JButton("Exit");
		c.gridx = 0;
		c.gridy = 4;
		f.add(exitButton, c);
		exitButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					System.exit(0);
				}
		});
	}
}
