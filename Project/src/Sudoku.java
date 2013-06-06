import java.io.Serializable;

/**
 * Contains all the information pertaining to one Sudoku puzzle.
 */
public class Sudoku implements Serializable {
	
	private int difficulty;
	private int[][] sudoku; 		
	private int[][] unsolvedSudoku; 
	private int[][] solvedSudoku;  
	private int gridSize;
	private int gridBoxSize;
	private boolean rowMatrixSolution[][];
    private boolean colMatrixSolution[][];
    private boolean boxMatrixSolution[][];
	
    /**
     * Initialises the empty Sudoku arrays
     */
	public Sudoku () {
		unsolvedSudoku = new int[9][9];
		solvedSudoku = new int[9][9];
	}

	/**
	 * Loads up the unsolved Sudoku and solves it
	 * @param coordinates The array of individual numbers in the Sudoku
	 * @param difficulty The selected difficulty by the player
	 */
	public void initSudoku(int[] coordinates, int difficulty) {
		int row = 0;
		this.difficulty = difficulty;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
			unsolvedSudoku[j][i] = coordinates[j + (9 * row)];
			}
			row++;
		}
		gridSize = unsolvedSudoku.length;
		gridBoxSize = (int)Math.sqrt(gridSize);

		sudoku = cloneUnsolvedSudoku();
		solvedSudoku = cloneUnsolvedSudoku();

		initConditionMatrices();
		solve();
	}

	private int[][] cloneUnsolvedSudoku (){
		int[][] clonedSudoku = new int[9][9];

		for (int k = 0; k < gridSize; k++) {
			clonedSudoku[k] = unsolvedSudoku[k].clone();
		}
		return clonedSudoku;
	}


	/**
	 * Initialises the Condition Matrices which are responsible for whether a given value exists in a row, column or sub-grid.
	 */
	public void initConditionMatrices() {
        rowMatrixSolution = new boolean[gridSize][gridSize];
        colMatrixSolution = new boolean[gridSize][gridSize];
        boxMatrixSolution = new boolean[gridSize][gridSize];
        for(int i = 0; i < sudoku.length; i++) {
            for(int j = 0; j < sudoku.length; j++) {
                int value = sudoku[i][j];
                if(value != 0) {
                    setSolutionValueConditions(i, j, value, true);
                }
            }
        }
    }
 
	/**
	 * If a new number is added to the Sudoku by the player, this method records its existence in the related conditional matrices.
	 * @param i The row of the value.
	 * @param j The column of the value.
	 * @param value The value that is being added.
	 * @param exists A boolean that says whether the exists in the corresponding rows, columns or sub-grids.
	 */
	private void setSolutionValueConditions(int i, int j, int value, boolean exists) {
	    rowMatrixSolution[i][value - 1] = exists;
	    colMatrixSolution[j][value - 1] = exists;
	    boxMatrixSolution[findBoxNum(i, j)][value - 1] = exists;
	}

	/**
	 * Method that calls the algorithm to be solved from the start
	 * @return Returns true or false for whether the Sudoku can be solved or not
	 */
	public boolean solve() {
	    return solve(0, 0);
	}

	/**
	 * The backtracking algorithm that is responsible for solving the Sudoku.
	 * @param i The current row.
	 * @param j The current column.
	 * @return Returns true if the algorithm can solve the Sudoku and false if it can't.
	 */
	private boolean solve(int i, int j) {
	    if(i == gridSize) {
	        i = 0;
	        if(++j == gridSize) {
	            return true;
	        }
	    }
	    if(solvedSudoku[i][j] != 0) {
	        return solve(i + 1, j);
	    }
	    for(int value = 1; value <= gridSize; value++) {
	        if(isValid(i, j, value)) {
	        	solvedSudoku[i][j] = value;
	            setSolutionValueConditions(i, j, value, true);
	            if(solve(i + 1, j)) {
	                return true;
	            }
	            setSolutionValueConditions(i, j, value, false);
	        }
	    }
	    solvedSudoku[i][j] = 0;
	    return false;
	}
	
	/**
	 * Checks whether a given value already exists in the row, column and sub-grid.
	 * @param i The row.
	 * @param j The column.
	 * @param value The value to be checked.
	 * @return Returns true if the value can go in the specified row, column and sub-grid; false otherwise.
	 */
	private boolean isValid(int i, int j, int value) {
	    value--;
	    boolean present = rowMatrixSolution[i][value] || colMatrixSolution[j][value] || boxMatrixSolution[findBoxNum(i, j)][value];
	    return !present;
	}

	/**
	 * Calculates which sub-grid a given coordinate is in.
	 * @param i The row.
	 * @param j The column.
	 * @return Returns the sub-grid number.
	 */
	private int findBoxNum(int i, int j) {
	    int boxRow = i / gridBoxSize;
	    int boxCol = j / gridBoxSize;
	    return (boxRow * gridBoxSize) + boxCol;
	}

	/**
	 * Resets the user's sudoku with the original unsolved sudoku.
	 */
	public void resetSudoku() {
		for (int k = 0; k < gridSize; k++) {
			sudoku[k] = unsolvedSudoku[k].clone();
		}
	}

	public int[][] getPlayerSudoku() {
		return sudoku;
	}

	public int[][] getSolvedSudoku() {
		return solvedSudoku;
	}

	public int[][] getUnsolvedSudoku() {
		return unsolvedSudoku;
	}

	public int getDifficulty() {
		return difficulty;
	}

}