package warcraftTD;

public class Arrow extends Projectile {

	public Arrow(World world, Position position, Monster monster, int damage, double speed) {
		super(world, position, monster, damage, speed);
		rotation = 45;
		Position vect = position.add(new Position(-monster.position.x, -monster.position.y));
		rotation += Math.atan2(vect.y,vect.x)/Math.PI * 180;
		
	}

	@Override
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/fleche.png", world.getSquareWidth(), world.getSquareHeight(), rotation);
	}

	@Override
	public void reached() {
		boolean touche = false;
		for (Monster m : world.monsters) {
			if (Math.abs(position.x-m.position.x) < world.getSquareWidth()/2 && Math.abs(position.y-m.position.y) < world.getSquareHeight()/2) {
				m.hp -= damage;
				reached = true;
				touche = true;
			}
			if (touche)
				break;
		}
	}

}
