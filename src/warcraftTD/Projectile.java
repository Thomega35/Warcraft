package warcraftTD;

public abstract class Projectile {

	protected Position position;
	protected Position obj;
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
		obj = monster.position;
	}

	public abstract void draw();

	public void move() {
		// TODO deplacement projectiles
		//position = nextPosition;
		Position dist = obj.add(new Position(-position.x, -position.y));
		double norme = new Position(0, 0).dist(dist);
		
		dist = new Position(speed * dist.x / (norme * (double) world.getNbSquareX() * 3.0),
				speed * dist.y / (norme * (double) world.getNbSquareY() * 3.0));
		position = position.add(dist);
		obj = obj.add(dist);
		
		if (position.x > 1 && position.y > 1) {
			out = true;
		} 
		for (Monster m : world.monsters) {
			if (Math.abs(position.x-m.position.x) < world.getSquareWidth() && Math.abs(position.y-m.position.y) < world.getSquareHeight()) {
				m.hp = m.hp-damage;
				reached = true;
			}
		}
	}

	public void update() {
		move();
		draw();
	}
}
