package except;

import gameElements.Board;

public class CaseOccupeeException extends RuntimeException {

	private Board board;
	
	public CaseOccupeeException(Board b) {
		board = b.clone();
	}

}
