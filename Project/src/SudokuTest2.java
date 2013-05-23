import java.io.*;
import java.util.*;


public class SudokuTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new FileReader(args[0]));
		int [] tall = new int [100];
		int i = 0;
		while(scanner.hasNextInt()){
		   tall[i++] = scanner.nextInt();
		}
		scanner.close();
		
		Sudoku sudoku = new Sudoku();
		sudoku.initSudoku(tall);
		sudoku.initConditionMatrices();
		sudoku.solve();
		sudoku.printUnsolvedSudoku();
		} catch (FileNotFoundException e) {}

		
	}

}
