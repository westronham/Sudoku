import java.util.Random;

/**
 * Hint system that keeps track of number of hints so far, and the max number of hints for a given
 * board's difficulty. 
 * @author Felix
 *
 */
public class HintSystem {
	private int numHints;
	private final int maxHints;
	private final static int EASYHINTSNUM = 7;
	private final static int MEDIUMHINTSNUM = 4;
	private final static int HARDHINTSNUM = 0;
	
	public HintSystem(Sudoku board){
		
		if(board.getDifficulty() == 1){
			this.maxHints = EASYHINTSNUM;	
		} else if (board.getDifficulty() == 2){
			this.maxHints = MEDIUMHINTSNUM;
		} else if (board.getDifficulty() == 3){
			this.maxHints = HARDHINTSNUM;
		} else {
			this.maxHints = 0;
		}
		numHints = 0;
	}
		
	/**
	 * Get's the hint which is to fill in one location. 
	 * @param board the given Sudoku board. 
	 * @param x the x coordinate
	 * @param y the y coordinate 
	 * @return the updated board with the fill.
	 */
	public Sudoku getHint (Sudoku board){ //assumes number >0
		
		int x = (new Random ()).nextInt(8) + 1;
		int y = (new Random ()).nextInt(8) + 1;
		while(!isEmptyField(board, x, y)) {
			x = (new Random ()).nextInt(8) + 1;
			y = (new Random ()).nextInt(8) + 1;
		}        	
		
		if(canHint()){
			board.getPlayerSudoku()[x][y] = board.getSolvedSudoku()[x][y];
			numHints++;
		}
		
		return board;
	}
	
	/**
	 * Returns if a field in the player's board in the given Sudoku is empty.
	 * @param board Sudoku board
	 * @param x the x coordinate
	 * @param y the y coordinate 
	 * @return if the field in the player's board in the given Sudoku is empty.
	 */
	private boolean isEmptyField (Sudoku board, int x, int y){
		if(board.getPlayerSudoku()[x][y] == 0){
			return true;
		}
		return false;
	}
	
	private boolean canHint(){
		return numHints < maxHints;
	}
	
	/**
	 * Returns the number of remainder of hints so far. 
	 * @return
	 */
	public int getNumHintsLeft(){
		return maxHints - numHints;
	}
}
