package warcraftTD;

public class Main {

	public static void main(String[] args) {
		int width = 800;
		int height = 800;
		int nbSquareX = 11;
		int nbSquareY = 11;	
		
		World w = new World(width, height, nbSquareX, nbSquareY);
		
<<<<<<< HEAD
=======
		// Ajout d'un monstre "a la main" pour afficher comment un monstre se deplace. Vous ne devez pas faire pareil, mais ajouter une vague comportant plusieurs monstres 
		Monster monster = new BaseMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2));
		monster.nextPosition = new Position(startX * w.squareWidth + w.squareWidth / 2, 0);
		monster.speed = 0.01;
		w.monsters.add(monster);
>>>>>>> 6b73487df45fbeb742c25ba597bc8602737d574b
		
		// Lancement de la boucle principale du jeu
		w.run();
	}
}

