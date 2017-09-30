package test;


import java.util.List;

import except.CaseOccupeeException;
import gameElements.Board;

public class BoardTester {

	public static void main(String[] args){
		Board b = new Board(3, 3);
		List<Board> succs = b.successeurs();
		assert(succs.size() == 3):"Mauvais nombre de successeurs";
		assert(b.getJoueurActuel() != succs.get(0).getJoueurActuel()):"Pas de changement de joueur";
		for(int i=0; i < succs.size(); ++i){
			for(int j=0; j < succs.size(); ++j){
				if(i != j){
					assert(!succs.get(i).equals(succs.get(j))):"Plusieurs successeurs sont identiques";
				}
			}
		}
		b.setCell(0, 0, Board.RED);
		assert(b.getCell(0, 0) == Board.RED):"setCell ne place pas la bonne couleur";
		try{
			b.setCell(0, 0, Board.YELLOW);
			assert(false):"Erreur non levée lorsqu'on pion est placé sur un autre";
		}
		catch(CaseOccupeeException e){
		}
		assert(b.getCell(0, 0) == Board.RED):"Un setCell qui lève une exception remplace tout de même";
		
		
		System.out.println("OK");
	}

}
