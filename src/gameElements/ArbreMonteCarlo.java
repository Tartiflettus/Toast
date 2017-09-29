package gameElements;

import java.util.Set;

/*
 * Un arbre de Monte-Carlo, qui permet en un temps limité de trouver le meilleur coup à jouer
 */
public class ArbreMonteCarlo {
	private int Ni;
	private static final double C = Math.sqrt((double) 2);
	private double Si;
	
	private ArbreMonteCarlo parent;
	private Set<ArbreMonteCarlo> fils;
	
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
	}
	
	public void majBValeur(int r){
		Si += (double) r;
		Ni++;
	}

}
