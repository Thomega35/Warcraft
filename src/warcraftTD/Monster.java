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
	int checkpoint = 0;
	
	public Monster(Position p) {
		this.position = p;
		this.nextPosition = new Position(p);
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

	public void update() {
		move();
		draw();
		checkpoint++;
	}
	
	/**
	 * Fonction abstraite qui sera instanciee dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void draw();
}
