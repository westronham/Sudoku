import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.*;


public class MainMenu {
  	
	public MainMenu() {
		importer = new SudokuImporter();
		sudokuFile = new int[81];
		sudoku = new Sudoku();
	}

	public void startMainMenu(final BackgroundJFrame f) {
		f.setBackgroundImage("image4.jpg");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		f.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		
		this.errorMessage(f, c);
		this.loadFailError(f, c);
		this.playButton(f, c);
		this.difficultyOptions(f, c);
		this.loadButton(f, c);
		this.highScoresButton(f, c);
		this.instructionButton(f, c);
		this.exitButton(f, c);
		
		f.setLocationRelativeTo(null);
		//f.pack();
		f.setVisible(true);
	}
      
	private void playButton(final BackgroundJFrame f, final GridBagConstraints c) {
		playButton = new JButton("New Game");
		c.gridx = 1;
		c.gridy = 10;
		c.ipady = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		/*if (difficultyError.isVisible()) {
			c.insets = new Insets(100, 100, 0, 0);
		} else if (loadError.isVisible()) {
			c.insets = new Insets(100, 0, 0, 0);
		} else {
			c.insets = new Insets(240,0,0,0);
		}*/
		//c.weighty = 0;
		//c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(240,0,0,0);
		f.add(playButton, c);
		playButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					//Read difficulty from drop down menu, set difficulty = {1.2.3}
					if (difficulty != 0) {
						sudokuFile = importer.readSudoku(difficulty);
						if (sudokuFile != null) {
							sudoku.initSudoku(sudokuFile, difficulty);
							f.getContentPane().removeAll();
							SwingUtilities.updateComponentTreeUI(f);
							PlayMenu p = new PlayMenu(sudoku);
							p.startPlayMenu(f);	
						} else {
							loadError.setVisible(true);
							/*f.getContentPane().remove(playButton);
							f.getContentPane().remove(modeCombo);
							f.getContentPane().remove(loadButton);
							f.getContentPane().remove(highScoreButton);
							f.getContentPane().remove(instructionButton);
							f.getContentPane().remove(exitButton);
							SwingUtilities.updateComponentTreeUI(f);
							playButton(f, c);
							difficultyOptions(f, c);
							loadButton(f, c);
							highScoresButton(f, c);
							instructionButton(f, c);
							exitButton(f, c);*/
						}
					} else {
						difficultyError.setVisible(true);
					}
				}
		});
	}

	private void errorMessage(BackgroundJFrame f, GridBagConstraints c) {
		Font font = new Font("Papyrus", Font.BOLD, 14);
		difficultyError = new JLabel("Please select a difficulty from the drop down menu");
		difficultyError.setFont(font);
		difficultyError.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.insets = new Insets(0,0,100,0);
		f.getContentPane().add(difficultyError, c);
		difficultyError.setVisible(false);
		SwingUtilities.updateComponentTreeUI(f);
	}
	
	private void loadFailError(BackgroundJFrame f, GridBagConstraints c) {
		Font font = new Font("Papyrus", Font.BOLD, 14);
		loadError = new JLabel("Error: No valid files in directory");
		loadError.setFont(font);
		loadError.setForeground(Color.red);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.insets = new Insets(0,0,100,0);
		f.getContentPane().add(loadError, c);
		loadError.setVisible(false);
		SwingUtilities.updateComponentTreeUI(f);
	}

	private void difficultyOptions(final BackgroundJFrame f, GridBagConstraints c) {
		String[] options = {"Choose Difficulty", "Easy", "Normal", "Hard"};
		modeCombo = new JComboBox(options);
		c.gridx = 2;
		c.gridy = 10;
		c.ipady = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		f.add(modeCombo, c);
		modeCombo.addActionListener(new
				ActionListener() {
					public void actionPerformed(ActionEvent event) {
						String s = (String)modeCombo.getSelectedItem();
						switch (s) {
						case "Easy":
							difficulty = 1;
							difficultyError.setVisible(false);
							loadError.setVisible(false);
							break;
						case "Normal":
							difficulty = 2;
							difficultyError.setVisible(false);
							loadError.setVisible(false);
							break;
						case "Hard":
							difficulty = 3;
							difficultyError.setVisible(false);
							loadError.setVisible(false);
							break;
						default:
							difficulty = 0;
							loadError.setVisible(false);
							break;
						}
					}
			});
	}

	private void loadButton(final BackgroundJFrame f, GridBagConstraints c) {
		loadButton = new JButton("Continue Game");
		c.gridx = 1;
		c.gridy = 11;
		c.insets = new Insets(10,0,0,0);
		c.gridheight = 1;
		f.add(loadButton, c);
		loadButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					try {
						FileInputStream fileStream = new FileInputStream("MyGame.ser");
						ObjectInputStream os = new ObjectInputStream(fileStream);
						Object one = os.readObject();
						Object two = os.readObject();
						Object three = os.readObject();
						os.close();

						sudoku = (Sudoku) one;
						int hintsLeft = (int) two;
						long saveTime = (long) three;

						PlayMenu p = new PlayMenu(sudoku);
						p.setHintsLeft(hintsLeft);
						p.setStartTime(saveTime);

						f.getContentPane().removeAll();
						SwingUtilities.updateComponentTreeUI(f);

						p.startPlayMenu(f);

					} catch (ClassNotFoundException | IOException e) {

						e.printStackTrace();
					}

				}
		});
	}

	/**
	 * Resets the high score by replacing the score with NOHIGHSCORE.
	 * @param f
	 * @param c
	 */
	private void highScoresButton (final BackgroundJFrame f, GridBagConstraints c) {
		highScoreButton = new JButton("High Scores");
		c.gridx = 1;
		c.gridy = 12;
		c.gridheight = 1;
		f.add(highScoreButton, c);
		highScoreButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {

					f.getContentPane().removeAll();
					SwingUtilities.updateComponentTreeUI(f);
					HighScoreMenu p = new HighScoreMenu();
					p.startHighScoreMenu(f);

				}
		});

	}


	private void instructionButton(final BackgroundJFrame f, GridBagConstraints c) {
		instructionButton = new JButton("Instruction");
		c.gridx = 1;
		c.gridy = 13;
		c.insets = new Insets(10,0,0,0);
		c.gridheight = 1;
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
		exitButton = new JButton("Exit");
		c.gridx = 1;
		c.gridy = 14;
		c.gridheight = 1;
		f.add(exitButton, c);
		exitButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					System.exit(0);
				}
		});
	}
	
	private int difficulty;
  	private SudokuImporter importer;
  	private int[] sudokuFile;
  	private Sudoku sudoku;
  	private JLabel difficultyError;
  	private JLabel loadError;
  	private JButton playButton;
  	private JComboBox modeCombo;
  	private JButton loadButton;
  	private JButton highScoreButton;
  	private JButton instructionButton;
  	private JButton exitButton;
}
