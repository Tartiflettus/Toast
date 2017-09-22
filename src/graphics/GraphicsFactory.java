package graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
 * Classe factory permettant un accès aux ressources graphiques
 */
public class GraphicsFactory {
	private GraphicsFactory instance = new GraphicsFactory();
	private Icon white = new ImageIcon(getClass().getResource("res/rond_blanc.png"));
	
	public GraphicsFactory getInstance(){
		return instance;
	}
	
	private GraphicsFactory() { //Singleton
	}
	
	public Icon getWhite(){
		return white;
	}

}
