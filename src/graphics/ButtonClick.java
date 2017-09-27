package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameElements.Game;

public class ButtonClick implements ActionListener {

	private Game game;
	private int x;

	public ButtonClick(Game g, int x){
		this.game=g;
		this.x=x;
	}

	
	
	public void actionPerformed(ActionEvent arg0) {
		

	}

}
