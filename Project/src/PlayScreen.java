import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlayScreen
{
   public PlayScreen()
   {
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      JFrame f=new JFrame();
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
            	Client c = new Client();
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
    	      JTextArea sodukuArea = new JTextArea();
    	      c.gridx = i;
    	      c.gridy = j+1;
    	      c.weightx = 1;
    	      c.weighty = 1;
    	      c.anchor = GridBagConstraints.CENTER;
    	      c.insets = new Insets(5,5,5,5);
    	      f.add(sodukuArea, c);
    	  }
      }

      f.setSize(500,400);
      f.setVisible(true);
   }
}
