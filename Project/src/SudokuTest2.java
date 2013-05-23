import java.io.*;
import java.util.*;


public class SudokuTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		Scanner scanner = new Scanner(new FileReader(args[0]));
		int[] row = new int [100];
		int i = 0;
		while(scanner.hasNextInt()){
		   row[i++] = scanner.nextInt();
		}
		scanner.close();
		
		Sudoku sudoku = new Sudoku();
		sudoku.initSudoku(row);
		sudoku.initConditionMatrices();
		sudoku.solve();
		sudoku.printSudoku();
		Client c = new Client();
		} catch (FileNotFoundException e) {}

		
	}

}
