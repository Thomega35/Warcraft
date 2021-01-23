package warcraftTD;

public abstract class Projectiles {

	Position position;
	Monster monster;
	int degat;
	int speed;
	boolean reached;
	protected World world;
	
	public Projectiles(World world, Position position, Monster monster) {
		this.world = world;
		this.position = position;
		this.monster = monster;
	}

	public abstract void draw();

	public void move() {
		// TODO deplacement projectiles
		Position obj = monster.position;
		//position = nextPosition;
		Position dist = obj.add(new Position(-position.x, -position.y));
		double norme = new Position(0, 0).dist(dist);
		dist = new Position(speed * dist.x / (norme * (double) world.nbSquareX * 3.0),
				speed * dist.y / (norme * (double) world.nbSquareY * 3.0));
	}

	public void update() {
		move();
		draw();
	}
}
