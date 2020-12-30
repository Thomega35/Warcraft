package warcraftTD;

public abstract class Monster {
	// Position du monstre a l'instant t
	Position position;
	// Vitesse du monstre
	double speed;
	// Position du monstre a l'instant t+1
	Position nextPosition;
	// Boolean pour savoir si le monstre a atteint le chateau du joueur
	boolean reached;
	// Compteur de deplacement pour savoir si le monstre a atteint le chateau du joueur
	//USELESS DUDE
	int checkpoint = 0;
	//Vitesse du mob
	int vitesse = 3;
	
	protected World world;
	
	public Monster(World w, Position startp) {
		this.position = startp;
		world = w;
		this.nextPosition = new Position(startp.x + this.world.squareWidth / 2/*+ world.squareWidth*speed*/,startp.y);
	}
	
	/**
	 * Deplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le monstre se dirige.
		double dx = nextPosition.x - position.x;
		double dy = nextPosition.y - position.y;
		if (dy + dx != 0){
			// Mesure la distance a laquelle le monstre a pu se deplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			position.x += ratioX * speed;
			position.y += ratioY * speed;
		}
	}

	//calcul la prochaine direction du mob
	public void calcl() {
		// TODO ATTENTION BULLSHIT
		/*if (world.board[(int) ((nextPosition.x*2 - position.x)/world.squareWidth)][(int) (nextPosition.y/world.squareWidth)] == 3) {
			nextPosition = new Position(nextPosition.x-position.x,nextPosition.y);
		}*/
		this.nextPosition = new Position(this.position.x + this.world.squareWidth / 2/*+ world.squareWidth*speed*/,this.position.y);
	}
	
	public void update() {
		move();
		calcl();
		draw();
		checkpoint++;
	}

	/**
	 * Fonction abstraite qui sera instanciee dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void draw();
}
