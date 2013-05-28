import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Document;


/*TODO: We need to restrict input to jTextArea to numbers only
 *"Finish and Check Answer" should ask sudoku if it is solved correctly, if not 
 *
 * When 
 * 
 */
public class PlayMenu {
   public PlayMenu(Sudoku SudokuBoard) {
	  this.SudokuBoarda = SudokuBoard;
   }
   
   public void startPlayMenu(final BackgroundJFrame f) {
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      
      f.setLayout(gridbag);
      c.fill =GridBagConstraints.BOTH;

      JButton playButton = new JButton("Save");
      c.gridx = 0;
      c.gridy = 0;
      c.gridwidth = 3;
      c.gridheight = 1;
      
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
              //Board resets but need to get the Sudoku grid to reprint
            	SudokuBoarda.resetSudoku();
            	f.getContentPane().removeAll();
            	SwingUtilities.updateComponentTreeUI(f);
            	PlayMenu p = new PlayMenu(SudokuBoarda);
            	p.startPlayMenu(f);
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
            	f.getContentPane().removeAll();
            	SwingUtilities.updateComponentTreeUI(f);
            	MainMenu mainMenu = new MainMenu(SudokuBoarda);
            	mainMenu.startMainMenu(f);
            }
         });
      
      JButton checkButton = new JButton("Check Answer");
      c.gridx = 3;
      c.gridy = 10;
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
      c.gridx = 1;
      c.gridy = 10;
      f.add(hintButton, c);
      hintButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               
            }
         });
           
      SudokuBoard board = new SudokuBoard(SudokuBoarda); 
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
      
      for(int i = 0; i < 9; i++)
      {
    	  for(int j = 0; j < 9; j++)
    	  {
    		  JTextField sudokuArea = board.getSubGrids()[i][j];
    		  
    		  Font font;
    	      if (SudokuBoarda.getSudoku()[i][j] != 0) {
    	    	  sudokuArea.setText(Integer.toString(SudokuBoarda.getSudoku()[i][j]));
        	      sudokuArea.setEditable(false);
        	      font = new Font("Verdana", Font.BOLD, 12);
    	      } else {
    	    	  sudokuArea.setEditable(true);
    	    	  font = new Font("Verdana", Font.PLAIN, 12);
    	      }
    	      Document styleDoc = (sudokuArea).getDocument();
    	      if (styleDoc instanceof AbstractDocument) {
    	    	  AbstractDocument doc = (AbstractDocument)styleDoc;
    	    	  doc.setDocumentFilter(new InputLimiter());
    	      }
    	      sudokuArea.setFont(font);
    	      //sudokuArea.setOpaque(true);
    	      sudokuArea.setHorizontalAlignment(JTextField.CENTER);
    	  }
      }
      

      f.setSize(600,500);
      f.setVisible(true);
      
   }
   
   private Sudoku SudokuBoarda;
}
