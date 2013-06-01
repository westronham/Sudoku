import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.SwingUtilities;


public class HighScore {

	/**
	 * Compares score with previous high score and replaces it if it is greater.
	 * @param newScore
	 */
	public HighScore(long newScore){
		try {
			FileInputStream fileStream = new FileInputStream("Highscore.ser");
			ObjectInputStream os = new ObjectInputStream(fileStream);
			Object one = os.readObject();
		
			os.close();
	
			int hintsLeft = (int) two;
	

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			FileOutputStream fileStream = new FileOutputStream("Highscore.ser");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);

			os.writeObject(newScore);
		
			os.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
	}
}
