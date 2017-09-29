package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import gameElements.Board;
import gameElements.Game;

public class GameUI extends JFrame implements Observer {
	
	private Game game;
	
	protected JButton[][] buttons;
	protected JPanel panel;

	protected JMenuBar menuBar;
	protected JMenuItem nouvellePartie;
	
	
	public GameUI(Game game){
		super("Puissance 4");
		setPreferredSize(new Dimension(700, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		game.addObserver(this);
		
		this.game = game;
		
		// JMENUBAR
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		nouvellePartie = new JMenuItem("nouvelle partie");
		menuBar.add(nouvellePartie);
		
		nouvellePartie.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{				
				JComboBox largeur = new JComboBox();
				largeur.addItem(4);	largeur.addItem(5);	largeur.addItem(6);	largeur.addItem(7);	largeur.addItem(8);	largeur.addItem(9); largeur.addItem(10);
				
				JComboBox hauteur = new JComboBox();
				hauteur.addItem(4);	hauteur.addItem(5);	hauteur.addItem(6);	hauteur.addItem(7);	hauteur.addItem(8);	hauteur.addItem(9); hauteur.addItem(10);
				
			    int option = JOptionPane.showOptionDialog(null, 
			      new Object[] {"Largeur :", largeur, "Hauteur :", hauteur},
			      "Nouvelle Partie",
			      JOptionPane.OK_CANCEL_OPTION,
			      JOptionPane.QUESTION_MESSAGE, null, null, null); 
				
			    if (option == JOptionPane.OK_OPTION){
			    	game.reset((int)largeur.getSelectedItem(), (int)hauteur.getSelectedItem());
			 
			    	
			    }
			    
			    
			}
		});
		
		// JPANEL
		
		panel = new JPanel();
		
		affichageBoard();
		
		panel.setSize(1000, 1000);
		
		add(panel);
		
		//--
		
		pack() ;
        setVisible(true);

	}

	
	private void affichageBoard(){
		buttons = new JButton[game.getWidth()][game.getHeight()];
		
		panel.setLayout(new GridLayout(game.getHeight(),game.getWidth()));
		
		for(int j=0; j<game.getHeight();j++){
			for(int i=0;i<game.getWidth();i++){
				buttons[i][j] = new JButton();
			
				/* IMAGE ICON ROND_BLANC A PLACER SUR TOUS LES JBUTTONS */
				buttons[i][j].setIcon(GraphicsFactory.getInstance().getWhite());
				buttons[i][j].setBackground(new Color(39, 80, 186));
				buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				buttons[i][j].addActionListener(new ButtonClick(game, i));
				panel.add(buttons[i][j]);
			}
		}
	}

	@Override
	public void update(Observable obs, Object arg1) {
		if (game.getTypeModification() == Game.NOUVELLE_PARTIE){
			panel.removeAll();
		
			affichageBoard();
		
			panel.revalidate();
        	panel.repaint();
		}

		if (game.getTypeModification() == Game.POSER_PION){			
			int x = game.getxSelectionne();
			int y = game.getySelectionne();
			if(game.getCell(x, y) == Board.RED){
				buttons[x][y].setIcon(GraphicsFactory.getInstance().getRed());
			}
			if(game.getCell(x, y) == Board.YELLOW){
				buttons[x][y].setIcon(GraphicsFactory.getInstance().getYellow());
			}
			
			buttons[x][y].repaint();
		}
	}

}
