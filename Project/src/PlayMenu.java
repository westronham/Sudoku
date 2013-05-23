import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*TODO: We need to restrict input to jTextArea to numbers only
 *"Finish and Check Answer" should ask sudoku if it is solved correctly, if not 
 *
 * When 
 * 
 */
public class PlayMenu
{
	
	private Sudoku SudokuBoarda;
   public PlayMenu(Sudoku SudokuBoard)
   {
	  this.SudokuBoarda = SudokuBoard;
	  
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      final BackgroundJFrame f=new BackgroundJFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLayout(gridbag);
      c.fill =GridBagConstraints.BOTH;
      
      
      JButton playButton = new JButton("Save");
      c.gridx = 0;
      c.gridy = 0;
      c.gridwidth = 3;
      f.add(playButton, c);
      playButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               
            }
         });

      JButton instructionButton = new JButton("Restart");
      c.gridx = 3;
      c.gridy = 0;
      c.gridwidth = 3;
      f.add(instructionButton, c);
      instructionButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               
            }
         });
      
      JButton exitButton = new JButton("Quit");
      c.gridx = 6;
      c.gridy = 0;
      c.gridwidth = 3;
      f.add(exitButton, c);
      exitButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	f.setVisible(false);
            	SudokuStore storage= new SudokuStore(); 
            	MainMenu c = new MainMenu(SudokuBoarda);
            }
         });
      
      JButton checkButton = new JButton("Check Answer");
      c.gridx = 9;
      c.gridy = 4;
      c.gridwidth =1;
      f.add(checkButton, c);
      checkButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               
            }
         });
      
      JButton hintButton = new JButton("Hint");
      c.gridx = 9;
      c.gridy = 6;
      f.add(hintButton, c);
      hintButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               
            }
         });
            
      for(int i = 0; i < 9; i++)
      {
    	  for(int j = 0; j < 9; j++)
    	  {
    		  JTextField sudokuArea;
    	      if (SudokuBoarda.getSudoku()[i][j] != 0) {
    	    	  sudokuArea = new JTextField(Integer.toString(SudokuBoarda.getSudoku()[i][j]));
        	      sudokuArea.setEditable(false);
    	      } else {
    	    	  sudokuArea = new JTextField();
    	    	  sudokuArea.setEditable(true);
    	      }
    	      Font font = new Font("Verdana", Font.BOLD, 12);
    	      sudokuArea.setFont(font);
    	      sudokuArea.setOpaque(true);
    	      sudokuArea.setHorizontalAlignment(JTextField.CENTER);
    	      c.gridx = i;
    	      c.gridy = j+1;
    	      c.weightx = 1;
    	      c.weighty = 1;
    	      c.anchor = GridBagConstraints.CENTER;
    	      c.insets = new Insets(5,5,5,5);
    	      f.add(sudokuArea, c);
    	  }
      }

      f.setSize(600,500);
      f.setVisible(true);
      
      
   }
   
}
