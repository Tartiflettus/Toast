package test;


import java.util.List;
import gameElements.Board;

public class BoardTester {

	public static void main(String[] args){
		Board b = new Board(3, 3);
		List<Board> succs = b.successeurs();
		assert(succs.size() == 3):"Mauvais nombre de successeurs";
		assert(b.getJoueurActuel() != succs.get(0).getJoueurActuel()):"Pas de changement de joueur";
		
		
		System.out.println("OK");
	}

}
