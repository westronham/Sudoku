
public class HintSystem {
	private int numHints;
	private final int maxHints;
	private boolean[] doneNumbers;
	private final static int EASYHINTSNUM = 3;
	private final static int MEDIUMHINTSNUM = 1;
	private final static int HARDHINTSNUM = 0;
	
	public HintSystem(Sudoku board){
		this.doneNumbers = new boolean[9];
		
		if(board.getDifficulty() == 1){
			this.maxHints = EASYHINTSNUM;	
		} else if (board.getDifficulty() == 1){
			this.maxHints = MEDIUMHINTSNUM;
		} else {
			this.maxHints = HARDHINTSNUM;
		}
		
		for(int i = 0; i < 9; i++){
			doneNumbers[i] = false;
		}
		numHints = 0;
		
	}
	
	/**
	 * Returns the sudoku grid with possible locations of a number
	 * @param number
	 * @return
	 */
	public Sudoku getHint (Sudoku board, int number){ //assumes number >0
		
		if(canHint()){
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if(board.getSolvedSudoku()[i][j] == number){
						board.getPlayerSudoku()[i][j] = number;
					}
				}
			}
			
			numHints++;
			doneNumbers[number - 1] = true;
		}
		
		return board;
	}
	
	public boolean doneNumber (int number){
		return doneNumbers[number-1] != false;
	}
	
	public boolean canHint(){
		return numHints < maxHints;
	}
}
