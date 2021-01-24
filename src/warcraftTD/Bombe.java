package warcraftTD;

public class Bombe extends Projectile {
	
	public Bombe(World world, Position position, Monster monster, int damage, double speed) {
		super(world, position, monster, damage, speed);
		rotation = 0;
	}

	@Override
	public void draw() {
		if (reached) {
			StdDraw.picture(position.x, position.y, "/images/Depart.png", world.getSquareWidth(), world.getSquareHeight());
		}else {
			StdDraw.picture(position.x, position.y, "/images/fleche.png", world.getSquareWidth(), world.getSquareHeight(),
					rotation);
			rotation = rotation +3;
		}
	}

	@Override
	public void reached() {
		for (Monster m : world.monsters) {
			if (!m.flying && Math.abs(position.x-m.position.x) < world.getSquareWidth() && Math.abs(position.y-m.position.y) < world.getSquareHeight()) {
				m.hp = m.hp-damage;
				reached = true;
			}
		}
	}

}
