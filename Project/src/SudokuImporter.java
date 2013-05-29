import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;


public class SudokuImporter {
	Scanner scanner;
	static Random random;
	
	public SudokuImporter() {
		random = new Random();
	}
	
	public int[] readSudoku(int difficulty) {
	try { //Get sudoku based on difficulty (test with only 1, it will be "easy". We can replace this with an input class
		if (difficulty == 1) {
			File directory = new File("/Users/Ron/Documents/GitHub/Project/Project/Tests/Easy/");      
			File[] listOfFiles = directory.listFiles();
			int randomNum = random.nextInt(listOfFiles.length);
			scanner = new Scanner(new FileReader(listOfFiles[randomNum]));
		} else if (difficulty == 2) {
			File directory = new File("/Users/Ron/Documents/GitHub/Project/Project/Tests/Medium/");      
			File[] listOfFiles = directory.listFiles();
			int randomNum = random.nextInt(listOfFiles.length);
			scanner = new Scanner(new FileReader(listOfFiles[randomNum]));
		} else if (difficulty == 3) {
			File directory = new File("/Users/Ron/Documents/GitHub/Project/Project/Tests/Hard/");      
			File[] listOfFiles = directory.listFiles();
			int randomNum = random.nextInt(listOfFiles.length);
			scanner = new Scanner(new FileReader(listOfFiles[randomNum]));
		}
		int[] row = new int[100];
		int i = 0;
		while(scanner.hasNextInt()){
		   row[i++] = scanner.nextInt();
		}
		scanner.close();
		return row;
	} catch (FileNotFoundException e) {}
	return null;
	}
	
}
	