package gameElements;

public class Board {
	
	public final int WHITE = 0;
	public final int RED = 1;
	public final int YELLOW = 2;
	
	protected int width;
	protected int height;
	protected int[][] board;
	
	
	public Board(){
		width = 7;
		height = 7;
		board = new int[width][height];
	}
	
	public Board(int width, int height){
		this.width = width;
		this.height = height;
		board = new int[width][height];
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0 ; i < width ; i++){
			sb.append("| ");
			for (int j = 0 ; j < height ; j++){
				sb.append(board[i][j]);
			}
			sb.append("|\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	//accès à la case (x,y) du plateau
	public int getCell(int x, int y){
		return board[x][y];
	}
}
