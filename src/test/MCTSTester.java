package test;

import gameElements.ArbreMonteCarlo;
import gameElements.Board;

public class MCTSTester {

	public static void main(String[] args){
		Board b = new Board(5, 5);
		ArbreMonteCarlo arbre = new ArbreMonteCarlo(b);
		for (int i = 0 ; i < 2 ; i ++){
			arbre.MCTS();
		}
		System.out.println("OK");
		/*int a = 5;
		a += 2;
		System.out.println(a);*/
	}

}
