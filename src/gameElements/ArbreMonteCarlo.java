package gameElements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * Un arbre de Monte-Carlo, qui permet en un temps limité de trouver le meilleur coup à jouer
 */
public class ArbreMonteCarlo {
	private int Ni;
	private static final double C = Math.sqrt((double) 2);
	private double Si;
	
	private ArbreMonteCarlo parent;
	private Set<ArbreMonteCarlo> filsSansBValeur;
	private Set<ArbreMonteCarlo> filsAvecBValeur;
	
	private Board board;
	
	private double mu(){
		return Si / (double) Ni; 
	}
	
	private int getNi(){
		return Ni;
	}
	
	private int NParent(){
		if(parent == null) return 0;
		return parent.getNi();
	}

	public ArbreMonteCarlo() {
		Ni = 0;
		Si = 0;
		filsAvecBValeur = new HashSet<>();
	}
	
	public ArbreMonteCarlo(Board b){
		this();
		board = b;
	}
	
	
	public void majBValeur(int r){
		Si += (double) r;
		Ni++;
	}
	
	public double getBValeur(){
		return mu() + C * Math.sqrt(Math.log(NParent()) / Ni);
	}
	
	/*
	 * Récupérer les fils sans BValeur
	 */
	public Iterable<ArbreMonteCarlo> getFilsSansBValeur(){
		if(filsSansBValeur == null){
			filsSansBValeur = new HashSet<>();
			for(Board b : board.successeurs()){
				filsSansBValeur.add(new ArbreMonteCarlo(b));
			}
		}
		return filsSansBValeur;
	}
	
	public Iterable<ArbreMonteCarlo> getFilsAvecBValeur(){
		return filsAvecBValeur;
	}
	
	/*
	 * Récupérer le noeud de plus grande B-Valeur
	 */
	public ArbreMonteCarlo selecPlusGrandeBValeur(){
		double BMax = -1;
		ArbreMonteCarlo meilleur = null;
		for(ArbreMonteCarlo a : getFilsAvecBValeur()){
			if(BMax < a.getBValeur()){
				BMax = a.getBValeur();
				meilleur = a;
			}
		}
		return meilleur;
	}
	
	/*
	 * Un noeud est terminal si au moins un fils n'a pas de BValeur (y compris non créé)
	 */
	public boolean estTerminal(){
		return filsSansBValeur == null || filsSansBValeur.size() != 0;
	}
	
	public int marcheAleatoire(){
		return board.marcheAleatoire();
	}
	
	/*public void MCTS(){
		
		ArbreMonteCarlo suivant = selecPlusGrandeBValeur();
		if()
		suivant.MCTS();
		
	}*/

}