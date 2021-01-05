package warcraftTD;

public class Zerg extends Monster {

	public Zerg(World w, Position p) {
		super(w, p);
		hp = 20;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		if(this.hp == 0) {
			this.world.gold += 20;
		}
		StdDraw.picture(position.x, position.y, "/images/Zerg.png", world.squareWidth, world.squareHeight);
	}

}
