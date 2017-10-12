package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameElements.Board;
import gameElements.Game;

public class ButtonClick implements ActionListener {

	private Game game;
	private int x;
	
	
	public ButtonClick(Game g, int x){
		this.game=g;
		this.x=x;	
	}

	
	
	public void actionPerformed(ActionEvent arg0) {
		if (game.getJoueurActuel() == Board.RED){
			game.poserPion(x);
			
		}
		else {
			game.ordiQuiJoue();
		}
	}

}
