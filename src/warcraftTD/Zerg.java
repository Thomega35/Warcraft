package warcraftTD;

public class Zerg extends Monster {

	public Zerg(World w, Position p) {
		super(w, p);
		this.hp = 200;
		this.goldValue = 10;
		this.flying = false;
	}

	@Override
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/Zerg.png", world.squareWidth, world.squareHeight);
	}

}
