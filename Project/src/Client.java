import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client
{
   public Client()
   {
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      JFrame f=new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setLayout(gridbag);
      c.fill =GridBagConstraints.BOTH;
      
      JButton playButton = new JButton("Play Soduku");
      c.gridx = 0;
      c.gridy = 0;
      c.ipady = 10;
      f.add(playButton, c);
      playButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	PlayScreen p = new PlayScreen();
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
            }
         });
      f.setSize(500,400);
      f.setVisible(true);
   }
}
