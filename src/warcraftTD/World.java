package warcraftTD;

import java.util.List;
import java.util.LinkedList;
import java.awt.geom.Arc2D.Float;
import java.time.chrono.ChronoZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("unused")
public class World {
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	List<Monster> monsters = new ArrayList<Monster>();
	int reserve;
	// List<Float> l = new ArrayList<Float>();
	// float f;
	// l'ensemble des points par lequels devront passer les monstres.
	List<Position> checkpoints = new ArrayList<Position>();

	// Position par laquelle les monstres vont venir
	Position spawn;

	// Information sur la taille du plateau de jeu
	int width;
	int height;
	int nbSquareX;
	int nbSquareY;
	int Wave = 0;
	Monster lastM = null;
	double squareWidth;
	double squareHeight;
	// [x][y]
	int[][] board;
	int[][] backboard;
	List<Tower> towers = new ArrayList<Tower>();

	// Nombre de points de vie du joueur
	int life = 20;
	int gold = 10000;



	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;

	// Affichage des FPS
	long startTime;
	int FPS;
	int calculFPS;

	// Condition pour terminer la partie
	boolean end = false;

	// TODO changer l'ordre des fonctions pour plus de clarté
	public void waveadd() {
		// ex
		// Ajout d'un monstre "a la main" pour afficher comment un monstre se deplaçe.
		// Vous ne devez pas faire pareil, mais ajouter une vague comportant plusieurs
		// monstres
		Monster monster = new Zerg(this, new Position(spawn.x, spawn.y));
		monster.setSpeed(0.2);
		this.monsters.add(monster);
		lastM = monster;
	}

	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de
	 * cases donnees
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
		// initialisation du plateau
		board = new int[nbSquareX][nbSquareY];
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
		initBack();
		initCheckpoints();
	}

	public void initBackground() {
		for (int i = 0; i < nbSquareX; i++) {
			for (int j = 0; j < nbSquareY; j++) {
				int aleat = (int) (Math.random() * 4);
				board[i][j] = aleat + 4;
			}
		}
	}

	/**
	 * Definit le decor du plateau de jeu.
	 */
	
	public void drawBackground() {
		String[] tabs = {"/images/Garden0.png","/images/Garden1.png", "/images/Garden2.png", "/images/Garden3.png"};
		for (int i = 0; i < nbSquareX; i++) {
			for (int j = 0; j < nbSquareY; j++) {
				if (board[i][j] >= 4) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							tabs[board[i][j]-4], squareWidth, squareHeight);
				}
			}
		}
	}
	
	public void initBack() {
		initBackground();
		initPath();
		drawPath();
		drawBackground();

		StdDraw.show();
		StdDraw.save("./src/images/save.png");
		StdDraw.clear();
	}
	
	public void drawBack() {
		StdDraw.picture(0.5,0.5, "./src/images/save.png",1,1);
	}

	/**
	 * Initialise le chemin sur la position du point de depart des monstres. Cette
	 * fonction permet d'afficher une route qui sera differente du decor.
	 */
	public void drawPath() {
		String[] tabs2 = {"/images/Path1.png","/images/Path2.png", "/images/Path3.png"};
		// dessin de Board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] > 0 && board[i][j] <= 3) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							tabs2[board[i][j]-1], squareWidth, squareHeight);
				}
			}
		}
	}

	public void drawTower() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 10) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/Archer1.jpg", squareWidth, squareHeight);

				} else if (board[i][j] == 100) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/Archer2.jpg", squareWidth, squareHeight);

				} else if (board[i][j] == 20) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/Bombe1.jpg", squareWidth, squareHeight);

				} else if (board[i][j] == 200) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/Bombe2.jpg", squareWidth, squareHeight);
				}
			}
		}
	}

	public void initPath() {
//		0 case vide
//		1 spawn
//		2 fin
//		3 chemin
		// setup du spawn
		int departX = 0;
		int departY = 0;
		spawn = new Position(departX * squareWidth + squareWidth / 2, departY * squareHeight + squareHeight / 2);
		// setup points de passage + chemin
		// ancien point de passage
		int precedentX = departX;
		int precedentY = departY;
		for (int coordY = 0; coordY < board[0].length; coordY = coordY + 2) {
			int aleatX = 0;
			// génération nb aléatoire (pas 2 fois le meme d'affilé
			do {
				// premiere ligne jusqu'au bout pour faire des U partout
				if (coordY == 0) {
					aleatX = nbSquareX - 1;
				} else {
					aleatX = (int) (Math.random() * nbSquareX);
				}
			} while (aleatX == precedentX);

			// remplissage de board sur le chemin en colonnes
			// parcours vertical pour completer les colonnes du chemin
			for (int k = precedentY + 1; k <= coordY; k++) {
				board[precedentX][k] = 3;
			}
			// cas derniere ligne si paire
			if (nbSquareY % 2 == 0 && coordY >= board[0].length - 2) {
				for (int lastX = precedentX; lastX < board.length; lastX++) {
					board[lastX][board[0].length - 1] = 3;
				}
			}
			// remplissage de board sur le chemin en ligne
			// parcours horizontal
			for (int k = precedentX; (precedentX < aleatX ? k <= aleatX : k >= aleatX);) {
				// cas commun (sauf fin)
				if (k < board.length && coordY < board[0].length - 2) {
					board[k][coordY] = 3;
					// derniere ligne
				} else if (nbSquareY % 2 == 1 && coordY == board[0].length - 1) {
					for (int X = precedentX; X < board.length - 1; X++) {
						board[X][board[0].length - 1] = 3;
					}
				}
				k = precedentX < aleatX ? k + 1 : k - 1;
			}
			// angle du chemin
			if (coordY < board[0].length - 2) {
				board[aleatX][coordY] = 3;
			}
			precedentX = aleatX;
			precedentY = coordY;
		}
		board[departX][departY] = 1;
		board[nbSquareX - 1][nbSquareY - 1] = 2;
		for (int Y = 0; Y < board[0].length - 2; Y++) {
			for (int X = 0; X < board.length - 2; X++) {
				// fait un U si pas de chemin au dessus:
				// X-0-0-0-X ///X-0-0-0-X
				// 0-0-0-0-0 => 0-3-3-3-X
				// X-P-3-3-X ///X-3-0-3-X
				if (board[X][Y] == 3 && board[X + 1][Y] == 3 && board[X + 2][Y] == 3 && board[X][Y + 1] != 3
						&& board[X + 1][Y + 1] != 3 && board[X + 2][Y + 1] != 3 && board[X][Y + 2] != 3
						&& board[X + 1][Y + 2] != 3 && board[X + 2][Y + 2] != 3 && (X <= 0 || board[X - 1][Y + 1] != 3)
						&& (X >= board.length - 3 || board[X + 3][Y + 1] != 3)) {
					board[X + 1][Y] = (int) (Math.random() * 4) + 4;
					board[X][Y + 1] = 3;
					board[X + 1][Y + 1] = 3;
					board[X + 2][Y + 1] = 3;
				}
			}
		}
	}

	public void initCheckpoints() {
		int i = 0; // X
		int j = 0; // Y
		int Lastdirection = 0; // 0 : -> // 1 : Down // 2 : <- // 3 : UP
		while (board[i][j] != 2) {
			switch (Lastdirection) {
			case 0: // ->
				// non continue?
				if (i + 1 == board.length || (board[i + 1][j] != 3 && board[i + 1][j] != 2)) {
					checkpoints.add(new Position((double) (i) / nbSquareX + squareWidth / 2,
							(double) (j) / nbSquareY + squareHeight / 2));
					// non Up?
					if (j + 1 == board[i].length || board[i][j + 1] != 3) {
						j--;
						Lastdirection = 1;
					} else {
						// UP
						j++;
						Lastdirection = 3;
					}
				} else {
					// continue
					i++;
				}
				break;
			case 1:// Down
					// non continue?
				if (j == 0 || board[i][j - 1] != 3) {
					checkpoints.add(new Position((double) (i) / nbSquareX + squareWidth / 2,
							(double) (j) / nbSquareY + squareHeight / 2));
					// non ->?
					if (i + 1 == board.length || board[i + 1][j] != 3) {
						i--;
						Lastdirection = 2;
					} else {
						// ->
						i++;
						Lastdirection = 0;
					}
				} else {
					// continue
					j--;
				}
				break;
			case 2:// <-
					// non continue?
				if (i == 0 || board[i - 1][j] != 3) {
					checkpoints.add(new Position((double) (i) / nbSquareX + squareWidth / 2,
							(double) (j) / nbSquareY + squareHeight / 2));
					// non Up?
					if (j + 1 == board[i].length || board[i][j + 1] != 3) {
						j--;
						Lastdirection = 1;
					} else {
						// UP
						j++;
						Lastdirection = 3;
					}
				} else {
					// continue
					i--;
				}
				break;
			case 3:// UP
					// non continue?
				if (j + 1 == board[i].length || board[i][j + 1] != 3) {
					checkpoints.add(new Position((double) (i) / nbSquareX + squareWidth / 2,
							(double) (j) / nbSquareY + squareHeight / 2));
					// non ->?
					if (i + 1 == board.length || (board[i + 1][j] != 3 && board[i + 1][j] != 2)) {
						i--;
						Lastdirection = 2;
					} else {
						// ->
						i++;
						Lastdirection = 0;
					}
				} else {
					// continue
					j++;
				}
				break;
			}
		}
		checkpoints.add(new Position((double) (board.length - 0.5) * squareWidth,
				(double) (board[0].length - 0.5) * squareHeight));
	}

	/**
	 * Affiche certaines informations sur l'ecran telles que les points de vie du
	 * joueur ou son or
	 */
	public void drawInfos() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.06, 0.98, "gold :" + gold);
		StdDraw.text(0.04, 0.96, "life :" + life);
	}

	public void calculFPS() {
		calculFPS++;
		if (System.currentTimeMillis() - startTime >= 1000) {
			FPS = calculFPS;
			startTime = System.currentTimeMillis();
			calculFPS = 0;
		}
		StdDraw.text(0.04, 0.94, "FPS :" + FPS);
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
			StdDraw.picture(normalizedX, normalizedY, "/images/Archer1.jpg", squareWidth, squareHeight);
			break;
		case 'b':
			// TODO Ajouter une image pour representer une tour a canon
			StdDraw.picture(normalizedX, normalizedY, "/images/Bombe1.jpg", squareWidth, squareHeight);
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
			if (monster.reached) {
				life--;
			}
			if (monster.hp <= 0)
				gold += monster.goldValue;
		}
		monsters.removeIf(x -> (x.reached));
		monsters.removeIf(x -> (x.hp <= 0));
	}

	private void updateWave() {
		if (monsters.size() == 0 && reserve == 0) {
			Wave++;
			reserve = Wave;
		}
		// TODO changer le spawn du monstre pour un spawn to les X ticks
		if ((lastM == null || Math.sqrt(
				Math.pow(lastM.position.x + spawn.x, 2) + Math.pow(lastM.position.y + spawn.y, 2)) >= squareWidth * 2)
				&& reserve > 0) {
			reserve--;
			// monsters.add(new Zerg(this, spawn));
			waveadd();
		}
	}

	/**
	 * Met a jour toutes les informations du plateau de jeu ainsi que les
	 * deplacements des monstres et les attaques des tours.
	 * 
	 * @return les points de vie restants du joueur
	 */
	public int update() {
//		drawBackground();
//		drawPath();
		drawBack();
		drawTower();
		drawInfos();
		calculFPS();
		updateMonsters();
		updateWave();
		drawMouse();
		// tir();
		return life;
	}

	public int myCasex(double p) {
		return (int) (p * nbSquareX);
	}

	public int myCasey(double p) {
		return (int) (p * nbSquareY);
	}

	public double posCasex(int n) {
		return (double) (n * squareWidth) + squareWidth / 2;
	}

	public double posCasey(int n) {
		return (double) (n * squareHeight) + squareHeight / 2;
	}

	/**
	 * Recupere la touche appuyee par l'utilisateur et affiche les informations pour
	 * la touche selectionnee
	 * 
	 * @param key la touche utilisee par le joueur
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		this.key = key;
		// StdDraw.pause(2000);
		switch (key) {
		case 'a':
			System.out.println("Arrow Tower selected (" + ArcherTower.buildCost + ".)");
			break;
		case 'b':
			System.out.println("Bomb Tower selected (" + BombTower.buildCost + ".)");
			break;
		case 'e':
			System.out.println("Evolution selected (" + ArcherTower.upgradeCost + ".)");
			break;
		case 's':
			System.out.println("Starting game!");
			break;
		case 'q':
			end = true;
		}
	}

	/**
	 * Verifie lorsque l'utilisateur clique sur sa souris qu'il peut: - Ajouter une
	 * tour a la position indiquee par la souris. - Ameliorer une tour existante.
	 * Puis l'ajouter a la liste des tours
	 * 
	 * @param x
	 * @param y
	 */
	public void mouseClick(double x, double y) {
		double normalizedX = (int) (x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int) (y / squareHeight) * squareHeight + squareHeight / 2;
		Position position = new Position(normalizedX, normalizedY);
		int X = myCasex(normalizedX);
		int Y = myCasey(normalizedY);
		switch (key) {
		case 'a':
			if (board[X][Y] > 3 && board[X][Y] < 10  && (gold > ArcherTower.buildCost)) {
				gold -= ArcherTower.buildCost;
				board[X][Y] = 10;
				towers.add(new ArcherTower(position));
			}
			break;
		case 'b':
			if (board[X][Y] > 3 && board[X][Y] < 10 && (gold > BombTower.buildCost)) {
				gold -= BombTower.buildCost;
				board[X][Y] = 20;
				towers.add(new BombTower(position));
			}
			break;
		case 'e':
			for(Tower t : towers) {
				if(t.position.equals(position) && gold >= t.upgradeCost && !t.upgraded ) {
					t.upgrade();
					gold -= t.upgradeCost;
				}
			}
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
		System.out.println("Press Q to quit.");
	}

	public void tir() {
		Iterator<Monster> iterator = monsters.iterator();
		Monster monster;
		Position position;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (towers[i][j] != null) {
					towers[i][j].attackDelay ++;
					position = new Position(posCasex(i), posCasey(j));
					while (iterator.hasNext()) {
						monster = iterator.next();
						if(towers[i][j].tir == false && towers[i][j].attackSpeed == towers[i][j].attackDelay && monster.position.dist(position) <= towers[i][j].range * squareHeight) {
							towers[i][j].tir(monster);
							System.out.println(monster.hp);
							towers[i][j].tir = true;
							towers[i][j].attackDelay = 0;
						}
						towers[i][j].tir = false;
					}

				}
			}
		}
	}

	/**
	 * Recupere la touche entree au clavier ainsi que la position de la souris et
	 * met a jour le plateau en fonction de ces interactions
	 */
	public void run() {
		printCommands();
		startTime = System.currentTimeMillis();
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

			//StdDraw.pause(20);


			if (life <= 0)
				end = true;
		}
	}
}
