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

public class GameUI extends JFrame {
	protected Board board;
	
	protected JButton[][] buttons;
	protected JPanel panel;

	protected JMenuBar menuBar;
	protected JMenuItem nouvellePartie;
	
	
	public GameUI(Board b){
		super("Puissance 4");
		setPreferredSize(new Dimension(700, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		board = b;
		
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
			    	/*
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * INITIALISER NOUVEAU MODEL
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * 
			    	 * board = new Board((int)largeur.getSelectedItem(), (int)hauteur.getSelectedItem());
			    	 * + UPDATE UI
			    	 * 
			    	 */
			    	
			    	
			    }
			    
			    
			}
		});
		
		// JPANEL
		
		panel = new JPanel();
		
		buttons = new JButton[b.getWidth()][b.getHeight()];
		
		panel.setLayout(new GridLayout(b.getWidth(),b.getHeight()));
		for(int i=0;i<b.getWidth();i++){
			for( int j=0; j<b.getHeight();j++){
				buttons[i][j] = new JButton();
			
				/* IMAGE ICON ROND_BLANC A PLACER SUR TOUS LES JBUTTONS */
				buttons[i][j].setIcon(GraphicsFactory.getInstance().getWhite());
				
				buttons[i][j].setBackground(new Color(39, 80, 186));
				buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				buttons[i][j].addActionListener(new ButtonClick(this, i, j));
				panel.add(buttons[i][j]);
			}
		}
		panel.setSize(1000, 1000);

		add(panel);
		
		//--
		
		pack() ;
        setVisible(true);

	}
}
