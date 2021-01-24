package warcraftTD;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int width = 800;
		int height = 800;
		int nbSquareX = 12;
		int nbSquareY = 12;	
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisissez la taille du plateau, largeur puis hauteur (Valeur conseillée : 12 puis 12) :");
		nbSquareX = sc.nextInt();
		nbSquareY = sc.nextInt();
		sc.close();

		World world = new World(width, height, nbSquareX, nbSquareY);

		// Lancement de la boucle principale du jeu
		world.run();
	}
}

