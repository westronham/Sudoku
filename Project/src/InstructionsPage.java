import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class that holds all the information contained on the Instruction Page
 */
public class InstructionsPage {
	
	/**
	 * Initialises the Instruction Page
	 * @param f The JFrame on which all the pages components will be displayed
	 */
	public void startInstructionsPage(BackgroundJFrame f) {
		f.setBackgroundImage("image.jpg");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		f.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		  
		this.instructionPanel(f, c);
		this.returnButton(f, c);

		f.setVisible(true);
	}
	
	/**
	 * The instructions that will be visible to the player
	 * @param f The JFrame
	 * @param c The GridBagLayout constraints
	 */
	private void instructionPanel(BackgroundJFrame f, GridBagConstraints c) {
		JLabel label = new JLabel();
        label.setText("<html>"
            + "<h2>How To Play Sudoku</h2>"
            + "<p>Sudoku is a logic-based thinking game that requires the player to fill in the cells of a 9x9 grid with the numbers 1-9 such that:</p>"
            + "<br><p>•	Each number appears only once in each row.</p>"
            + "<br><p>•	Each number appears only once in each column.</p>"
            + "<br><p>•	Each number appears only once in each of the 3x3 sub-grids.</p>"
            + "<br><p>There is only one correct solution to each puzzle.</p>"
            + "<h2>Using This Program</h2>"
            + "<p>To start a game, choose a difficulty and select New Game in the Main Menu. To fill in the cells of your Sudoku, click on cell you would like to fill and press the number on your keyboard.</p>"
            + "<br><p>If you are unsure of your progress you can use the ‘Check Progress’ button to see if you are on the right track. If you find that you have lost yourself you can ‘Restart’ the game and try again.</p>"
            + "<br><p>There is a timer that allows you to challenge yourself to solving the puzzle in the shortest time possible and your best time is saved so you can try and beat your previous records too.</p>"
            + "<br><p>If you need to step away from the computer you can pause the game with the ‘Pause Game’ button or you can ‘Save and Quit’ the game and return to it later by pressing ‘Continue Game’ in the Main Menu.</p>"
            + "<br><p>There is also a ‘Hint’ button which will fill in a random cell on the board for you. You only get a limited number of these so make sure you save them for a time of need!</p>"
            + "<h1>Good Luck!</h1>"
            + "</html>");
        c.gridx = 2;
        c.gridwidth = 10;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0, 80, 100, 0);
        f.add(label, c);
	}
	
	/**
	 * The button taking the player to the main menu
	 * @param f The JFrame
	 * @param c The GridBagLayout constraints
	 */
	private void returnButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton returnButton = new JButton("Back");
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridwidth = 1;
		c.gridx = 12;
		c.gridy = 10;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(0, 0, 0, 0);
		c.ipadx = 30;
		c.ipady = 20;
		f.add(returnButton, c);
		returnButton.addActionListener(new
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