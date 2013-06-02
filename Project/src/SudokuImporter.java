import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class SudokuImporter {
	Scanner scanner;
	static Random random;
	int difficulty;
	List<File> listOfFiles;
	int randomNum;
	
	public SudokuImporter() {
		random = new Random();
		listOfFiles = new ArrayList<File>();
	}
	
	public int[] readSudoku(int difficulty) {
		this.difficulty = difficulty;
		try { //Get sudoku based on difficulty (test with only 1, it will be "easy". We can replace this with an input class
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
	
	private Scanner readFile() throws FileNotFoundException {
		if (listOfFiles.size() > 0) {
			randomNum = random.nextInt(listOfFiles.size());
		scanner = new Scanner(new FileReader(listOfFiles.get(randomNum)));
		return scanner;
		}
		return null;
	}
	
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
	