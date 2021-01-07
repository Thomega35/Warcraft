package warcraftTD;

public class Main {

	public static void main(String[] args) {
		int width = 800;
		int height = 800;
		int nbSquareX = 8;
		int nbSquareY = 8;	
		

		World world = new World(width, height, nbSquareX, nbSquareY);

		// Lancement de la boucle principale du jeu
		world.run();
	}
}

