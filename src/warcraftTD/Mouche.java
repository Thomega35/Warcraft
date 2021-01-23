package warcraftTD;

public class Mouche extends Monster {

	public Mouche(World w, Position p) {
		super(w, p);
		this.hp = 200;
		this.goldValue = 10;
		this.flying = true;
	}
	
	public void draw() {
		StdDraw.picture(position.x, position.y, "/images/Depart.png", world.squareWidth, world.squareHeight);
	}

}
