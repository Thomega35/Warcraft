package warcraftTD;

public class Arrow extends Projectile {

	public Arrow(World world, Position position, Monster monster) {
		super(world, position, monster);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		StdDraw.picture(position.x, position.y, "/images/Zerg.png", world.squareWidth, world.squareHeight);
	}

}
