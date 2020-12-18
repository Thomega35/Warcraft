package warcraftTD;

public class Main {

	public static void main(String[] args) {
		int width = 800;
		int height = 800;
		int nbSquareX = 11;
		int nbSquareY = 11;	
		

		World world = new World(width, height, nbSquareX, nbSquareY, startX, startY);
		
		// Ajout d'un monstre "a la main" pour afficher comment un monstre se deplace. Vous ne devez pas faire pareil, mais ajouter une vague comportant plusieurs monstres 
		Monster monster = new BaseMonster(new Position(startX * world.squareWidth + world.squareWidth / 2, startY * world.squareHeight + world.squareHeight / 2));
		monster.nextPosition = new Position(startX * world.squareWidth + world.squareWidth / 2, 0);
		monster.speed = 0.01;
		world.monsters.add(monster);

		World w = new World(width, height, nbSquareX, nbSquareY);

		
		// Lancement de la boucle principale du jeu
		world.run();
	}
}

