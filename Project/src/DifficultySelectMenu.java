import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 * Displays up options for easy, medium and hard games. Option to go back. 
 * @author Felix
 *
 */
public class DifficultySelectMenu {
	private SudokuImporter importer;
  	private int[] sudokuFile;
  	private Sudoku sudoku;
  	private JLabel loadError;
  	
	public DifficultySelectMenu() {
		importer = new SudokuImporter();
		sudokuFile = new int[81];
		sudoku = new Sudoku();
	}
	
	/**
	 * Initialise all the buttons and labels.
	 * @param f
	 */
	public void startDifficultySelectMenu(final BackgroundJFrame f) {
		f.setBackgroundImage("image.jpg");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		f.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		this.displayMessage(f, c);
		this.playEasyButton(f, c);
		this.playMediumButton(f, c);
		this.playHardButton(f, c);
		this.backButton(f, c);
		
		f.setLocationRelativeTo(null);

		f.setVisible(true);
	}
	
	/**
	 * Message for choosing difficulty. 
	 * @param f
	 * @param c
	 */
	private void displayMessage(BackgroundJFrame f, GridBagConstraints c) {
		JLabel label = new JLabel("CHOOSE DIFFICULTY");
		
		Font font = new Font("Avenir", Font.BOLD, 20);
		label.setForeground(Color.red);
		label.setFont(font);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;

		f.add(label, c);
	}

    /**
     * Button for loading up an easy game. 
     * @param f
     * @param c
     */
	private void playEasyButton(final BackgroundJFrame f, final GridBagConstraints c) {
		JButton playButton = new JButton("Easy");
		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(60,0,0,0);
		f.add(playButton, c);
		playButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					sudokuFile = importer.readSudoku(1);
					if (sudokuFile != null) {
						sudoku.initSudoku(sudokuFile, 1);
						f.getContentPane().removeAll();
						SwingUtilities.updateComponentTreeUI(f);
						PlayMenu p = new PlayMenu(sudoku);
						p.startPlayMenu(f);	
					} else {
						loadError.setVisible(true);
					}
				
				}
		});
	}
	
	/**
     * Button for loading up a medium game. 
     * @param f
     * @param c
     */
	private void playMediumButton(final BackgroundJFrame f, final GridBagConstraints c) {
		JButton playButton = new JButton("Medium");
		c.gridx = 0;
		c.gridy = 2;
		c.ipady = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(50,0,0,0);
		f.add(playButton, c);
		playButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					sudokuFile = importer.readSudoku(2);
					if (sudokuFile != null) {
						sudoku.initSudoku(sudokuFile, 2);
						f.getContentPane().removeAll();
						SwingUtilities.updateComponentTreeUI(f);
						PlayMenu p = new PlayMenu(sudoku);
						p.startPlayMenu(f);	
					} else {
						loadError.setVisible(true);
					}
				
				}
		});
	}
	
	/**
     * Button for loading up an hard game. 
     * @param f
     * @param c
     */
	private void playHardButton(final BackgroundJFrame f, final GridBagConstraints c) {
		JButton playButton = new JButton("Hard");
		c.gridx = 0;
		c.gridy = 3;
		c.ipady = 10;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(50,0,0,0);
		f.add(playButton, c);
		playButton.addActionListener(new
			ActionListener() {
				public void actionPerformed(ActionEvent event) {
					
					sudokuFile = importer.readSudoku(3);
					if (sudokuFile != null) {
						sudoku.initSudoku(sudokuFile, 3);
						f.getContentPane().removeAll();
						SwingUtilities.updateComponentTreeUI(f);
						PlayMenu p = new PlayMenu(sudoku);
						p.startPlayMenu(f);	
					} else {
						loadError.setVisible(true);
					}
				}
		});
	}
	
	/**
	 * Loads up the main menu to go back. 
	 * @param f
	 * @param c
	 */
	private void backButton(final BackgroundJFrame f, GridBagConstraints c) {
		
		JButton exitButton = new JButton("Back");
		Font font = new Font("Avenir", Font.BOLD, 12);
		exitButton.setFont(font);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
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
}
