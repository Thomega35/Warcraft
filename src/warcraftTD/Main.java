package warcraftTD;

public class Main {

	public static void main(String[] args) {
		int width = 800;
		int height = 800;
		int nbSquareX = 11;
		int nbSquareY = 11;	
		
		World w = new World(width, height, nbSquareX, nbSquareY);
		
		// Lancement de la boucle principale du jeu
		w.run();
	}
}

