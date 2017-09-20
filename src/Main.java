import gameElements.Board;
import graphics.GameUI;

public class Main {
	
	public Main(){
		Board b = new Board();
		
		GameUI game = new GameUI(b);
	}
	
	public static void main(String[] args) {
        new Main();
    }

}
