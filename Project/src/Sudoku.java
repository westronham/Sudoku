//We'll have two doubly linked lists
//The rows of the matrix are doubly linked as circular lists via the L and R fields
//Columns are doubly linked as circular lists via the U and D fields. 
//Each column list also includes a special data object called its list header
public class Sudoku {
	public Sudoku () {
		sudoku = new int[9][9];
	}
	
	public void initSudoku(int[] coordinates) {
		int row = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
			sudoku[j][i] = coordinates[j + (9 * row)];
			}
			row++;
		}
		gridSize = sudoku.length;
		gridBoxSize = (int)Math.sqrt(gridSize);
		unsolvedSudoku = (int[][])sudoku.clone();
	}
	
	public int[][] getUnsolvedSudoku() {
		return sudoku;
	}
	
	public void printUnsolvedSudoku() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(sudoku[j][i] + " ");
			}
			System.out.println();
		}
	}
		
	 /*
	  * There are 3 different conditions we need to meet in order for a number to be valid
	  * 	1) A number must only appear once in any row
	  * 	2) A number must only appear once in any column
	  * 	3) A number must only appear once in all 3x3 grids
	  * We create 3 9x9 grids that hold true in a cell corresponding to the row/column/box of a value in the actual Sudoku if it satisfies the condition
	  * When we initialise them we set the flags for all values that aren't 0 (i.e. for actual Sudoku entries)
	  */
	 public void initConditionMatrices() {
	        rowMatrix = new boolean[gridSize][gridSize];
	        colMatrix = new boolean[gridSize][gridSize];
	        boxMatrix = new boolean[gridSize][gridSize];
	        for(int i = 0; i < sudoku.length; i++) {
	            for(int j = 0; j < sudoku.length; j++) {
	                int value = sudoku[i][j];
	                if(value != 0) {
	                    setValueConditions(i, j, value, true);
	                }
	            }
	        }
	    }
	 
	 /*
	  * We use this method to flag different values as satisfying the specific constraint
	  * rowSubset - We go to row i and put down that our value is present in that row
	  * solSubset - We go to column j and put down that our value is present in that column
	  * boxSubset - We determine which box our coordinates place us in and then put down that our value is present in that box
	  * Note how we do value-1 because our index starts from 0
	  */
	    private void setValueConditions(int i, int j, int value, boolean exists) {
	        rowMatrix[i][value - 1] = exists;
	        colMatrix[j][value - 1] = exists;
	        boxMatrix[findBoxNum(i, j)][value - 1] = exists;
	    }
	    
	    /*
	     * To solve for every value we start from 0
	     */
	    public boolean solve() {
	        return solve(0, 0);
	    }
	    
	    
	    public boolean solve(int i, int j) {
	        if(i == gridSize) {
	            i = 0;
	            if(++j == gridSize) {
	                return true;
	            }
	        }
	        if(sudoku[i][j] != 0) {
	            return solve(i + 1, j);
	        }
	        for(int value = 1; value <= gridSize; value++) {
	            if(isValid(i, j, value)) {
	                sudoku[i][j] = value;
	                setValueConditions(i, j, value, true);
	                if(solve(i + 1, j)) {
	                    return true;
	                }
	                setValueConditions(i, j, value, false);
	            }
	        }
	 
	        sudoku[i][j] = 0;
	        return false;
	    }
	    
	    /*
	     * Method that checks whether a number in a certain location is valid
	     * It does this by checking our row, column and box conditional matrices
	     * If none of the conditions have been satisfied already it returns false and so we return !false (i.e. true)
	     */
	    private boolean isValid(int i, int j, int value) {
	        value--;
	        boolean present = rowMatrix[i][value] || colMatrix[j][value] || boxMatrix[findBoxNum(i, j)][value];
	        return !present;
	    }
	    
	    /*
	     * Quick method for calculating which box any coordinate is in
	     */
	    private int findBoxNum(int i, int j) {
	        int boxRow = i / gridBoxSize;
	        int boxCol = j / gridBoxSize;
	        return (boxRow * gridBoxSize) + boxCol;
	    }
	
	
	private int[][] sudoku;
	private int[][] unsolvedSudoku;
	private int gridSize;
	private int gridBoxSize;
	private boolean rowMatrix[][];
    private boolean colMatrix[][];
    private boolean boxMatrix[][];
}
