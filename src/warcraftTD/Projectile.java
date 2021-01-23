package warcraftTD;

public abstract class Projectile {

	protected Position position;
	protected Monster monster;
	protected int damage;
	protected double speed;
	protected boolean reached;
	protected boolean out;
	protected World world;
	
	public Projectile(World world, Position position, Monster monster, int damage, double speed) {
		this.speed = speed;
		this.damage = damage;
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
		
		dist = new Position(speed * dist.x / (norme * (double) world.getNbSquareX() * 3.0),
				speed * dist.y / (norme * (double) world.getNbSquareY() * 3.0));
		position = position.add(dist);
		
		if (position.x > 1 && position.y > 1) {
			out = true;
		}else if (Math.abs(position.x-monster.position.x) < world.getSquareWidth() && Math.abs(position.y-monster.position.y) < world.getSquareHeight()) {
			monster.hp = monster.hp-damage;
			reached = true;
		}
	}

	public void update() {
		move();
		draw();
	}
}
