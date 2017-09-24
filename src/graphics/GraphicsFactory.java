package graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/*
 * Classe factory permettant un accès aux ressources graphiques
 */
public class GraphicsFactory {
	private static GraphicsFactory instance = new GraphicsFactory();
	private Icon white;
	private Icon red;
	private Icon yellow;
	
	public static GraphicsFactory getInstance(){
		return instance;
	}
	
	private GraphicsFactory() { //Singleton
		try{
			final Class<? extends GraphicsFactory> c = getClass();
			white  = new ImageIcon(c.getResource("/rond_blanc.png"));
			red = new ImageIcon(c.getResource("/rond_rouge.png"));
			yellow = new ImageIcon(c.getResource("/rond_jaune.png"));
		}
		catch(NullPointerException e){
			//ressource non trouvée
			JOptionPane.showMessageDialog(null,
					"Une ressource graphique est introuvable",
				    "Erreur de ressources",
				    JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	
	public Icon getWhite(){
		return white;
	}
	
	public Icon getRed(){
		return red;
	}
	
	public Icon getYellow(){
		return yellow;
	}
	
	public static void main(String[] args){
		JLabel lab = new JLabel();
		lab.setIcon(GraphicsFactory.getInstance().getRed());
		JFrame win = new JFrame();
		win.add(lab);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.pack();
		win.show();
	}

}
