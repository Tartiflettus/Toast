package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClick implements ActionListener {

	private GameUI gameUI;
	private int x,y;

	public ButtonClick(GameUI g, int x, int y){
		this.gameUI=g;
		this.x=x;
		this.y=y;
	}

	
	
	public void actionPerformed(ActionEvent arg0) {
		

	}

}
