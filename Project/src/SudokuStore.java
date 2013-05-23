import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Stores easy, medium and hards. Can be replaced with a generator if we have enough time. 
 * @author fydp014
 *
 */
public class SudokuStore {
	private LinkedList<Sudoku> listOfEasyBoards;
	private LinkedList<Sudoku> listOfMediumBoards;
	private LinkedList<Sudoku> listOfhardBoards;
	
	
	public SudokuStore (){
		listOfEasyBoards = new LinkedList<Sudoku>();
		listOfMediumBoards = new LinkedList<Sudoku>();
		listOfhardBoards = new LinkedList<Sudoku>();
	}
	
	public void setBoard(int difficulty, Sudoku board){
		if(difficulty == 1){
			listOfEasyBoards.add(board);
		} else if(difficulty == 2){
			listOfMediumBoards.add(board);
		} else if(difficulty == 3){
			listOfhardBoards.add(board);
		}
	}
	
	public Sudoku getSudoku (int difficulty){
		if(difficulty == 1){
			return listOfEasyBoards.getFirst();
		} else if(difficulty == 2){
			return listOfMediumBoards.getFirst();
		} else if(difficulty == 3){
			return listOfhardBoards.getFirst();
		}
		return null;
	}
}
