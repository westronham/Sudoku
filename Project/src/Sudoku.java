import java.io.Serializable;


public class Sudoku implements Serializable {
	
	public Sudoku () {
		unsolvedSudoku = new int[9][9];
		solvedSudoku = new int[9][9];
	}

	/*
	 * We need to initialise a few things in our Sudoku before we can solve it
	 * Firstly we need to load all the given values into the correct coordinates
	 * Then we just initialise a few values:
	 * gridSize - will always be 9
	 * gridBoxSize - will always be 3
	 * I'm trying to make a copy of the unsolved Sudoku so we can reload it if the user wants to restart but it's not working yet
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


 /*
  * There are 3 different conditions we need to meet in order for a number to be valid
  * 	1) A number must only appear once in any row
  * 	2) A number must only appear once in any column
  * 	3) A number must only appear once in all 3x3 grids
  * We create 3 9x9 grids that hold true in a cell corresponding to the row/column/box of a value in the actual Sudoku if it satisfies the condition
  * When we initialise them we set the flags for all values that aren't 0 (i.e. for actual Sudoku entries)
  * It allows us to really quickly check if there is already a number in a row/column/box before we put it in a cell
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
 
 /*
  * We use this method to flag different values as satisfying the specific constraint
  * rowSubset - We go to row i and put down that our value is present in that row
  * solSubset - We go to column j and put down that our value is present in that column
  * boxSubset - We determine which box our coordinates place us in and then put down that our value is present in that box
  * Note how we do value-1 because our index starts from 0
  */
	private void setSolutionValueConditions(int i, int j, int value, boolean exists) {
	    rowMatrixSolution[i][value - 1] = exists;
	    colMatrixSolution[j][value - 1] = exists;
	    boxMatrixSolution[findBoxNum(i, j)][value - 1] = exists;
	}


/*
 * Our solve algorithm is given the coordinates from which to solve onwards
 * This method simply calls it to start from the first cell and thus solve the whole Sudoku
 */
public boolean solve() {
    return solve(0, 0);
}

/*
 * The algorithm that actually figures out what number goes where in our Sudoku
 * I'll break it up:
 * The algorithm takes in 2 coordinates: i is the column and j is the row number and from these points onwards we get the solutions
 * Firstly this is a recursive algorithm so we have a base case which is if we get to the last coordinate in the grid
 * Otherwise we go through each coordinate
 * 		If the value in the coordinate is not 0 (i.e. a given value), we recurse to the next cell
 * 		Otherwise we want to figure out the value of the cell so we go through every possible value and if it is a valid value
 * 		we put the value in that cell and flag it in the conditional matrices
 * 		Now the backtracking comes into play (it took me ages to wrap my head around this so message me if it confuses you):
 * 			If we recurse forward without encountering any issues, we return all true and we have the right answer
 * 			If the number turns out to be bad we change the value back to 0 and change the conditional matrix back to false and try the next available value
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

/*
 * Method that checks whether a number in a certain location is valid
 * It does this by checking our row, column and box conditional matrices
 * If none of the conditions have been satisfied already it returns false and so we return !false (i.e. true)
 */
	private boolean isValid(int i, int j, int value) {
	    value--;
	    boolean present = rowMatrixSolution[i][value] || colMatrixSolution[j][value] || boxMatrixSolution[findBoxNum(i, j)][value];
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

	/*
	 * Got it working
	 */
	public void resetSudoku() {
		for (int k = 0; k < gridSize; k++) {
			sudoku[k] = unsolvedSudoku[k].clone();
		}
	}

	//set check that it is a [9][9] array
	/*public void setSudoku(int[][] board) {
		sudoku = board;
	}*/

	/*public boolean checkBoard(){
		for(int i = 0; i < 9; i++)
        {
      	  for(int j = 0; j < 9; j++)
      	  {
      		 if(!(sudoku[i][j] == solvedSudoku[i][j])){
      			return false;
      		 }
      	  }
        }

		return true;
	}*/

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


	private int difficulty;
	private int[][] sudoku; 		
	private int[][] unsolvedSudoku; 
	private int[][] solvedSudoku;  
	private int gridSize;
	private int gridBoxSize;
	private boolean rowMatrixSolution[][];
    private boolean colMatrixSolution[][];
    private boolean boxMatrixSolution[][];
}