import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class VictoryGUI {

   
	public VictoryGUI()
   {

   }
   
   public void startVictoryGUI () {
	   
      GridBagLayout gridbag = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();
      VictoryFrame vFrame = new VictoryFrame();
      
      vFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      vFrame.setLayout(gridbag);
      c.fill =GridBagConstraints.VERTICAL;
      
      Font font1 = new Font("Verdana", Font.BOLD, 17);
      
      JLabel VictoryMessage = new JLabel("CONGRATULATIONS!");
      c.gridx = 4;
      c.gridy = 0;
      c.gridwidth =1;
      c.gridheight =1;
      VictoryMessage.setFont(font1);
      
      vFrame.add(VictoryMessage, c);
      
      JLabel message = new JLabel("Now that you've completed (insert difficulty) would you like to try (next level difficulty?)");
      message.setSize(500, 1000);
     // message.setWrapStyleWord(false);
    //  message.setEditable(false);
      message.setOpaque(false);
      
      Font font2 = new Font("Verdana", Font.PLAIN, 16);
      c.gridx = 4;
      c.gridy = 3;
      c.gridwidth =1;
      vFrame.add(message, c);
      message.setFont(font2);
      
      JButton yesButton = new JButton("yes");
      c.gridx = 4;
      c.gridy = 4;
      vFrame.add(yesButton, c);
      yesButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	
            }
         });
           
      JButton noButton = new JButton("no");
      c.gridx = 4;
      c.gridy = 5;
      vFrame.add(noButton, c);
      noButton.addActionListener(new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	
            }
         });
      vFrame.setSize(800,150);
      vFrame.setVisible(true);
      
   }
   
}
