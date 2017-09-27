import gameElements.Board;
import gameElements.Game;
import graphics.GameUI;

public class Main {
	
	public Main(){
		Game g = new Game();
		
		GameUI game = new GameUI(g);
	}
	
	public static void main(String[] args) {
        new Main();
    }

}
