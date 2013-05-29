import javax.swing.JOptionPane;
import javax.swing.text.*;
import java.util.regex.*;

public class InputLimiter extends DocumentFilter {
    public InputLimiter() {
        maxChars = 2;
    }
    
    public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
        throws BadLocationException {
    	    
    		Pattern validDigits = Pattern.compile("\\d");
    		Matcher digitMatcher = validDigits.matcher(str); 
    		boolean isDigit = digitMatcher.matches();
    		
    		Pattern emptySpace = Pattern.compile("");
    		Matcher emptyMatcher = emptySpace.matcher(str);
    		boolean isEmpty = emptyMatcher.matches();
    		
    		if(isDigit || isEmpty)
    			if ((fb.getDocument().getLength() + str.length()) <= maxChars && !str.equals("0"))
        			super.replace(fb, offs, length, str, a);
        		//else
        			//JOptionPane.showMessageDialog
        				//(null,"Please choose a number between 1-9", "Invalid Number", JOptionPane.PLAIN_MESSAGE);
    		//else
    			//JOptionPane.showMessageDialog
				//(null,"Please choose a number between 1-9", "Illegal Character", JOptionPane.PLAIN_MESSAGE);
    }
    
    int maxChars;
}
