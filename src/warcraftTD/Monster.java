package warcraftTD;

public abstract class Monster {
	// Position du monstre a l'instant t
	Position position;
	// Vitesse du monstre
	private double speed;
	// Points de vie du monstre
	int hp = 20;
	// Valeur en gold du monstre une fois tué
	int goldValue;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
//		this.nextPosition = new Position(
//				position.x + this.world.squareWidth * this.speed/* + world.squareWidth*speed */, position.y);
	}

	// Position du monstre a l'instant t+1
	Position nextPosition;
	// Boolean pour savoir si le monstre a atteint le chateau du joueur
	boolean reached;

	// Compteur de deplacement pour savoir si le monstre a atteint le chateau du
	// joueur
	int checkpoint = 0;

	protected World world;

	public Monster(World w, Position startp) {
		this.position = startp;
		world = w;
		this.nextPosition = new Position(startp.x + this.world.squareWidth * speed/* + world.squareWidth*speed */,
				startp.y);
	}

	/**
	 * Deplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de
	 * sa prochaine position.
	 */
	public void move() {
		// TXT
//		prend le checkpoint qui lui correspond dans la liste avec la variable checkpoint
//		prend la position suivante
//		calcule le vecteur entre "position" et "obj"
//	PB	NORMER LE VECTEUR? TODO
//		Si le vecteur mouvement est superieur alors, TP sur checkpoint et ++
//		si plus de checkpoints arrivé
		if (checkpoint < world.checkpoints.size()) {
			Position obj = world.checkpoints.get(checkpoint);
			position = nextPosition;
			Position dist = obj.add(new Position(-position.x, -position.y));
			double norme = new Position(0, 0).dist(dist);
			System.out.println(norme);
			System.out.println(dist.x / norme);
			dist = new Position(speed * dist.x / (norme * (double) world.nbSquareX * 3.0),
					speed * dist.y / (norme * (double) world.nbSquareY * 3.0));
//			if (obj.equals(position)) {
//				System.out.print("[pb]");
//				obj = world.checkpoints.get(checkpoint++);
//			}

//			StdDraw.setPenColor(StdDraw.RED);
//			StdDraw.filledCircle(obj.x, obj.y, 0.01);
//			StdDraw.show();

//	PB		double norme = /*vec/*/new Position(obj.x*world.squareWidth,obj.y*world.squareHeight).dist(new Position(position.x*world.nbSquareX,position.y*world.nbSquareY)); 
			// TODO
			// Position vecNorme = new Position(vec.x/ norme, vec.y/ norme);
//			System.out.print(norme);
//			System.out.println(vecNorme);
			// System.out.println(world.squareWidth + " " + world.squareHeight);

//Si vec trop grand
			if (position.dist(obj) <= world.squareWidth && position.dist(obj) <= world.squareHeight) {
				nextPosition = obj;
				checkpoint++;

			} else {
				nextPosition = position.add(dist);
				nextPosition = position.add(new Position(dist.x / 4, dist.y / 4));
			}
		} else {
			// TODO Arrive
			reached = true;
		}
	}

	public void update() {
		move();
		draw();
		if (this.hp == 0) {
			this.world.gold += 20;
		}
	}

	/**
	 * Fonction abstraite qui sera instanciee dans les classes filles pour afficher
	 * le monstre sur le plateau de jeu.
	 */
	public abstract void draw();
}
