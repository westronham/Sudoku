import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;


public class InstructionsPage {
	public InstructionsPage() {
		
	}
	
	public void startInstructionsPage(BackgroundJFrame f) {
		f.setBackgroundImage("image.jpg");
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		f.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		  
		this.instructionPanel(f, c);
		this.returnButton(f, c);
		
		f.setSize(720,700);
		//f.pack();
		f.setVisible(true);
	}
	
	private void instructionPanel(BackgroundJFrame f, GridBagConstraints c) {
		JLabel label = new JLabel();
        label.setText("<html>"
            + "<h3>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</h3>"
            + "<p>Duis a tincidunt urna. Phasellus tristique interdum mauris, "
            + "ut vestibulum purus suscipit eget. Aenean massa elit, accumsan "
            + "non faucibus vel, dictum placerat urna. In bibendum est sagittis "
            + "urna iaculis quis sagittis velit commodo. Cum sociis natoque "
            + "penatibus et magnis dis parturient montes, nascetur ridiculus "
            + "mus. Nam quis lacus mauris. Phasellus sem libero, convallis "
            + "mattis sagittis vel, auctor eget ipsum. Vivamus molestie semper "
            + "adipiscing. In ac neque quis elit suscipit pharetra. Nulla at "
            + "orci a tortor consequat consequat vitae sit amet elit. Praesent "
            + "commodo lacus a magna mattis vehicula. Curabitur a hendrerit "
            + "risus. Aliquam accumsan lorem quis orci lobortis malesuada.</p>"
            + "<p>Proin quis viverra ligula. Donec pulvinar, dui id facilisis "
            + "vulputate, purus justo laoreet augue, a feugiat sapien dolor ut "
            + "nisi. Sed semper augue ac felis ultrices a rutrum dui suscipit. "
            + "Praesent et mauris non tellus gravida mollis. In hac habitasse "
            + "platea dictumst. Vestibulum ante ipsum primis in faucibus orci "
            + "luctus et ultrices posuere cubilia Curae; Vestibulum mattis, "
            + "tortor sed scelerisque laoreet, tellus neque consectetur lacus, "
            + "eget ultrices arcu mi sit amet arcu. Nam gravida, nulla interdum "
            + "interdum gravida, elit velit malesuada arcu, nec aliquam lectus "
            + "velit ut turpis. Praesent pretium magna in nibh hendrerit et "
            + "elementum tellus viverra. Praesent eu ante diam. Proin risus "
            + "eros, dapibus at eleifend sit amet, blandit eget purus. "
            + "Pellentesque eu mollis orci. Sed venenatis diam a nisl tempor "
            + "congue.</p>"
            + "</html>");
        c.gridx = 2;
        c.gridwidth = 10;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.anchor = GridBagConstraints.CENTER;
        f.add(label, c);
        f.pack();
	}
	
	private void returnButton(final BackgroundJFrame f, GridBagConstraints c) {
		JButton returnButton = new JButton("Return");
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridwidth = 2;
		c.gridx = 10;
		c.gridy = 10;
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
		
		/*JLabel label = new JLabel();
        label.setText("<html>"
            + "<h3>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</h3>"
            + "<p>Duis a tincidunt urna. Phasellus tristique interdum mauris, "
            + "ut vestibulum purus suscipit eget. Aenean massa elit, accumsan "
            + "non faucibus vel, dictum placerat urna. In bibendum est sagittis "
            + "urna iaculis quis sagittis velit commodo. Cum sociis natoque "
            + "penatibus et magnis dis parturient montes, nascetur ridiculus "
            + "mus. Nam quis lacus mauris. Phasellus sem libero, convallis "
            + "mattis sagittis vel, auctor eget ipsum. Vivamus molestie semper "
            + "adipiscing. In ac neque quis elit suscipit pharetra. Nulla at "
            + "orci a tortor consequat consequat vitae sit amet elit. Praesent "
            + "commodo lacus a magna mattis vehicula. Curabitur a hendrerit "
            + "risus. Aliquam accumsan lorem quis orci lobortis malesuada.</p>"
            + "<p>Proin quis viverra ligula. Donec pulvinar, dui id facilisis "
            + "vulputate, purus justo laoreet augue, a feugiat sapien dolor ut "
            + "nisi. Sed semper augue ac felis ultrices a rutrum dui suscipit. "
            + "Praesent et mauris non tellus gravida mollis. In hac habitasse "
            + "platea dictumst. Vestibulum ante ipsum primis in faucibus orci "
            + "luctus et ultrices posuere cubilia Curae; Vestibulum mattis, "
            + "tortor sed scelerisque laoreet, tellus neque consectetur lacus, "
            + "eget ultrices arcu mi sit amet arcu. Nam gravida, nulla interdum "
            + "interdum gravida, elit velit malesuada arcu, nec aliquam lectus "
            + "velit ut turpis. Praesent pretium magna in nibh hendrerit et "
            + "elementum tellus viverra. Praesent eu ante diam. Proin risus "
            + "eros, dapibus at eleifend sit amet, blandit eget purus. "
            + "Pellentesque eu mollis orci. Sed venenatis diam a nisl tempor "
            + "congue.</p>"
            + "</html>");
        
        label.setPreferredSize(new Dimension(300, 300));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(300, 300));
        f.add(panel);
        f.setSize(620, 600);
        f.setBackgroundImage("image.jpg");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(panel);
        //f.pack();
        f.setVisible(true);

	}
}*/
