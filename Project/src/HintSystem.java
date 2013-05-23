
public class HintSystem {
	private int[][] sudoku;
	private int[][] unsolvedSudoku;
	private int gridSize;
	private int gridBoxSize;
	private int numHints;
		
	public HintSystem(int[][] sudoku,  int[][] unsolvedSudoku){
		this.sudoku = sudoku;
		this.unsolvedSudoku = unsolvedSudoku;
		numHints = 0;
		
	}
	
	/**
	 * Returns the sudoku grid with possible locations of a number
	 * @param number
	 * @return
	 */
	public int[][] getHint (int number){
		return null;
	}
	
	
	//public 
}
