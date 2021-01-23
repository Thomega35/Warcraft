package warcraftTD;

public class Arrow extends Projectile {

	public Arrow(World world, Position position, Monster monster, int damage, double speed) {
		super(world, position, monster, damage, speed);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		StdDraw.picture(position.x, position.y, "/images/Zerg.png", world.getSquareWidth(), world.getSquareHeight());
	}

}
