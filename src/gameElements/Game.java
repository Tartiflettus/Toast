package gameElements;

import java.util.Observable;

public class Game extends Observable {
	
	Board board;

	public Game() {
		board = new Board();
	}
	
	private void maj(){
		setChanged();
		notifyObservers();
	}
	
	public int getWidth(){
		return board.getWidth();
	}
	
	public int getHeight(){
		return board.getHeight();
	}
	
	public void reset(){
		//TODO
	}

}
