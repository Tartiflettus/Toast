package gameElements;

import java.util.Observable;
import java.util.Observer;

public class Game extends Observable {
	
	Board board;
	int typeModification;  // 0 = pas de modif ; 1 = nouvelle partie ; 2 = poser un pion

	public Game() {
		board = new Board();
		typeModification = 0;
	}
	
	private void maj(){
		setChanged();
		notifyObservers();
		System.out.println(hasChanged());
	}
	
	public int getWidth(){
		return board.getWidth();
	}
	
	public int getHeight(){
		return board.getHeight();
	}
	
	public void reset(int width, int height){
		board = new Board(width, height);
		typeModification = 1;
		maj();
		typeModification = 0;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}



	
}
