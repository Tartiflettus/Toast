package gameElements;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import util.FloatUtility;

/*
 * Un arbre de Monte-Carlo, qui permet en un temps limité de trouver le meilleur coup à jouer
 */
public class ArbreMonteCarlo {
	private int Ni;
	private static final double C = Math.sqrt((double) 2);
	private double Si;
	
	private ArbreMonteCarlo parent;
	private List<ArbreMonteCarlo> filsSansBValeur;
	private List<ArbreMonteCarlo> filsAvecBValeur;
	
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
		filsAvecBValeur = new ArrayList<>();
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
	public List<ArbreMonteCarlo> getFilsSansBValeur(){
		if(filsSansBValeur == null){
			filsSansBValeur = new ArrayList<>();
			for(Board b : board.successeurs()){
				ArbreMonteCarlo arbre = new ArbreMonteCarlo(b);
				arbre.setParent(this);
				filsSansBValeur.add(new ArbreMonteCarlo(b));
			}
		}
		return filsSansBValeur;
	}
	
	/*
	 * Récupérer les fils avec une BValeur
	 */
	private List<ArbreMonteCarlo> getFilsAvecBValeur(){
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
	private boolean possedeNonDeveloppe(){
		return filsSansBValeur == null || filsSansBValeur.size() != 0;
	}
	
	private boolean estTerminal(){
		return board.isFinal() != Board.WHITE;
	}
	
	/*
	 * Déroule une partie avec des coups aléatoires.
	 * Renvoie la récompense de cette partie
	 * (voir Board::marcheAleatoire)
	 */
	private int marcheAleatoire(){
		return board.marcheAleatoire();
	}
	
	public void MCTS(){
		if (estTerminal()){
			int marcheAlea = marcheAleatoire();
			miseAJourParBackTracking(marcheAlea);
		}
		if (possedeNonDeveloppe()){
			/* Developpement de C3 (cf cours) */
			List<ArbreMonteCarlo> lesFils = getFilsSansBValeur();
			int index = (int)Math.random()*lesFils.size();
			ArbreMonteCarlo fils = lesFils.get(index);
			changerListe(index);
			
			/* Developpement de C31 */
			List<ArbreMonteCarlo> lesPetitsFils = fils.getFilsSansBValeur();
			index = (int)Math.random()*lesPetitsFils.size();
			ArbreMonteCarlo petitFils = lesPetitsFils.get(index);
			changerListe(index);
			
			/* Marche al�atoire sur C31 */
			int marcheAlea = petitFils.marcheAleatoire();
			
			/* Mettre a jour par backtracking */
			petitFils.miseAJourParBackTracking(marcheAlea);
		}
		ArbreMonteCarlo suivant = selecPlusGrandeBValeur();
		if(suivant != null){
			suivant.MCTS();
		}
		
	}
	
	private void miseAJourParBackTracking(int recompense){
		ArbreMonteCarlo noeudActuel = this;
		while (noeudActuel != null){
			noeudActuel.majBValeur(recompense);
			noeudActuel = noeudActuel.parent;
		}
	}
	
	private void changerListe(int i){
		filsAvecBValeur.add(filsSansBValeur.get(i));
		filsSansBValeur.remove(i);
	}
	
	private void setParent(ArbreMonteCarlo parent) {
		this.parent = parent;
	}

	public static void main(String[] args){
		ArbreMonteCarlo a = new ArbreMonteCarlo(new Board(2, 2));
		
		//test du nombre de fils (successeurs)
		List<ArbreMonteCarlo> succs = a.getFilsSansBValeur();
		assert(succs.size() == 2):"Mauvais nombre de successeurs";
		
		//maj de BValeur
		a.majBValeur(1); //victoire
		assert(a.mu() == 1f):"Mauvaise moyenne des récompenses";
		a.majBValeur(1); //victoire
		assert(a.mu() == 1f):"Mauvaise moyenne des récompenses";
		a.majBValeur(0); //défaite
		assert(FloatUtility.near(a.mu(), 2d/3d)):"Mauvaise moyenne des récompenses";
		assert(a.getNi() == 3):"Mauvais comptage du nombre de mises à jour";
		
		System.out.println("OK");
	}

}
