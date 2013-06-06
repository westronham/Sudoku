import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Class responsible for reading in the Sudoku text from file and converting it into an array of ints
 */
public class SudokuImporter {
	
	private Scanner scanner;
	private  static Random random;
	private int difficulty;
	private List<File> listOfFiles;
	private int randomNum;
	
	/**
	 * Initialises the importer
	 */
	public SudokuImporter() {
		random = new Random();
		listOfFiles = new ArrayList<File>();
	}
	
	/**
	 * Reads in a file and converts it into an array of ints.
	 * @param difficulty The difficulty specified by the user.
	 * @return Returns an array of the ints in the Sudoku puzzle.
	 */
	public int[] readSudoku(int difficulty) {
		this.difficulty = difficulty;
		try {
		if (difficulty == 1) {
			File directory = new File("../Project/Tests/Easy/"); 
			File[] files = directory.listFiles();
			Collections.addAll(listOfFiles, files);
		} else if (difficulty == 2) {
			File directory = new File("../Project/Tests/Medium/");      
			File[] files = directory.listFiles();
			Collections.addAll(listOfFiles, files);
		} else if (difficulty == 3) {
			File directory = new File("../Project/Tests/Hard/");      
			File[] files = directory.listFiles();
			Collections.addAll(listOfFiles, files);
		}
		Scanner scanner = this.readFile();
		if (scanner != null) {
			int[] row = new int[100];
			int i = 0;
			while(scanner.hasNextInt()){
			   row[i++] = scanner.nextInt();
			}
			scanner.close();
			while (listOfFiles.size() > 0) {
				if (validFileCheck(row)) {
					return row;
				} else {
					listOfFiles.remove(randomNum);
					if (listOfFiles.size() > 0) {
						randomNum = random.nextInt(listOfFiles.size());
					}
				}
			}
		}
	} catch (FileNotFoundException e) {}
	return null;
	}
	
	/**
	 * Ensures there is a file in the specified directory.
	 * @return Returns the file read in from the directory.
	 * @throws FileNotFoundException
	 */
	private Scanner readFile() throws FileNotFoundException {
		if (listOfFiles.size() > 0) {
			randomNum = random.nextInt(listOfFiles.size());
		scanner = new Scanner(new FileReader(listOfFiles.get(randomNum)));
		return scanner;
		}
		return null;
	}
	
	/**
	 * Checks whether the file read in is valid.
	 * @param check The array of ints pertaining to the Sudoku.
	 * @return True or False depending on whether the file was valid.
	 */
	private boolean validFileCheck(int[] check) {
		if (check.length != 100) {
			return false;
		}
		for (int i = 0; i < check.length; i++) {
			if (check[i] < 0 || check[i] > 9) {
				return false;
			}
		}
		Sudoku sudoku = new Sudoku();
		sudoku.initSudoku(check, difficulty);
		if (!sudoku.solve()) {
			return false;
		}
		return true;
	}
}
	