package gameElements;

import java.util.Observable;
import java.util.Observer;

public class Game extends Observable {
	
	Board board;
	int typeModification;  // 0 = pas de modif ; 1 = nouvelle partie ; 2 = poser un pion
	int xSelectionne;
	int ySelectionne;

	public Game() {
		board = new Board();
		typeModification = 0;
		xSelectionne = -1;
		ySelectionne = -1;
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
	
	public void reset(int width, int height){
		board = new Board(width, height);
		typeModification = 1;
		maj();
		typeModification = 0;
	}
	
	public int selectionnerCaseAccessible(int x){
		return board.selectionnerCaseAccessible(x);
	}
	
	public void poserPionRouge(int x){
		int y = selectionnerCaseAccessible(x);
		if (y != -1){	
			setBoutonSelectionne(x, y);
			board.poserPionRouge(x, y);
			typeModification = 2;
			maj();
			typeModification = 0;
		}
	}
	
	public void poserPionJaune(int x){
		int y = selectionnerCaseAccessible(x);
		if (y != -1){
			setBoutonSelectionne(x, y);
			board.poserPionJaune(x, y);
			typeModification = 2;
			maj();
			typeModification = 0;
		}
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getTypeModification() {
		return typeModification;
	}

	private void setBoutonSelectionne(int x, int y){
		xSelectionne = x;
		ySelectionne = y;
	}

	public int getxSelectionne() {
		return xSelectionne;
	}

	public int getySelectionne() {
		return ySelectionne;
	}
	
	public String toString(){
		return board.toString();
	}
	
	public int getCell(int x, int y){
		return board.getCell(x, y);
	}
	
}
