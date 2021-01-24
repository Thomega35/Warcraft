package warcraftTD;

public class Arrow extends Projectile {

	public Arrow(World world, Position position, Monster monster, int damage, double speed) {
		super(world, position, monster, damage, speed);
		rotation = 90;
		Position vect = position.add(new Position(-monster.position.x, -monster.position.y));
		boolean x = vect.x >= 0;
		boolean y = vect.y >= 0;
		if (x && !y) {
			rotation = rotation - 90;
		} else if (!x && !y) {
			rotation = rotation + 180;
		} else if (!x && y){
			rotation = rotation + 90;
		}
	}

	@Override
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/fleche.png", world.getSquareWidth(), world.getSquareHeight(),
				rotation);
	}

	@Override
	public void reached() {
		for (Monster m : world.monsters) {
			if (Math.abs(position.x-m.position.x) < world.getSquareWidth()/2 && Math.abs(position.y-m.position.y) < world.getSquareHeight()/2) {
				m.hp = m.hp-damage;
				reached = true;
			}
		}
	}

}
