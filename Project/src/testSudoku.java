import java.io.*;
import java.util.*;


public class testSudoku {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		/*
		 * Get a bunch of sudoku boards
		 */
		   try { //Get sudoku based on diffuiculty (test with only 1, it will be "easy". We can replace this with an input class
				Scanner scanner = new Scanner(new FileReader(args[0]));
				int[] row = new int[100];
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
			//sudoku.resetSudoku();
			//SudokuStore storage= new SudokuStore(); 
			//storage.setBoard(sudoku.getDifficulty(), sudoku); //Give easy (1) sudoku board Sudoku' to the storage class		
			final BackgroundJFrame f=new BackgroundJFrame();
			MainMenu mainMenu = new MainMenu(sudoku);
			mainMenu.startMainMenu(f);
			} catch (FileNotFoundException e) {}
	   }
		
}
