package warcraftTD;

public abstract class Monster {

	// Position du monstre a l'instant t
	Position position;
	// Vitesse du monstre
	 protected double speed;
	// Points de vie du monstre
	protected int hp;
	// Valeur en gold du monstre une fois tué
	protected int goldValue;
	// Monstre volant ou non
	protected boolean flying;
	//Valeur de la rotation de l'image
	protected double rotation;
	
	// Position du monstre a l'instant t+1
	Position nextPosition;
	// Boolean pour savoir si le monstre a atteint le chateau du joueur
	protected boolean reached;

	// Compteur de deplacement pour savoir si le monstre a atteint le chateau du
	// joueur
	protected int checkpoint = 0;

	protected World world;

	public Monster(World w, Position startp) {
		this.position = startp;
		world = w;
		this.nextPosition = new Position(startp.x + this.world.getSquareWidth() * speed/* + world.squareWidth*speed */,
				startp.y);
	}

	/**
	 * Deplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de
	 * sa prochaine position.
	 */
	public void move() {
		this.rotation = 0;
		if (checkpoint < world.checkpoints.size()) {
			Position obj = world.checkpoints.get(checkpoint);
			position = nextPosition;
			Position dist = obj.add(new Position(-position.x, -position.y));
			double norme = new Position(0, 0).dist(dist);
//			System.out.println(norme);
//			System.out.println(dist.x / norme);
			dist = new Position(speed * dist.x / (norme * (double) world.getNbSquareX() * 3.0),
					speed * dist.y / (norme * (double) world.getNbSquareY() * 3.0));
			boolean yneg = dist.y < 0;
			boolean ypos = dist.y > 0;
			boolean xneg = dist.x < 0;
			if (ypos) {
				rotation = 90;
			}else if (yneg) {
				rotation = - 90;
			}else if (xneg){
				rotation = 180;
			}
//Si vec trop grand TP to checkpoint
			if (position.dist(obj) <= world.getSquareWidth()/5 && position.dist(obj) <= world.getSquareHeight()/5) {
				nextPosition = obj;
				checkpoint++;

			} else {
				//nextPosition = position.add(dist);
				nextPosition = position.add(dist);
			}
		} else {
			// Arrive
			reached = true;
		}
	}

	public void update() {
		move();
		//draw();
	}

	/**
	 * Fonction abstraite qui sera instanciee dans les classes filles pour afficher
	 * le monstre sur le plateau de jeu.
	 */
	public abstract void draw();
}
