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
    		Pattern emptySpace = Pattern.compile("");
    	    Matcher digitMatcher = validDigits.matcher(str); 
    	    Matcher emptyMatcher = emptySpace.matcher(str);
    	    boolean isDigit = digitMatcher.matches();
    	    boolean isEmpty = emptyMatcher.matches();
    	    
    		if(isDigit || isEmpty)
    			if ((fb.getDocument().getLength() + str.length()) <= maxChars)
        			super.replace(fb, offs, length, str, a);
        		else
        			JOptionPane.showMessageDialog
        				(null,"Please choose a number between 1-9", "Number Too Big", JOptionPane.PLAIN_MESSAGE);
    		else
    			JOptionPane.showMessageDialog
				(null,"Please choose a number between 1-9", "Illegal Character", JOptionPane.PLAIN_MESSAGE);
    }
    
    int maxChars;
}
