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
		return b;
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
		
	public void poserPionRouge(int x, int y){
		board[x][y] = 2;
	}
	
	public void poserPionJaune(int x, int y){
		board[x][y] = 1;
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

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	//accès à la case (x,y) du plateau
	public int getCell(int x, int y){
		return board[x][y];
	}
}
