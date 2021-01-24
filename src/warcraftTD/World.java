package warcraftTD;

import java.util.List;
import java.util.ArrayList;
import java.awt.Font;

public class World {
	// l ensemble des monstres, pour gerer (notamment) l affichage
	List<Monster> monsters;
	// l ensemble des points par lequels devront passer les monstres.
	List<Position> checkpoints;
	// l ensemble des tours, pour gerer l update des tours
	List<Tower> towers;
	// l ensemble des projectiles
	List<Projectile> projectiles;

	// Position par laquelle les monstres vont venir
	Position spawn;

	// Information sur la taille du plateau de jeu
	private int width;
	private int height;
	private int nbSquareX;
	private int nbSquareY;
	private double squareWidth;
	private double squareHeight;

	// Plateau permettant l initialisation du terrain et de savoir ou l on peut
	// placer une tour
	// [x][y]
	private int[][] board;

	// Nombre de points de vie du joueur
	private int life;

	// Argent du joueur
	private int gold;

	private int wave;// Vague du joueur, permet d adapter les monstres envoyes
	private int reserve;// Nombre de monstres a envoyer avant la prochaine vague
	private int spawnTime;// Temps entre 2 spawn de monstre

	// Commande sur laquelle le joueur appuie (sur le clavier)
	private char key;

	// Affichage des FPS
	// Aucune triche possible en modifiant ces valeurs => public
	public long startTimeFPS;
	public int fPS;
	public int calculFPS;

	// Calcul temps entre 2 monstres
	private long startTimeMonster;
	private long globalStart;

	// temps d affichage de la nouvelle vague
	private long newWaveTime;

	// Police d affichage
	Font starting = new Font("Arial", Font.BOLD, 20);
	Font infos = new Font("Arial", Font.PLAIN, 12);
	Font fontwave = new Font("Calibri", Font.BOLD, 50);

	// Condition pour terminer la partie
	private boolean end = false;
	private boolean start = false;

	public List<Monster> getMonsters() {
		return monsters;
	}

	public void setMonsters(List<Monster> monsters) {
		this.monsters = monsters;
	}

	public List<Position> getCheckpoints() {
		return checkpoints;
	}

	public void setCheckpoints(List<Position> checkpoints) {
		this.checkpoints = checkpoints;
	}

	public List<Tower> getTowers() {
		return towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(List<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	public Position getSpawn() {
		return spawn;
	}

	public void setSpawn(Position spawn) {
		this.spawn = spawn;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getNbSquareX() {
		return nbSquareX;
	}

	public void setNbSquareX(int nbSquareX) {
		this.nbSquareX = nbSquareX;
	}

	public int getNbSquareY() {
		return nbSquareY;
	}

	public void setNbSquareY(int nbSquareY) {
		this.nbSquareY = nbSquareY;
	}

	public double getSquareWidth() {
		return squareWidth;
	}

	public void setSquareWidth(double squareWidth) {
		this.squareWidth = squareWidth;
	}

	public double getSquareHeight() {
		return squareHeight;
	}

	public void setSquareHeight(double squareHeight) {
		this.squareHeight = squareHeight;
	}

	public int[][] getBoard() {
		return board;
	}

	public void setBoard(int[][] board) {
		this.board = board;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getWave() {
		return wave;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

	public int getReserve() {
		return reserve;
	}

	public void setReserve(int reserve) {
		this.reserve = reserve;
	}

	public int getSpawnTime() {
		return spawnTime;
	}

	public void setSpawnTime(int spawnTime) {
		this.spawnTime = spawnTime;
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public long getStartTimeFPS() {
		return startTimeFPS;
	}

	public void setStartTimeFPS(long startTimeFPS) {
		this.startTimeFPS = startTimeFPS;
	}

	public int getfPS() {
		return fPS;
	}

	public void setfPS(int fPS) {
		this.fPS = fPS;
	}

	public int getCalculFPS() {
		return calculFPS;
	}

	public void setCalculFPS(int calculFPS) {
		this.calculFPS = calculFPS;
	}

	public long getStartTimeMonster() {
		return startTimeMonster;
	}

	public void setStartTimeMonster(long startTimeMonster) {
		this.startTimeMonster = startTimeMonster;
	}

	public long getGlobalStart() {
		return globalStart;
	}

	public void setGlobalStart(long globalStart) {
		this.globalStart = globalStart;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	/**
	 * Initialisation du monde en fonction des parametres Initialisation de toutes
	 * les variables du monde Creation de l image de fond et des checkpoints
	 * 
	 * @param width     largeur de la fenetre du monde
	 * @param height    hauteur de la fenetre du monde
	 * @param nbSquareX nombre de case dans la largeur du monde
	 * @param nbSquareY nombre de case dans la hauteur du monde
	 */
	public World(int width, int height, int nbSquareX, int nbSquareY) {
		this.width = width;
		this.height = height;
		this.nbSquareX = nbSquareX;
		this.nbSquareY = nbSquareY;
		this.squareWidth = (double) 1 / nbSquareX;
		this.squareHeight = (double) 1 / nbSquareY;
		this.gold = 100;
		this.life = 20;
		this.wave = 0;
		this.spawnTime = 1000;
		// initialisation du plateau
		this.board = new int[nbSquareX][nbSquareY];
		this.towers = new ArrayList<Tower>();
		this.monsters = new ArrayList<Monster>();
		this.checkpoints = new ArrayList<Position>();
		this.projectiles = new ArrayList<Projectile>();
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
		initImageFond();
		initCheckpoints();
	}

	/**
	 * remplit le tableau du monde (board) avec des cases de verdures [4;7]
	 */
	public void initBackground() {
		for (int i = 0; i < nbSquareX; i++) {
			for (int j = 0; j < nbSquareY; j++) {
				int aleat = (int) (Math.random() * 4);
				board[i][j] = aleat + 4;
			}
		}
	}

	/**
	 * Fabrique un chemin aleatoire dans board et le complexifie avec des U inversé
	 */
	public void initPath() {
//		1 spawn
//		2 fin
//		3 chemin
		// difinition du spawn
		int departX = 0;
		int departY = 0;
		spawn = new Position(departX * squareWidth + squareWidth / 2, departY * squareHeight + squareHeight / 2);
// positionnement des angles du chemin et reliage de ces points entre eux
		// ancien point de passage
		int precedentX = departX;
		int precedentY = departY;
		for (int coordY = 0; coordY < nbSquareY; coordY = coordY + 2) {
			int aleatX = 0;
			// génération nb aléatoire (pas 2 fois le meme d'affile)
			do {
				// La premiere ligne va jusqu'au bout pour maximiser le nombre de U dans la 2nd
				// partie du programme
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
			if (nbSquareY % 2 == 0 && coordY >= nbSquareY - 2) {
				for (int lastX = precedentX; lastX < nbSquareX; lastX++) {
					board[lastX][nbSquareY - 1] = 3;
				}
			}
			// remplissage de board sur le chemin en ligne
			// parcours horizontal
			for (int k = precedentX; (precedentX < aleatX ? k <= aleatX : k >= aleatX);) {
				// cas commun (sauf fin)
				if (k < nbSquareX && coordY < nbSquareY - 2) {
					board[k][coordY] = 3;
					// derniere ligne
				} else if (nbSquareY % 2 == 1 && coordY == nbSquareY - 1) {
					for (int X = precedentX; X < nbSquareX - 1; X++) {
						board[X][nbSquareY - 1] = 3;
					}
				}
				k = precedentX < aleatX ? k + 1 : k - 1;
			}
			// angle du chemin (points de passage)
			if (coordY < nbSquareY - 2) {
				board[aleatX][coordY] = 3;
			}
			precedentX = aleatX;
			precedentY = coordY;
		}
//Creation des U inverse
		board[departX][departY] = 1;
		board[nbSquareX - 1][nbSquareY - 1] = 2;
		for (int Y = 0; Y < nbSquareY - 2; Y++) {
			for (int X = 0; X < nbSquareX - 2; X++) {
				// fait un U inverse si pas de chemin au dessus:
				// X-0-0-0-X ///X-0-0-0-X
				// 0-0-0-0-0 => 0-3-3-3-X
				// X-3-3-3-X ///X-3-0-3-X
				// schema de la condition si dessous
				if (board[X][Y] == 3 && board[X + 1][Y] == 3 && board[X + 2][Y] == 3 && board[X][Y + 1] != 3
						&& board[X + 1][Y + 1] != 3 && board[X + 2][Y + 1] != 3 && board[X][Y + 2] != 3
						&& board[X + 1][Y + 2] != 3 && board[X + 2][Y + 2] != 3 && (X <= 0 || board[X - 1][Y + 1] != 3)
						&& (X >= nbSquareX - 3 || board[X + 3][Y + 1] != 3)) {
					board[X + 1][Y] = (int) (Math.random() * 4) + 4;
					board[X][Y + 1] = 3;
					board[X + 1][Y + 1] = 3;
					board[X + 2][Y + 1] = 3;
				}
			}
		}
	}

	/**
	 * Dessine le plateau de jeu. @1 = depart @2 = arrivee @3 = chemin @4 =
	 * garden1 @5 = garden2 @6 = garden3 @7 = garden4 En vue d en faire une photo
	 */
	public void drawBackground() {
		String[] tabs = { "/images/Depart.png", "/images/Arrivee.png", "/images/Chemin.png", "/images/Gazon_baies.png",
				"/images/Gazon_blue_red.png", "/images/Gazon_weed.png", "/images/Gazon_jaune.png" };
		for (int i = 0; i < nbSquareX; i++) {
			for (int j = 0; j < nbSquareY; j++) {
				StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
						tabs[board[i][j] - 1], squareWidth, squareHeight);
			}
		}
	}

	/**
	 * Appelle les fonctions definient si dessus pour fabriquer l'image de fond
	 * ImageFond.png
	 */
	public void initImageFond() {
		initBackground();
		initPath();
		drawBackground();

		StdDraw.show();
		StdDraw.save("./src/images/ImageFond.png");
		StdDraw.clear();
	}

	/*
	 * Fonction qui affiche l'image de fond, sans les tours ni les monstres,
	 * uniquement avec le chemin, le départ et l'arrivée
	 */
	public void drawImageFond() {
		StdDraw.picture(0.5, 0.5, "./src/images/ImageFond.png", 1, 1);
	}

	/*
	 * Fonction qui affiche les tours par dessus l'image de fond
	 */
	public void drawTower() {
		for (int i = 0; i < nbSquareX; i++) {
			for (int j = 0; j < nbSquareY; j++) {
				if (board[i][j] == 10) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/Archer1.png", squareWidth, squareHeight);

				} else if (board[i][j] == 100) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/Archer2.png", squareWidth, squareHeight);

				} else if (board[i][j] == 20) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/TourBombe1.png", squareWidth, squareHeight);

				} else if (board[i][j] == 200) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/TourBombe2.png", squareWidth, squareHeight);

				} else if (board[i][j] == 30) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/RocketLauncher.png", squareWidth, squareHeight);

				} else if (board[i][j] == 300) {
					StdDraw.picture(i * squareWidth + squareWidth / 2, j * squareHeight + squareHeight / 2,
							"/images/RocketLauncher_UP.png", squareWidth, squareHeight);
				}
			}
		}
	}

	/**
	 * Fonction qui parcourt le chemin en entier (de case en case) et remplit la
	 * liste "checkpoints" avec les positions par lesquelles devront passer les
	 * monstres. En utilisant un chemin VALIDE de [0][0] jusqu'à
	 * [nbSquareX][nbSquareY] stocké dans board
	 */
	public void initCheckpoints() {
		// Coordonée où en est le parcours du chemin
		int i = 0; // X
		int j = 0; // Y
		// La derniere direction prise par le parcours du chemin
		int Lastdirection = 0; // 0 : -> // 1 : Down // 2 : <- // 3 : UP
		// Tant que le parcours n'a pas trouvé la fin du chemin
		while (board[i][j] != 2) {
			// Determine si l'on peut continuer tout droit et sinon où il faut tourner
			switch (Lastdirection) {
			case 0: // ->
				// non continue?
				if (i + 1 == nbSquareX || (board[i + 1][j] != 3 && board[i + 1][j] != 2)) {
					checkpoints.add(new Position((double) (i) / nbSquareX + squareWidth / 2,
							(double) (j) / nbSquareY + squareHeight / 2));
					// Up?
					if (j + 1 == nbSquareY || board[i][j + 1] != 3) {
						// Down
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
					// ->?
					if (i + 1 == nbSquareX || board[i + 1][j] != 3) {
						// <-
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
					// Up?
					if (j + 1 == nbSquareY || board[i][j + 1] != 3) {
						// Down
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
				if (j + 1 == nbSquareY || board[i][j + 1] != 3 && board[i][j + 1] != 2) {
					checkpoints.add(new Position((double) (i) / nbSquareX + squareWidth / 2,
							(double) (j) / nbSquareY + squareHeight / 2));
					// ->?
					if (i + 1 == nbSquareX || (board[i + 1][j] != 3 && board[i + 1][j] != 2)) {
						// <-
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
		// Rajoute le checkpoint de l'arrivé
		checkpoints
				.add(new Position((double) (nbSquareX - 0.5) * squareWidth, (double) (nbSquareY - 0.5) * squareHeight));
	}

	/**
	 * Affiche certaines informations sur l'ecran telles que les points de vie du
	 * joueur ou son or
	 */
	public void drawInfos() {
		calculFPS();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.setFont(infos);
		StdDraw.text(0.04, 0.98, "gold :" + gold);
		StdDraw.text(0.04, 0.96, "life :" + life);
		StdDraw.text(0.04, 0.94, "FPS :" + fPS);
		StdDraw.text(0.04, 0.92, "Wave :" + wave);
	}

	/*
	 * Fonction qui permet de calculer le nombre d'images par secondes en ajoutant 1
	 * à une variable à chaque tour de boucle et chaque image affichée, puis qui
	 * affiche ce nombre au bout d'une seconde
	 */
	public void calculFPS() {
		calculFPS++;
		if (System.currentTimeMillis() - startTimeFPS >= 1000) {
			fPS = calculFPS;
			startTimeFPS = System.currentTimeMillis();
			calculFPS = 0;
		}
	}

	/**
	 * Fonction qui recupere le positionnement de la souris et permet d'afficher une
	 * image de tour en temps reel lorsque le joueur appuie sur une des touches
	 * permettant la construction d'une tour.
	 */
	public void drawMouse() {
		double normalizedX = (int) (StdDraw.mouseX() / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int) (StdDraw.mouseY() / squareHeight) * squareHeight + squareHeight / 2;
		switch (key) {
		case 'a':
			StdDraw.picture(normalizedX, normalizedY, "/images/Archer1.png", squareWidth, squareHeight);
			break;
		case 'b':
			StdDraw.picture(normalizedX, normalizedY, "/images/TourBombe1.png", squareWidth, squareHeight);
			break;
		case 'r':
			StdDraw.picture(normalizedX, normalizedY, "/images/RocketLauncher.png", squareWidth, squareHeight);
			break;
		case 'e':
			StdDraw.picture(StdDraw.mouseX(), StdDraw.mouseY(), "/images/Hammer.png", squareWidth / 3,
					squareHeight / 3);
			break;
		}
	}

	/**
	 * Pour chaque monstre de la liste "monstres", utilise la fonction update() qui
	 * appelle les fonctions run() et draw() de la class Monster. 
	 * Retire les monstres arrives ou morts 
	 */
	public void updateMonsters() {
		for (Monster monster : monsters) {
			monster.move();
			if (monster.reached) {
				life--;
				if (monster instanceof Boss) {
					life = life - 2;
				}
			}
			if (monster.hp <= 0)
				gold += monster.goldValue;
		}
		monsters.removeIf(x -> (x.reached));
		monsters.removeIf(x -> (x.hp <= 0));
		for (Monster monster : monsters) {
			monster.draw();
		}
	}

	/*
	 * Fonction qui permet de changer de vague lorsqu'il n'y a plus de monstres sur
	 * le plateau et plus de monstres à envoyer
	 */
	private void updateWave() {
		if (monsters.size() == 0 && reserve <= 0) {
			wave++;
			reserve = wave;
			projectiles = new ArrayList<Projectile>();
			newWaveTime = timer();
		}
		if (timer() - newWaveTime < 2) {
			StdDraw.setFont(fontwave);
			StdDraw.setPenColor(StdDraw.DARK_GRAY);
			StdDraw.text(0.5, 0.5, "Wave " + wave + " :");
		}
		if (reserve > 0 && System.currentTimeMillis() - startTimeMonster >= (spawnTime / wave + 200)) {
			startTimeMonster = System.currentTimeMillis();
			waveadd();
		}
	}

	/*
	 * Pour chaque projectiles de la liste "monstres", utilise la fonction update() qui
	 * appelle les fonctions run() et draw() de la class Monster. 
	 * Retire les monstres arrives ou morts 
	 */
	private void updateProjectiles() {
		for (Projectile p : projectiles) {
			p.update();
		}
		projectiles.removeIf(x -> (x.out));
		projectiles.removeIf(x -> (x.reached));
	}

	private void updateTowers() {
		drawTower();
		tir();
	}

	/*
	 * Fonction qui ajoute les monstres correspondant à la vague sur le plateau, au
	 * niveau du spawn
	 */
	public void waveadd() {
		Monster monster;
		if (wave % 10 == 0) {
			monster = new Boss(this, new Position(spawn.x, spawn.y));
			reserve -= 10;
		} else if (wave % 5 == 0) {
			monster = new Dragon(this, new Position(spawn.x, spawn.y));
			reserve = reserve - 2;
		} else {
			monster = new Car(this, new Position(spawn.x, spawn.y));
			reserve--;
		}
		this.monsters.add(monster);
	}

	/**
	 * Met a jour toutes les informations du plateau de jeu ainsi que les
	 * deplacements des monstres et les attaques des tours.
	 * 
	 * @return les points de vie restants du joueur
	 */
	public int update() {
		drawImageFond();
		drawInfos();
		if (start) {
			updateMonsters();
			updateWave();
		} else {
			StdDraw.setFont(starting);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(0.5, 0.52, "Vous pouvez commencer à placer vos tours");
			StdDraw.text(0.5, 0.48, "Appuyer sur S pour lancer la wave 1 :");
		}
		updateTowers();
		updateProjectiles();
		drawMouse();
		tir();
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
		case 'w':
			wave++;
			break;
		case 'k':
			for (Monster m : monsters) {
				m.hp = 0;
			}
			break;
		case 'g':
			gold += 1000;
			break;
		case 'b':
			System.out.println("Bomb Tower selected (" + BombTower.buildCost + ".)");
			break;
		case 'r':
			System.out.println("Rocket Launcher selected (" + RocketLauncher.buildCost + ".)");
			break;
		case 'e':
			System.out.println("Evolution selected (" + ArcherTower.upgradeCost + ".)");
			break;
		case 's':
			System.out.println("Starting game!");
			start = true;
			break;
		case 'q':
			System.out.println("Game ended");
			end = true;
			break;
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
			if (board[X][Y] > 3 && board[X][Y] < 10 && (gold >= ArcherTower.buildCost)) {
				gold -= ArcherTower.buildCost;
				board[X][Y] = 10;
				towers.add(new ArcherTower(this, position));
				key = 'm';
			}
			break;
		case 'b':
			if (board[X][Y] > 3 && board[X][Y] < 10 && (gold >= BombTower.buildCost)) {
				gold -= BombTower.buildCost;
				board[X][Y] = 20;
				towers.add(new BombTower(this, position));
				key = 'm';
			}
			break;
		case 'r':
			if (board[X][Y] > 3 && board[X][Y] < 10 && (gold >= RocketLauncher.buildCost)) {
				gold -= RocketLauncher.buildCost;
				board[X][Y] = 30;
				towers.add(new RocketLauncher(this, position));
				key = 'm';
			}
			break;
		case 'e':
			for (Tower t : towers) {
				if (t.position.equals(position) && gold >= Tower.upgradeCost && !t.upgraded) {
					t.upgrade();
					gold -= Tower.upgradeCost;
					board[X][Y] = board[X][Y] * 10;
					key = 'm';
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
		System.out.println("Press R to select Rocket Tower (cost 60g).");
		System.out.println("Press E to update a tower (cost 40g).");
		System.out.println("Click on the grass to build it.");
		System.out.println("Press S to start.");
		System.out.println("Press Q to quit.");
	}

	/*
	 * Fonction qui regarde la liste des tours, puis celle des monstres pour
	 * déterminer si les tours ont un monstre à portée et si elles peuvent tirer.
	 * Elle appelle ensuite la fonction tir() propre à chaque tour, qui contrôle
	 * l'animation du tir
	 */
	public void tir() {
		for (Tower t : towers) {
			for (Monster m : monsters) {
				if ((t.targetFlying || !m.flying)
						&& t.position.dist(m.position) < (double) (t.range) / (double) (nbSquareX)) {
					t.tir(m);
					break;
				}
			}
		}

	}

	public long timer() {
		return (System.currentTimeMillis() - globalStart) / 1000;
	}

	/**
	 * Recupere la touche entree au clavier ainsi que la position de la souris et
	 * met a jour le plateau en fonction de ces interactions
	 */
	public void run() {
		printCommands();
		startTimeFPS = System.currentTimeMillis();
		startTimeMonster = System.currentTimeMillis();
		globalStart = System.currentTimeMillis();
		while (!start) {
			drawImageFond();
			StdDraw.setFont(starting);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(0.5, 0.52, "Bienvenue sur Tower Defense !");
			StdDraw.text(0.5, 0.48, "Appuyez sur S pour commencer la partie");
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
			StdDraw.show();
		}
		start = false;
		while (!end) {

			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}

			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
			}
			update();
			StdDraw.show();

			if (life <= 0)
				end = true;
		}
	}
}
