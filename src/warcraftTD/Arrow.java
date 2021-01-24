package warcraftTD;

public class Arrow extends Projectile {
	double rotation;

	public Arrow(World world, Position position, Monster monster, int damage, double speed) {
		super(world, position, monster, damage, speed);
		rotation = 135;
		Position vect = position.add(new Position(-monster.position.x, -monster.position.y));
		boolean x = vect.x >= 0;
		boolean y = vect.y >= 0;
		if (x && y) {
			rotation = rotation - 45;
		} else if (x && !y) {
			rotation = rotation - 135;
		} else if (!x && !y) {
			rotation = rotation + 135;
		} else {
			rotation = rotation + 45;
		}
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		StdDraw.picture(position.x, position.y, "/images/fleche.png", world.getSquareWidth(), world.getSquareHeight(),
				rotation);
	}

}
