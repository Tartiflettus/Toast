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
	
	/*
	 * Moyenne des récompenses
	 */
	public double mu(){
		return Si / (double) Ni; 
	}
	
	/*
	 * Nombre de passages par le noeud
	 */
	private int getNi(){
		return Ni;
	}
	
	/*
	 * Nombre de passages par le noeud du parent
	 */
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
	
	/*
	 * Mise à jour de la BValeur en fonction de la récompense
	 */
	private void majBValeur(int r){
		Si += (double) r;
		Ni++;
	}
	
	/*
	 * BValeur
	 */
	private double getBValeur(){
		return mu() + C * Math.sqrt(Math.log(NParent()) / Ni);
	}
	
	/*
	 * Récupérer les fils sans BValeur
	 */
	public Set<ArbreMonteCarlo> getFilsSansBValeur(){
		if(filsSansBValeur == null){
			filsSansBValeur = new HashSet<>();
			for(Board b : board.successeurs()){
				filsSansBValeur.add(new ArbreMonteCarlo(b));
			}
		}
		return filsSansBValeur;
	}
	
	/*
	 * Récupérer les fils avec une BValeur
	 */
	private Set<ArbreMonteCarlo> getFilsAvecBValeur(){
		return filsAvecBValeur;
	}

	
	/*
	 * Récupérer le noeud de plus grande B-Valeur
	 */
	private ArbreMonteCarlo selecPlusGrandeBValeur(){
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
	private boolean estTerminal(){
		return filsSansBValeur == null || filsSansBValeur.size() != 0;
	}
	
	/*
	 * Déroule une partie avec des coups aléatoires.
	 * Renvoie la récompense de cette partie
	 * (voir Board::marcheAleatoire)
	 */
	private int marcheAleatoire(){
		return board.marcheAleatoire();
	}
	
	/*public void MCTS(){
		
		ArbreMonteCarlo suivant = selecPlusGrandeBValeur();
		if()
		suivant.MCTS();
		
	}*/
	
	public static boolean near(double f1, double f2){
		return Math.abs(f1 - f2) < 0.0001;
	}
	
	
	public static void main(String[] args){
		ArbreMonteCarlo a = new ArbreMonteCarlo(new Board(2, 2));
		
		//test du nombre de fils (successeurs)
		Set<ArbreMonteCarlo> succs = a.getFilsSansBValeur();
		assert(succs.size() == 2):"Mauvais nombre de successeurs";
		
		//maj de BValeur
		a.majBValeur(1); //victoire
		assert(a.mu() == 1f):"Mauvaise moyenne des récompenses";
		a.majBValeur(1); //victoire
		assert(a.mu() == 1f):"Mauvaise moyenne des récompenses";
		a.majBValeur(0); //défaite
		assert(near(a.mu(), 2f/3f));
		
		System.out.println("OK");
	}

}
