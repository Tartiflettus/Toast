package test;


import java.util.List;

import except.CaseOccupeeException;
import gameElements.Board;

public class BoardTester {

	public static void main(String[] args){
		Board b = new Board(3, 3);
		//test du nombre de successeurs et de leurs différences
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
		//test du cas où il n'y a pas toutes les colonnes dispos
		b = new Board(2, 2);
		b.poserPion(0);
		b.poserPion(0);
		succs = b.successeurs();
		assert(succs.size() == 1):"On attend un seul successeur";
		b.poserPion(1);
		b.poserPion(1);
		succs = b.successeurs();
		assert(succs.isEmpty()):"On n'attend aucun successeur, le plateau est rempli";
		
		//test du placement de pions
		b = new Board(3, 3);
		b.setCell(0, 0, Board.RED);
		assert(b.getCell(0, 0) == Board.RED):"setCell ne place pas la bonne couleur";
		try{
			b.setCell(0, 0, Board.YELLOW);
			assert(false):"Erreur non levée lorsqu'on pion est placé sur un autre";
		}
		catch(CaseOccupeeException e){
		}
		assert(b.getCell(0, 0) == Board.RED):"Un setCell qui lève une exception remplace tout de même";
		
		//test de détection de remplissage
		b = new Board(2, 1);
		b.poserPion(0, 0);
		b.poserPion(1, 0);
		assert(b.estRempli()):"Plateau non détecté comme rempli alors qu'il l'est";
		
		System.out.println("OK");
	}

}
