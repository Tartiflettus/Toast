package gameElements;

public class Board {
	
	public final int WHITE = 0;
	public final int RED = 1;
	public final int YELLOW = 2;
	
	protected int x;
	protected int y;
	protected int[][] board;
	
	
	public Board(){
		x = 7;
		y = 7;
		board = new int[x][y];
	}
	
	public Board(int x, int y){
		this.x = x;
		this.y = y;
		board = new int[x][y];
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < x ; i++){
			sb.append("| ");
			for (int j = 0 ; j < y ; j++){
				sb.append(board[i][j]);
			}
			sb.append("|\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	//accès à la case (x,y) du plateau
	public int getCell(int x, int y){
		return board[x][y];
	}
}
