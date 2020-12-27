package warcraftTD;

import java.util.List;
import java.util.LinkedList;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("unused")
public class World {
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	List<Monster> monsters = new ArrayList<Monster>();

	// Position par laquelle les monstres vont venir
	Position spawn;

	// Information sur la taille du plateau de jeu
	int width;
	int height;
	int nbSquareX;
	int nbSquareY;
	double squareWidth;
	double squareHeight;
	// [x][y]
	int[][] board;

	// Nombre de points de vie du joueur
	int life = 20;

	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;

	// Condition pour terminer la partie
	boolean end = false;

	public void wave() {
		// ex
		// Ajout d'un monstre "à la main" pour afficher comment un monstre se déplaçe.
		// Vous ne devez pas faire pareil, mais ajouter une vague comportant plusieurs
		// monstres
		Monster monster = new BaseMonster(new Position(spawn.x * this.squareWidth + this.squareWidth / 2,
				spawn.y * this.squareHeight + this.squareHeight / 2));
		monster.nextPosition = new Position(spawn.x * this.squareWidth + this.squareWidth / 2, 0);
		monster.speed = 0.01;
		this.monsters.add(monster);
	}

	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de
	 * cases donn馥s
	 * 
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startSquareX
	 * @param startSquareY
	 */
	public World(int width, int height, int nbSquareX, int nbSquareY) {
		this.width = width;
		this.height = height;
		this.nbSquareX = nbSquareX;
		this.nbSquareY = nbSquareY;
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;
		initPath();
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
	}

	/**
	 * Definit le decor du plateau de jeu.
	 */
	public void drawBackground() {
		StdDraw.setPenColor(StdDraw.GREEN);
		for (int i = 0; i < nbSquareX; i++)
			for (int j = 0; j < nbSquareY; j++)
				StdDraw.filledRectangle(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
						squareWidth, squareHeight);
		// StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight +
		// squareHeight / 2, "images/grass.jpg", squareWidth, squareHeight);
	}

	/**
	 * Initialise le chemin sur la position du point de depart des monstres. Cette
	 * fonction permet d'afficher une route qui sera differente du decor.
	 */
	public void drawPath() {
		// Position position = new Position(spawn);
		// dessin
		// Spawn
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(spawn.x, spawn.y, squareWidth / 2, squareHeight / 2);
		// Path
		StdDraw.setPenColor(StdDraw.YELLOW);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) {
					StdDraw.setPenColor(StdDraw.GREEN);
				} else if (board[i][j] == 1) {
					StdDraw.setPenColor(StdDraw.RED);
				} else if (board[i][j] == 2) {
					StdDraw.setPenColor(StdDraw.BLUE);
				} else if (board[i][j] == 3) {
					StdDraw.setPenColor(StdDraw.YELLOW);
				}
				StdDraw.filledRectangle(j * squareWidth + squareWidth / 2, i * squareHeight + squareHeight / 2, squareWidth / 2, squareHeight / 2);
			}
		}

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledRectangle(8 * 1/11 + squareWidth / 2, 7 * squareHeight + squareHeight / 2, squareWidth / 2, squareHeight / 2);
		// StdDraw.filledRectangle(0 * squareWidth + squareWidth / 2, 1 * squareHeight +
		// squareHeight / 2, squareWidth / 2, squareHeight / 2);

		// StdDraw.setPenColor(StdDraw.YELLOW);
		// StdDraw.filledRectangle(position.x, position.y, squareWidth / 2, squareHeight
		// / 2);

		// StdDraw.filledRectangle(position.x, position.y, squareWidth / 2, squareHeight
		// / 2);
		// StdDraw.filledRectangle(0.1,0.1, squareWidth / 2, squareHeight / 2);

	}

	public void initPath() {
//		0 case vide
//		1 spawn
//		2 fin
//		3 chemin
		// initialisation du plateau
		board = new int[nbSquareX][nbSquareY];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
			}
		}
		// setup du spawn
		int departX = 0 /* (int) (Math.random() * 10) */;
		int departY = 0 /* (int) (Math.random() * 10) */;
		spawn = new Position(departX * squareWidth + squareWidth / 2, departY * squareHeight + squareHeight / 2);
		board[departX][departY] = 1;
		board[nbSquareX - 1][nbSquareY - 1] = 2;

		// setup points de passage + chemin
		int precedentX = (int) spawn.x;
		int precedentY = (int) spawn.y;
		for (int i = 0; i < board.length; i = i + 2) {
			int j = 0;
			// génération nb aléatoire
			do {
				j = (int) (Math.random() * nbSquareY);
			} while (j == precedentY);
			// chemin sur les colonnes
			for (int k = precedentX; k <= i; k++) {
				if (k != precedentX) {
					board[k][precedentY] = 3;
				}
			}
			// chemin sur les lignes
			System.out.println("i " + i + "board[0].length - 2 " + (board[0].length - 2) );
			if (nbSquareY % 2 == 0 && i > board[0].length - 2) {
				for (int k = precedentY; k < board.length - 1; k++) {
					board[i][k] = 3;
				}
			} else {
				for (int k = precedentY; (precedentY < j ? k <= j : k >= j);) {
					if (k != precedentY && i < board.length - 1) {
						board[i][k] = 3;
					} else if (i >= board.length - 1) {
						// derniere ligne
						for (int l = precedentY; l < board.length - 1; l++) {
							board[board.length - 1][l] = 3;
						}
					}
					k = precedentY < j ? k + 1 : k - 1;
				}
			}
			// angle du chemin
			if (i != board.length - 1) {
				board[i][j] = 3;
			}
			precedentX = i;
			precedentY = j;
		}

	}

	/**
	 * Affiche certaines informations sur l'ecran telles que les points de vie du
	 * joueur ou son or
	 */
	public void drawInfos() {
		StdDraw.setPenColor(StdDraw.BLACK);
	}

	/**
	 * Fonction qui recupere le positionnement de la souris et permet d'afficher une
	 * image de tour en temps reel lorsque le joueur appuie sur une des touches
	 * permettant la construction d'une tour.
	 */
	public void drawMouse() {
		double normalizedX = (int) (StdDraw.mouseX() / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int) (StdDraw.mouseY() / squareHeight) * squareHeight + squareHeight / 2;
		String image = null;
		switch (key) {
		case 'a':
			// TODO Ajouter une image pour representer une tour d'archers
			break;
		case 'z':
			// TODO Ajouter une image pour representer une tour a canon
			break;
		}
		if (image != null)
			StdDraw.picture(normalizedX, normalizedY, image, squareWidth, squareHeight);
	}

	/**
	 * Pour chaque monstre de la liste de monstres de la vague, utilise la fonction
	 * update() qui appelle les fonctions run() et draw() de Monster. Modifie la
	 * position du monstre au cours du temps a l'aide du parametre nextP.
	 */
	public void updateMonsters() {

		Iterator<Monster> iterator = monsters.iterator();
		Monster monster;
		while (iterator.hasNext()) {
			monster = iterator.next();
			monster.update();
			if (monster.position.y < 0) {
				monster.position.y = 1;
			}
		}
	}

	/**
	 * Met a jour toutes les informations du plateau de jeu ainsi que les
	 * deplacements des monstres et les attaques des tours.
	 * 
	 * @return les points de vie restants du joueur
	 */
	public int update() {
		drawBackground();
		drawPath();
		drawInfos();
		updateMonsters();
		drawMouse();
		return life;
	}

	/**
	 * Recupere la touche appuyee par l'utilisateur et affiche les informations pour
	 * la touche selectionnﾃｩe
	 * 
	 * @param key la touche utilisee par le joueur
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		this.key = key;
		switch (key) {
		case 'a':
			System.out.println("Arrow Tower selected (50g).");
			break;
		case 'z':
			System.out.println("Bomb Tower selected (60g).");
			break;
		case 'e':
			System.out.println("Evolution selected (40g).");
			break;
		case 's':
			System.out.println("Starting game!");
		case 'q':
			System.out.println("Exiting.");
		}
	}

	/**
	 * Vﾃｩrifie lorsque l'utilisateur clique sur sa souris qu'il peut: - Ajouter une
	 * tour ﾃ� la position indiquﾃｩe par la souris. - Amﾃｩliorer une tour existante.
	 * Puis l'ajouter ﾃ� la liste des tours
	 * 
	 * @param x
	 * @param y
	 */
	public void mouseClick(double x, double y) {
		double normalizedX = (int) (x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int) (y / squareHeight) * squareHeight + squareHeight / 2;
		Position position = new Position(normalizedX, normalizedY);
		switch (key) {
		case 'a':
			System.out.println("il faut ajouter une tour d'archers si l'utilisateur a de l'or !!");
			break;
		case 'z':
			System.out.println("Ici il faut ajouter une tour de bombes");
			break;
		case 'e':
			System.out.println("Ici il est possible de faire evoluer une des tours");
			break;
		}
	}

	/**
	 * Comme son nom l'indique, cette fonction permet d'afficher dans le terminal
	 * les differentes possibilites offertes au joueur pour interagir avec le
	 * clavier
	 */
	public void printCommands() {
		System.out.println("Press A to select Arrow Tower (cost 50g).");
		System.out.println("Press B to select Cannon Tower (cost 60g).");
		System.out.println("Press E to update a tower (cost 40g).");
		System.out.println("Click on the grass to build it.");
		System.out.println("Press S to start.");
	}

	/**
	 * Recupere la touche entree au clavier ainsi que la position de la souris et
	 * met a jour le plateau en fonction de ces interactions
	 */
	public void run() {
		printCommands();
		while (!end) {

			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}

			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}

			update();
			StdDraw.show();
			StdDraw.pause(20);
		}
	}
}
