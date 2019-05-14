package connect_four;

public interface GameBoardInterface {

	
	void clearBoard();
	void displayBoard();
	void populateBoard();
	void takeTurn();
	void displayWinner();
	void displayCurrentPlayer();

	
	boolean isFull();
	boolean isEmpty();
	boolean isWinner(String currPlayer);
	boolean isDraw();
	void displayChampion();
	
	
}