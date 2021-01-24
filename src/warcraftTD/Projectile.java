package warcraftTD;

public abstract class Projectile {

	protected Position position;
	protected Position obj;
	protected Monster monster;
	protected int damage;
	protected double speed;
	protected boolean reached;
	protected boolean out;
	protected double rotation;
	protected World world;
	
	public Projectile(World world, Position position, Monster monster, int damage, double speed) {
		this.speed = speed;
		this.damage = damage;
		this.world = world;
		this.position = position;
		this.monster = monster;
		obj = monster.position;
	}

	public abstract void draw();
	
	//Fonction de deplacement du projectile en fonction de sa vitesse et de la direction a suivre
	public void move() {
		Position dist = obj.add(new Position(-position.x, -position.y));
		double norme = new Position(0, 0).dist(dist);
		
		dist = new Position(speed * dist.x / (norme * (double) world.getNbSquareX()),
				speed * dist.y / (norme * (double) world.getNbSquareY()));
		position = position.add(dist);
		obj = obj.add(dist);
		
		if (position.x > 1 && position.y > 1) {
			out = true;
		} 
	}

	//Fonction qui actualise le projectile en le deplacant, en verifiant s'il a atteint une cible puis en affichant sa nouvelle position
	public void update() {
		move();
		reached();
		draw();
	}

	public abstract void reached();
	
}
