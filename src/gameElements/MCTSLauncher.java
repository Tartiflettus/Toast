package gameElements;

public class MCTSLauncher implements Runnable {
	
	private Game game;
	
	private static final long DELAI = 1000;
	
	public MCTSLauncher(Game game) {
		this.game = game;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * Lance le calcul de MCTS
	 */
	@Override
	public void run() {
		game.attendre();
		
		ArbreMonteCarlo arbre = game.getProchainCoup();
		arbre.developper();
		final long start = System.currentTimeMillis();
		final long end = start + DELAI;
		int cptMCTS = 0;
		while(System.currentTimeMillis() < end){
			arbre.MCTS();
			++cptMCTS;
		}
		game.setCptMCTS(cptMCTS);
		game.setProchainCoup(arbre);
		
		game.finCalculOrdi();
		
		game.ordiQuiJoue();
	}

}
