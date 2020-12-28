package warcraftTD;

public class Main {

	public static void main(String[] args) {
		int width = 800;
		int height = 800;
		int nbSquareX = 13;
		int nbSquareY = 13;	
		

		World world = new World(width, height, nbSquareX, nbSquareY);


		world.initPath();
		// Lancement de la boucle principale du jeu
		world.run();
	}
}

