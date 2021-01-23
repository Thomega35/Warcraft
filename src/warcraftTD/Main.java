package warcraftTD;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int width = 800;
		int height = 800;
		int nbSquareX = 12;
		int nbSquareY = 12;	
		StdDraw.filledCircle(0.5, 0.5, 100);
		StdDraw.show();
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisissez la taille du plateau (valeur par défaut : 12x12) :");
		nbSquareX = sc.nextInt();
		nbSquareY = sc.nextInt();
		sc.close();

		World world = new World(width, height, nbSquareX, nbSquareY);

		// Lancement de la boucle principale du jeu
		world.run();
	}
}

