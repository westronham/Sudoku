import javax.swing.text.*;
import java.util.regex.*;

/**
 * Limits what the player is capable of inputting with the keyboard to a number between 1 and 9
 */
public class InputLimiter extends DocumentFilter {
    
    private int maxChars;
	
	/**
	 * Initialises the InputLimiter
	 */
	public InputLimiter() {
        maxChars = 2;
    }
    
	/**
	 * Method that only allows a value in a JTextBox to be replaced by a digit or backspace
	 */
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
    }
   
}
