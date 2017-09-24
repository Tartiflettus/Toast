package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import gameElements.Board;

public class GameUI extends JFrame {
	protected Board board;
	
	protected JButton[][] buttons;
	protected JPanel panel;

	public GameUI(Board b){
		super("Puissance 4");
		setPreferredSize(new Dimension(700, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		board = b;
		
		panel = new JPanel();
		
		buttons = new JButton[b.getX()][b.getY()];
		
		panel.setLayout(new GridLayout(b.getX(),b.getY()));
		for(int i=0;i<b.getX();i++){
			for( int j=0; j<b.getY();j++){
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
		
		
		
		pack() ;
        setVisible(true);

	}
}
