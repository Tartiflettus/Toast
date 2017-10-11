package test;


import java.util.List;

import except.CaseOccupeeException;
import gameElements.Board;

public class BoardTester {

	public static void main(String[] args){
		Board b = new Board(3, 3);
		//test du nombre de successeurs et de leurs diff√©rences
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
		//test du cas o√π il n'y a pas toutes les colonnes dispos
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
			assert(false):"Erreur non lev√©e lorsqu'on pion est plac√© sur un autre";
		}
		catch(CaseOccupeeException e){
		}
		assert(b.getCell(0, 0) == Board.RED):"Un setCell qui l√®ve une exception remplace tout de m√™me";
		
		//test de d√©tection de remplissage
		b = new Board(2, 1);
		b.poserPion(0);
		b.poserPion(1);
		assert(b.estRempli()):"Plateau non d√©tect√© comme rempli alors qu'il l'est";
		
		//test des fonctions isFinal de la classe Board
		//vertical
		b = new Board(5,5);
		assert(b.isFinal() == Board.WHITE):"isFinal() retourne qu'un joueur a gagnÈ alors que le plateau est vide";
		
		b.poserPion(0);
		b.poserPion(0);
		b.poserPion(0);
		b.poserPion(0);
		assert(b.isFinal(0, 2) != Board.WHITE): "isFinal(x,y) retourne que personne n'a gagnÈ alors que c'est faux";
		assert(b.isFinal() != Board.WHITE): "isFinal retourne que personne n'a gagnÈ alors que c'est faux";
		
		//horizontal
		b = new Board(5,5);
		b.poserPion(1);
		b.poserPion(2);
		b.poserPion(3);
		b.poserPion(4);
		assert(b.isFinal(2,4) != Board.WHITE): "isFinal(x,y) retourne que personne n'a gagnÈ alors que c'est faux";
		assert(b.isFinal() != Board.WHITE): "isFinal retourne que personne n'a gagnÈ alors que c'est faux";
		
		//diagonale 1
		b = new Board(5,5);
		b.setCell(1, 0, Board.RED);
		b.setCell(2, 1, Board.RED);
		b.setCell(3, 2, Board.RED);
		b.setCell(4, 3, Board.RED);
		assert(b.isFinal(2,1) != Board.WHITE): "isFinal(x,y) retourne que personne n'a gagnÈ alors que c'est faux";
		assert(b.isFinal() != Board.WHITE): "isFinal retourne que personne n'a gagnÈ alors que c'est faux";
		
		//diagonale 2
		b = new Board(5,5);
		b.setCell(1, 3, Board.RED);
		b.setCell(2, 2, Board.RED);
		b.setCell(3, 1, Board.RED);
		b.setCell(4, 0, Board.RED);
		assert(b.isFinal(2,2) != Board.WHITE): "isFinal(x,y) retourne que personne n'a gagnÈ alors que c'est faux";
		assert(b.isFinal() != Board.WHITE): "isFinal retourne que personne n'a gagnÈ alors que c'est faux";
		
		System.out.println("OK");
	}

}
