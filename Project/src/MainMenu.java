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

public class MainMenu
{
	//private SudokuSotre; //Has list of boards (easy, medium, hard)
	private Sudoku SudokuBoards;
	private int difficulty;
	
	
	
	public MainMenu(Sudoku boards)
	
   {
	  this.SudokuBoards = boards;
	  try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      final BackgroundJFrame f=new BackgroundJFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLayout(gridbag);
      c.fill =GridBagConstraints.BOTH;
      
      
      JButton playButton = new JButton("Play Sudoku");
      c.gridx = 0;
      c.gridy = 0;
      c.ipady = 10;
      f.add(playButton, c);
      playButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	//Read difficulty from drop down menu, set difficulty = {1.2.3}
            	f.setVisible(false);
            	PlayMenu p = new PlayMenu(SudokuBoards);
            }
         });

      String[] options = {"Difficulty", "Easy", "Normal", "Hard"};
      JComboBox modeCombo = new JComboBox(options);
      c.gridx = 1;
      c.gridy = 0;
      c.ipady = 0;
      f.add(modeCombo, c);

      JButton instructionButton = new JButton("Instruction");
      c.gridx = 0;
      c.gridy = 1;
      c.insets = new Insets(10,0,0,0);
      f.add(instructionButton, c);
      instructionButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               
            }
         });
      
      JButton exitButton = new JButton("Exit");
      c.gridx = 0;
      c.gridy = 2;
      f.add(exitButton, c);
      exitButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	System.exit(0);
            }
         });
      f.setSize(600,500);
      f.setVisible(true);
   }
   
   /*private Sudoku getSudoku (int difficulty){
	   return SudokuBoards.getSudoku(difficulty);
	   
   }*/
   
}
