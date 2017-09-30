package gameElements;

import java.util.Observable;

public class Game extends Observable {
	
	Board board;
	int typeModification;  // 0 = pas de modif ; 1 = nouvelle partie ; 2 = poser un pion
	int xSelectionne;
	int ySelectionne;
	
	public static final int PAS_MODIF = 0, NOUVELLE_PARTIE = 1, POSER_PION = 2;

	public Game() {
		board = new Board();
		typeModification = 0;
		xSelectionne = -1;
		ySelectionne = -1;
	}
	
	/*
	 * Prévient les vues que le modèle a été modifié
	 */
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
	
	/*
	 * Remet le plateau à 0
	 */
	public void reset(int width, int height){
		board = new Board(width, height);
		typeModification = NOUVELLE_PARTIE;
		maj();
		typeModification = PAS_MODIF;
	}
	
	/*
	 * Renvoie la ligne d'une case accessible de colonne x
	 * Ou -1 si la colonne est pleine
	 */
	public int selectionnerCaseAccessible(int x){
		return board.selectionnerCaseAccessible(x);
	}
	
	/*
	 * Pose un pion dans la colonne x
	 * Ne fait rien si la colonne est pleine
	 */
	public void poserPion(int x){
		int y = selectionnerCaseAccessible(x);
		if (y != -1){	
			setBoutonSelectionne(x, y);
			board.poserPion(x, y);
			typeModification = POSER_PION;
			setJoueurActuel(Board.YELLOW);
			maj();
			typeModification = PAS_MODIF;
		}
	}
	
	/*
	 * L'ordi pose un pion dans une colonne au hasard.
	 * Peut échouer si la colonne choisie est remplie
	 */
	public void ordiQuiJoue(){
		int x = (int) (Math.random()*getBoard().getWidth());
		int y = selectionnerCaseAccessible(x);
		if (y != -1){
			setBoutonSelectionne(x, y);
			board.poserPion(x, y);
			setJoueurActuel(Board.RED);
			typeModification = POSER_PION;
			maj();
			typeModification = PAS_MODIF;
		}
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	/*
	 * Indique quelle partie de l'affichage est modifiée
	 */
	public int getTypeModification() {
		return typeModification;
	}

	/*
	 * Indique quelle case a été modifiée en dernier
	 */
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
	
	public int getJoueurActuel() {
		return board.getJoueurActuel();
	}

	public void setJoueurActuel(int joueurActuel) {
		board.setJoueurActuel(joueurActuel);
	}

	public String toString(){
		return board.toString();
	}
	
	public int getCell(int x, int y){
		return board.getCell(x, y);
	}
	
}
