package warcraftTD;

public abstract class Monster {
	// Position du monstre ﾃ� l'instant t
	Position p;
	// Vitesse du monstre
	double speed;
	// Position du monstre ﾃ� l'instant t+1
	Position nextP;
	// Boolean pour savoir si le monstre ﾃ� atteint le chateau du joueur
	boolean reached;
	// Compteur de deplacement pour savoir si le monstre ﾃ� atteint le chateau du joueur
	int checkpoint = 0;
	
	public Monster(Position p) {
		this.p = p;
		this.nextP = new Position(p);
	}
	
	/**
	 * Dﾃｩplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le monstre se dirige.
		double dx = nextP.x - p.x;
		double dy = nextP.y - p.y;
		if (dy + dx != 0){
			// Mesure la distance ﾃ� laquelle le monstre ﾃ� pu se dﾃｩplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			p.x += ratioX * speed;
			p.y += ratioY * speed;
		}
	}

	public void update() {
		move();
		draw();
		checkpoint++;
	}
	
	/**
	 * Fonction abstraite qui sera instanciﾃｩe dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void draw();
}
