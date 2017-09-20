package gameElements;

public class Board {
	
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

	public int[][] getBoard() {
		return board;
	}
	
	
}
