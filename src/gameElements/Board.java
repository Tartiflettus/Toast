package gameElements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import except.CaseOccupeeException;

public class Board {
	
	public final static int WHITE = 0;
	public final static int RED = 1;
	public final static int YELLOW = 2;
	
	private int width;
	private int height;
	private int[][] board;
	private int cptPions;
	
	private int joueurActuel;
	
	
	public Board(){
		width = 7;
		height = 6;
		board = new int[width][height];
		joueurActuel = RED;
		cptPions = 0;
	}
	
	public Board(int width, int height){
		this.width = width;
		this.height = height;
		board = new int[width][height];
		joueurActuel = RED;
		cptPions = 0;
	}
		
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int j = 0 ; j < height ; j++){
			sb.append("| ");
			for (int i = 0 ; i < width ; i++){
				sb.append(board[i][j]);
				sb.append(" | ");
			}
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	public Board clone(){
		int res[][] = new int[width][height];
		for (int i = 0 ; i < width ; i++){
			for (int j =0 ; j < height ; j++){
				res[i][j] = board[i][j];
			}
		}
		Board b = new Board(width, height);
		b.setBoard(res);
		b.setJoueurActuel(joueurActuel);
		b.cptPions = cptPions;
		return b;
	}
	
	public List<Board> successeurs(){
		List<Board> succ = new ArrayList<>();
		for(int x = 0 ; x < width ; x++){
			int y = selectionnerCaseAccessible(x);
			if (y != -1){
				Board cloneBoard = clone();
				cloneBoard.poserPion(x,y);
				cloneBoard.swapJoueurActuel();
				succ.add(cloneBoard);
			}
		}
		return succ;
	}
	
	/*
	 * RED = le joueur rouge a gagne
	 * YELLOW = le joueur rouge a perdu
	 * WHITE = le jeu n est pas final
	 */
	public int isFinal(){
		return YELLOW; //TODO
	}
	
	public boolean estRempli(){
		return cptPions == width * height;
	}
	
	/*
	 * Joue la partie jusque la fin, puis renvoie le résultat:
	 * 1 si le rouge a gagné
	 * 0 si le jaune a gagné ou que le plateau est complet
	 */
	public int marcheAleatoire(){
		int fin = isFinal();
		if(fin != WHITE){
			return fin == RED ? 1 : 0;
		}
		List<Board> succ = successeurs();
		int index = ((int)Math.random())*succ.size();
		return succ.get(index).marcheAleatoire();
	}
	
	public int selectionnerCaseAccessible(int x){
		int y = height-1;
		while (y >= 0 && getCell(x, y) != 0){
			y--;
		}
		if (y >= 0){
			return y;
		}
		return -1;
	}
		
	public void poserPion(int x, int y){
		if(joueurActuel == RED){
			setCell(x, y, RED);
		} else {
			setCell(x, y, YELLOW);
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}


	public int getJoueurActuel() {
		return joueurActuel;
	}

	public void setJoueurActuel(int joueurActuel) {
		this.joueurActuel = joueurActuel;
	}
	
	public void swapJoueurActuel(){
		joueurActuel = joueurActuel == YELLOW ? RED : YELLOW;
	}

	//accès à la case (x,y) du plateau
	public int getCell(int x, int y){
		return board[x][y];
	}
	
	//accès à la case (x,y) du plateau
	//exception si la case n'est pas vide
	public void setCell(int x, int y, int color){
		if(board[x][y] != WHITE){
			throw new CaseOccupeeException(this);
		}
		board[x][y] = color;
		if(color != WHITE){
			++cptPions;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if(width != other.width || height != other.height){
			return false;
		}
		for(int i=0; i < width; ++i){
			for(int j=0; j < height; ++j){
				if(getCell(i, j) != other.getCell(i, j)){
					return false;
				}
			}
		}
		return true;
	}
	
	
}
